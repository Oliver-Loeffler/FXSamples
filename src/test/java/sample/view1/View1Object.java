package sample.view1;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import org.junit.Assert;
import org.testfx.api.FxRobotInterface;

import static junit.framework.TestCase.fail;

public class View1Object {

    private final FxRobotInterface robot;

    public View1Object(FxRobotInterface robot) {
        this.robot = robot;
    }

    private FxRobotInterface getRobot() {
        return robot;
    }

    public View1Object enterOperandValue(int operandValue) {
        for (char c : String.valueOf(operandValue).toCharArray()) {
            switch (c) {
                case '1' :  getRobot().clickOn("#one", MouseButton.PRIMARY); break;
                case '2' :  getRobot().clickOn("#two", MouseButton.PRIMARY); break;
                case '3' :  getRobot().clickOn("#three", MouseButton.PRIMARY); break;
                case '4' :  getRobot().clickOn("#four", MouseButton.PRIMARY); break;
                case '5' :  getRobot().clickOn("#five", MouseButton.PRIMARY); break;
                case '6' :  getRobot().clickOn("#six", MouseButton.PRIMARY); break;
                case '7' :  getRobot().clickOn("#seven", MouseButton.PRIMARY); break;
                case '8' :  getRobot().clickOn("#eight", MouseButton.PRIMARY); break;
                case '9' :  getRobot().clickOn("#nine", MouseButton.PRIMARY); break;
                case '0' :  getRobot().clickOn("#zero", MouseButton.PRIMARY); break;
                default : fail("not supported");
            }
        }
        return this;
    }

    public View1Object enterOperation(View1Model.Operation operation) {
        switch (operation) {
            case ADD: getRobot().clickOn("#plus", MouseButton.PRIMARY); break;
            case SUB: getRobot().clickOn("#minus", MouseButton.PRIMARY); break;
            case MUL: getRobot().clickOn("#multiply", MouseButton.PRIMARY); break;
            case DIV: getRobot().clickOn("#divide", MouseButton.PRIMARY); break;
            case NONE: fail("Operation NONE cannot be seleted from GUI."); break;
            case SQRT: fail("Operation is not implemented."); break;
            default : fail("not supported");
        }
        return this;
    }

    public View1Object calculateResult() {
        getRobot().clickOn("#equals", MouseButton.PRIMARY);
        return this;
    }

    public View1Object assertResult(int i) {
        TextField put = getRobot().lookup("#result").query();
        Assert.assertEquals(String.valueOf(i), put.getText());
        return this;
    }

    public View1Object clear() {
        getRobot().clickOn("#clear", MouseButton.PRIMARY);
        return this;
    }

    public View1Object assertResultCleared() {
        return assertResult(0);
    }

    public View1Object calculateAndAssert(int a, View1Model.Operation operand, int b) {
        this.enterOperandValue(a).enterOperation(operand).enterOperandValue(b).calculateResult();
        switch (operand) {
            case ADD: assertResult(a+b); break;
            case SUB: assertResult(a-b); break;
            case DIV: assertResult(a / b); break;
            case MUL: assertResult(a * b); break;
            case SQRT: fail("Operation not supported."); break;
            case NONE: fail("NONE Operation must not be used here."); break;

            default: {
                fail("Unsupported operation.");
            }
        }
        return this;
    }


}
