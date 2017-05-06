package flipTracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main class
 */
public class Main extends Application {

    /**
     * Start the program
     *
     * @param primaryStage the stage
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("/images/Money-icon256.png"));
        String version = "V.17.5.6";
        primaryStage.setTitle("FlipTracker " + version);
        FlipTracker flipTracker = new FlipTracker();
        primaryStage.setScene(new Scene(flipTracker.getUI(), 270, 400));
        primaryStage.show();
        primaryStage.getScene().getWindow().setOnCloseRequest(e -> flipTracker.closeRequested());
        primaryStage.setMinWidth(270);
    }

    public static void main(String[] args) {
        Main.launch((String[]) args);
    }
}

