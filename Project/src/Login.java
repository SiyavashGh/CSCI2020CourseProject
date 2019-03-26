//main class to run the project takes passwords verify and accepts

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;


public class Login extends Application {
    Create create = new Create();
    Menu menu = new Menu();
    Scene loginScene;
    String[][] records = new String[50][2];
    ObservableList<String> userNamesList = FXCollections.observableArrayList();
    ObservableList<String> passwordsList = FXCollections.observableArrayList();
    private String playerUserName;


    @Override
    public void start(Stage stage){
        Pane p = new Pane();
        p.setPadding(new Insets(10, 10, 10, 10));

        Label un = new Label("User name: ");
        ChoiceBox userName = new ChoiceBox();
        userName.setPrefWidth(455);
        Label psw = new Label("Password: ");
        TextField passWord = new TextField();
        passWord.setPrefWidth(455);
        Button login = new Button("Login");
        Button createUser = new Button("Create User");
        Button exit = new Button("Exit");

        un.setLayoutX(14);
        un.setLayoutY(91);
        userName.setLayoutX(97);
        userName.setLayoutY(91);

        psw.setLayoutX(14);
        psw.setLayoutY(133);
        passWord.setLayoutX(97);
        passWord.setLayoutY(133);

        login.setLayoutX(260);
        login.setLayoutY(170);
        createUser.setLayoutX(240);
        createUser.setLayoutY(200);
        exit.setLayoutX(265);
        exit.setLayoutY(230);


        try {
            load();

            userName.setItems(userNamesList);

        } catch (IOException e) {
            e.printStackTrace();
        }



        login.setOnAction(event -> {
            if(userName.getValue() == null){

            }else {
            System.out.println(passwordsList);
            int index = userNamesList.indexOf(userName.getValue());
                if (passWord.getText().equals(passwordsList.get(index))) {
                    playerUserName = userNamesList.get(index);
                    menu.start(stage);
                } else {
                    passWord.setText("WRONG PASSWORD");
                }

            }
        });

        createUser.setOnAction(event -> {
            //go over to create user
            try {
                create.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exit.setOnAction(event -> System.exit(0));

        p.getChildren().addAll(un, userName, psw, passWord, login, createUser, exit);

        loginScene = new Scene(p, 575, 260);
        loginScene.getStylesheets().add("stylesheet.css");
        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.show();

    }

    private void load() throws IOException {
        userNamesList.clear();
        passwordsList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                userNamesList.add(values[0]);
                passwordsList.add(values[1]);
            }
        }
    }

    public String getPlayerUserName(){ return playerUserName; }
    public ObservableList<String> getUserNameList() throws IOException {
        load();
        return userNamesList;
    }
    public static void main(String[] args){ Application.launch(args);}
}
