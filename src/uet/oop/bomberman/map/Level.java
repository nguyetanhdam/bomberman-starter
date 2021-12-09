package uet.oop.bomberman.map;

import uet.oop.bomberman.character.enemy.Balloon;
import uet.oop.bomberman.character.enemy.Oneal;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.TileEntities;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Grass;
import uet.oop.bomberman.tile_object.Portal;
import uet.oop.bomberman.tile_object.Wall;
import uet.oop.bomberman.tile_object.item.BombItem;
import uet.oop.bomberman.tile_object.item.FlameItem;
import uet.oop.bomberman.tile_object.item.SpeedItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
    private static int width;
    private static int height;
    private static int level;
    private static char[][] _map;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getLevel() {
        return level;
    }

    public static void loadLevel(int level) {
        String path = ".\\res\\levels\\Level" + level + ".txt";
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);

            level = reader.nextInt();
            height = reader.nextInt();
            width = reader.nextInt();
            reader.nextLine();

            _map = new char[height][width];
            for (int i = 0; i < height; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < width; j++) {
                    _map[i][j] = line.charAt(j);
                    System.out.print(_map[i][j]);
                }
                System.out.println();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createEntities() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char sign = _map[y][x];
                switch (sign) {
                    case '#' -> {
                        TileEntities t1 = new TileEntities(x, y);
                        t1.addEntity(new Wall(x, y));
                        GameData.createTileEntity(t1);
                    }
                    case '*' -> {
                        TileEntities t2 = new TileEntities(x, y);
                        t2.addEntity(new Grass(x, y));
                        t2.addEntity(new Brick(x, y));
                        GameData.createTileEntity(t2);
                    }
                    case 'x' -> {
                        TileEntities t3 = new TileEntities(x, y);
                        t3.addEntity(new Grass(x, y));
                        t3.addEntity(new Portal(x, y));
                        t3.addEntity(new Brick(x, y));
                        GameData.createTileEntity(t3);
                    }
                    case 'f' -> {
                        TileEntities t4 = new TileEntities(x, y);
                        t4.addEntity(new Grass(x, y));
                        t4.addEntity(new FlameItem(x, y));
                        t4.addEntity(new Brick(x, y));
                        GameData.createTileEntity(t4);
                    }
                    case 'b' -> {
                        TileEntities t5 = new TileEntities(x, y);
                        t5.addEntity(new Grass(x, y));
                        t5.addEntity(new BombItem(x, y));
                        t5.addEntity(new Brick(x, y));
                        GameData.createTileEntity(t5);
                    }
                    case 's' -> {
                        TileEntities t6 = new TileEntities(x, y);
                        t6.addEntity(new Grass(x, y));
                        t6.addEntity(new SpeedItem(x, y));
                        t6.addEntity(new Brick(x, y));
                        GameData.createTileEntity(t6);
                    }
                    case '1' -> {
                        TileEntities t7 = new TileEntities(x, y);
                        t7.addEntity(new Grass(x, y));
                        GameData.createTileEntity(t7);
                        GameData.addEnemy(new Balloon(x, y));
                    }
                    case '2' -> {
                        TileEntities t8 = new TileEntities(x, y);
                        t8.addEntity(new Grass(x, y));
                        GameData.createTileEntity(t8);
                        GameData.addEnemy(new Oneal(x, y));
                    }
                    case 'p' -> {
                        TileEntities t9 = new TileEntities(x, y);
                        t9.addEntity(new Grass(x, y));
                        GameData.createTileEntity(t9);
                        GameData.setPlayerLocation(x, y);
                    }
                    default -> {
                        TileEntities td = new TileEntities(x, y);
                        td.addEntity(new Grass(x, y));
                        GameData.createTileEntity(td);
                    }
                }
            }
        }
    }
}
