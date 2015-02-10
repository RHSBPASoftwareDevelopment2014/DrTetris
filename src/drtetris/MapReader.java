
package drtetris;

import com.csvreader.CsvReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class MapReader {
    
    public static Tile[][] getMapFromFile(String dir, String name) throws IOException, NumberFormatException {
        CsvReader reader = new CsvReader(dir + name + ".csv");
        
        List<String[]> records = new ArrayList<>();
        
        while(reader.readRecord()) {
            String[] record = new String[reader.getColumnCount()];
            for (int i = 0; i < record.length; i++) {
                record[i] = reader.get(i);
            }
            records.add(record);
        }
        
        if (records.size() > 0) {
            String uuid = UUID.randomUUID().toString();
            Tile[][] map = new Tile[records.size()][records.get(0).length];
            for (int i = 0; i < records.size(); i++) {
                for (int j = 0; j < records.get(i).length; j++) {
                    try {
                        int id = Integer.parseInt(records.get(i)[j]);
                        switch (Config.TILETYPELIST[id]) {
                            case Config.TILETYPENORMAL:
                                map[i][j] = new LinkedTile(id, uuid);
                                break;
                                
                            case Config.TILETYPETUNNEL:
                                map[i][j] = new Tunnel(id);
                                break;
                        }
                    } catch (SlickException | ArrayIndexOutOfBoundsException ex) {}
                }
            }
            return map;
        } else {
            throw new IOException();
        }
    }
}
