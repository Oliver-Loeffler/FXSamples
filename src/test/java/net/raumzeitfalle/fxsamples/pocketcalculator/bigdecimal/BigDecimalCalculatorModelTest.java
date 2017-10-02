package net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal;

import org.junit.Test;

import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.BigDecimalCalculatorModel;
import net.raumzeitfalle.fxsamples.pocketcalculator.bigdecimal.Calculator.Operation;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BigDecimalCalculatorModelTest {

    private BigDecimalCalculatorModel classUnderTest = new BigDecimalCalculatorModel();

    @Test
    public void clear() {
    		classUnderTest.load(BigDecimal.ONE);
        classUnderTest.load(BigDecimal.TEN);
        classUnderTest.setOperation(Operation.ADD);
        classUnderTest.load(BigDecimal.TEN);
        
        classUnderTest.clear();
        assertEquals(0,  classUnderTest.operands());
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void addWithoutOperands() {
        classUnderTest.clear();
        classUnderTest.add();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void subtractWithoutOperands() {
        classUnderTest.clear();
        classUnderTest.subtract();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void multiplyWithoutOperands() {
        classUnderTest.clear();
        classUnderTest.multiply();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void divideWithoutOperands() {
        classUnderTest.clear();
        classUnderTest.divide();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void add() {
    	 	classUnderTest.clear();
        classUnderTest.load(new BigDecimal("1"));
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.ADD);
        classUnderTest.load(new BigDecimal("1000"));
        classUnderTest.calculate();
        assertEquals(1001, classUnderTest.getResult().intValue());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void addWithOtherLoadSequence() {
        classUnderTest.load(new BigDecimal("1000"));
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.ADD);
        classUnderTest.load(new BigDecimal("1"));
        classUnderTest.calculate();
        assertEquals(1001, classUnderTest.getResult().intValue());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());

    }

    @Test
    public void subtract() {
    		classUnderTest.clear();
        classUnderTest.load(new BigDecimal("-10"));
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.SUBTRACT);
        classUnderTest.load(new BigDecimal("1000"));
        classUnderTest.calculate();
        assertEquals(-1010, classUnderTest.getResult().intValue());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());

    }

    @Test
    public void multiply() {
        classUnderTest.load(new BigDecimal("2"));
        classUnderTest.setOperation(Operation.MULTIPLY);
        classUnderTest.load(new BigDecimal("99"));
        classUnderTest.calculate();
        assertEquals(198, classUnderTest.getResult().intValue());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());

    }

    @Test
    public void multiplyByZero() {
        classUnderTest.load(new BigDecimal("0"));
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.MULTIPLY);
        classUnderTest.load(new BigDecimal("99"));
        classUnderTest.calculate();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }

    @Test
    public void divide() {		
        classUnderTest.load(new BigDecimal("1"));
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.DIVIDE);
        classUnderTest.load(new BigDecimal("3"));
        classUnderTest.calculate();

        assertEquals(0.33333333, classUnderTest.getResult().doubleValue(),0.0000001);
        assertEquals(BigDecimalCalculatorModel.Operation.NONE, classUnderTest.operationProperty().get());
    }
    
    @Test
    public void none_noOperation() {
        classUnderTest.setOperation(BigDecimalCalculatorModel.Operation.NONE);
        classUnderTest.calculate();
        assertEquals(BigDecimal.ZERO, classUnderTest.getResult());
        assertEquals("0", classUnderTest.resultTextProperty().get());
    }
    

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        classUnderTest.load(new BigDecimal("1"));
        classUnderTest.setOperation(Operation.DIVIDE);
        classUnderTest.load(new BigDecimal("0"));
        classUnderTest.calculate();
    }

    @Test
    public void load() {
        classUnderTest.clear();
        classUnderTest.load(new BigDecimal("2"));
        classUnderTest.load(new BigDecimal("22"));
        classUnderTest.load(new BigDecimal("223"));      
        assertEquals(1, classUnderTest.operands());
    }

    @Test
    public void takeInput() {
    		classUnderTest.clear();
    		receiveInput(".");
    		assertEquals("0.", classUnderTest.resultTextProperty().get());
    		
    		receiveInput("2");
    		assertEquals("0.2", classUnderTest.resultTextProperty().get());
    		
    		receiveInput(".abc");
    		assertEquals("0.2", classUnderTest.resultTextProperty().get());
    		
    		receiveInput("98");
    		assertEquals("0.298", classUnderTest.resultTextProperty().get());
    		classUnderTest.takeInput('-');
    		assertEquals("-0.298", classUnderTest.resultTextProperty().get());
    		
    		classUnderTest.takeInput('-');
    		assertEquals("0.298", classUnderTest.resultTextProperty().get());
    		
    		classUnderTest.clear();
    		receiveInput("31.41");
    		assertEquals("31.41", classUnderTest.resultTextProperty().get());
    		
    		receiveInput("-");
    		assertEquals("-31.41", classUnderTest.resultTextProperty().get());
    		
    		receiveInput("-");
    		assertEquals("31.41", classUnderTest.resultTextProperty().get());
    }
    
    @Test
    public void acceptInput() {
    		classUnderTest.clear();
    		receiveInput("31.41-");
    		classUnderTest.acceptInput();
    		classUnderTest.setOperation(Operation.MULTIPLY);
    		classUnderTest.load(BigDecimal.ONE);
    		classUnderTest.calculate();
    		assertEquals(-31.41, classUnderTest.getResult().doubleValue(), 0.001);
    }

	private void receiveInput(String input) {
		input.chars().forEach(c-> classUnderTest.takeInput((char) c));
	}

}
