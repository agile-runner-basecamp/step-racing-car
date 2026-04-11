package badcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceGame {
    private Cars cars;

    public RaceGame(List<String> names) {
       this.cars = Cars.from(names);
    }

    public void run(int[] randomValues) {
        cars.moveAll(randomValues);
    }

    public Cars getCars() {
        return cars;
    }


}
