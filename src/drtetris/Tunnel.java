package drtetris;

import java.util.UUID;

public class Tunnel extends LinkedTile {

	public Tunnel(int index) {
		super(index, 4, true, false, "Tunnel");
	}

	@Override
	public String getBlockId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void draw(int x, int y) {
		super.draw(LinkedTile.STRAIGHT, 0, x, y, 90F);
	}

	public void draw(int state, int x, int y, float rotation) {
		super.draw(state, 0, x, y, rotation);
	}

	@Override
	public void draw(boolean[] surroundings, int x, int y) {
		TileConstruct construct = LinkedTile.TUNNELCONSTRUCTS[surroundings[0] ? 1 : 0][surroundings[1] ? 1 : 0][surroundings[2] ? 1 : 0][surroundings[3] ? 1 : 0];
		super.draw(construct.getState(), 0, x, y, construct.getRotation());
	}
}
