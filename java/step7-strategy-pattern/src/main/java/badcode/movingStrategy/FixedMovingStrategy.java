package badcode.movingStrategy;

import java.util.LinkedList;
import java.util.List;

public class FixedMovingStrategy implements MovingStrategy {

    private final LinkedList<Integer> fixedValues;

    public FixedMovingStrategy(int[] fixedValues) {
        validate(fixedValues);
        this.fixedValues = new LinkedList<>();
        for (int v : fixedValues) {
            this.fixedValues.add(v);
        }
    }

    @Override
    public Integer getRandomValue() {
        return fixedValues.pollFirst();
    }


    private void validate(int[] fixedValues) {
        if (fixedValues.length < 1) {
            throw new IllegalArgumentException("fixedValues의 입력값이 하나 이상 필요합니다.");
        }
    }
}
