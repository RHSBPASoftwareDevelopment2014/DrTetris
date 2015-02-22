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
	private Button tutorialbutton;
	private Button challengebutton;
	private Button infinitebutton;
	private Button optionsbutton;
	private Button exitbutton;
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
		backgroundMusic = new Music(Config.MAINMENUBACKGROUNDMUSIC);
		background = new Image(Config.MAINMENU);
		tutorialbutton = new Button(Config.TUTORIALBUTTON, 5, 200);
		challengebutton = new Button(Config.CHALLENGEBUTTON, 5, 265);
		infinitebutton = new Button(Config.INFINITEBUTTON, 5, 330);
		optionsbutton = new Button(Config.OPTIONSBUTTON, 5, 395);
		exitbutton = new Button(Config.EXITBUTTON, 5, 460);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw();
		tutorialbutton.draw();
		challengebutton.draw();
		infinitebutton.draw();
		optionsbutton.draw();
		exitbutton.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (infinitebutton.getClicked()) {
			sbg.enterState(DrTetris.INFINITE_MODE);
			infinitebutton.setClicked(false);
		}

		if (challengebutton.getClicked()) {
			sbg.enterState(DrTetris.LEVEL_SELECTION);
			challengebutton.setClicked(false);
		}

		if (exitbutton.getClicked()) {
			gc.exit();
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
		tutorialbutton.mousePressed(button, x, y);
		challengebutton.mousePressed(button, x, y);
		infinitebutton.mousePressed(button, x, y);
		optionsbutton.mousePressed(button, x, y);
		exitbutton.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		tutorialbutton.mouseReleased(button, x, y);
		challengebutton.mouseReleased(button, x, y);
		infinitebutton.mouseReleased(button, x, y);
		optionsbutton.mouseReleased(button, x, y);
		exitbutton.mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		tutorialbutton.mouseMoved(oldX, oldY, newX, newY);
		challengebutton.mouseMoved(oldX, oldY, newX, newY);
		infinitebutton.mouseMoved(oldX, oldY, newX, newY);
		optionsbutton.mouseMoved(oldX, oldY, newX, newY);
		exitbutton.mouseMoved(oldX, oldY, newX, newY);
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		tutorialbutton.mouseDragged(oldX, oldY, newX, newY);
		challengebutton.mouseDragged(oldX, oldY, newX, newY);
		infinitebutton.mouseDragged(oldX, oldY, newX, newY);
		optionsbutton.mouseDragged(oldX, oldY, newX, newY);
		exitbutton.mouseDragged(oldX, oldY, newX, newY);
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
