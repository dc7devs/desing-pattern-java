package desafios.gerador_numero_aleatorio;
// Design pattern: Strategy

import java.security.SecureRandom;
import java.util.Random;

interface NumberGeneratorStrategy {
    int generate(int min, int max);
}

// Strategies:

// with Math.random()
class MathRandomGeneratorStrategy implements NumberGeneratorStrategy {
    @Override
    public int generate(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}

// with security.SecureRandom
class SecureRandomGeneratorStrategy implements NumberGeneratorStrategy {
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public int generate(int min, int max) {
        return min + secureRandom.nextInt((max - min) + 1);
    }
}

// with util.Random
class DefaultRandomGeneratorStrategy implements NumberGeneratorStrategy {
    private final Random random;

    public DefaultRandomGeneratorStrategy() {
        this.random = new Random();
    }

    @Override
    public int generate(int min, int max) {
        return min + random.nextInt((max - min) + 1);
    }
}

public class GenerateRandomNumber {
    private final static int RANGE_MIN = 200;
    private final static int RANGE_MAX = 500;

    public static void main(String... args) {
        RandomGeneratorNumber generatorWithMathRandom = new RandomGeneratorNumber(new MathRandomGeneratorStrategy());
        RandomGeneratorNumber generatorWithSecureRandom = new RandomGeneratorNumber(new SecureRandomGeneratorStrategy());
        RandomGeneratorNumber generatorWithDefaultRandom = new RandomGeneratorNumber(new DefaultRandomGeneratorStrategy());

        System.out.println("Numeros gerados aleatoriamente: ");
        System.out.println(generatorWithMathRandom.generate(RANGE_MIN, RANGE_MAX));
        System.out.println(generatorWithSecureRandom.generate(RANGE_MIN, RANGE_MAX));
        System.out.println(generatorWithDefaultRandom.generate(RANGE_MIN, RANGE_MAX));
    }
}

// Generator Context
class RandomGeneratorNumber {
    private final NumberGeneratorStrategy strategy;

    RandomGeneratorNumber(NumberGeneratorStrategy strategy) {
        this.strategy = strategy;
    }

    public int generate(int min, int max) {
        return strategy.generate(min, max);
    }
}