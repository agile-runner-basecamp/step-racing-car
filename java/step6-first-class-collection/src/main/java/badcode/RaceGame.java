package badcode;

import java.util.List;

public class RaceGame {
    private Cars cars;

    public RaceGame(List<String> names) {
        cars = Cars.from(names);
    }

    // 게임 흐름 제어
    public void run(int[] randomValues) {
        cars.moveAll(randomValues);
    }
}