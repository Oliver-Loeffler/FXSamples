package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.ViewFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ApplicationViewTest extends ApplicationTestTemplate {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("TestFX");
        Scene scene = new Scene(ViewFactory.createApplicationView(stage));
        scene.getStylesheets().add(Main.class.getResource("AppSample.css").toExternalForm());
        stage.setScene(scene);
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> errorInUi.set(e));
        stage.show();
    }

    @Test
    public void createScreenShotOfApplicationView() throws IOException {
        Parent put = lookup("#applicationpane").query();
        BufferedImage bImage = SwingFXUtils.fromFXImage(capture(put).getImage(), null);
        ImageIO.write(bImage, "png", new File("ApplicationScreen.png"));
    }
}
