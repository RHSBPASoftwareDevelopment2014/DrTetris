
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
                    this.map[(int) (y + 10) / Tile.SIZE + i][x + j] = map[i][j];
                }
            }
        }
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double y) {
        return isRoom(tileMap, rotation, x, y, 0, false);
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double yDouble, int yShift, boolean absolute) {
        
        int[] ys;

        if (absolute) {
            ys = new int[]{(int) yDouble / Tile.SIZE + yShift};
        } else {
            ys = yPositions(yDouble, yShift);
        }

        for (int h = 0; h < ys.length; h++) {
            if(x < 0 || x > getWidth() - tileMap.getWidth(rotation) || ys[h] < 0 || ys[h] > getHeight() - tileMap.getHeight(rotation)) {
                return false;
            }

            Tile[][] checkedMap = tileMap.getMap(rotation);

            for (int i = 0; i < checkedMap.length; i++) {
                for (int j = 0; j < checkedMap[i].length; j++) {
                    if(checkedMap[i][j] != null && this.map[ys[h] + i][x + j] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private static int[] yPositions(double y, int offset) {
        int[] ys = new int[2];
        
        if (y >= -TOLERANCE) {
            if (y % Tile.SIZE > 5) {
                if (y % Tile.SIZE >= 45) {
                    ys[0] = (int) y / Tile.SIZE + offset + 1;
                } else {
                    ys[0] = (int) y / Tile.SIZE + offset;
                    ys[1] = (int) y / Tile.SIZE + offset + 1;
                }
            } else {
                ys[0] = (int) y / Tile.SIZE + offset;
            }
        } else {
            ys[0] = 0;
        }
        
        return ys;
    }

    double yLimit(Block currentBlock, int rotation, int x, double y) {
        return yLimit(currentBlock, rotation, x, y, 0);
    }
    
    double yLimit(Block currentBlock, int rotation, int x, double y, int yShift) {
        if (y > -TOLERANCE && !isRoom(currentBlock, rotation, x, y, yShift + 1, true)) {
            if (y % Tile.SIZE > TOLERANCE) {
                if (y % Tile.SIZE >= Tile.SIZE - TOLERANCE) {
                    return y - y % Tile.SIZE + Tile.SIZE;
                } else {
                    return y;
                }
            } else {
                return y - y % Tile.SIZE;
            }
        } else {
            return y;
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
