package uet.oop.bomberman.map;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.enemy.Balloon;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.item.BombItem;
import uet.oop.bomberman.item.FlameItem;
import uet.oop.bomberman.item.SpeedItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private static Entity[][] mapObject;

    private static String mapLv1 = "res/levels/Level1.txt";

    public static void render(GraphicsContext gc) {
        for(int i = 0;i<mapObject.length;i++) {
            for(int j = 0;j<mapObject[i].length;j++) {
                mapObject[i][j].render(gc);
            }
        }
    }



    public static void createMap(int level) {
        File file = null;
        try {
            if(level == 1) file = new File(mapLv1);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String firstLine = br.readLine();
            String[] mapData = firstLine.split(" ");
            int lv = Integer.valueOf(mapData[0]);
            int height = Integer.valueOf(mapData[1]);
            int width = Integer.valueOf(mapData[2]);

            mapObject = new Entity[height][width];

            for(int i = 0; i < height; i++) {
                String line = br.readLine();
                for(int j = 0; j<width; j++) {
                    mapObject[i][j] = createObject(line.charAt(j),j,i);
                }
            }
            br.close();
            fr.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't load map file!");
            System.exit(0);
        }

    }

    private static Entity createObject(Character sign, int width, int height) {
        switch (sign) {
            case '#':
                return new Wall(width,height);
            case '*':
                return new Brick(width,height);
            case 'x':
                return new Portal(width,height);
            case 'p':
                return new Bomber(width,height);
            case '1':
                return new Balloon(width,height);
            case '2':
                return new Oneal(width,height);
            case 'b':
                return new BombItem(width,height);
            case 'f':
                return new FlameItem(width,height);
            case 's':
                return new SpeedItem(width,height);
            default:
                return new Grass(width,height);
        }

    }

}
