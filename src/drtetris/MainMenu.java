
package drtetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


class MainMenu implements GameState {

    private final int id;
    
    private Image background;
    private Button playbutton;
    private Music backgroundMusic;
    
    public MainMenu(int id) {
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        backgroundMusic = new Music(Config.BACKGROUNDMUSIC);
        background = new Image("res/mainmenu.png");
        playbutton = new Button("res/playbutton.png", "res/playbuttonselected.png", "res/playbuttonclicked.png", 322, 375);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        playbutton.draw();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (playbutton.getClicked()) {
            sbg.enterState(DrTetris.INFINITE_MODE);
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        backgroundMusic.loop(1F, 0.07F);
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    public void mouseWheelMoved(int i) {
        
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        playbutton.mousePressed(button, x, y);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        playbutton.mouseReleased(button, x, y);
    }

    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY) {
        playbutton.mouseMoved(oldX, oldY, newX, newY);
    }

    @Override
    public void mouseDragged(int oldX, int oldY, int newX, int newY) {
        playbutton.mouseDragged(oldX, oldY, newX, newY);
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
    public void keyPressed(int i, char c) {
        
    }

    @Override
    public void keyReleased(int i, char c) {
        
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
