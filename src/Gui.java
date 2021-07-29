import automat.GeschäftslogikImpl;
import automat.KuchenBoden;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/sample.fxml"));
        primaryStage.setTitle("Kuchenautomat");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
