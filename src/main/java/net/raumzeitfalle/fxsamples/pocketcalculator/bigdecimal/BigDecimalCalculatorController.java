package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
		this.model = model;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
