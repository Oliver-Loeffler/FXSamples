package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import javafx.beans.property.ReadOnlyStringProperty;

public interface Calculator {

	enum Operation {
		NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE
	}

	void takeInput(char input);
	void acceptInput();
	void calculate();
	void clear();
	void setOperation(Operation operation);
	ReadOnlyStringProperty resultTextProperty();
}
