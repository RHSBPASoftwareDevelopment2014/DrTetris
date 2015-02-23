
package drtetris;

import java.awt.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelSelection implements GameState {

	private final int id;
	private final Button[] levelOptionButtons = new Button[Config.NUMBEROFLEVELS];
	
	private UnicodeFont font;
	private Image background;
	private Button mainMenuButton;
	
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
		for (int i = 0; i < levelOptionButtons.length; i++) {
			levelOptionButtons[i] = new Button(Config.LEVELSELECTBUTTON, 58 + 150 * (i % 5), 158 + 100 * (i / 5), 84, 84);
		}
		mainMenuButton = new Button(Config.BACKMAINMENUBUTTON, 250, 540, 300, 60);
		font = new UnicodeFont(Config.FONT, 32, false, false);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.WHITE));
		font.loadGlyphs();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setFont(font);
		background.draw(0, 0);
		mainMenuButton.draw();
		for (int i = 0; i < levelOptionButtons.length; i++) {
			levelOptionButtons[i].draw();
			g.drawString(String.valueOf(i + 1), (i >= 9 ? (i >= 19 ? 72 : 77) : 87) + 150 * (i % 5), 185 + 100 * (i / 5));
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (mainMenuButton.getClicked()) {
			sbg.enterState(DrTetris.MAIN_MENU);
			mainMenuButton.setClicked(false);
		}
		
		for (int i = 0; i < levelOptionButtons.length; i++) {
			if (levelOptionButtons[i].getClicked()) {
				((ChallengeMode) sbg.getState(DrTetris.CHALLENGE_MODE)).setLevel(i);
				sbg.enterState(DrTetris.CHALLENGE_MODE);
				levelOptionButtons[i].setClicked(false);
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
	public void mousePressed(int button, int x, int y) {
		mainMenuButton.mousePressed(button, x, y);
		for (Button levelOptionButton : levelOptionButtons) {
			levelOptionButton.mousePressed(button, x, y);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		mainMenuButton.mouseReleased(button, x, y);
		for (Button levelOptionButton : levelOptionButtons) {
			levelOptionButton.mouseReleased(button, x, y);
		}
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		mainMenuButton.mouseMoved(oldX, oldY, newX, newY);
		for (Button levelOptionButton : levelOptionButtons) {
			levelOptionButton.mouseMoved(oldX, oldY, newX, newY);
		}
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		mainMenuButton.mouseDragged(oldX, oldY, newX, newY);
		for (Button levelOptionButton : levelOptionButtons) {
			levelOptionButton.mousePressed(newY, id, id);
		}
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
