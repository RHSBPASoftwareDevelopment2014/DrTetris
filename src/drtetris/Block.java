
package drtetris;


public class Block extends TileMap {
    
    public static final Block BLOCKS[] = new Block[]{
        new Block(new Tile[][]
            {
                {Tile.DIRT, Tile.DIRT, Tile.DIRT}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.DIRT, Tile.DIRT},
                {Tile.SAPPHIRE}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.DIRT, Tile.DIRT}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.TUNNEL}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.TUNNEL},
                {Tile.TUNNEL}
            }
        )
    };
    
    public Block(Tile[][] map) {
        super(map);
    }
}
