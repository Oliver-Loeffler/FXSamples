package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.EnumSet;
import java.util.Stack;

public class BigDecimalCalculatorModel implements Calculator {

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    private final StringProperty resultText;

    private BigDecimal result;

    private final Stack<BigDecimal> stack;

    private SimpleObjectProperty<Operation> operation = new SimpleObjectProperty<>(Operation.NONE);

    enum Operation {
        NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    public BigDecimalCalculatorModel() {
        resultText = new SimpleStringProperty("0");
        result = BigDecimal.ZERO;
        stack = new Stack<>();
    }

    BigDecimal getResult() {
        return result;
    }
    
    public SimpleObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation.set(operation);
    }

    void add() {
        if (stack.size() == 2) {
            result = stack.pop().add(stack.pop(), MATH_CONTEXT);
            store();
        }
    }

    void subtract() {
        if (stack.size() == 2) {
            result = stack.pop().negate(MATH_CONTEXT).add(stack.pop(),MATH_CONTEXT);
            store();
        }
    }

    void multiply() {
        if (stack.size() == 2) {
            result = stack.pop().multiply(stack.pop(),MATH_CONTEXT);
            store();
        }
    }

    void divide() {
        if (stack.size() == 2) {
            BigDecimal divisor = stack.pop();
           // BigDecimal.ONE.divide(stack.pop()).multiply(stack.pop());
            result = stack.pop().divide(divisor,MATH_CONTEXT);
            store();
        }
    }

    public void clear() {
        while (!stack.isEmpty()) {
            stack.pop();
        }
        this.result = BigDecimal.ZERO;
        this.resultText.setValue(this.result.toString());
        this.operation.set(Operation.NONE);
    }

    private void store() {
        resultText.setValue(result.toString());
        stack.push(result);
    }

    int operands() {
        return this.stack.size();
    }

    public void calculate() {
        switch (operation.getValue()) {
            case ADD: add(); break;
            case SUBTRACT: subtract(); break;
            case DIVIDE: divide(); break;
            case MULTIPLY: multiply(); break;
            case NONE: break;
            default: {
                throw new ArithmeticException("unsupported operation");
            }
        }
        operation.setValue(Operation.NONE);
    }

    void load(BigDecimal operand) {
    		EnumSet<Operation> multiOperand = EnumSet.of(Operation.ADD, Operation.DIVIDE, Operation.MULTIPLY, Operation.SUBTRACT);
    		
    		if (!stack.isEmpty() && !multiOperand.contains(this.operation.get())) {
    			stack.pop();
    		}
    		stack.push(operand);
    }

	public void takeInput(char input) {
		String value = this.resultText.get();
		if (value.equals("0")) value = "";
		switch (input) {
		 	case '-': {
		    			if (value.startsWith("-")) this.resultText.setValue(value.substring(1));
		    			else this.resultText.setValue(input+value);
		    			break;
		 		}
		 	case '.': { if (value.contains(".")) break;
		 				if (value.equals("")) value = "0";}
        		case '1':; 
        		case '2':;
        		case '3':;
        		case '4':;
        		case '5':;
        		case '6':;
        		case '7':;
        		case '8':;
        		case '9':;
        		case '0': this.resultText.setValue(value +input );
        		default: break;
		}
		System.out.println(this.resultText.getValue());
	}
	
	public ReadOnlyStringProperty resultTextProperty() { return this.resultText; }
	
	public void acceptInput() {
		BigDecimal operand = new BigDecimal(this.resultText.get());
		load(operand);
	}
}
