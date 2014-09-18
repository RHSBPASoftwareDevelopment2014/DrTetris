
package drtetris;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class Game implements GameState {

    private final int id;
    
    private Image background;
    
    private Field field;
    
    public Game(int id) {
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/gamebackground.png");
        field = new Field(new Tile[11][10]);
        currentBlock = Block.DEFAULT;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();
        currentBlock.draw((int)x * Tile.SIZE + 150, (int)y - Tile.SIZE * currentBlock.getHeight(rotation) + 25, rotation);
        field.draw(150, 25);
    }
    
    double y = 0;
    int x = 0;
    int rotation = Block.ROTATENONE;
    
    Block currentBlock;
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(y < 550) {
            y += delta * .1;
        } else {
            field.addBlock(currentBlock, rotation, x, (int) (y / Tile.SIZE) - currentBlock.getHeight(rotation));
            y = 0;
            x = 0;
            rotation = Block.ROTATENONE;
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void mouseWheelMoved(int i) {
    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mousePressed(int i, int i1, int i2) {
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }

    @Override
    public void keyPressed(int key, char c) {
        switch(key) {
            case Keyboard.KEY_Q:
                rotation += Block.ROTATELEFT;
                break;
                
            case Keyboard.KEY_E:
                rotation += Block.ROTATERIGHT;
                break;
            
            case Keyboard.KEY_A:
                x--;
                break;
                
            case Keyboard.KEY_D:
                x++;
                break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        
    }

    @Override
    public void controllerLeftPressed(int i) {
    }

    @Override
    public void controllerLeftReleased(int i) {
    }

    @Override
    public void controllerRightPressed(int i) {
    }

    @Override
    public void controllerRightReleased(int i) {
    }

    @Override
    public void controllerUpPressed(int i) {
    }

    @Override
    public void controllerUpReleased(int i) {
    }

    @Override
    public void controllerDownPressed(int i) {
    }

    @Override
    public void controllerDownReleased(int i) {
    }

    @Override
    public void controllerButtonPressed(int i, int i1) {
    }

    @Override
    public void controllerButtonReleased(int i, int i1) {
    }

}
