package drtetris;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BlockGenerator {
    
    private Random random;
            
    public BlockGenerator(Long seed) {
        random = new Random(seed);
    }
    
    public BlockGenerator() {
        random = new Random();
    }
    
    public Block nextBlock() {
        try {
            File file = new File(Config.BLOCKDIRECTORY);
            File[] files = file.listFiles();
            return new Block(files[random.nextInt(files.length)].getName().replace(".csv", ""));
        } catch (IOException e) {
            return null;
        }
    }
}
