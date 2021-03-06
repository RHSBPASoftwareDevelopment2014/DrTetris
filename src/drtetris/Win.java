
package drtetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Win implements GameState {

	private final int id;
	
	private Image background;
	private Button backButton;
	
	public Win(int id) {
		this.id = id;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image(Config.WINIMAGE);
		backButton = new Button(Config.BACKBUTTON, 480, 520, 300, 60);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		background.draw();
		backButton.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		if (backButton.getClicked()) {
			backButton.setClicked(false);
			sbg.enterState(DrTetris.MAIN_MENU);
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
	public void mousePressed(int button, int x, int y) {
		backButton.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		backButton.mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		backButton.mouseMoved(oldX, oldY, newX, newY);
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		backButton.mouseDragged(oldX, oldY, newX, newY);
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
