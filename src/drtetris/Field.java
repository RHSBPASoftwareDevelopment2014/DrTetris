
package drtetris;

public class Field extends TileMap {
    
    public static final int STAY = 0, END = 1, CONTINUE = 2;
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void draw(int x, int y, Tile[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    if (map[i][j] instanceof Tunnel) {
                        ((Tunnel) map[i][j]).draw(getSurroundingTunnels(j, i), x + Config.BLOCKSIZE * j, y + Config.BLOCKSIZE * i);
                    } else {
                        map[i][j].draw(x + Config.BLOCKSIZE * j, y + Config.BLOCKSIZE * i);
                    }
                }
            }
        }
    }
    
    public void reset() {
        map = new Tile[getHeight()][getWidth()];
    }
    
    public void addMap(MovingBlock block) {
        addMap(block.getMap(), block.getX(), block.getY());
    }
    
    public void addMap(TileMap map, int rotation, int x, double y) {
        addMap(map.getMap(rotation), x, y);
    }
    
    public void addMap(Tile[][] map, int x, double y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    int yPos = (int) (y + Config.FIELDOFFSET) / Config.BLOCKSIZE + i;
                    if(yPos >= 0) {
                        this.map[yPos][x + j] = map[i][j];
                    }
                }
            }
        }
    }
    
    public boolean isRoom(MovingBlock block, double tolerance, boolean falling) {
        return isRoom(block.getMap(), block.getX(), block.getY(), tolerance, falling);
    }
    
    public boolean isRoom(Tile[][] map, int x, double y, double tolerance, boolean falling) {
        int lowCheck = (int) ((y + tolerance) / Config.BLOCKSIZE),
                highCheck = (int) ((y - tolerance) / Config.BLOCKSIZE + 1),
                lowY = (int) (y / Config.BLOCKSIZE),
                highY = (int) (y / Config.BLOCKSIZE + 1);
        
        if(x < 0 || x + map[0].length > 12 || (lowY + map.length >= 12 && falling)) {
            return false;
        }
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    if (lowCheck < 0  || lowCheck + map.length > this.map.length || this.map[i + lowCheck][j + x] != null) {
                        return false;
                    } else if (highCheck < 0 || highCheck + map.length > this.map.length || this.map[i + (falling ? highY : highCheck)][j + x] != null) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public void yLimit(MovingBlock block, double tolerance) {
        if(!isRoom(block, tolerance, true) && block.getY() > ((int) block.getY() / Config.BLOCKSIZE) * Config.BLOCKSIZE) {
            block.setY(((int) block.getY() / Config.BLOCKSIZE) * Config.BLOCKSIZE);
        }
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
    
    private static final int UP = 0, LEFT = 1, RIGHT = 2;
    
    private boolean isTunnel() {
        boolean isTunnel = false;
        for (int i = 0; i < map[map.length - 1].length && !isTunnel; i++) {
            if (map[map.length - 1][i] instanceof Tunnel) {
                isTunnel = isTunnel || isTunnel(i, map.length - 1, UP);
            }
        }
        
        return isTunnel;
    }
    
    private boolean isTunnel(int x, int y, int direction) {
        
        boolean isTunnel = false;
        if (y < 3) {
            isTunnel = true;
        } else {
            if (y > 0 && map[y - 1][x] instanceof Tunnel && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x, y - 1, UP);
            }

            if (direction != RIGHT && x > 0 && map[y][x - 1] instanceof Tunnel && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x - 1, y, LEFT);
            }

            if (direction != LEFT && x < map[y].length - 1 && map[y][x + 1] instanceof Tunnel && !isTunnel) {
                isTunnel = isTunnel || isTunnel(x + 1, y, RIGHT);
            }
        }
        
        return isTunnel;
    }
    
    /**
     * @param x
     * @param y
     * @return [tunnel above, tunnel below, tunnel left, tunnel right]
     */
    public boolean[] getSurroundingTunnels(int x, int y) {
        return new boolean[]{y - 1 >= 0 && y < map.length && x >= 0 && x < map.length && map[y - 1][x] instanceof Tunnel ? true : false,
                y + 1 >= 0 && y + 1 <= map.length && x >= 0  && x < map.length && (y + 1 == map.length || map[y + 1][x] instanceof Tunnel) ? true : false,
                y >= 0 && y < map.length && x - 1 >= 0  && x + 1 < map.length && map[y][x - 1] instanceof Tunnel ? true : false,
                y >= 0 && y < map.length && x - 1 >= 0  && x - 1 < map.length && map[y][x + 1] instanceof Tunnel ? true : false};
    }
}
