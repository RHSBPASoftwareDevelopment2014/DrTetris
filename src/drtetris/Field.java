
package drtetris;

public class Field extends TileMap {
    
    public Field(Tile[][] map) {
        super(map);
    }
    
    public void addBlock(Block block, int rotation, int x, int y) {
        addMap(block.getMap(rotation), x, y);
    }
    
    public void addMap(Tile[][] map, int x, int y) {
        System.out.println(x + " " + y);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.println((y + i) + " " + (x + j));
                if(map[i][j] != null) {
                    this.map[y + i][x + j] = map[i][j];
                }
            }
        }
    }
}
