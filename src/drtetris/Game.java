
package drtetris;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Timer;
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
    private Image pausedOverlay;
    private Image gameoverOverlay;
    
    private Field field;
    
    private BlockGenerator blockGen;
    
    private Block currentBlock;
    
    private Timer timer;
    
    private boolean gameover = false;
    
    private double y = -50;
    private int x = 6;
    private int rotation = Block.ROTATENONE;
    private double speed = Config.BASESPEED;
    private int stackDelay = 0;
    private int level = 1;
    
    private float A = 0,
            D = 0;
    
    public Game(int id) {
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image(Config.GAMEBACKGROUND);
        pausedOverlay = new Image(Config.PAUSESCREEN);
        gameoverOverlay = new Image(Config.GAMEOVERSCREEN);
        field = new Field(new Tile[Config.FIELDHEIGHT][Config.FIELDWIDTH]);
        blockGen = new BlockGenerator();
        currentBlock = blockGen.nextBlock();
        timer = new Timer();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        currentBlock.draw((int)x * Config.BLOCKSIZE + Config.FIELDX, (int)y + Config.FIELDY, rotation);
//        g.drawString(Integer.toString((int) ((y - Config.STACKTOLERANCE) / Config.BLOCKSIZE + 1)),(int)x * Config.BLOCKSIZE + Config.FIELDX, (int)y + Config.FIELDY);
//        g.drawString(Double.toString(y),(int)x * Config.BLOCKSIZE + Config.FIELDX, (int)y + Config.FIELDY + 14);
        field.draw(Config.FIELDX, Config.FIELDY);
        g.drawString("Level: " + level, 5, 5);
        if (gameover) {
            gameoverOverlay.draw();
        } else if (timer.isPaused()) {
            pausedOverlay.draw();
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        switch (field.getState()) {
            case Field.CONTINUE:
                field.reset();
                level++;gc.getInput().isKeyPressed(Input.KEY_A);
                break;
            case Field.END:
                gameover = true;
                break;
            default:
                if (!timer.isPaused()) {
                    if (!field.isRoom(currentBlock, rotation, x, (int) y, Config.STACKTOLERANCE, true)) {
                        if (stackDelay >= Config.BLOCKDELAY) {
                            field.addMap(currentBlock, rotation, x, y);
                            currentBlock = blockGen.nextBlock();
                            y = -50;
                            x = 6;
                            rotation = Block.ROTATENONE;
                            stackDelay = 0;
                        } else {
                            stackDelay += delta;
                        }
                    } else {
                        y = field.yLimit(currentBlock, rotation, x, y + delta * speed, Config.STACKTOLERANCE);
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
        
        if (!timer.isPaused() && !gameover) {
            switch(key) {
                case Keyboard.KEY_Q:
                    if (field.isRoom(currentBlock, rotation + Block.ROTATELEFT, x, (int) y, Config.STACKTOLERANCE, false)) {
                        rotation += Block.ROTATELEFT;
                        y = field.yLimit(currentBlock, rotation, x, y, Config.BLOCKSIZE);
                    }
                    break;

                case Keyboard.KEY_E:
                    if (field.isRoom(currentBlock, rotation + Block.ROTATERIGHT, x, (int) y, Config.STACKTOLERANCE, false)) {
                        rotation += Block.ROTATERIGHT;
                        y = field.yLimit(currentBlock, rotation, x, y, Config.BLOCKSIZE);
                    }
                    break;

                case Keyboard.KEY_A:
                    if (field.isRoom(currentBlock, rotation, x - 1, (int) y, Config.STACKTOLERANCE, false)) {
                        x--;
                        y = field.yLimit(currentBlock, rotation, x, y, Config.BLOCKSIZE);
                        if (A <= 0) {
                            A = timer.getTime();
                        }
                        D = 0;
                    }
                    break;

                case Keyboard.KEY_D:
                    if (field.isRoom(currentBlock, rotation, x + 1, (int) y, Config.STACKTOLERANCE, false)) {
                        x++;
                        y = field.yLimit(currentBlock, rotation, x, y, Config.BLOCKSIZE);
                        if (D <= 0) {
                           D = timer.getTime();
                        }
                        A = 0;
                    }
                    break;
                case Keyboard.KEY_S:
                    speed = (Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1)) * 2;
                    break;
                case Keyboard.KEY_P:
                    speed = Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1);
                    timer.pause();
                    break;
            }
        } else {
            if (key == Keyboard.KEY_P) {
                timer.resume();
            } 
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        A = 0;
        D = 0;
        if (!timer.isPaused() && !gameover) {
            switch (key) {
                case Keyboard.KEY_S:
                    speed = Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1);
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
