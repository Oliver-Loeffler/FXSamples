package sample.view1;

import javafx.beans.property.*;

public class View1Model {

    private final SimpleIntegerProperty a = new SimpleIntegerProperty(0);

    private final SimpleIntegerProperty b = new SimpleIntegerProperty(0);

    private final SimpleIntegerProperty result = new SimpleIntegerProperty(0);

    private final SimpleObjectProperty<Operation> op = new SimpleObjectProperty<>(Operation.NONE);

    public View1Model() {
        a.addListener((a,b,c) -> print());
        b.addListener((a,b,c) -> print());
        result.addListener((a,b,c) -> print());
        op.addListener((a,b,c) -> print());
    }

    private void print() {
        System.out.println("A = " + a.get() + " B = " + b.get() + " Result = " + result.get() + " Operation = " + op.get().name());
    }

    public Operation getOp() {
        return op.get();
    }

    public SimpleObjectProperty<Operation> opProperty() {
        return op;
    }

    public void setOp(Operation op) {
        this.op.set(op);
    }

    public int getA() {
        return a.get();
    }

    public SimpleIntegerProperty aProperty() {
        return a;
    }

    public void setA(int a) {
        this.a.set(a);
    }

    public int getB() {
        return b.get();
    }

    public SimpleIntegerProperty bProperty() {
        return b;
    }

    public void setB(int b) {
        this.b.set(b);
    }

    public int getResult() {
        return result.get();
    }

    public SimpleIntegerProperty resultProperty() {
        return result;
    }

    public void setResult(int result) {
        this.result.set(result);
    }


    public enum Operation {
        NONE, ADD, SUB, MUL, DIV, SQRT;
    }
}
