package main.bomberman.level;

import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Grass;
import main.bomberman.entities.tile.Wall;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadLevel {
    private static int _width, _height, _level;
    private static char[][] _map;

    private static ArrayList<Entity> map = new ArrayList<>();

    public LoadLevel(){

    }

    public static void load(int level) {
        String path = ".\\res\\levels\\Level"+level+".txt";
        try{
            File file = new File(path);
            Scanner reader = new Scanner(file);

            _level = reader.nextInt();
            _height = reader.nextInt();
            _width = reader.nextInt();
            reader.nextLine();
            System.out.println(_level + " " + _height + " " + _width);
            _map = new char[_height][_width];
            for (int i = 0; i < _height; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < _width; j++){
                    _map[i][j] = line.charAt(j);
                    System.out.print(_map[i][j]);
                }
                System.out.println("");
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static char[][] get_map() {
        return _map;
    }

    public static int get_width(){
        return _width;
    }

    public static int get_height(){
        return _height;
    }

    public static ArrayList<Entity> getMap(int lv){
        load(lv);
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++){
                switch (_map[i][j]){
                    case '#':
                        Wall wall = new Wall();
                        wall.setPosition(j*wall.getWidth(), i*wall.getHeight());
                        map.add(wall);
                        break;
                    case '*':
                        Brick brick = new Brick();
                        brick.setPosition(j*brick.getWidth(), i*brick.getHeight());
                        map.add(brick);
                        break;
                    case 's':
                        Brick speedItem = new Brick("speed");
                        speedItem.setPosition(j*speedItem.getWidth(), i*speedItem.getHeight());
                        map.add(speedItem);
                        break;
                    case 'f':
                        Brick flamesItem = new Brick("flames");
                        flamesItem.setPosition(j*flamesItem.getWidth(), i*flamesItem.getHeight());
                        map.add(flamesItem);
                        break;
                    case 'b':
                        Brick bombItem = new Brick("bomb");
                        bombItem.setPosition(j*bombItem.getWidth(), i*bombItem.getHeight());
                        map.add(bombItem);
                        break;
                    default:
                        Grass grass = new Grass();
                        grass.setPosition(j*grass.getWidth(), i*grass.getHeight());
                        map.add(grass);
                        break;
                }
            }
        }
        return map;
    }
}
