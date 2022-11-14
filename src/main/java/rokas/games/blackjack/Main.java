package rokas.games.blackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Parent root;
    @Override
    public void start(Stage gameStage) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("GameView.fxml"));
            gameStage.getIcons().add(new Image("file:src/main/resources/rokas/games/blackjack/Media/icons8-ace-of-diamonds-100.png"));
            Scene scene = new Scene(root);
            gameStage.setTitle("BlackJack");
            gameStage.setResizable(false);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            gameStage.setScene(scene);
            gameStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}


