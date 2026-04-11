package badcode.movingStrategy;

import java.util.Random;

public class RandomMovingStrategy implements MovingStrategy {
    private final Random random;

    public RandomMovingStrategy(int bound) {
        this.random = new Random(bound);
    }

    @Override
    public Integer getRandomValue() {
        return this.random.nextInt();
    }
}
