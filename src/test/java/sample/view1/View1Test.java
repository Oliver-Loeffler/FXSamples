package sample.view1;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;
import org.testfx.service.support.Capture;
import sample.Main;
import sample.ViewFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.fail;
import static sample.view1.View1Model.Operation.*;

public class View1Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calculator");
        Scene scene = new Scene(ViewFactory.createView1());
        scene.getStylesheets().add(Main.class.getResource("Calculator.css").toExternalForm());
        stage.setScene(scene);
        Thread.currentThread().setUncaughtExceptionHandler((t,e)->errorInUi.set(e));
        stage.show();
    }

    @Test
    public void resultFieldExists() {
        Assert.assertNotNull( lookup("#result").query() );
    }

    @Test
    public void resultFieldIsNotEditable() {
        TextField put = lookup("#result").query();
        Assert.assertFalse(put.isEditable());
    }

    @Test
    public void performAddition() {
        new View1Object(this).calculateAndAssert(0, ADD, 0);
    }

    @Test
    public void performAdditionOfLargeNumbers() throws IOException {
        new View1Object(this).calculateAndAssert(12340, ADD, 1);
    }

    @Test
    public void viewIsCreatable() throws IOException {
        Parent put = lookup("#rootpane").query();
        BufferedImage bImage = SwingFXUtils.fromFXImage(capture(put).getImage(), null);
        ImageIO.write(bImage, "png", new File("sceneRoot.png") );
   }


    @Test
    public void performSubtractionOfLargeNumbers() throws IOException {
        new View1Object(this).calculateAndAssert(12340, SUB, 1);

    }

    @Test
    public void performMultiplyOfLargeNumbers() throws IOException {
        new View1Object(this).calculateAndAssert(12340, MUL, 0);

    }

    @Test
    public void performDivisionOfLargeNumbers() throws IOException {
        new View1Object(this)
                .enterOperandValue(12340)
                .enterOperation(DIV)
                .enterOperandValue(1)
                .calculateResult()
                .assertResult(12340);
    }

    @Test
    public void performDivisionOfNotSoLargeNumbers() throws IOException {
        new View1Object(this)
                .enterOperandValue(999)
                .enterOperation(DIV)
                .enterOperandValue(333)
                .calculateResult()
                .assertResult(3);
    }

    @Test
    public void performMissingNumberInputs() {
        new View1Object(this)
                .calculateAndAssert(856, ADD, 14 );
    }

    @Test(expected = ArithmeticException.class)
    public void divisionByZero() {
        new View1Object(this)
                .calculateAndAssert(10, DIV, 0 );
    }

    @Test
    public void performEndlessResets() {
        new View1Object(this)
                .clear()
                .assertResultCleared().clear()
                .assertResultCleared().clear()
                .assertResultCleared().clear()
                .assertResultCleared().clear()
                .assertResultCleared().clear()
                .assertResultCleared().clear()
                .assertResultCleared();

    }

    @Test
    public void multipleCalculationsWithClearInbetween() {

        new View1Object(this)
                .calculateAndAssert(24, DIV, 2)
                .clear()
                .assertResultCleared()
                .calculateAndAssert(10, SUB, 7)
                .clear()
                .assertResultCleared();

        new View1Object(this)
                .enterOperandValue(2)
                .enterOperandValue(4)
                .enterOperation(DIV)
                .enterOperandValue(2)
                .calculateResult()
                .assertResult(12)
                .clear()
                .assertResultCleared()
                .enterOperandValue(10)
                .enterOperation(SUB)
                .enterOperandValue(7)
                .calculateResult()
                .assertResult(3)
                .clear()
                .assertResultCleared();
    }

    public void arithmeticExceptionInCaseOfUnsupportedOperation() {
        new View1Object(this).calculateResult();
    }

    public static AtomicReference<Throwable> errorInUi = new AtomicReference<>(null);

    @Before
    public void resetUiError() {
        errorInUi.set(null);
    }

    @After
    public void checkUiExceptions() throws Throwable {
        if (errorInUi.get() != null) {
            fail(errorInUi.get().getMessage());
        }
    }
}
