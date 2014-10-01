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
        switch (random.nextInt(5)) {
            case 0:
                return Block.NUMBAONE;
            case 1:
                return Block.NUMBATWO;
            case 2:
                return Block.NUMBATHREE;
            case 3:
                return Block.NUMBAFOUR;
            case 4:
                return Block.NUMBAFIVE;
            default:
                return null;
        }
    }
}
