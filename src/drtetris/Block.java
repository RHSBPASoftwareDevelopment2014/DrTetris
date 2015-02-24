
package drtetris;

import java.io.IOException;

//This class gets the blocks for the level
public class Block extends TileMap {
    
    public Block(String name) throws IOException, NumberFormatException {
        super(MapReader.getMapFromFile(Config.BLOCKDIRECTORY, name));
    }
    
    protected Block(Tile[][] map) {
        super(map);
    }
}
