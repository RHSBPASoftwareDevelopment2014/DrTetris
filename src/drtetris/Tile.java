
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    public static final Tile DIRT = new Tile(Config.DIRTTILE);
    public static final Tile SAPPHIRE = new Tile(Config.SAPPHIRETILE);
    public static final Tile TUNNEL = new Tile(Config.TUNNELTILE);
    
    private SpriteSheet image;
    
    private Tile(String image) {
        try {
            this.image = new SpriteSheet(new Image(image).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE), Config.TILEPARTICLESIZE, Config.TILEPARTICLESIZE);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(int x, int y) {
        for (int i = 0; i < Config.BLOCKSIZE / Config.TILEPARTICLESIZE; i++) {
            for (int j = 0; j < Config.BLOCKSIZE / Config.TILEPARTICLESIZE; j++) {
                image.getSprite(i,j).draw(x + i * Config.TILEPARTICLESIZE + (int) ((Math.random()) * Config.PARTICLESTRAY), y + j * Config.TILEPARTICLESIZE + (int) ((Math.random()) * Config.PARTICLESTRAY));
            }
        }
    }
}
