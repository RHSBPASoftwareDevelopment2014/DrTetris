
package drtetris;

public class Field extends TileMap {
    
    private static final int TOLERANCE = 8;
    
    public static final int STAY = 0, END = 1, CONTINUE = 2;
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void reset() {
        map = new Tile[getHeight()][getWidth()];
    }
    
    public void addMap(TileMap map, int rotation, int x, double y) {
        addMap(map.getMap(rotation), x, y);
    }
    
    public void addMap(Tile[][] map, int x, double y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    this.map[(int) (y + Config.FIELDOFFSET
                            ) / Config.BLOCKSIZE + i][x + j] = map[i][j];
                }
            }
        }
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, int y, double tolerance) {
        Tile[][] map = tileMap.getMap(rotation);
        
        double lowY = y - tolerance,
                lowCheck = y - tolerance,
                highCheck = y + tolerance,
                highY = y + tolerance;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                //TODO
            }
        }
        
        return true;
    }
    
    double yLimit(Block currentBlock, int rotation, int x, double y) {
        return y;
    }
    
    public int getState() {
        if (isTunnel()) {
            return CONTINUE;
        } else if (isFull()) {
            return END;
        } else {
            return STAY;
        }
    }
    
    private boolean isFull() {
        for (Tile tile : map[0]) {
            if (tile != null) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isTunnel() {
        boolean isTunnel = false;
        
        for (int i = 0; i < map[map.length - 1].length && !isTunnel; i++) {
            if (map[map.length - 1][i] == Tile.TUNNEL) {
                isTunnel = isTunnel || isTunnel(i, map.length - 1);
            }
        }
        
        return isTunnel;
    }
    
    private boolean isTunnel(int x, int y) {
        
        boolean isTunnel = false;
        
        if (y < 3) {
            isTunnel = true;
        } else {
            if (y > 0 && map[y - 1][x] == Tile.TUNNEL && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x, y - 1);
            }

            if (x > 0 && map[y][x - 1] == Tile.TUNNEL && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x - 1, y - 1);
            }

            if (x < map[y].length - 1 && map[y][x + 1] == Tile.TUNNEL && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x + 1, y - 1);
            }
        }
        
        return isTunnel;
    }
}
