//class for the main menu includes buttons and respective properties

import Handler.Controller;
import Handler.MyMouseListener;
import Model.Player;
import View.BoardPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Menu extends JFrame{
    Scene menuScene;
    ClientMain clientMain = new ClientMain();


    private BorderPane bp = new BorderPane();

    public void start(Stage stage){
        ServerMain serverMain = new ServerMain();
        Login login = new Login();
        Rules rulesScene = new Rules();
        String name = login.getPlayerUserName();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
        vBox.alignmentProperty().setValue(Pos.CENTER);

        Button joinGame = new Button("Join Game");
        Button highScores = new Button("High Scores");
        Button rules = new Button("Rules");
        Button exit = new Button("Exit");

        vBox.getChildren().addAll(joinGame, highScores, rules, exit);
        bp.setCenter(vBox);

        joinGame.setOnAction(event -> {
            clientMain.main(new String[0]);
            stage.hide();

        });
        rules.setOnAction(event -> {
            rulesScene.start(stage);
        });

        exit.setOnAction(event -> {
            System.exit(0);
        });
        menuScene = new Scene(bp, 250, 300);
        menuScene.getStylesheets().add("stylesheet.css");
        stage.setScene(menuScene);
        stage.setResizable(false);
        stage.show();
    }

}

