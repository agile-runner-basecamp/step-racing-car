package badcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BadRacer {

    private static final int MIN_MOVE_VALUE = 4;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int INIT_DISTANCE = 0;

    public boolean canMove(int randomValue) {
        return randomValue >= MIN_MOVE_VALUE;
    }

    public boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        if (name.length() > MAX_NAME_LENGTH) {
            return false;
        }
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<String> getWinners(Map<String, Integer> car) {
        int maxDistance = INIT_DISTANCE;
        for (Map.Entry<String, Integer> e : car.entrySet()) {
            if (e.getValue() > maxDistance) {
                maxDistance = e.getValue();
            }
        }
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> e : car.entrySet()) {
            if (e.getValue() == maxDistance) {
                winners.add(e.getKey());
            }
        }
        return winners;
    }
}
