package drtetris;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DrTetris extends StateBasedGame {

    public static final int MAIN_MENU = 0, GAME = 1;
    
    public DrTetris() {
        super("Dr Tetris");
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
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MainMenu(MAIN_MENU));
        addState(new Game(GAME));
    }
}
