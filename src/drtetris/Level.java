
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
    private Random random;
    
    public Level(String local) throws SlickException, IOException {
	random = new Random();
        File dir = new File(Config.LEVELDIRECTORY + local);
        if (!dir.isDirectory()) {
            throw new FileNotFoundException();
        }
	field = new Field(MapReader.getMapFromFile(Config.LEVELDIRECTORY, local + "/field"));
	Scanner scanner = new Scanner(new File(dir.getAbsolutePath() + "/level.config"));
	while(scanner.hasNextLine()) {
		String line = scanner.nextLine();
		String[] keyValue = line.split(":");
		switch (keyValue[0].toLowerCase()) {
			case "name":
				name = keyValue[1];
				break;
			case "blocks":
				blocks = keyValue[1].split(",");
				break;
			case "gamebackground":
				background = new Image("res/" + keyValue[1]);
				break;
		}
	}
    }
    
    public Block nextBlock() {
	    try {
		return new Block(blocks[random.nextInt(blocks.length)]);
	    } catch (IOException | NumberFormatException ex) {
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
