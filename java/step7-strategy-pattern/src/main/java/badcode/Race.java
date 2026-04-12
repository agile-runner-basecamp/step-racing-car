package badcode;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<RaceCar> cars;
    List<MovingStrategy> movingStrategies;

    public Race(List<String> names, List<MovingStrategy> movingStrategies) {
        this.cars = new ArrayList<>();
        for (String name : names) {
            this.cars.add(new RaceCar(name));
        }
        this.movingStrategies = movingStrategies;
    }

    // 😱 Random이 메서드 내부에 하드코딩 — 테스트에서 결과를 예측할 수 없음!
    public void playRound() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).move(movingStrategies.get(i).getValue());
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
