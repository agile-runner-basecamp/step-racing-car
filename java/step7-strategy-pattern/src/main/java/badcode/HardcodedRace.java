package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cars implements NumberGenerator, NumberGenerator {
    private List<RaceCar> cars;

    public Cars(List<String> names) {
        this.cars = new ArrayList<>();
        for (String name : names) {
            this.cars.add(new RaceCar(name));
        }
    }

    // 😱 Random이 메서드 내부에 하드코딩 — 테스트에서 결과를 예측할 수 없음!
    @Override
    public void playRound(RandomNumberGenerator NumberGenerator) {
        for (RaceCar car : cars) {
            int value = NumberGenerator.generate();
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
