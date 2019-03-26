import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
//class to collect username
public class Create {
    Scene createScene;
    public void start(Stage stage) throws IOException {
        Login login = new Login();

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(10);
        gp.setHgap(10);
        Label un = new Label("Username: ");
        Label psw = new Label("Password: ");
        Label psw2 = new Label("Re-Password:");
        TextField userName = new TextField();
        userName.setPrefWidth(400);
        TextField passWord = new TextField();
        passWord.setPrefWidth(400);
        TextField passWord2 = new TextField();
        passWord.setPrefWidth(400);
        Button createUser = new Button("Create User");
        Button back = new Button("Back");

        gp.add(un, 0, 1);
        gp.add(userName, 1, 1);
        gp.add(psw, 0, 2);
        gp.add(passWord, 1, 2);
        gp.add(psw2, 0, 3);
        gp.add(passWord2, 1, 3);
        gp.add(createUser, 1, 4);
        gp.add(back, 1,5);

        ObservableList<String> usernameList = login.getUserNameList();

        createUser.setOnAction(event -> {
            boolean usnm = false;
            System.out.println(usernameList.size());

            for (int i = 0; i < usernameList.size(); i++) {
                if (userName.getText().equals(usernameList.get(i))) {
                    userName.setText("Username exists already");
                    usnm = true;
                    break;
                }
            }
            if (!usnm) {
                if (passWord.getText().equals(passWord2.getText())) {
                    append(userName.getText(),passWord.getText());
                    login.start(stage);
                } else {
                    passWord2.setText("Does not match with passwords 1");
                }
            }
        });

        back.setOnAction(event -> {
            login.start(stage);
        });

        createScene = new Scene(gp, 575, 260);
        createScene.getStylesheets().add("stylesheet.css");
        stage.setScene(createScene);
        stage.setResizable(false);
        stage.show();
    }
    //put collected info into file
    //this is to show the user options of logins later

    private void append(String username, String password){
        try {
            FileWriter pw = new FileWriter("src/users.csv",true);
            pw.append(username);
            pw.append(",");
            pw.append(password);
            pw.append("0");
            pw.append(",");
            pw.append("0");
            pw.append("\n");
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
