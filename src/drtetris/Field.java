
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
                    this.map[parseY(y)[0] + i][x + j] = map[i][j];
                }
            }
        }
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double y) {
        return isRoom(tileMap, rotation, x, y, 0);
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, double yDouble, int yShift) {
        
        int[] ys = parseY(yDouble, yShift);
        
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
    
    private static int[] parseY(double y) {
        return parseY(y, 0);
    }
    
    private static int[] parseY(double y, int offset) {
        int[] ys = new int[2];
        System.out.println(y);
        ys[0]  = (int) (y) / Tile.SIZE + offset;
        System.out.println(ys[0]);
//        if (y % Tile.SIZE > 45) {
//            ys[1] = ys[0] - 1;
//        }
        return ys;
    }
}
