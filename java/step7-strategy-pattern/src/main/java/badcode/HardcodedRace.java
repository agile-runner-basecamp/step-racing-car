package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardcodedRace {
    private List<RaceCar> cars;

    public HardcodedRace(List<String> names) {
        this.cars = new ArrayList<>();
        for (String name : names) {
            this.cars.add(new RaceCar(name));
        }
    }

    // 😱 Random이 메서드 내부에 하드코딩 — 테스트에서 결과를 예측할 수 없음!
    public void playRound() {
        Random random = new Random();
        for (RaceCar car : cars) {
            int value = random.nextInt(10);
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
