package badcode;

import java.util.Random;

public class RandomMovingStrategy implements MovingStrategy {

    private static final int RANDOM_BOUND = 10;
    private static final Random random = new Random();

    @Override
    public int getValue() {
        return random.nextInt(RANDOM_BOUND);
    }
}