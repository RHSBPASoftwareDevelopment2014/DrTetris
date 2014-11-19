
package drtetris;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class InfiniteMode implements GameState {
    
    private final int id;
    
    private Image background;
    private Image pausedOverlay;
    private Image gameoverOverlay;
    
    private Field field;
    
    private BlockGenerator blockGen;
    
    private MovingBlock currentBlock;
    
    private boolean gameover = false,
            paused = false;
    
    private double speed = Config.BASESPEED;
    
    private int stackDelay = 0,
            aDelay = 0,
            dDelay = 0;
    
    private int level = 1;
    
    private boolean A = false,
            D = false;
    
    private String exception;
    
    public InfiniteMode(int id) {
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        try {
            background = new Image(Config.GAMEBACKGROUND);
            pausedOverlay = new Image(Config.PAUSESCREEN);
            gameoverOverlay = new Image(Config.GAMEOVERSCREEN);
            field = new Field(new Tile[Config.FIELDHEIGHT][Config.FIELDWIDTH]);
            blockGen = new BlockGenerator();
            currentBlock = new MovingBlock(blockGen.nextBlock(), TileMap.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
        } catch (Exception e) {
            sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, DrTetris.stackTraceToString(e)));
            sbg.enterState(DrTetris.ERR_REPORT);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        try {
            background.draw();
            currentBlock.draw(Config.FIELDX, Config.FIELDY);
            field.draw(Config.FIELDX, Config.FIELDY);
            g.drawString("Level: " + level, 5, 5);
            if (gameover) {
                gameoverOverlay.draw();
            } else if (paused) {
                pausedOverlay.draw();
            }
        } catch (Exception e) {
            sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, DrTetris.stackTraceToString(e)));
            sbg.enterState(DrTetris.ERR_REPORT);
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        try {
            if (exception != null) {
            sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, exception));
            sbg.enterState(DrTetris.ERR_REPORT);
            }
            
            if (speed > Config.SPEEDLIMIT) {
                speed = Config.SPEEDLIMIT;
            }
            
            switch (field.getState()) {
                case Field.CONTINUE:
                    field.reset();
                    level++;gc.getInput().isKeyPressed(Input.KEY_A);
                    break;
                case Field.END:
                    gameover = true;
                    break;
                default:
                    if (!paused) {

                        if(A) {
                            aDelay += delta;
                        } else {
                            aDelay = 0;
                        }

                        if(D) {
                            dDelay += delta;
                        } else {
                            dDelay = 0;
                        }

                        if(aDelay >= Config.XMOVEDELAY) {
                            double speed = Config.BASEXSPEED + Config.SPEEDXINCREMENT * level;
                            
                            if (speed > Config.SPEEDXLIMIT) {
                                speed = Config.SPEEDXLIMIT;
                            }
                            
                            int xDelta = -(int)((aDelay - Config.XMOVEDELAY) * speed);
                            if (xDelta < 0) {
                                for (int i = -1; i >= xDelta && field.isRoom(currentBlock.getMap(), currentBlock.getX() + i, (int) currentBlock.getY(), Config.STACKTOLERANCE, false); i--) {
                                    currentBlock.modX(-1);
                                    field.yLimit(currentBlock, Config.STACKTOLERANCE);
                                }
                                aDelay = Config.XMOVEDELAY;
                            }
                        }

                        if(dDelay >= Config.XMOVEDELAY) {
                            double speed = Config.BASEXSPEED + Config.SPEEDXINCREMENT * level;
                            
                            if (speed > Config.SPEEDXLIMIT) {
                                speed = Config.SPEEDXLIMIT;
                            }
                            
                            int xDelta = (int)((dDelay - Config.XMOVEDELAY) * speed);
                            if (xDelta > 0) {
                                for (int i = 1; i <= xDelta && field.isRoom(currentBlock.getMap(), currentBlock.getX() + i, (int) currentBlock.getY(), Config.STACKTOLERANCE, false); i++) {
                                    currentBlock.modX(1);
                                    field.yLimit(currentBlock, Config.STACKTOLERANCE);
                                }
                                dDelay = Config.XMOVEDELAY;
                            }
                        }

                        if (!field.isRoom(currentBlock, Config.STACKTOLERANCE, true)) {
                            if (stackDelay >= Config.BLOCKDELAY) {
                                field.addMap(currentBlock);
                                currentBlock = new MovingBlock(blockGen.nextBlock(), Field.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
                                stackDelay = 0;
                                aDelay = 0;
                                dDelay = 0;
                            } else {
                                stackDelay += delta;
                            }
                        } else {
                            currentBlock.modY(delta * speed);
                            field.yLimit(currentBlock, Config.STACKTOLERANCE);
                            stackDelay = 0;
                        }
                    }
            }
        } catch (Exception e) {
            sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, DrTetris.stackTraceToString(e)));
            sbg.enterState(DrTetris.ERR_REPORT);
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
        try {
            if (!paused && !gameover) {
                switch(key) {
                    case Keyboard.KEY_Q:
                        currentBlock.modRotation(Block.ROTATELEFT);
                        if (field.isRoom(currentBlock, Config.STACKTOLERANCE, false)) {
                            field.yLimit(currentBlock, Config.STACKTOLERANCE);
                        } else {
                            currentBlock.modRotation(Block.ROTATERIGHT);
                        }
                        break;

                    case Keyboard.KEY_E:
                        currentBlock.modRotation(Block.ROTATERIGHT);
                        if (field.isRoom(currentBlock, Config.STACKTOLERANCE, false)) {
                            field.yLimit(currentBlock, Config.STACKTOLERANCE);
                        } else {
                            currentBlock.modRotation(Block.ROTATELEFT);
                        }
                        break;

                    case Keyboard.KEY_A:
                        if (field.isRoom(currentBlock.getMap(currentBlock.getRotation()), currentBlock.getX() - 1, currentBlock.getY(), Config.STACKTOLERANCE, false)) {
                            currentBlock.modX(-1);
                            field.yLimit(currentBlock, Config.STACKTOLERANCE);
                        }
                        A = true;
                        D = false;
                        break;

                    case Keyboard.KEY_D:
                        if (field.isRoom(currentBlock.getMap(currentBlock.getRotation()), currentBlock.getX() + 1, currentBlock.getY(), Config.STACKTOLERANCE, false)) {
                            currentBlock.modX(1);
                             field.yLimit(currentBlock, Config.STACKTOLERANCE);
                        }
                        D = true;
                        A = false;
                        break;
                    case Keyboard.KEY_S:
                        speed = (Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1)) * 2;
                        break;
                    case Keyboard.KEY_P:
                        speed = Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1);
                        paused = true;
                        break;
                }
            } else {
                if (key == Keyboard.KEY_P) {
                    paused = false;
                } 
            }
        } catch (Exception e) {
            exception = DrTetris.stackTraceToString(e);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        try {
            if (!paused && !gameover) {
                switch (key) {
                    case Keyboard.KEY_S:
                        speed = Config.BASESPEED + Config.SPEEDINCREMENT * (level - 1);
                        break;

                    case Keyboard.KEY_A:
                        A = false;
                        D = false;
                        break;

                    case Keyboard.KEY_D:
                        A = false;
                        D = false;
                        break;
                }
            }
        } catch (Exception e) {
            exception = DrTetris.stackTraceToString(e);
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
