
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    public static final Tile DIRT = new Tile(Config.DIRTTILE, 6),
            SAPPHIRE = new Tile(Config.SAPPHIRETILE, 1),
            RUBY = new Tile(Config.RUBYTILE, 1),
            AMETHYST = new Tile(Config.AMETHYSTTILE, 1),
            SAND = new Tile(Config.SANDTILE, 1),
            GREENGARNET = new Tile(Config.GREENGARNETTILE, 1);
            
    
    protected SpriteSheet image;
    
    protected int length;
    
    protected Tile(String image, int length) {
        try {
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(length * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
        } catch (SlickException ex) {}
    }
    
    protected Tile(SpriteSheet image, int length) {
        this.length = length;
        this.image = image;
    }
    
    protected Tile(String image, int length, int width, int height, int tWidth, int tHeight) {
        try {
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(width, height), tWidth, tHeight);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String image, int length, int tWidth, int tHeight) {
        try {
            this.length = length;
            this.image = new SpriteSheet(image, tWidth, tHeight);
        } catch (SlickException ex) {}
    }

    public void draw(int x, int y) {
        image.getSprite(0,0).draw(x, y);
    }
    
    public void draw(int row, int column, int x, int y, float angle) {
        Image i = image.getSprite(row, column).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE);
        i.setRotation(angle);
        i.draw(x, y);
    }
}
