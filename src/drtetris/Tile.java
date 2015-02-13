
package drtetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Tile {
    
    private int id;
    private SpriteSheet image;
    private boolean gravity;
    private boolean locked;
    
    public Tile(int index) throws SlickException, ArrayIndexOutOfBoundsException {
        this.id = index;
        this.gravity = Config.TILEGRAVITYLIST[index];
        this.image = new SpriteSheet(new Image(Config.TILEIMAGELIST[index]).getScaledCopy(6 * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
        this.locked = false;
    }
    
    protected Tile(int index, int length, boolean gravity, boolean locked) {
        try {
            this.id = index;
            this.image = new SpriteSheet(new Image(Config.TILEIMAGELIST[index]).getScaledCopy(length * Config.BLOCKSIZE, Config.BLOCKSIZE), Config.BLOCKSIZE, Config.BLOCKSIZE);
            this.gravity = gravity;
            this.locked = locked;
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
    
    public int getId() {
        return id;
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
        return tile != null ? id == tile.getId() : false;
    }
}
