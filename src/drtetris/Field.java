
package drtetris;

public class Field extends TileMap {
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void addMap(TileMap map, int rotation, int x, int y) {
        addMap(map.getMap(rotation), x, y);
    }
    
    public void addMap(Tile[][] map, int x, int y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null) {
                    this.map[y + i][x + j] = map[i][j];
                }
            }
        }
    }
    
    public boolean isRoom(TileMap tileMap, int rotation, int x, int y) {
        
        if(x < 0 || x > getWidth() - tileMap.getWidth(rotation) || y < 0 || y > getHeight() - tileMap.getHeight(rotation)) {
            return false;
        }
        
        Tile[][] checkedMap = tileMap.getMap(rotation);
        
        for (int i = 0; i < checkedMap.length; i++) {
            for (int j = 0; j < checkedMap[i].length; j++) {
                if(checkedMap[i][j] != null && this.map[y + i][x + j] != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
