package drtetris;

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
        return Block.BLOCKS[(random.nextInt(Block.BLOCKS.length))];
    }
}
