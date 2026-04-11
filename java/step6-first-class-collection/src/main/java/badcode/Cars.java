package badcode;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    // 정적 팩토리 메서드 패턴
    public static Cars of(List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars from(List<String> names) {
        ArrayList<Car> carList = new ArrayList<>();
        for (String name : names) {
            carList.add(new Car(name));
        }
        return new Cars(carList);
    }

    // 😱 내부 컬렉션을 그대로 반환 — 외부에서 직접 조작 가능!
    public List<Car> getCars() {
        return new ArrayList<>(cars);
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