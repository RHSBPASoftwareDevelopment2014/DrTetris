
package drtetris;


public class Block extends TileMap {
    
    public static final Block BLOCKS[] = new Block[]{
        new Block(new Tile[][]
            {
                {Tile.RUBY},
                {Tile.SAPPHIRE}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tile.GREENGARNET, Tile.AMETHYST}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.BLUE}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.GREEN}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.RED}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.BLUE},
                {Tunnel.BLUE}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.GREEN},
                {Tunnel.GREEN}
            }
        ),
        
        new Block(new Tile[][]
            {
                {Tunnel.RED},
                {Tunnel.RED}
            }
        ),

        new Block(new Tile[][]
            {
                {new LinkedTile(Tile.DIRT, "A"), new LinkedTile(Tile.DIRT, "A")},
                {new LinkedTile(Tile.DIRT, "A")}
            }
        ),

        new Block(new Tile[][]
            {
                {new LinkedTile(Tile.DIRT, "B"), Tile.AMETHYST}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.SAND}
            }
        ),

        new Block(new Tile[][]
            {
                {Tile.GREENGARNET},
                {new LinkedTile(Tile.DIRT, "C")}
            }
        ),
            
        new Block(new Tile[][]
            {
                {new LinkedTile(Tile.DIRT, "D"), new LinkedTile(Tile.DIRT, "D")}
            }
        ),
        
//        new Block(new Tile[][]
//            {
//                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
//                {Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND},
//                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
//                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
//                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
//                {Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND},
//            }
//        )
    };
    
    public Block(Tile[][] map) {
        super(map);
    }
}
