
package drtetris;

public class TileMap {
    
    public static final int ROTATENONE = 0, ROTATELEFT = 1, ROTATEHALF = 2, ROTATERIGHT = 3;
    
    protected Tile[][] map;
    
    protected TileMap(Tile[][] map) {
        this.map = map;
    }
    
    public Tile[][] getMap() {
        return getMap(ROTATENONE);
    }
    
    public Tile[][] getMap(int rotation) {
        Tile[][] rotatedMap;
        
        switch(trimRotation(rotation)) {
            
            case ROTATELEFT:
                rotatedMap = new Tile[getWidth()][getHeight()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[getWidth() - j - 1][i] = map[i][j];
                    }
                }
                break;
                
            case ROTATERIGHT:
                rotatedMap = new Tile[getWidth()][getHeight()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[j][getHeight() - i - 1] = map[i][j];
                    }
                }
                break;
                
            case ROTATEHALF:
                rotatedMap = new Tile[getHeight()][getWidth()];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        rotatedMap[getHeight() - i - 1][getWidth() - j - 1] = map[i][j];
                    }
                }
                break;
            
            default:
                rotatedMap = map;
        }
        
        return rotatedMap;
    }
    
    public int getHeight(int rotation) {
        switch(trimRotation(rotation)) {
            
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
        switch(trimRotation(rotation)) {
            
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
    
    public void draw() {
        draw(0, 0, ROTATENONE);
    }
    
    public void draw(int x, int y) {
        draw(x, y, ROTATENONE);
    }
    
    public void draw(int x, int y, int rotation) {
        draw(x, y, getMap(rotation));
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
    
    public static int trimRotation(int rotation) {
        return rotation % 4;
    }
}
