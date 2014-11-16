
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    public static final Tile DIRT = new Tile(Config.DIRTTILE),
            SAPPHIRE = new Tile(Config.SAPPHIRETILE, true),
            RUBY = new Tile(Config.RUBYTILE, true),
            AMETHYST = new Tile(Config.AMETHYSTTILE, true),
            SAND = new Tile(Config.SANDTILE),
            GREENGARNET = new Tile(Config.GREENGARNETTILE, true);
            
    
    private SpriteSheet image;
    
    private boolean fuzz = false;
    
    private Tile(String image) {
        try {
            this.image = new SpriteSheet(new Image(image).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE), Config.TILEPARTICLESIZE, Config.TILEPARTICLESIZE);
        } catch (SlickException ex) {}
    }
    
    private Tile(String image, boolean fuzz) {
        try {
            this.fuzz = fuzz;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE), Config.TILEPARTICLESIZE, Config.TILEPARTICLESIZE);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String image, int width, int height, int tWidth, int tHeight) {
        try {
            this.image = new SpriteSheet(new Image(image).getScaledCopy(width, height), tWidth, tHeight);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String image, int tWidth, int tHeight) {
        try {
            this.image = new SpriteSheet(image, tWidth, tHeight);
        } catch (SlickException ex) {}
    }

    public void draw(int x, int y) {
        for (int i = 0; i < Config.BLOCKSIZE / Config.TILEPARTICLESIZE; i++) {
            for (int j = 0; j < Config.BLOCKSIZE / Config.TILEPARTICLESIZE; j++) {
                if(fuzz) image.getSprite(i,j).draw(x + i * Config.TILEPARTICLESIZE + (int) ((Math.random()) * Config.PARTICLESTRAY), y + j * Config.TILEPARTICLESIZE + (int) ((Math.random()) * Config.PARTICLESTRAY));
                else image.getSprite(i,j).draw(x + i * Config.TILEPARTICLESIZE, y + j * Config.TILEPARTICLESIZE);
            }
        }
    }
    
    public void draw(int row, int column, int x, int y, float angle) {
        Image i = image.getSprite(row, column).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE);
        i.setRotation(angle);
        i.draw(x, y);
    }
}
