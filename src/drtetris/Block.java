
package drtetris;


public class Block extends TileMap {
    
    public static final Block NUMBAONE = new Block(new Tile[][]
        {
            {Tile.DIRT},
            {Tile.SAPPHIRE, Tile.SAPPHIRE}
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
            {Tile.DIRT, Tile.SAPPHIRE},
            {Tile.SAPPHIRE, Tile.SAPPHIRE}
        }
    ),
    NUMBAFOUR = new Block(new Tile[][]
        {
            {Tile.TUNNEL},
            {Tile.TUNNEL}
        }
    );
    
    public Block(Tile[][] map) {
        super(map);
    }
}
