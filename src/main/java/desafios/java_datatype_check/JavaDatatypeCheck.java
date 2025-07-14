package desafios.java_datatype_check;
// Design pattern: Strategy

import java.util.*;

interface TypeChecker<T extends Number> {
    void checkType(long value);
}

class RangeChecker<T extends Number> implements TypeChecker<T> {
    private final Class<T> typeClass;
    private final long minValue;
    private final long maxValue;
    private final String typeName;

    public RangeChecker(Class<T> typeClass, long minValue, long maxValue) {
        this.typeClass = typeClass;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.typeName = typeClass.getSimpleName().toLowerCase();
    }

    @Override
    public void checkType(long value) {
        if (value >= minValue && value <= maxValue) {
            System.out.println("* " + typeName);
        }
    }
}

public class JavaDatatypeCheck {
    private final List<TypeChecker<?>> checkers;

    public JavaDatatypeCheck() {
        this.checkers = Arrays.asList(
                new RangeChecker<>(Byte.class, Byte.MIN_VALUE, Byte.MAX_VALUE),
                new RangeChecker<>(Short.class, Short.MIN_VALUE, Short.MAX_VALUE),
                new RangeChecker<>(Integer.class, Integer.MIN_VALUE, Integer.MAX_VALUE),
                new RangeChecker<>(Long.class, Long.MIN_VALUE, Long.MAX_VALUE)
        );
    }

    public void verifyFittedValue(List<String> entries) {
        for(String input : entries) {
            try {
                long numLong = Long.parseLong(input);

                System.out.println(input+" can be fitted in:");

                for(TypeChecker<?> checker : checkers) {
                    checker.checkType(numLong);
                }

            } catch (NumberFormatException ex) {
                System.out.println(input+" can't be fitted anywhere.");
                System.out.println("Uso: <nome1> [<nome2> ...]");
            }
        }
    }

    public static void main(String[] args) {
        (new JavaDatatypeCheck())
                .verifyFittedValue(Arrays.stream(args).toList());
    }
}
