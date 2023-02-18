package javafx;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

public class MainApplicationTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        var mainApplication = new MainApplication();
        mainApplication.show(stage);
    }

    @Test
    public void test_should_contain_button_with_text() {
        FxAssert.verifyThat("#welcomeButton", LabeledMatchers.hasText("Hello!"));
    }

    @Test
    public void test_when_button_is_clicked_text_changes() {
        clickOn("#welcomeButton");
        FxAssert.verifyThat("#welcomeText", LabeledMatchers.hasText("clicked!"));
    }
}