package badcode;

public class RawCar {
    private CarName carname;
    private Position position;

    public RawCar(String carname) {
        this.carname = CarName.of(carname);
        this.position = Position.of(0);
    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            position = position.plus(1);
        }
    }

    public CarName getName() {
        return carname;
    }

    public Position getPosition() {
        return position;
    }
}
