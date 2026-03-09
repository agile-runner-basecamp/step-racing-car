package badcode;

public class RawCar {
    private CarName carName;
    private Position position;

    public RawCar(String name) {
        this.carName = CarName.of(name);
        this.position = Position.from(0);
    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            this.position = position.next();
        }
    }

    public CarName getName() {
        return carName;
    }

    public Position getPosition() {
        return position;
    }
}
