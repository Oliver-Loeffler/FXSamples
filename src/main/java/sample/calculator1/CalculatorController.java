package sample.calculator1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    private final CalculatorModel model;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button zero;

    @FXML
    private Button multiply;

    @FXML
    private Button divide;

    @FXML
    private Button plus;

    @FXML
    private Button minus;

    @FXML
    private Button equals;

    @FXML
    private Button clear;

    @FXML
    private Label operation;

    @FXML
    private TextField result;

    private boolean useA = true;

    public CalculatorController(final CalculatorModel model) {
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        one.setOnAction(e -> takeNumberInput(1));
        two.setOnAction(e -> takeNumberInput(2));
        three.setOnAction(e -> takeNumberInput(3));
        four.setOnAction(e -> takeNumberInput(4));
        five.setOnAction(e -> takeNumberInput(5));
        six.setOnAction(e -> takeNumberInput(6));
        seven.setOnAction(e -> takeNumberInput(7));
        eight.setOnAction(e -> takeNumberInput(8));
        nine.setOnAction(e -> takeNumberInput(9));
        zero.setOnAction(e -> takeNumberInput(0));

        plus.setOnAction(e -> toggleOperand(false, CalculatorModel.Operation.ADD));
        minus.setOnAction(e -> toggleOperand(false, CalculatorModel.Operation.SUB));
        multiply.setOnAction(e -> toggleOperand(false, CalculatorModel.Operation.MUL));
        divide.setOnAction(e -> toggleOperand(false, CalculatorModel.Operation.DIV));

        clear.setOnAction(e -> reset());

        equals.setOnAction(e -> calculate());

        operation.textProperty().bind(model.opProperty().asString());
        result.textProperty().bind(model.aProperty().asString());
    }

    private void calculate() {
        model.resultProperty().unbind();
        result.textProperty().unbind();
        switch (model.opProperty().get()) {
            case ADD: {
                model.setResult(model.getA() + model.getB()); break;
            }
            case SUB: {
                model.setResult(model.getA() - model.getB()); break;
            }
            case MUL: {
                model.setResult(model.getA() * model.getB()); break;
            }
            case DIV: {
                if (model.bProperty().get() == 0) {
                    // throw new ArithmeticException("cannot divide by zero");
                    // divisionByZeroAlert(); break;
                } else {
                    model.setResult(model.getA() / model.getB()); break;
                }
            }
            default : {
                // throw new ArithmeticException("undefined and unsupported operation");
            }
        }
        result.textProperty().bind(model.resultProperty().asString());
    }

    private void takeNumberInput(int number) {
        if (useA) {
            model.setA( model.getA() * 10 + number);
        } else {
            model.setB( model.getB() * 10 + number);
        }
    }

    private void toggleOperand(boolean value, CalculatorModel.Operation operation) {
        this.useA = value;
        model.setOp(operation);
        result.textProperty().unbind();

        if (useA) {
            result.textProperty().bind(model.aProperty().asString());
        } else {
            result.textProperty().bind(model.bProperty().asString());
        }
    }

    private void reset() {
        model.setA(0);
        model.setB(0);
        model.setResult(0);
        toggleOperand(true, CalculatorModel.Operation.NONE);
    }

    private void divisionByZeroAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Division By Zero");
        //alert.setHeaderText("Arithmetic ");
        alert.setContentText("Division by Zero is not defined");
        alert.showAndWait();
    }

}
