package net.raumzeitfalle.fxsamples.pocketcalculator;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import net.raumzeitfalle.fxsamples.pocketcalculator.Main;
import net.raumzeitfalle.fxsamples.pocketcalculator.ViewFactory;

import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;

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
    public void captureBigDecimalCalculator() throws IOException {
        Parent put = lookup("#applicationpane").query();
        clickOn("#BigDecimalCalculator", MouseButton.PRIMARY);
        captureImage(put, "ApplicationScreen_BigDecimalCalculator.png");
    }
    
    @Test
    public void captureSimpleCalculator() throws IOException {
        Parent put = lookup("#applicationpane").query();
        clickOn("#SimpleCalculator", MouseButton.PRIMARY);
        captureImage(put, "ApplicationScreen_SimpleCalculator.png");
    }
    
    @Test
    public void applicationMustHave2Tabs() {
    		TabPane tabPane = (TabPane) lookup("#modules").query();
    		assertEquals(2, tabPane.getTabs().size());
    }
    
    @Test
    public void captureRearrangedTabs() throws IOException {
    		Parent put = lookup("#applicationpane").query();
        TabPane tabPane = (TabPane) lookup("#modules").query();
        
        tabPane.setSide(Side.LEFT);
        captureImage(put,"ApplicationScreen_TabsLeft.png");
        
        tabPane.setSide(Side.BOTTOM);
        captureImage(put,"ApplicationScreen_TabsBottom.png");
    }

	private void captureImage(Parent put, String filename) throws IOException {
		BufferedImage bImage = SwingFXUtils.fromFXImage(capture(put).getImage(), null);
        ImageIO.write(bImage, "png", new File(filename));
	}
}
