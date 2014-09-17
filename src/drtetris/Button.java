
package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button {
    
    public static final int UNSELECTED = 0, SELECTED = 1, CLICKED = 2;
    
    private final Image unselected, selected, clicked;
    
    private int state = 0;
    private boolean click = false;
    private int x, y;
    
    public Button(String unselected, int x, int y) throws SlickException {
        this.unselected = new Image(unselected);
        this.selected = new Image(unselected);
        this.clicked = new Image(unselected);
        this.x = x;
        this.y = y;
    }
    
    public Button(String unselected, String selected, int x, int y) throws SlickException {
        this.unselected = new Image(unselected);
        this.selected = new Image(selected);
        this.clicked = new Image(selected);
        this.x = x;
        this.y = y;
    }
    
    public Button(String unselected, String selected, String clicked, int x, int y) throws SlickException {
        this.unselected = new Image(unselected);
        this.selected = new Image(selected);
        this.clicked = new Image(clicked);
        this.x = x;
        this.y = y;
    }
    
    public void draw() {
        switch (state) {
        
            case SELECTED:
                selected.draw(x, y);
                break;
                
            case CLICKED:
                clicked.draw(x, y);
                break;
                
            default:
                unselected.draw(x, y);
        }
    }
    
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;
        draw();
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getState() {
        return state;
    }
    
    public void setClicked(boolean click) {
        this.click = click;
    }
    
    public boolean getClicked() {
        return click;
    }
    
    public boolean isFocusRegion(int x, int y) {
        return (x >= this.x && x < (this.x + unselected.getWidth())) && (y >= this.y && y < (this.y + unselected.getHeight()));
    }
    
    public void mousePressed(int button, int x, int y) {
        if(isFocusRegion(x, y)) {
            setState(2);
        }
    }

    public void mouseReleased(int button, int x, int y) {
        if (isFocusRegion(x, y)) {
            setState(1);
            setClicked(true);
        } else {
            setState(0);
        }
    }

    public void mouseMoved(int oldX, int oldY, int newX, int newY) {
        if (isFocusRegion(newX, newY)) {
            setState(1);
        } else {
            setState(0);
        }
    }

    public void mouseDragged(int oldX, int oldY, int newX, int newY) {
        if (isFocusRegion(newX, newY)) {
            setState(2);
        } else {
            setState(0);
        }
    }
}
