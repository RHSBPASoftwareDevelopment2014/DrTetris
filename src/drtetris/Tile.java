
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tile {
    
    public static final Tile DIRT = new Tile(Config.DIRTTILE);
    public static final Tile SAPPHIRE = new Tile(Config.SAPPHIRETILE);
    public static final Tile TUNNEL = new Tile(Config.TUNNELTILE);
    
    private Image image;
    
    private Tile(String image) {
        try {
            this.image = new Image(image).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(int x, int y) {
        image.draw(x, y);
        
    }
}
