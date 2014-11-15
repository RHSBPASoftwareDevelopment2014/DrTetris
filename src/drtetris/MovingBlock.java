
package drtetris;

public class MovingBlock extends Block {
    
    private int rotation;
    private int x;
    private double y;
    
    public MovingBlock(Tile[][] map, int rotation, int x, double y) {
        super(map);
        this.rotation = rotation;
        this.x = x;
        this.y = y;
    }
    
    public MovingBlock(Block block, int rotation, int x, double y) {
        this(block.getMap(), rotation, x, y);
    }
    
    @Override
    public Tile[][] getMap() {
        return super.getMap(rotation);
    }
    
    @Override
    public void draw() {
        super.draw(x * Config.BLOCKSIZE, (int) y);
    }
    
    @Override
    public void draw(int xOffset, int yOffset) {
        super.draw(x * Config.BLOCKSIZE + xOffset, (int) y + yOffset, rotation);
    }
    
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    
    public void modRotation(int rotation) {
        this.rotation += rotation;
    }
    
    public int getRotation() {
        return rotation;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void modX(int x) {
        this.x += x;
    }
    
    public int getX() {
        return x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void modY(double y) {
        this.y += y;
    }
    
    public double getY() {
        return y;
    }
}
