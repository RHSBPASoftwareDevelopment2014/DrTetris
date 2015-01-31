package drtetris;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

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
        checkBlocks = false;
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

            breakBlocks();
            findFallingBlocks();

            checkBlocks = false;

            if (fallingBlocks.size() <= 0) {
                state = NORMAL;
            }
        } else {
            for (MovingBlock block : fallingBlocks) {
                if (block != null) {
                    if (!isRoom(block, Config.STACKTOLERANCE, true)) {
                        addMap(block, false);
                        checkBlocks = true;
                        fallingBlocks.remove(block);
                    } else {
                        block.modY(delta * Config.FALLINGBLOCKSPEED);
                        yLimit(block, Config.STACKTOLERANCE);
                    }
                }
            }
        }

        updateState();
    }

    public void reset() {
        map = new Tile[getHeight()][getWidth()];
        state = NORMAL;
        fallingBlocks = new CopyOnWriteArrayList<MovingBlock>();
        checkBlocks = false;
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
        UUID id = UUID.randomUUID();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    if (yPos + i >= 0) {
                        if (map[i][j] instanceof Tunnel) {
                            this.map[yPos + i][x + j] = map[i][j];
                        } else {
                            this.map[yPos + i][x + j] = new LinkedTile(map[i][j], id.toString());
                        }
                    }
                }
            }
        }

        if (breakBlocks) {
            breakBlocks();
            findFallingBlocks();
        }
    }

    private void findFallingBlocks() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null && map[i + 1][j] == null) {
                    if (linkedFallingBlocks(j, i)) {
                        fallingBlocks.add(new MovingBlock(new Tile[][]{{map[i][j]}}, TileMap.ROTATENONE, j, i * Config.BLOCKSIZE));
                        map[i][j] = null;
                        state = ANIMATE;
                    }
                }
            }
        }
    }

    private boolean linkedFallingBlocks(int x, int y) {
        if ((map[y][x] instanceof LinkedTile)) {
            return false;
        } else {
            return true;
        }
    }

    private void breakBlocks() {
        boolean[][] breakMap = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
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
