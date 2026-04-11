package badcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        validate(cars);
        this.cars = new ArrayList<>(cars);
    }

    public static Cars of(List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars from(List<String> names) {
        return new Cars(names.stream().map(Car::of).toList());
    }

    private void validate(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void moveAll(int[] randomValues) {
        validateRandomValues(randomValues);
        for (int i = 0; i < cars.size(); i++) {
            this.cars.get(i).move(randomValues[i]);
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
        return Collections.unmodifiableList(winners);
    }

    private void validateRandomValues(int[] randomValues) {
        if (randomValues == null || randomValues.length == 0) {
            throw new IllegalArgumentException("랜덤 수 배열은 비어있을 수 없습니다.");
        }
        if (randomValues.length < this.cars.size()) {
            throw new IllegalArgumentException("랜덤 수 배열의 길이는 차 대수보다 작을 수 없습니다.");
        }
    }
}
