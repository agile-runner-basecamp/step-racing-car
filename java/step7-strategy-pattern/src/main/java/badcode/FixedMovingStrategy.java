package badcode;

import java.util.ArrayList;

public class FixedMovingStrategy implements MovingStrategy {

    private int value;

    public FixedMovingStrategy(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
