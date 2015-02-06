
package drtetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;


public class Tile {
    
    protected String name;
    protected SpriteSheet image;
    protected int length;
    private boolean gravity;
    private boolean locked;
    
    protected Tile(String name, String image, int length, boolean gravity, boolean locked) {
        try {
            this.name = name;
            this.length = length;
            this.gravity = gravity;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(length * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
            this.locked = locked;
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, SpriteSheet image, int length, boolean gravity, boolean locked) {
        this.name = name;
        this.length = length;
        this.image = image;
        this.gravity = gravity;
        this.locked = locked;
    }
    
    protected Tile(String name, String image, int length, int width, int height, int tWidth, int tHeight, boolean gravity, boolean locked) {
        try {
            this.name = name;
            this.length = length;
            this.image = new SpriteSheet(new Image(image).getScaledCopy(width, height), tWidth, tHeight);
            this.gravity = gravity;
            this.locked = locked;
        } catch (SlickException ex) {}
    }
    
    protected Tile(String name, String image, int length, int tWidth, int tHeight, boolean gravity, boolean locked) {
        try {
            this.name = name;
            this.length = length;
            this.gravity = gravity;
            this.locked = locked;
            this.image = new SpriteSheet(image, tWidth, tHeight);
        } catch (SlickException ex) {}
    }

    public void draw(int x, int y) {
        image.getSprite(0,0).draw(x, y);
    }
    
    public void draw(int row, int column, int x, int y, float angle) {
        Image i = image.getSprite(row, column).getScaledCopy(Config.BLOCKSIZE, Config.BLOCKSIZE);
        i.setRotation(angle);
        if (locked) {
            i.draw(x, y, Color.lightGray);
        } else {
            i.draw(x, y);
        }
    }
    
    public void draw(boolean[] surroundings, int x, int y) {
        TileConstruct construct = LinkedTile.CONSTRUCTS[surroundings[0] ? 1 : 0][surroundings[1] ? 1 : 0][surroundings[2] ? 1 : 0][surroundings[3] ? 1 : 0];
        draw(construct.getState(), 0, x, y, construct.getRotation());
    }
    
    public boolean hasGravity() {
        return gravity;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public boolean equals(Tile tile) {
        return tile != null ? name.equals(tile.name) : false;
    }
}
