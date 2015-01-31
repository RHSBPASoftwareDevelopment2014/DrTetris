
package drtetris;

import java.io.File;
import java.io.FileNotFoundException;

public class Level {
    
    private String name;
    private File config;
    
    public Level(String name) throws FileNotFoundException {
        this.name = name;
        File dir = new File(Config.LEVELDIRECTORY + name);
        if (!dir.isDirectory()) {
            throw new FileNotFoundException();
        }
    }
}
