
package drtetris;

public class Field extends TileMap {
    
    public static final int STAY = 0, END = 1, CONTINUE = 2;
    
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    @Override
    public void draw(int x, int y) {
        super.draw(x, y, true);
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
        int yPos = (int) (y + Config.FIELDOFFSET) / Config.BLOCKSIZE;
        for (int i = 0; i < map.length + 1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    if(yPos + i >= 0) {
                        this.map[yPos + i][x + j] = map[i][j];
                    }
                }
            }
        }
        
        breakBlocks(x, yPos, map);
    }
    
    private void breakBlocks(int minX, int minY, Tile[][] tempMap) {
        if (minY >= 0 && minX >= 0 && minY + tempMap.length <= map.length && minX + tempMap[0].length <= map[minY].length) {
            for (int i = 0; i < tempMap.length; i++) {
                for (int j = 0; j < tempMap[i].length; j++) {
                    if(tempMap[i][j] != null) {
                        blockMatch(tempMap[i][j], j + minX, i + minY, UP);
                        blockMatch(tempMap[i][j], j + minX, i + minY, LEFT);
                    }
                }
            }
        }
    }
    
    private void blockMatch(Tile tile, int x, int y, int direction) {
        int count1, count2;
        switch (direction) {
            case UP:
                count1 = blockMatch(tile, x, y, UP, 0) + 1;
                count2 = blockMatch(tile, x, y, DOWN, 0) + 1;
                blockMatch(tile, x, y, UP, count2);
                blockMatch(tile, x, y, DOWN, count1);
                break;
                
            case DOWN:
                count1 = blockMatch(tile, x, y, DOWN, 0) + 1;
                count2 = blockMatch(tile, x, y, UP, 0) + 1;
                blockMatch(tile, x, y, DOWN, count2);
                blockMatch(tile, x, y, UP, count1);
                break;

            case LEFT:
                count1 = blockMatch(tile, x, y, LEFT, 0) + 1;
                count2 = blockMatch(tile, x, y, RIGHT, 0) + 1;
                blockMatch(tile, x, y, LEFT, count2);
                blockMatch(tile, x, y, RIGHT, count1);
                break;
                
            case RIGHT:
                count1 = blockMatch(tile, x, y, RIGHT, 0) + 1;
                count2 = blockMatch(tile, x, y, LEFT, 0) + 1;
                blockMatch(tile, x, y, RIGHT, count2);
                blockMatch(tile, x, y, LEFT, count1);
                break;
        }
    }
    
    private int blockMatch(Tile tile, int x, int y, int direction, int count) {
        
        switch(direction) {

            case UP:
                if(y - 1 >= 0 && map[y - 1][x] == tile) {
                    count = blockMatch(tile, x, y - 1, direction, count + 1);
                }
                break;
                
            case DOWN:
                if(y + 1 < map.length && map[y + 1][x] == tile) {
                    count = blockMatch(tile, x, y + 1, direction, count + 1);
                }
                break;

            case LEFT:
                if(x - 1 >= 0 && map[y][x - 1] == tile) {
                    count = blockMatch(tile, x - 1, y, direction, count + 1);
                }
                break;
                
            case RIGHT:
                if(x + 1 < map[y].length && map[y][x + 1] == tile) {
                    count = blockMatch(tile, x + 1, y, direction, count + 1);
                }
                break;
        }
        
        if (count >= 4) {
            map[y][x] = null;
        }
        
        return count;
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
}
