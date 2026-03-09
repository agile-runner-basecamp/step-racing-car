package badcode;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private List<Car> cars;

    Cars(List<Car> cars){
        this.cars = cars;
    }

    public static Cars from(List<String> names) {
        ArrayList<Car> carList = new ArrayList<>();

        for (String name : names) {
            Car car = new Car(name);
            carList.add(car);
        }
        return new Cars(carList);
    }

    public Cars getCars(){
        return new Cars(cars);
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
