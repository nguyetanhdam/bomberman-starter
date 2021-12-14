package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.bomberman.BombermanGame;
import main.bomberman.board.BoardGame;
import main.bomberman.graphics.Properties;
import main.bomberman.sound.Sound;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreView implements Initializable {
    private static final String url = "jdbc:mysql://localhost:3307/bomberman";

    private static Connection con;
    private static Statement statement;

    @FXML
    WebView listRank;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String s = getListRank();
        listRank.getEngine().loadContent(s);
    }

    private String getListRank(){
        String res = "";
        try {
            con = DriverManager.getConnection(url, "root", "");
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM rank GROUP BY name ORDER BY score DESC LIMIT 5\n");
            ArrayList<String> lName = new ArrayList<>();
            ArrayList<String> lScore = new ArrayList<>();
            while(rs.next()){
                lName.add(rs.getString(1));
                lScore.add(rs.getString(2));
            }
            res = creatListRankHtml(lName, lScore);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    private String creatListRankHtml(ArrayList<String> lName, ArrayList<String> lScore){
        StringBuilder res = new StringBuilder();
        res.append("<html lang=\"en\">");
        res.append("<body><table border=\"1\" width = \"582\" height = \"410\"><tr height = \"38\">");
        res.append("<th ><p style=\"font-size: 20px \">Name</p></th>");
        res.append("<th ><p style=\"font-size: 20px \">Score</p></th>");
        res.append("</tr><tr><th>");
        for(String name : lName){
            res.append("<p style=\"font-size: 20px \">" + name + "</p>");
        }
        res.append("</p></th><th>");
        for(String score : lScore){
            res.append("<p style=\"font-size: 20px \">" + score + "</p>");
        }
        res.append("</th></tr></table></body></html>");

        return res.toString();
    }

    public static void saveScore(){
        try {
            String addquery = "INSERT INTO `rank` (`name`, `score`) VALUES ('" + Properties.getName()
                    + "', '" + BoardGame.getScore() + "');";
            con = DriverManager.getConnection(url, "root", "");
            statement = con.createStatement();
            statement.executeUpdate(addquery);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void exit(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("first-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
