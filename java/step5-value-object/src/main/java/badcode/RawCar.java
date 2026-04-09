package badcode;

import badcode.value_object.CarName;
import badcode.value_object.Position;

public class RawCar {
    private CarName name;
    private Position position;

    public RawCar(String name) {
        this.name = CarName.of(name);
        this.position = Position.of(0);
    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            this.position = position.increase();
        }
    }

    public String getName() {
        return this.name.getName();
    }

    public int getPosition() {
        return this.position.getPosition();
    }
}
