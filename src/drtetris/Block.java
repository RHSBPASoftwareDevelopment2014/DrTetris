
package drtetris;


public class Block extends TileMap {
    
    public static final Block BLOCKS[] = new Block[]{
//        new Block(new Tile[][]
//            {
//                {Tile.RUBY},
//                {Tile.SAPPHIRE}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tile.GREENGARNET, Tile.AMETHYST}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.BLUE}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.GREEN}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.RED}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.BLUE},
//                {Tunnel.BLUE}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.GREEN},
//                {Tunnel.GREEN}
//            }
//        ),
//        
//        new Block(new Tile[][]
//            {
//                {Tunnel.RED},
//                {Tunnel.RED}
//            }
//        ),
//
//        new Block(new Tile[][]
//            {
//                {Tile.DIRT, Tile.DIRT},
//                {Tile.DIRT}
//            }
//        ),
//
//        new Block(new Tile[][]
//            {
//                {Tile.DIRT, Tile.AMETHYST}
//            }
//        ),
//
//        new Block(new Tile[][]
//            {
//                {Tile.SAND}
//            }
//        ),
//
//        new Block(new Tile[][]
//            {
//                {Tile.GREENGARNET},
//                {Tile.DIRT}
//            }
//        ),
//            
//        new Block(new Tile[][]
//            {
//                {Tile.DIRT, Tile.DIRT}
//            }
//        ),
        
        new Block(new Tile[][]
            {
                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
                {Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND},
                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
                {Tile.AMETHYST, Tile.RUBY, Tile.SAPPHIRE, Tile.GREENGARNET, Tile.DIRT},
                {Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND, Tile.SAND},
            }
        )
    };
    
    public Block(Tile[][] map) {
        super(map);
    }
}
