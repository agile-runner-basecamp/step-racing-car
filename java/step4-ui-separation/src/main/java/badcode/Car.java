package badcode;

import java.util.List;

public class Car {

    private final String ownerName;
    private int position;

    public Car(String ownerName) {

        validateCarNameLength(ownerName);
        this.ownerName = ownerName;
        this.position = 0;  // 방어적 복사

    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            this.position ++;
        }
    }

    public int getPosition() {
        return this.position;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getDistanceView() {
        return this.ownerName + ":" + "-".repeat(this.position);
    }

    public void setPositionToZero() {
        this.position = 0;
    }

    private static void validateCarNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다: " + name);
        }
    }
}
