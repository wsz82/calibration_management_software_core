package javafx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStarter extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        var mainApplication = new MainApplication();
        mainApplication.show(stage);
    }
}