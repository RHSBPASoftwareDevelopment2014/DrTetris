package drtetris;

import java.io.FileNotFoundException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu implements GameState {

	private final int id;
	//Declares the background image and music along with the buttons
	private Image background;
	private Button tutorialbutton;
	private Button challengebutton;
	private Button infinitebutton;
	private Button optionsbutton;
	private Button exitbutton;
	private Button savebutton;
	private Music backgroundMusic;

	private SaveHandler saveHandler;

	public MainMenu(int id) {
		this.id = id;
	}

	@Override
	public int getID() {
		return id;
	}

	public SaveHandler getSaveHandler() {
		return saveHandler;
	}

	public void save(StateBasedGame sbg) throws FileNotFoundException {
		saveHandler.save(((ChallengeMode) sbg.getState(DrTetris.CHALLENGE_MODE)).getHighestLevel(), ((InfiniteMode) sbg.getState(DrTetris.INFINITE_MODE)).getHighestLevel(), ((Options) sbg.getState(DrTetris.OPTIONS)).getDifficulty(), (int) (((Options) sbg.getState(DrTetris.OPTIONS)).getVolume() * 100));
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Initializes the background image and music along with the buttons' positions
		try {
			backgroundMusic = new Music(Config.MAINMENUBACKGROUNDMUSIC);
			background = new Image(Config.MAINMENU);
			tutorialbutton = new Button(Config.TUTORIALBUTTON, 5, 200, 300, 60);
			challengebutton = new Button(Config.CHALLENGEBUTTON, 5, 265, 300, 60);
			infinitebutton = new Button(Config.INFINITEBUTTON, 5, 330, 300, 60);
			optionsbutton = new Button(Config.OPTIONSBUTTON, 5, 395, 300, 60);
			savebutton = new Button(Config.SAVEBUTTON, 5, 460, 300, 60);
			exitbutton = new Button(Config.EXITBUTTON, 5, 525, 300, 60);
			saveHandler = new SaveHandler(Config.SAVEFILE);
			saveHandler.load();
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//Draws all of the buttons and the background
		try {
			background.draw();
			tutorialbutton.draw();
			challengebutton.draw();
			infinitebutton.draw();
			optionsbutton.draw();
			savebutton.draw();
			exitbutton.draw();
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {
			//Gives the buttons their purposes and sets the volume
			backgroundMusic.setVolume(((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());

			if (tutorialbutton.getClicked()) {
				sbg.enterState(DrTetris.TUTORIAL);
				tutorialbutton.setClicked(false);
			}

			if (infinitebutton.getClicked()) {
				sbg.enterState(DrTetris.INFINITE_MODE);
				infinitebutton.setClicked(false);
			}

			if (challengebutton.getClicked()) {
				sbg.enterState(DrTetris.LEVEL_SELECTION);
				challengebutton.setClicked(false);
			}

			if (optionsbutton.getClicked()) {
				((Options) sbg.getState(DrTetris.OPTIONS)).setBackgroundState(sbg.getCurrentStateID());
				sbg.enterState(DrTetris.OPTIONS);
				optionsbutton.setClicked(false);
			}

			if (savebutton.getClicked()) {
				save(sbg);
				savebutton.setClicked(false);
			}

			if (exitbutton.getClicked()) {
				save(sbg);
				gc.exit();
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			//Loops the music
			if (!backgroundMusic.playing()) {
				backgroundMusic.loop(1F, ((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
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

	//The next four methods tell the buttons what to do depending on what the mouse does in relation to them
	@Override
	public void mousePressed(int button, int x, int y) {
		tutorialbutton.mousePressed(button, x, y);
		challengebutton.mousePressed(button, x, y);
		infinitebutton.mousePressed(button, x, y);
		optionsbutton.mousePressed(button, x, y);
		savebutton.mousePressed(button, x, y);
		exitbutton.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		tutorialbutton.mouseReleased(button, x, y);
		challengebutton.mouseReleased(button, x, y);
		infinitebutton.mouseReleased(button, x, y);
		optionsbutton.mouseReleased(button, x, y);
		savebutton.mouseReleased(button, x, y);
		exitbutton.mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		tutorialbutton.mouseMoved(oldX, oldY, newX, newY);
		challengebutton.mouseMoved(oldX, oldY, newX, newY);
		infinitebutton.mouseMoved(oldX, oldY, newX, newY);
		optionsbutton.mouseMoved(oldX, oldY, newX, newY);
		savebutton.mouseMoved(oldX, oldY, newX, newY);
		exitbutton.mouseMoved(oldX, oldY, newX, newY);
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		tutorialbutton.mouseDragged(oldX, oldY, newX, newY);
		challengebutton.mouseDragged(oldX, oldY, newX, newY);
		infinitebutton.mouseDragged(oldX, oldY, newX, newY);
		optionsbutton.mouseDragged(oldX, oldY, newX, newY);
		savebutton.mouseDragged(oldX, oldY, newX, newY);
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
