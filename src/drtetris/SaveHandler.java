package drtetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveHandler {

	private File saveFile;
	private Scanner reader;
	private PrintWriter writer;
	private int challengeLevel = 0, infiniteLevel = 0, difficulty = Config.MEDIUM, volume = 100;

	public SaveHandler(String saveFile) throws IOException {
		this.saveFile = new File(saveFile);
		this.saveFile.createNewFile();
	}

	public void save(int challengeLevel, int infiniteLevel, int difficulty, int volume) throws FileNotFoundException {
		this.challengeLevel = challengeLevel;
		this.infiniteLevel = infiniteLevel;
		this.difficulty = difficulty;
		this.volume = volume;
		writer = new PrintWriter(saveFile);
		writer.write("Challenge Level:" + challengeLevel + "\nInfinite Level:" + infiniteLevel + "\nDifficulty:" + difficulty + "\nVolume:" + volume);
		writer.flush();
	}

	public void load() throws FileNotFoundException {
		reader = new Scanner(saveFile);
		while (reader.hasNextLine()) {
			String[] keyValue = reader.nextLine().split(":");
			if (keyValue[0].toLowerCase().equals("challenge level")) {
				challengeLevel = Integer.parseInt(keyValue[1]);
			} else if (keyValue[0].toLowerCase().equals("infinite level")) {
				infiniteLevel = Integer.parseInt(keyValue[1]);
			} else if (keyValue[0].toLowerCase().equals("difficulty")) {
				difficulty = Integer.parseInt(keyValue[1]);
			} else if (keyValue[0].toLowerCase().equals("volume")) {
				volume = Integer.parseInt(keyValue[1]);
			}
		}
		System.out.println("Difficulty: " + difficulty);
	}

	public int getChallengeLevel() {
		return challengeLevel;
	}

	public int getInfiniteLevel() {
		return infiniteLevel;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getVolume() {
		return volume;
	}
}
