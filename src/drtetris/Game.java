
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

    private static final double SPEED = .16;
    private static final double SPEEDINCREMENT = .1;
    
    private final int id;
    
    private Image background;
    private Image pausedOverlay;
    private Image gameoverOverlay;
    
    private Field field;
    
    private BlockGenerator blockGen;
    
    private boolean paused = false;
    private boolean gameover = false;
    
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
        field = new Field(new Tile[12][12]);
        blockGen = new BlockGenerator();
        currentBlock = blockGen.nextBlock();
        pausedOverlay = new Image("res/pausedscreen.png");
        gameoverOverlay = new Image("res/gameover.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        currentBlock.draw((int)x * Tile.SIZE + 150, (int)y + 90, rotation);
        field.draw(150, 90);
        g.drawString("Level: " + level, 5, 5);
        if (gameover) {
            gameoverOverlay.draw();
        } else if (paused) {
            pausedOverlay.draw();
        }
    }
    
    double y = -50;
    int x = 6;
    int rotation = Block.ROTATENONE;
    double speed = SPEED;
    Block currentBlock;
    int delay = 0;
    int level = 1;
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        switch (field.getState()) {
            case Field.CONTINUE:
                field.reset();
                level++;
                break;
            case Field.END:
                gameover = true;
                break;
            default:
                if (!paused) {
                    if (field.isRoom(currentBlock, rotation, x, y + delta * speed)) {
                        if (!field.isRoom(currentBlock, rotation, x, y + delta * speed, 1, true)) {
                            if (delay >= 500) {
                                field.addMap(currentBlock, rotation, x, y);
                                currentBlock = blockGen.nextBlock();
                                y = -50;
                                x = 6;
                                rotation = Block.ROTATENONE;
                                delay = 0;
                            } else {
                                delay += delta;
                            }
                        } else {
                            y = field.yLimit(currentBlock, rotation, x, y + delta * speed);
                        }
                    }
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
        if (!paused && !gameover) {
            switch(key) {
                case Keyboard.KEY_Q:
                    if (field.isRoom(currentBlock, rotation + Block.ROTATELEFT, x, y)) {
                        rotation += Block.ROTATELEFT;
                    }
                    break;

                case Keyboard.KEY_E:
                    if (field.isRoom(currentBlock, rotation + Block.ROTATERIGHT, x, y)) {
                        rotation += Block.ROTATERIGHT;
                    }
                    break;

                case Keyboard.KEY_A:
                    if (field.isRoom(currentBlock, rotation, x - 1, y)) {
                        x--;
                    }
                    break;

                case Keyboard.KEY_D:
                    if (field.isRoom(currentBlock, rotation, x + 1, y)) {
                        x++;
                    }
                    break;
                case Keyboard.KEY_S:
                    speed = (SPEED + SPEEDINCREMENT * (level - 1)) * 2;
                    break;
                case Keyboard.KEY_P:
                    speed = SPEED + SPEEDINCREMENT * (level - 1);
                    paused = true;
                    break;
            }     
        } else {
            paused = key != Keyboard.KEY_P; 
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (!paused && !gameover) {
            switch (key) {
                case Keyboard.KEY_S:
                    speed = SPEED + SPEEDINCREMENT * (level - 1);
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
