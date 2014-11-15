
package drtetris;

public class Tunnel extends Tile {
    
    public static final int STRAIGHT = 0,  ALL = 1, CORNER = 2, T = 3;
    
    public static final Tunnel BLUE = new Tunnel(Config.BLUETUNNEL),
            RED = new Tunnel(Config.REDTUNNEL),
            GREEN = new Tunnel(Config.GREENTUNNEL);
    
    public Tunnel(String image) {
        super(image, Config.TUNNELSHEETWIDTH, Config.TUNNELSHEETWIDTH);
    }
    
    public void draw(int x, int y) {
        super.draw(STRAIGHT, 0, x, y, 90);
    }
    
    public void draw(int state, int x, int y, float rotation) {
        super.draw(state, 0, x, y, rotation);
    }
}
