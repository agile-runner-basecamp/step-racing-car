package badcode;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator{
    public static final Random RANDOM = new Random();

    public int generate() {
        return RANDOM.nextInt(10);
    }
}
