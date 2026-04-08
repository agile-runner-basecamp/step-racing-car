package badcode;

public class RawCar {
    private CarName name;
    private Position position;

    public RawCar(String name) {
        this.name = CarName.of(name);
        this.position = Position.from("0");
    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            position = position.next();
        }
    }

    public CarName getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
