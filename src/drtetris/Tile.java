
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    public static final Tile DIRT = new Tile("Dirt", Config.DIRTTILE, 6),
            SAPPHIRE = new Tile("Sapphire", Config.SAPPHIRETILE, 6),
            RUBY = new Tile("Ruby", Config.RUBYTILE, 6),
            AMETHYST = new Tile("Amethyst", Config.AMETHYSTTILE, 6),
            SAND = new Tile("Sand", Config.SANDTILE, 6),
            GREENGARNET = new Tile("Green Garnet", Config.GREENGARNETTILE, 6);
            
    
    protected String name;
    
    protected SpriteSheet image;
    
    protected int length;
    
    protected Tile(String name, String image, int length) {
        try {
            this.name = name;
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(length * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, SpriteSheet image, int length) {
        this.name = name;
        this.length = length;
        this.image = image;
    }
    
    protected Tile(String name, String image, int length, int width, int height, int tWidth, int tHeight) {
        try {
            this.name = name;
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(width, height), tWidth, tHeight);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, String image, int length, int tWidth, int tHeight) {
        try {
            this.name = name;
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
    
    public void draw(boolean[] surroundings, int x, int y) {
        TileConstruct construct = LinkedTile.CONSTRUCTS[surroundings[0] ? 1 : 0][surroundings[1] ? 1 : 0][surroundings[2] ? 1 : 0][surroundings[3] ? 1 : 0];
        draw(construct.getState(), 0, x, y, construct.getRotation());
    }
    
    public boolean equals(Tile tile) {
        return name.equals(tile.name);
    }
}
