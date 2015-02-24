package drtetris;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Button {

	public static final int UNSELECTED = 0, SELECTED = 1, CLICKED = 2;

	private final SpriteSheet sheet;

	private int state = 0;
	private boolean click = false;
	private int x, y;

	public Button(String image, int x, int y, int width, int height) throws SlickException {
		sheet = new SpriteSheet(new Image(image), width, height);
		this.x = x;
		this.y = y;
	}

	//Chooses what the button looks like

	public void draw() {
		if (click) {
			sheet.getSprite(2, 0).draw(x, y);
		} else {
			switch (state) {

				case SELECTED:
					sheet.getSprite(1, 0).draw(x, y);
					break;

				case CLICKED:
					sheet.getSprite(2, 0).draw(x, y);
					break;
				default:
					sheet.getSprite(0, 0).draw(x, y);
			}
		}
	}

	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		draw();
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setClicked(boolean click) {
		this.click = click;
	}

	public boolean getClicked() {
		return click;
	}

	public boolean isFocusRegion(int x, int y) {
		return (x >= this.x && x < (this.x + sheet.getSprite(0, 0).getWidth())) && (y >= this.y && y < (this.y + sheet.getSprite(0, 0).getHeight()));
	}

	public void mousePressed(int button, int x, int y) {
		if (isFocusRegion(x, y)) {
			setState(CLICKED);
		}
	}

	public void mouseReleased(int button, int x, int y) {
		if (isFocusRegion(x, y)) {
			setState(SELECTED);
			setClicked(true);
		} else {
			setState(UNSELECTED);
		}
	}

	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		if (isFocusRegion(newX, newY)) {
			setState(SELECTED);
		} else {
			setState(UNSELECTED);
		}
	}

	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		if (isFocusRegion(newX, newY)) {
			setState(CLICKED);
		} else {
			setState(UNSELECTED);
		}
	}
}
