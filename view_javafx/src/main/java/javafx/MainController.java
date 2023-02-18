package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button welcomeButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("clicked!");
    }
}