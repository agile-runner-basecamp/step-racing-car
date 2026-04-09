package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public static Cars from(Stream<Car> stream) {
        return new Cars(stream.toList());
    }


    // 라운드 하나 진행
    public void executeRound(int[] randomValues, int randomIndex) {
        for  (int i = 0; i < this.cars.size(); i++) {
            this.cars.get(i).move(randomValues[randomIndex+i]);
        }
    }

    public int getMaxPosition() {
        return this.cars.stream().mapToInt(Car::getPosition).max().getAsInt();
    }

    public List<String> getWinnerNames() {
        int maxPosition = getMaxPosition();
        return this.cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getOwnerName)
                .toList();
    }

    public List<String> getDistanceViews(){
        return this.cars.stream().map(Car::getDistanceView).toList();

    }

    public int getSize() {
        return this.cars.size();
    }

    public void initializePositions() {
        this.cars.forEach(Car::setPositionToZero);
    }
}
