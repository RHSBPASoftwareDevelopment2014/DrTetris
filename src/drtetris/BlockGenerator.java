package drtetris;

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
            return new Block("1");
        } catch (IOException | NumberFormatException ex) {
            return null;
        }
    }
}
