package drtetris;

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
}
