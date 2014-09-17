
package drtetris;


public class Block {
    
    public static final int ROTATENONE = 0, ROTATELEFT = 1, ROTATEHALF = 2, ROTATERIGHT = 3;
    
    public static final Block DEFAULT = new Block(new Tile[][]
        {
            {Tile.DIRT, Tile.DIRT, Tile.DIRT},
            {Tile.DIRT}
        }
    );
    
    private final Tile[][] map;
    
    private Block(Tile[][] map) {
        this.map = map;
    }
    
    public int getHeight(int rotation) {
        switch(rotation) {
            
            case ROTATELEFT:
                
            case ROTATERIGHT:
                return getWidth();
            
            default:
                return getHeight();
        }
    }
    
    public int getHeight() {
        return map.length;
    }
    
    public int getWidth(int rotation) {
        switch(rotation) {
            
            case ROTATELEFT:
                
            case ROTATERIGHT:
                return getHeight();
            
            default:
                return getWidth();
        }
    }
    
    public int getWidth() {
        int width = 0;
        
        for (Tile[] row : map) {
            width = Math.max(width, row.length);
        }
        
        return width;
    }
    public void draw(int x, int y) {
        draw(x, y, ROTATENONE);
    }
    
    public void draw(int x, int y, int rotation) {
        
        rotation %= 4;
        
        Tile[][] rotatedMap;
        
        switch(rotation) {
            
            case ROTATELEFT:
                rotatedMap = new Tile[getWidth()][getHeight()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[getWidth() - j - 1][i] = map[i][j];
                    }
                }
                draw(x, y, rotatedMap);
                break;
                
            case ROTATERIGHT:
                rotatedMap = new Tile[getWidth()][getHeight()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[j][getHeight() - i - 1] = map[i][j];
                    }
                }
                draw(x, y, rotatedMap);
                break;
                
            case ROTATEHALF:
                rotatedMap = new Tile[getHeight()][getWidth()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[getHeight() - i - 1][getWidth() - j - 1] = map[i][j];
                    }
                }
                draw(x, y, rotatedMap);
                break;
                
            default:
                draw(x, y, map);
        }
    }
    
    public void draw(int x, int y, Tile[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null ) {
                    map[i][j].draw(x + Tile.SIZE * j, y + Tile.SIZE * i);
                }
            }
        }
    }
}
