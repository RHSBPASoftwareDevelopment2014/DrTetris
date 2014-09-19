
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tile {
    
    public static final int SIZE = 50;
    
    public static final Tile DIRT = new Tile("res/dirttile.png");
    public static final Tile SAPPHIRE = new Tile("res/sapphiretile.png");
    public static final Tile TUNNEL = new Tile("res/tunneltile.png");
    
    private Image image;
    
    private Tile(String image) {
        try {
            this.image = new Image(image);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(int x, int y) {
        image.draw(x, y);
    }
}
