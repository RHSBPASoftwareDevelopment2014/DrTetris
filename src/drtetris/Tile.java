
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    public static final Tile DIRT = new Tile("Dirt", 1, Config.DIRTTILE, 6),
            SAPPHIRE = new Tile("Sapphire", 2, Config.SAPPHIRETILE, 6),
            RUBY = new Tile("Ruby", 3, Config.RUBYTILE, 6),
            AMETHYST = new Tile("Amethyst", 4, Config.AMETHYSTTILE, 6),
            SAND = new Tile("Sand", 5, Config.SANDTILE, 6),
            GREENGARNET = new Tile("Green Garnet", 6, Config.GREENGARNETTILE, 6);
            
    
    protected String name;
    protected int id;
    
    protected SpriteSheet image;
    
    protected int length;
    
    protected Tile(String name, int id, String image, int length) {
        try {
            this.name = name;
            this.id = id;
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(length * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, int id, SpriteSheet image, int length) {
        this.name = name;
        this.id = id;
        this.length = length;
        this.image = image;
    }
    
    protected Tile(String name, int id, String image, int length, int width, int height, int tWidth, int tHeight) {
        try {
            this.name = name;
            this.id = id;
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(width, height), tWidth, tHeight);
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, int id, String image, int length, int tWidth, int tHeight) {
        try {
            this.name = name;
            this.id = id;
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
        return tile != null ? name.equals(tile.name) : false;
    }
}
