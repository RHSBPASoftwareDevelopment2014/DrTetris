package drtetris;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DrTetris extends BasicGame {

    public DrTetris() {
        super("Dr Tetris");
    }
    
    public static void main(String[] args) {
        
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.libarypath", System.getProperty("org.lwjgl.librarypath"));
        
        try {
            AppGameContainer app = new AppGameContainer(new DrTetris());
            app.setDisplayMode(800, 600, false);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        
    }

}
