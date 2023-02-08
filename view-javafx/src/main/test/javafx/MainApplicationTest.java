package javafx;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;


@ExtendWith(ApplicationExtension.class)
class MainApplicationTest {

    @Start
    private void start(Stage stage) throws IOException {
        var mainApplication = new MainApplication();
        mainApplication.show(stage);
    }

    @Test
    void should_contain_button_with_text(FxRobot robot) {
        FxAssert.verifyThat("#welcomeButton", LabeledMatchers.hasText("Hello!"));
    }

    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        robot.clickOn("#welcomeButton");
        FxAssert.verifyThat("#welcomeText", LabeledMatchers.hasText("clicked!"));
    }
}