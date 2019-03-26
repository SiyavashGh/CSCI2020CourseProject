import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// class to read/wrie and display the highscores
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Highscores extends Application{
    private final ObservableList<Person> data =
            FXCollections.observableArrayList();
    TableView tableView = new TableView();
    public void start(Stage stage) throws IOException {
        BorderPane bp = new BorderPane();

        load();

        TableColumn userNameCol = new TableColumn("username");
        userNameCol.setMinWidth(100);
        TableColumn gamesPlayedCol = new TableColumn("Games Played");
        gamesPlayedCol.setMinWidth(200);
        TableColumn winsCol = new TableColumn("Wins");
        winsCol.setMinWidth(100);

        userNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("username"));
        gamesPlayedCol.setCellValueFactory(new PropertyValueFactory<Person, String>("gamesPlayed"));
        winsCol.setCellValueFactory(new PropertyValueFactory<Person, String>("wins"));

        tableView.setItems(data);
        tableView.getColumns().addAll(userNameCol, gamesPlayedCol, winsCol);
        bp.setCenter(tableView);
        Scene scene = new Scene(bp, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void load() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values[0] + "    " + values[2] + "   " + values[3]);
                data.add(new Person(values[0], values[2], values[3]));
            }
        }
    }

    public static class Person {

        private final SimpleStringProperty username;
        private final SimpleStringProperty gamesPlayed;
        private final SimpleStringProperty wins;

        private Person(String un, String gp, String w) {
            this.username = new SimpleStringProperty(un);
            this.gamesPlayed = new SimpleStringProperty(gp);
            this.wins = new SimpleStringProperty(w);
        }


        public String getUserName() {
            return username.get();
        }

        public String getGamesPlayed() {
            return gamesPlayed.get();
        }
        public String getWins() {
            return wins.get();
        }
    }



    public static void main(String[] args){
        Application.launch(args);
    }

}
