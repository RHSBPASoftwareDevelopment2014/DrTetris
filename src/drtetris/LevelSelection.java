
package drtetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelSelection implements GameState {

	private int id;
	
	private Image background;
	
	public LevelSelection(int id) {
		this.id = id;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image(Config.LEVELSELECTIONBACKGROUND);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		background.draw(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
