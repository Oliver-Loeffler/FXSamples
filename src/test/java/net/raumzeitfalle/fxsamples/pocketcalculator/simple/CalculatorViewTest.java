package net.raumzeitfalle.fxsamples.pocketcalculator.simple;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.raumzeitfalle.fxsamples.pocketcalculator.ApplicationTestTemplate;
import net.raumzeitfalle.fxsamples.pocketcalculator.Main;
import net.raumzeitfalle.fxsamples.pocketcalculator.ViewFactory;

import org.junit.*;

import javax.imageio.ImageIO;

import static net.raumzeitfalle.fxsamples.pocketcalculator.simple.CalculatorModel.Operation.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CalculatorViewTest extends ApplicationTestTemplate {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calculator");
        Scene scene = new Scene(ViewFactory.createSimpleCalculatorView());
        scene.getStylesheets().add(Main.class.getResource("AppSample.css").toExternalForm());
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
        new CalculatorViewAdapter(this).calculateAndAssert(0, ADD, 0);
    }

    @Test
    public void performAdditionOfLargeNumbers() throws IOException {
        new CalculatorViewAdapter(this).calculateAndAssert(12340, ADD, 1);
    }

    @Test
    public void createViewAndMakeScreenshot() throws IOException {
        Parent put = lookup("#rootpane").query();
        BufferedImage bImage = SwingFXUtils.fromFXImage(capture(put).getImage(), null);
        ImageIO.write(bImage, "png", new File("CalculatorScreen.png"));
   }


    @Test
    public void performSubtractionOfLargeNumbers() throws IOException {
        new CalculatorViewAdapter(this).calculateAndAssert(12340, SUB, 1);

    }

    @Test
    public void performMultiplyOfLargeNumbers() throws IOException {
        new CalculatorViewAdapter(this).calculateAndAssert(12340, MUL, 0);

    }

    @Test
    public void performDivisionOfLargeNumbers() throws IOException {
        new CalculatorViewAdapter(this)
                .enterOperandValue(12340)
                .enterOperation(DIV)
                .enterOperandValue(1)
                .calculateResult()
                .assertResult(12340);
    }

    @Test
    public void performDivisionOfNotSoLargeNumbers() throws IOException {
        new CalculatorViewAdapter(this)
                .enterOperandValue(999)
                .enterOperation(DIV)
                .enterOperandValue(333)
                .calculateResult()
                .assertResult(3);
    }

    @Test
    public void performMissingNumberInputs() {
        new CalculatorViewAdapter(this)
                .calculateAndAssert(856, ADD, 14 );
    }

    @Test(expected = ArithmeticException.class)
    public void divisionByZero() {
        new CalculatorViewAdapter(this)
                .calculateAndAssert(10, DIV, 0 );
    }

    @Test
    public void performEndlessResets() {
        new CalculatorViewAdapter(this)
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

        new CalculatorViewAdapter(this)
                .calculateAndAssert(24, DIV, 2)
                .clear()
                .assertResultCleared()
                .calculateAndAssert(10, SUB, 7)
                .clear()
                .assertResultCleared();

        new CalculatorViewAdapter(this)
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

    @Test
    public void arithmeticExceptionInCaseOfUnsupportedOperation() {
        new CalculatorViewAdapter(this).calculateResult();
    }

}
