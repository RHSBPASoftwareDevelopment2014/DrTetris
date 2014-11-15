
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
        super.draw(STRAIGHT, 0, x, y, 90F);
    }
    
    public void draw(int state, int x, int y, float rotation) {
        super.draw(state, 0, x, y, rotation);
    }
    
    public void draw(boolean[] surroundings, int x, int y) {
        int state = STRAIGHT;
        float rotation = 90F;
        if (surroundings[2]) {
            if (surroundings[3]) {
                if (surroundings[0]) {
                    if (surroundings[1]) {
                        state = ALL;
                        rotation = 0F;
                    } else {
                        state = T;
                        rotation = 180F;
                    }
                } else if (surroundings[1]) {
                    state = T;
                    rotation = 0F;
                } else {
                    rotation = 0F;
                }
            } else if (surroundings[0]) {
                if (surroundings[1]) {
                    state = T;
                } else {
                    state = CORNER;
                }
            } else if (surroundings[1]) {
                state = CORNER;
                rotation = 0F;
            } else {
                rotation = 0F;
            }
        } else if (surroundings[3]) {
            if (surroundings[0]) {
                if (surroundings[1]) {
                    state = T;
                    rotation = -90F;
                } else {
                    state = CORNER;
                    rotation = 180F;
                }
            } else if (surroundings[1]) {
                state = CORNER;
                rotation = -90F;
            } else {
                rotation = 0F;
            }
        }
        super.draw(state, 0, x, y, rotation);
    }
}
