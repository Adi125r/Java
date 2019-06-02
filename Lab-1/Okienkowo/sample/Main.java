package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private static Locale locale = new Locale("pl", "PL");
    private Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("plecak.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("sample.bundles.jezyk", locale);
        fxmlLoader.setResources(bundle);

        Parent root = fxmlLoader.load();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(bundle.getString("Title.app"));
        stage.show();
    }
    public void reload() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("plecak.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("sample.bundles.jezyk", locale);
        fxmlLoader.setResources(bundle);
        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);

    }
    public static void setLocale(Locale locale1){
        locale = locale1;
    }




    public static void main(String[] args) {
        launch(args);
    }
}
