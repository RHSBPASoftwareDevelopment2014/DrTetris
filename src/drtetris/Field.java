
package drtetris;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Field extends TileMap {
    
    public static final int END = 0, CONTINUE = 1, NORMAL = 2, ANIMATE = 3;
    
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    
    private int state;
    private List<MovingBlock> fallingBlocks;
    private List<MovingBlock> checkBlocks;
    
    public Field(Tile[][] map) {
        super(map);
        state = NORMAL;
        fallingBlocks = new CopyOnWriteArrayList<MovingBlock>();
        checkBlocks = new ArrayList<MovingBlock>();
    }
    
    @Override
    public void draw(int x, int y) {
        super.draw(x, y, true);
        
        for (MovingBlock block : fallingBlocks) {
            block.draw(Config.FIELDX, Config.FIELDY);
        }
    }
    
    
    
    public void update(int delta) {
        
        if (fallingBlocks.size() <= 0) {
            for (MovingBlock block : checkBlocks) {
                breakBlocks(block.getX(), (int) (block.getY() + Config.FIELDOFFSET) / Config.BLOCKSIZE, block.getMap());
            }
            
            findFallingBlocks();
            
            if (fallingBlocks.size() <= 0) {
                state = NORMAL;
            }
        } else {
            for (MovingBlock block : fallingBlocks) {
                if (block != null) {
                    if (!isRoom(block, Config.STACKTOLERANCE, true)) {
                        addMap(block, false);
                        checkBlocks.add(block);
                        fallingBlocks.remove(block);
                    } else {
                        block.modY(delta * Config.FALLINGBLOCKSPEED);
                        yLimit(block, Config.STACKTOLERANCE);
                    }
                }
            }
            
            findFallingBlocks();
        }
        
        updateState();
    }
    
    public void reset() {
        map = new Tile[getHeight()][getWidth()];
        state = NORMAL;
        fallingBlocks = new CopyOnWriteArrayList<MovingBlock>();
        checkBlocks = new ArrayList<MovingBlock>();
    }
    
    public void addMap(MovingBlock block, boolean breakBlocks) {
        addMap(block.getMap(), block.getX(), block.getY(), breakBlocks);
    }
    
    public void addMap(MovingBlock block) {
        addMap(block.getMap(), block.getX(), block.getY(), true);
    }
    
    public void addMap(TileMap map, int rotation, int x, double y) {
        addMap(map.getMap(rotation), x, y, true);
    }
    
    public void addMap(Tile[][] map, int x, double y, boolean breakBlocks) {
        int yPos = (int) (y + Config.FIELDOFFSET) / Config.BLOCKSIZE;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    if(yPos + i >= 0) {
                        this.map[yPos + i][x + j] = map[i][j];
                    }
                }
            }
        }
        
        if (breakBlocks) {
            breakBlocks(x, yPos, map);
            findFallingBlocks();
        }
    }
    
    private void findFallingBlocks() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null && map[i + 1][j] == null) {
                    fallingBlocks.add(new MovingBlock(new Tile[][]{{map[i][j]}}, TileMap.ROTATENONE, j, i * Config.BLOCKSIZE));
                    map[i][j] = null;
                    state = ANIMATE;
                }
            }
        }
    }
    
    private void breakBlocks(int minX, int minY, Tile[][] tempMap) {
        if (minY >= 0 && minX >= 0 && minY + tempMap.length <= map.length && minX + tempMap[0].length <= map[minY].length) {
            for (int i = 0; i < tempMap.length; i++) {
                for (int j = 0; j < tempMap[i].length; j++) {
                    if(tempMap[i][j] != null) {
                        blockMatch(tempMap[i][j], j + minX, i + minY, tempMap, minX, minY, UP);
                        blockMatch(tempMap[i][j], j + minX, i + minY, tempMap, minX, minY, LEFT);
                    }
                }
            }
        }
    }
    
    private void blockMatch(Tile tile, int x, int y, Tile[][] tempMap, int startX, int startY, int direction) {
        int count1, count2;
        switch (direction) {
            case UP:
                count1 = blockMatch(tile, x, y, tempMap, startX, startY, UP, 0) + 1;
                count2 = blockMatch(tile, x, y, tempMap, startX, startY, DOWN, 0) + 1;
                blockMatch(tile, x, y, tempMap, startX, startY, UP, count2);
                blockMatch(tile, x, y, tempMap, startX, startY, DOWN, count1);
                break;
                
            case DOWN:
                count1 = blockMatch(tile, x, y, tempMap, startX, startY, DOWN, 0) + 1;
                count2 = blockMatch(tile, x, y, tempMap, startX, startY, UP, 0) + 1;
                blockMatch(tile, x, y, tempMap, startX, startY, DOWN, count2);
                blockMatch(tile, x, y, tempMap, startX, startY, UP, count1);
                break;

            case LEFT:
                count1 = blockMatch(tile, x, y, tempMap, startX, startY, LEFT, 0) + 1;
                count2 = blockMatch(tile, x, y, tempMap, startX, startY, RIGHT, 0) + 1;
                blockMatch(tile, x, y, tempMap, startX, startY, LEFT, count2);
                blockMatch(tile, x, y, tempMap, startX, startY, RIGHT, count1);
                break;
                
            case RIGHT:
                count1 = blockMatch(tile, x, y, tempMap, startX, startY, RIGHT, 0) + 1;
                count2 = blockMatch(tile, x, y, tempMap, startX, startY, LEFT, 0) + 1;
                blockMatch(tile, x, y, tempMap, startX, startY, RIGHT, count2);
                blockMatch(tile, x, y, tempMap, startX, startY, LEFT, count1);
                break;
        }
    }
    
    private int blockMatch(Tile tile, int x, int y, Tile[][] tempMap, int startX, int startY, int direction, int count) {
        
        switch(direction) {

            case UP:
                if(y - 1 >= 0 && map[y - 1][x] == tile ||
                        ( y - 1 >= startY && y - 1 < startY + tempMap.length && 
                          x >= startX && x < startX + tempMap[y - startY - 1].length &&
                          tempMap[y - startY - 1][x - startX] == tile)) {
                    count = blockMatch(tile, x, y - 1, tempMap, startX, startY, direction, count + 1);
                }
                break;
                
            case DOWN:
                if(y + 1 < map.length && map[y + 1][x] == tile ||
                        ( y + 1 >= startY && y + 1 < startY + tempMap.length && 
                          x >= startX && x < startX + tempMap[y - startY + 1].length &&
                          tempMap[y - startY + 1][x - startX] == tile)) {
                    count = blockMatch(tile, x, y + 1, tempMap, startX, startY, direction, count + 1);
                }
                break;

            case LEFT:
                if(x - 1 >= 0 && map[y][x - 1] == tile ||
                        ( y >= startY && y < startY + tempMap.length && 
                          x - 1 >= startX && x - 1 < startX + tempMap[y - startY].length &&
                          tempMap[y - startY][x - startX - 1] == tile)) {
                    count = blockMatch(tile, x - 1, y, tempMap, startX, startY, direction, count + 1);
                }
                break;
                
            case RIGHT:
                if(x + 1 < map[y].length && map[y][x + 1] == tile ||
                        ( y >= startY && y < startY + tempMap.length && 
                          x + 1 >= startX && x + 1 < startX + tempMap[y - startY].length &&
                          tempMap[y - startY][x - startX + 1] == tile)) {
                    count = blockMatch(tile, x + 1, y, tempMap, startX, startY, direction, count + 1);
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
    
    public void updateState() {
        if (isTunnel()) {
            state = CONTINUE;
        } else if (isFull()) {
            state = END;
        }
    }
    
    public int getState() {
        return state;
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
