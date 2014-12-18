
package drtetris;

public class TileMap {
    
    public static final int ROTATENONE = 0, ROTATELEFT = 1, ROTATEHALF = 2, ROTATERIGHT = 3;
    
    protected Tile[][] map;
    
    public TileMap(Tile[][] map) {
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
    
    public void draw(int x, int y, boolean field) {
        draw(x, y, ROTATENONE, field);
    }
    
    public void draw(int x, int y, int rotation, boolean field) {
        draw(x, y, getMap(rotation), field);
    }
    
    public void draw(int x, int y, int rotation) {
        draw(x, y, getMap(rotation), false);
    }
    
    public void draw(int x, int y, Tile[][] map, boolean field) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    if (map[i][j] instanceof Tunnel) {
                        ((Tunnel) map[i][j]).draw(getSurroundingTunnels(j, i, map, field), x + Config.BLOCKSIZE * j, y + Config.BLOCKSIZE * i);
                    } else if (field ? map[i][j] instanceof LinkedTile : true) {
                        map[i][j].draw(getSurroundingBlocks(j, i, map, field), x + Config.BLOCKSIZE * j, y + Config.BLOCKSIZE * i);
                    } else {
                        map[i][j].draw(x + Config.BLOCKSIZE * j, y + Config.BLOCKSIZE * i);
                    }
                }
            }
        }
    }
    
    public static int trimRotation(int rotation) {
        return rotation % 4;
    }
    
    /**
     * @param x
     * @param y
     * @param map
     * @param field
     * @return [tunnel above, tunnel below, tunnel left, tunnel right]
     */
    protected boolean[] getSurroundingTunnels(int x, int y, Tile[][] map, boolean field) {
        return new boolean[]{y - 1 >= 0 && y - 1 < map.length && x >= 0 && x < map[y - 1].length && map[y - 1][x] instanceof Tunnel,
                y + 1 >= 0 && x >= 0  && x < map[0].length && ((field && y + 1 == map.length) || (y + 1 < map.length && map[y + 1][x] instanceof Tunnel)),
                y >= 0 && y < map.length && x - 1 >= 0  && x - 1 < map[y].length && map[y][x - 1] instanceof Tunnel,
                y >= 0 && y < map.length && x + 1 >= 0  && x + 1 < map[y].length && map[y][x + 1] instanceof Tunnel};
    }
    
    /**
     * @param x
     * @param y
     * @param map
     * @param field
     * @return [block above, block below, block left, block right]
     */
    protected boolean[] getSurroundingBlocks(int x, int y, Tile[][] map, boolean field) {
        return new boolean[]{y - 1 >= 0 && y - 1 < map.length && x >= 0 && x < map[y - 1].length && (field ? map[y - 1][x] instanceof LinkedTile : map[y - 1][x] != null) && (field ? ((LinkedTile) map[y - 1][x]).getBlockId().equals(((LinkedTile) map[y][x]).getBlockId()) : true),
                y + 1 >= 0 && x >= 0 &&  y + 1 < map.length && x < map[y + 1].length && (field ? map[y + 1][x] instanceof LinkedTile : map[y + 1][x] != null) && (field ? ((LinkedTile) map[y + 1][x]).getBlockId().equals(((LinkedTile) map[y][x]).getBlockId()) : true),
                y >= 0 && y < map.length && x - 1 >= 0  && x - 1 < map[y].length && (field ? map[y][x - 1] instanceof LinkedTile : map[y][x - 1] != null) && (field ? ((LinkedTile) map[y][x - 1]).getBlockId().equals(((LinkedTile) map[y][x]).getBlockId()) : true),
                y >= 0 && y < map.length && x + 1 >= 0  && x + 1 < map[y].length && (field ? map[y][x + 1] instanceof LinkedTile : map[y][x + 1] != null) && (field ? ((LinkedTile) map[y][x + 1]).getBlockId().equals(((LinkedTile) map[y][x]).getBlockId()) : true)};
    }
}
