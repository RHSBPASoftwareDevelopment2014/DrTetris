
package drtetris;

public class Field extends TileMap {
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void addBlock(Block block, int rotation, int x, int y) {
        addMap(block.getMap(rotation), x, y);
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
}
