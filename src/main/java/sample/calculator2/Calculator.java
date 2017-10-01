package sample.calculator2;

import javafx.beans.property.ReadOnlyStringProperty;
import sample.calculator2.BigDecimalCalculatorModel.Operation;

public interface Calculator {
	void takeInput(char input);
	void acceptInput();
	void calculate();
	void clear();
	void setOperation(Operation operation);
	ReadOnlyStringProperty resultTextProperty();
}
