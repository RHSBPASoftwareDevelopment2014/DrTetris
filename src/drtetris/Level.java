package drtetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Level {

	private String name;
	private String[] blocks;
	private Image background;
	private Field field;
	private final Random random;

	//Decides the name, background, and spawning blocks of a level by reading the level.config file

	public Level(String local, boolean isField) throws SlickException, IOException {
		random = new Random();
		File dir = new File(Config.LEVELDIRECTORY + local);
		if (!dir.isDirectory()) {
			throw new FileNotFoundException();
		}
		field = isField ? new Field(MapReader.getMapFromFile(Config.LEVELDIRECTORY, local + "/field")) : new Field(new Tile[Config.FIELDHEIGHT][Config.FIELDWIDTH]);
		Scanner scanner = new Scanner(new File(dir.getAbsolutePath() + "/level.config"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] keyValue = line.split(":");
			if (keyValue[0].toLowerCase().equals("name")) {
				name = keyValue[1];
			} else if (keyValue[0].toLowerCase().equals("blocks")) {
				blocks = keyValue[1].split(",");
			} else if (keyValue[0].toLowerCase().equals("gamebackground")) {
				background = new Image("res/" + keyValue[1]);
			}
		}
	}

	public Block nextBlock() {
		try {
			return new Block(blocks[random.nextInt(blocks.length)]);
		} catch (IOException ex) {
			return null;
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public Image getBackground() {
		return background;
	}

	public Field getField() {
		return field;
	}
}
