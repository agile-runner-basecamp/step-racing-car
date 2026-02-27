package badcode;

import java.util.ArrayList;
import java.util.List;

public class RaceGame {
    private List<Car> cars;

    public RaceGame(List<String> names) {
        this.cars = new ArrayList<>();
        for (String name : names) {
            this.cars.add(new Car(name));
        }
    }

    // 😱 내부 컬렉션을 그대로 반환 — 외부에서 직접 조작 가능!
    public List<Car> getCars() {
        return cars;
    }

    public void moveAll(int[] randomValues) {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).move(randomValues[i]);
        }
    }

    public List<String> findWinners() {
        int maxPosition = 0;
        for (Car car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}
