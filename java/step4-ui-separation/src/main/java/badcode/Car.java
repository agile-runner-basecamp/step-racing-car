package badcode;

public class Car {

    private final String ownerName;
    private int position;

    public Car(String ownerName) {
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
}
