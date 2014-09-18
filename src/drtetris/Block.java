
package drtetris;


public class Block extends TileMap {
    
    public static final Block DEFAULT = new Block(new Tile[][]
        {
            {null, Tile.DIRT, Tile.DIRT},
            {Tile.DIRT, Tile.DIRT}
        }
    );

    public Block(Tile[][] map) {
        super(map);
    }
}
