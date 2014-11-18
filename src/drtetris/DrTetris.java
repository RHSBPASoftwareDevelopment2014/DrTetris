package drtetris;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class DrTetris extends StateBasedGame {

    public static final int MAIN_MENU = 0, INFINITE_MODE = 1, ERR_REPORT = 2;
    
    public DrTetris() {
        super("Siranga");
    }
    
    public static void main(String[] args) {
        
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.libarypath", System.getProperty("org.lwjgl.librarypath"));
        
        try {
            AppGameContainer app = new AppGameContainer(new DrTetris());
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(120);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            Log.error(e);
        }
    }
    
    public static String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MainMenu(MAIN_MENU));
        addState(new InfiniteMode(INFINITE_MODE));
    }
}
