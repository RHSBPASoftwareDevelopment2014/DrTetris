
package drtetris;

public class Field extends TileMap {
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void addMap(TileMap map, int rotation, int x, double y) {
        addMap(map.getMap(rotation), x, y);
    }
    
    public void addMap(Tile[][] map, int x, double y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    this.map[(int) (y - 10) / Tile.SIZE + i][x + j] = map[i][j];
                }
            }
        }
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double y) {
        return isRoom(tileMap, rotation, x, y, 0);
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double yDouble, int yShift) {
        
        int[] ys = yTolerance(yDouble, yShift);
        
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
    
    private static int[] yTolerance(double y, int offset) {
        int[] ys = new int[2];
        
        ys[0] = (int) y / Tile.SIZE + offset;
        
        return ys;
    }

    public double yLimit(double y) {
        return y;
    }
}
