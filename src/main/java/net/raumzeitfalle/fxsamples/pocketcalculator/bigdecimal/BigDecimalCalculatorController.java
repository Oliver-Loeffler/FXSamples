package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.Calculator.Operation;

public class BigDecimalCalculatorController implements Initializable {

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
    private TextField bigDecimalResult;

    @FXML
    private Label operation;
	
	private final BigDecimalCalculatorModel model;
	
	public BigDecimalCalculatorController(final BigDecimalCalculatorModel model) {
		this.model = Objects.requireNonNull(model, "The model must not be null");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		one.setOnAction(e->model.takeInput('1'));
		two.setOnAction(e->model.takeInput('2'));
		three.setOnAction(e->model.takeInput('3'));
		four.setOnAction(e->model.takeInput('4'));
		five.setOnAction(e->model.takeInput('5'));
		six.setOnAction(e->model.takeInput('6'));
		seven.setOnAction(e->model.takeInput('7'));
		eight.setOnAction(e->model.takeInput('8'));
		nine.setOnAction(e->model.takeInput('9'));
		zero.setOnAction(e->model.takeInput('0'));
		
		bigDecimalResult.textProperty().bind(model.resultTextProperty());
		
		clear.setOnAction(e->model.clear());
		
		plus.setOnAction(e->applyOperation(Operation.ADD));
		minus.setOnAction(e->applyOperation(Operation.SUBTRACT));
		multiply.setOnAction(e->applyOperation(Operation.MULTIPLY));
		divide.setOnAction(e->applyOperation(Operation.DIVIDE));
		
		ObservableValue<String> status = Bindings
				.concat(model.previouslyEnteredValue(),
						" ",model.operationProperty().asString(),
						" ",model.resultTextProperty());
		
		operation.textProperty().bind(status);
		
		equals.setOnAction(e->model.calculate());
	}

	private void applyOperation(Operation op) {
		model.acceptInput();
		model.setOperation(op);
		model.calculate();
	}
}
