package drtetris;

//This class is used to find which links should be used by the state and how it is rotated by the rotation
public class TileConstruct {

	private int state;
	private float rotation;

	public TileConstruct(int state, float rotation) {
		this.state = state;
		this.rotation = rotation;
	}

	public int getState() {
		return state;
	}

	public float getRotation() {
		return rotation;
	}
}
