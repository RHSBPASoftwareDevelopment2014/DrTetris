
package drtetris;


public class Block {
    
    public static final int ROTATENONE = 0, ROTATELEFT = 1, ROTATERIGHT = 2, ROTATEHALF = 3;
    
    public static final Block DEFAULT = new Block(0, new Tile[][]
        {
            {Tile.DIRT, Tile.DIRT, Tile.DIRT},
            {Tile.DIRT}
        }
    );
    
    private final Tile[][] map;
    private final int id;
    
    private Block(int id, Tile[][] map) {
        this.id = id;
        this.map = map;
    }
    
    public int getId() {
        return id;
    }
    
    public void draw(int x, int y) {
        draw(x, y, ROTATENONE);
    }
    
    public void draw(int x, int y, int rotation) {
        switch(rotation) {
            
            case ROTATELEFT:
                
                break;
                
            case ROTATERIGHT:
                
                break;
                
            case ROTATEHALF:
                
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
