package main.bomberman.graphics;

import java.io.File;
import java.net.MalformedURLException;

public class GetImage {
    public static String get(String name) throws MalformedURLException {
        File file = new File(".\\res\\" + name);
        return file.toURI().toURL().toString();
    }
}
