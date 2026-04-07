package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Racer {

    private final int REVERSE_GEAR_MIN = 4;
    private final int MAX_RACER_NAME_LENGTH = 5;
    private final int INITIALIZE_ZERO_DISTANCE = 0;

    // 후진 여부 (변속기 4단계)
    public boolean isReverse(int gear) {
        return gear >= REVERSE_GEAR_MIN;
    }

    // 이름 5자 이하로 지정했는지 여부
    public boolean isRacerNameValid(String racerName) {
        if (racerName == null) {
            return false;
        }
        if (racerName.length() > MAX_RACER_NAME_LENGTH) {
            return false;
        }
        if (racerName.isEmpty()) {
            return false;
        }
        return true;
    }

    // 종점(또는 최고점) 도달한 레이서 목록 추출
    public List<String> findFinishedRacers(Map<String, Integer> drivingDistanceMap) {
        int maxDistance = INITIALIZE_ZERO_DISTANCE;
        for (Map.Entry<String, Integer> drivingDistance : drivingDistanceMap.entrySet()) {
            if (drivingDistance.getValue() > maxDistance) {
                maxDistance = drivingDistance.getValue();
            }
        }
        List<String> finishedRacers = new ArrayList<>();
        for (Map.Entry<String, Integer> drivingDistance : drivingDistanceMap.entrySet()) { //
            if (drivingDistance.getValue() == maxDistance) {
                finishedRacers.add(drivingDistance.getKey());
            }
        }
        return finishedRacers;
    }
}
