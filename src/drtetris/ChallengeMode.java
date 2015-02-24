package drtetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ChallengeMode extends InfiniteMode {

	public ChallengeMode(int id) {
		super(id);
		challenge = true;
	}

	@Override
	protected int nextLevel() {
		levelCount = getLevel() + 1;
		highestLevel = Math.max(highestLevel, levelCount);
		return highestLevel;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (getLevel() >= Config.NUMBEROFLEVELS - 1) {
			sbg.enterState(DrTetris.WIN);
		}
	}
}
