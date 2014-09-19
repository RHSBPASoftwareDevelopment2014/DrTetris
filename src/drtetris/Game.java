
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
    
    private BlockGenerator blockGen;
    
    private boolean paused = false;
    
    private Image pausedOverlay;
    
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
        blockGen = new BlockGenerator();
        currentBlock = blockGen.nextBlock();
        pausedOverlay = new Image("res/pausedscreen.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();
        currentBlock.draw((int)x * Tile.SIZE + 150, (int)y - Tile.SIZE * currentBlock.getHeight(rotation) + 25, rotation);
        field.draw(150, 25);
        if (paused) {
            pausedOverlay.draw();
        }
    }
    
    double y = 0;
    int x = 0;
    int rotation = Block.ROTATENONE;
    double speed = .1;
    Block currentBlock;
    int delay = 0;
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!paused) {
            if(y < 550) {
                y += delta * speed;
            } else if (delay >= 500) {
                field.addBlock(currentBlock, rotation, x, (int) (y / Tile.SIZE) - currentBlock.getHeight(rotation));
                currentBlock = blockGen.nextBlock();
                y = 0;
                x = 0;
                rotation = Block.ROTATENONE;
                delay = 0;
            } else {
                delay += delta;
            }
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
        if (!paused) {
            switch(key) {
                case Keyboard.KEY_Q:
                    rotation += Block.ROTATELEFT;
                    break;

                case Keyboard.KEY_E:
                    rotation += Block.ROTATERIGHT;
                    break;

                case Keyboard.KEY_A:
                    if (x > 0) {
                        x--;
                    }
                    break;

                case Keyboard.KEY_D:
                    if (x < 10 - currentBlock.getWidth(rotation)) {
                        x++;
                    }
                    break;
                case Keyboard.KEY_S:
                    speed = .3;
                    break;
                case Keyboard.KEY_P:
                    paused = true;
                    break;
            }     
        } else {
            paused = (key != Keyboard.KEY_P); 
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (!paused) {
            switch (key) {
                case Keyboard.KEY_S:
                    speed = .1;
                    break;
            }
        }
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
