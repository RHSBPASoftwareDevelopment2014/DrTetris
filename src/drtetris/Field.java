package drtetris;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.SlickException;

public class Field extends TileMap {
    
    public static final int END = 0, CONTINUE = 1, NORMAL = 2, ANIMATE = 3;
    
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    private int state;
    private List<MovingBlock> fallingBlocks;
    private boolean checkBlocks;

    public Field(Tile[][] map) {
        super(map);
        state = NORMAL;
        fallingBlocks = new CopyOnWriteArrayList<MovingBlock>();
        checkBlocks = true;
        
        lockBlocks();
    }
    
    private void lockBlocks() {
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                if (tile != null && tile.getId() != 6) {
                    tile.setLocked(true);
                }
            }
        }
    }

    @Override
    public void draw(int x, int y) {
        super.draw(x, y, true);
	
        for (MovingBlock block : fallingBlocks) {
            block.draw(Config.FIELDX, Config.FIELDY);
        }
    }

    public void update(int delta) {
	for (MovingBlock block : fallingBlocks) {
		if (block != null) {
			if (!isRoom(block, Config.STACKTOLERANCE, true)) {
				addMap(block, false, false);
				checkBlocks = true;
				fallingBlocks.remove(block);
			} else {
				block.modY(delta * Config.FALLINGBLOCKSPEED);
				yLimit(block, Config.STACKTOLERANCE);
			}
		}
	}
	
	if (checkBlocks && fallingBlocks.size() <= 0) {
		breakBlocks();
		findFallingBlocks();
		checkBlocks = false;
        }

        updateState();
    }

    public void reset(Tile[][] map) throws IOException, NumberFormatException {
        this.map = map;
        state = NORMAL;
        fallingBlocks = new CopyOnWriteArrayList<MovingBlock>();
        checkBlocks = true;
        lockBlocks();
    }

    public void addMap(MovingBlock block, boolean breakBlocks, boolean findFallingBlocks) {
        addMap(block.getMap(), block.getX(), block.getY(), breakBlocks, findFallingBlocks);
    }

    public void addMap(MovingBlock block) {
        addMap(block.getMap(), block.getX(), block.getY(), true, true);
    }

    public void addMap(TileMap map, int rotation, int x, double y) {
        addMap(map.getMap(rotation), x, y, true, true);
    }

    public void addMap(Tile[][] map, int x, double y, boolean breakBlocks, boolean findFallingBlocks) {
        int yPos = (int) (y + Config.FIELDOFFSET) / Config.BLOCKSIZE;
        String blockId = UUID.randomUUID().toString();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    if (yPos + i >= 0) {
                        if (map[i][j] instanceof Tunnel || map[i][j] instanceof LinkedTile) {
                            this.map[yPos + i][x + j] = map[i][j];
                        } else {
                            try {
                                this.map[yPos + i][x + j] = new LinkedTile(map[i][j].getId(), blockId);
                            } catch (SlickException ex) {
			    } catch (ArrayIndexOutOfBoundsException ex) {}
                        }
                    }
                }
            }
        }

        if (breakBlocks) {
            breakBlocks();
	}
	if (findFallingBlocks) {
            findFallingBlocks();
	}
    }

    //Finds tiles that are floating and groups of linked tiles thats are floating
    private void findFallingBlocks() {
        int lastSize;
        do {
            lastSize = fallingBlocks.size();
            List<LinkedTile> antiFallingBlocks = new ArrayList<LinkedTile>();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] instanceof LinkedTile) {
                        if (i < map.length - 1 && map[i][j] != null && !map[i][j].isLocked() && (map[i + 1][j] == null || (map[i + 1][j] instanceof LinkedTile && ((LinkedTile) map[i][j]).getBlockId().equals(((LinkedTile) map[i + 1][j]).getBlockId())))) {
                            fallingBlocks.add(new MovingBlock(new Tile[][]{{map[i][j]}}, TileMap.ROTATENONE, j, i * Config.BLOCKSIZE));
                        } else {
                            antiFallingBlocks.add(((LinkedTile) map[i][j]));
                        }
                    } else if (i < map.length - 1) {
                        if (map[i][j] != null && !map[i][j].isLocked() && map[i + 1][j] == null) {
                            fallingBlocks.add(new MovingBlock(new Tile[][]{{map[i][j]}}, TileMap.ROTATENONE, j, i * Config.BLOCKSIZE));
                        }
                    }
                }
            }

            for (LinkedTile antiFallingBlock : antiFallingBlocks) {
                for (MovingBlock block : fallingBlocks) {
                    if (block.getMap()[0][0] instanceof LinkedTile && ((!block.getMap()[0][0].hasGravity() && !antiFallingBlock.hasGravity()) || (block.getMap()[0][0].getId() == 6 && antiFallingBlock.getId() == 6)) && ((LinkedTile) block.getMap()[0][0]).getBlockId().equals(antiFallingBlock.getBlockId())) {
                        fallingBlocks.remove(block);
                    }
                }
            }

            for (MovingBlock block : fallingBlocks) {
                map[(int) block.getY() / Config.BLOCKSIZE][block.getX()] = null;
            }
        } while ((lastSize != fallingBlocks.size()));
    }

    private void breakBlocks() {
        boolean[][] breakMap = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null && map[i][j].getId() != 10) {
                    for (int k = (j > map[i].length - 4 ? 3 - map[i].length + j : j); k >= j - 3 && k >= 0; k--) {
                        if (map[i][k] != null && (map[i][k].equals(map[i][k + 1]) && map[i][k].equals(map[i][k + 2]) && map[i][k].equals(map[i][k + 3]))) {
                            breakMap[i][k] = true;
                            breakMap[i][k + 1] = true;
                            breakMap[i][k + 2] = true;
                            breakMap[i][k + 3] = true;
                        }
                    }
                    for (int k = (i > map.length - 4 ? 3 - map.length + i : i); k >= i - 3 && k >= 0; k--) {
                        if (map[k][j] != null && (map[k][j].equals(map[k + 1][j]) && map[k][j].equals(map[k + 2][j]) && map[k][j].equals(map[k + 3][j]))) {
                            breakMap[k][j] = true;
                            breakMap[k + 1][j] = true;
                            breakMap[k + 2][j] = true;
                            breakMap[k + 3][j] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < breakMap.length; i++) {
            for (int j = 0; j < breakMap[i].length; j++) {
                if (breakMap[i][j]) {
                    map[i][j] = null;
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

        if (x < 0 || x + map[0].length > 12 || (lowY + map.length >= 12 && falling)) {
            return false;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    if (lowCheck < 0 || lowCheck + map.length > this.map.length || this.map[i + lowCheck][j + x] != null) {
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
        if (!isRoom(block, tolerance, true) && block.getY() > ((int) block.getY() / Config.BLOCKSIZE) * Config.BLOCKSIZE) {
            block.setY(((int) block.getY() / Config.BLOCKSIZE) * Config.BLOCKSIZE);
        }
    }
    //Decides whether the blocks are falling, the next level is continued to, 
    //there is a game over, or if the game is continuing as normal
    public void updateState() {
        if (fallingBlocks.size() > 0) {
            state = ANIMATE;
        } else if (isTunnel()) {
            state = CONTINUE;
        } else if (isFull()) {
            state = END;
        } else {
            state = NORMAL;
        }
    }

    public int getState() {
        return state;
    }
    //Checks to see if the there is a block above the limit
    private boolean isFull() {
        for (Tile tile : map[0]) {
            if (tile != null) {
                return true;
            }
        }

        return false;
    }
    //The isTunnel methods are used to check if a tunnel is connected from the bottom to the top
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
        if (y < 2) {
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
