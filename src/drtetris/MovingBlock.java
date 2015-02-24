package drtetris;

public class MovingBlock extends Block {

	private int rotation;
	private int x;
	private double y;

	private int xOffset = 0;

	public MovingBlock(Tile[][] map, int rotation, int x, double y) {
		super(map);
		this.rotation = rotation;
		this.x = x;
		this.y = y;
	}

	public MovingBlock(Block block, int rotation, int x, double y) {
		this(block.getMap(), rotation, x, y);
	}

	private int realX() {
		return x + xOffset;
	}

	@Override
	public Tile[][] getMap() {
		return super.getMap(rotation);
	}

	@Override
	public void draw() {
		super.draw(realX() * Config.BLOCKSIZE, (int) y);
	}

	@Override
	public void draw(int startX, int startY) {
		super.draw(realX() * Config.BLOCKSIZE + startX, (int) y + startY, rotation);
	}

	private void offset() {
		int width = getHeight();
		int height = getWidth();
		switch (trimRotation(rotation)) {
			case TileMap.ROTATELEFT:
				xOffset = width >= height ? -(width - 1) / 2 : (height - 1) / 2;
				break;

			case TileMap.ROTATERIGHT:
				xOffset = width >= height ? -(width - 1) / 2 : (height - 1) / 2;
				break;

			default:
				xOffset = 0;
		}
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		offset();
	}

	public void modRotation(int rotation) {
		this.rotation += rotation;
		offset();
	}

	public int getRotation() {
		return rotation;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void modX(int x) {
		this.x += x;
	}

	public int getX() {
		return realX();
	}

	public void setY(double y) {
		this.y = y;
	}

	public void modY(double y) {
		this.y += y;
	}

	public double getY() {
		return y;
	}
}
