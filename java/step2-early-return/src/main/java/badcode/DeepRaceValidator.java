package badcode;

public class DeepRaceValidator {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_THRESHOLD = 4;
    private static final int MAX_RANDOM_VALUE = 9;

    public int move(String name, int randomValue, int currentPosition) {
        if (name != null) {
            if (!name.isEmpty()) {
                if (name.length() <= MAX_NAME_LENGTH) {
                    if (randomValue >= 0) {
                        if (randomValue <= MAX_RANDOM_VALUE) {
                            if (randomValue >= MOVE_THRESHOLD) {
                                return currentPosition + 1;
                            } else {
                                return currentPosition;
                            }
                        } else {
                            throw new IllegalArgumentException("랜덤 값은 0~9 사이여야 합니다.");
                        }
                    } else {
                        throw new IllegalArgumentException("랜덤 값은 0~9 사이여야 합니다.");
                    }
                } else {
                    throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
                }
            } else {
                throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
            }
        } else {
            throw new IllegalArgumentException("자동차 이름은 null일 수 없습니다.");
        }
    }
}
