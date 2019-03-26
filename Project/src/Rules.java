//this class allows for a user to click a button and read the rules of the game
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Rules extends Application{
    String dirName = "src/rules.txt";
    private BorderPane bp = new BorderPane();
    String fileContents;
    TextArea name = new TextArea();

    public void start(Stage stage){
        Menu menu = new Menu();

        name.setMinHeight(550);
        name.setWrapText(true);

        Button back = new Button("Back");
        back.setOnAction(event -> {
            menu.start(stage);
        });

        try {
            getRules();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText(fileContents);
        bp.setTop(name);
        bp.setBottom(back);
        Scene scene = new Scene(bp, 400, 600);
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }

    public void getRules() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dirName));
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContents = stringBuilder.toString();
        } finally {
            br.close();
        }

    }

    public static void main(String[] args){
        Application.launch();
    }
}
