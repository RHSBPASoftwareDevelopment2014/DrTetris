package drtetris;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class DrTetris extends StateBasedGame {

    public static final int MAIN_MENU = 1, INFINITE_MODE = 4, CHALLENGE_MODE = 3, ERR_REPORT = 7, OPTIONS = 5, LEVEL_SELECTION = 2, CREDITS = 0, TUTORIAL = 6;
    
    public DrTetris() {
        super("Siranga");
    }
    
    public static void main(String[] args) {
        
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.libarypath", System.getProperty("org.lwjgl.librarypath"));
        
        try {
            AppGameContainer app = new AppGameContainer(new DrTetris());
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(60);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            Log.error(e);
        }
    }
    //Creates the multiple gamestates
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.addState(new Credits(CREDITS));
        this.addState(new MainMenu(MAIN_MENU));
	this.addState(new LevelSelection(LEVEL_SELECTION));
	this.addState(new ChallengeMode(CHALLENGE_MODE));
        this.addState(new InfiniteMode(INFINITE_MODE));
	this.addState(new Options(OPTIONS, MAIN_MENU));
	this.addState(new Tutorial(TUTORIAL));
    }
}
