package main.bomberman.graphics;

import java.io.File;
import java.net.MalformedURLException;

public class GetImage {

    /**
     * lấy ra đường dẫn đầy đủ của file
     * */
    public static String get(String name){
        try {
            File file = new File(".\\res\\" + name);
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
