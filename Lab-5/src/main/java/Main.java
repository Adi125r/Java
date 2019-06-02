import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {



    private Scene scene;
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));
        Parent root = fxmlLoader.load();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Zajecia 5");
        stage.show();
    }
    public void loadU() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));

        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);

    }
    public void loadV() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("value.fxml"));

        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);

    }
    public void loadW() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tes.fxml"));

        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);


    }

    public void window() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));

        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);
    }
    public void kom() throws IOException {
        Stage tip = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("komunikat.fxml"));
        tip.setScene(new Scene(root));
        tip.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
