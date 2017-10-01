package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import javafx.beans.property.ReadOnlyStringProperty;
import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.BigDecimalCalculatorModel.Operation;

public interface Calculator {
	void takeInput(char input);
	void acceptInput();
	void calculate();
	void clear();
	void setOperation(Operation operation);
	ReadOnlyStringProperty resultTextProperty();
}
