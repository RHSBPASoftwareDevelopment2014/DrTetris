
package drtetris;

public class Tunnel extends LinkedTile {
    
    public static final Tunnel BLUE = new Tunnel("Blue Tunnel", 7, Config.BLUETUNNEL),
            RED = new Tunnel("Red Tunnel", 8, Config.REDTUNNEL),
            GREEN = new Tunnel("Green Tunnel", 9, Config.GREENTUNNEL);
    
    public Tunnel(String name, int id, String image) {
        super(name, id, image, 4, Config.TUNNELSHEETWIDTH, Config.TUNNELSHEETWIDTH, "Tunnel");
    }
    
    public void draw(int x, int y) {
        super.draw(LinkedTile.STRAIGHT, 0, x, y, 90F);
    }
    
    public void draw(int state, int x, int y, float rotation) {
        super.draw(state, 0, x, y, rotation);
    }
    
    public void draw(boolean[] surroundings, int x, int y) {
        TileConstruct construct = LinkedTile.TUNNELCONSTRUCTS[surroundings[0] ? 1 : 0][surroundings[1] ? 1 : 0][surroundings[2] ? 1 : 0][surroundings[3] ? 1 : 0];
        super.draw(construct.getState(), 0, x, y, construct.getRotation());
    }
}
