package desafios.calculadora;
// Design pattern: Strategy

interface Operation {
    double execute(double num1, double num2);
}

// Strategies:
class AddOperation implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 + num2;
    }
}

class SubtractOperation implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 - num2;
    }
}

class MultiplyOperation implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 * num2;
    }
}

class DivideOperation implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 / num2;
    }
}

// Context
class Calculator {
    private Operation operation;

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double performOperation(double num1, double num2) {
        return operation.execute(num1, num2);
    }
}

public class SimpleCalculator {
    public static void main(String[] args) {
        Operation addOperation = new AddOperation();
        Operation subtractOperation = new SubtractOperation();
        Operation multiplyOperation = new MultiplyOperation();
        Operation divideOperation = new DivideOperation();

        Calculator calculator = new Calculator();

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[1]);

        Operation[] operations = {
            addOperation,
            subtractOperation,
            multiplyOperation,
            divideOperation
        };

        for (Operation op : operations) {
            calculator.setOperation(op);

            double result = calculator.performOperation(num1, num2);
            System.out.println(result);
        }
    }
}
