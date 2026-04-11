package badcode;

import badcode.movingStrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private final List<RaceCar> cars;
    private final MovingStrategy movingStrategy;

    public Race(List<String> names, MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
        this.cars = new ArrayList<>();
        for (String name : names) {
            this.cars.add(new RaceCar(name));
        }
    }

    // 😱 Random이 메서드 내부에 하드코딩 — 테스트에서 결과를 예측할 수 없음!
    public void playRound() {
        for (RaceCar car : cars) {
            int value = movingStrategy.getRandomValue();
            car.move(value);
        }
    }

    public List<String> findWinners() {
        int maxPosition = 0;
        for (RaceCar car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
        List<String> winners = new ArrayList<>();
        for (RaceCar car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}
