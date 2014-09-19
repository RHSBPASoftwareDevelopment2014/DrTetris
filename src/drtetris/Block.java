
package drtetris;


public class Block extends TileMap {
    
    public static final Block NUMBAONE = new Block(new Tile[][]
        {
            {Tile.DIRT, Tile.DIRT, Tile.DIRT}
        }
    ),
    
    NUMBATWO = new Block(new Tile[][]
        {
            {Tile.DIRT, Tile.DIRT},
            {Tile.SAPPHIRE}
        }
    ),
    
    NUMBATHREE = new Block(new Tile[][]
        {
            {Tile.DIRT, Tile.DIRT}
        }
    ),
    NUMBAFOUR = new Block(new Tile[][]
        {
            {Tile.TUNNEL}
        }
    );
    
    public Block(Tile[][] map) {
        super(map);
    }
}
