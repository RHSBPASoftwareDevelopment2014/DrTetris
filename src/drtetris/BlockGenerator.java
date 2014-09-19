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
        switch (random.nextInt(4)) {
            case 0:
                return Block.NUMBAONE;
            case 1:
                return Block.NUMBATWO;
            case 2:
                return Block.NUMBATHREE;
            case 3:
                return Block.NUMBAFOUR;
                
            default:
                return null;
        }
    }
}
