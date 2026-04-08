package badcode;

import java.util.ArrayList;
import java.util.List;

public class PlayRace {
    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_THRESHOLD = 4;

    public List<String> findWinners(List<String> names, int[] positions) {
        int maxPosition = 0;
        for (int position : positions) {
            if (position > maxPosition) {
                maxPosition = position;
            }
        }
        List<String> winners = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (positions[i] == maxPosition) {
                winners.add(names.get(i));
            }
        }
        return winners;
    }

    public int[][] playRounds(List<String> names, int rounds, int[] randomValues) {
        int[] positions = new int[names.size()];
        int[][] roundResults = new int[rounds][names.size()];
        int randomIndex = 0;

        for (int round = 0; round < rounds; round++) {
            for (int i = 0; i < names.size(); i++) {
                if (randomValues[randomIndex] >= MOVE_THRESHOLD) {
                    positions[i]++;
                }
                randomIndex++;
            }
            roundResults[round] = positions.clone();
        }
        return roundResults;
    }

    public void validateInput(List<String> names) {
        if (names == null || names.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
        for (String name : names) {
            if (name.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다: " + name);
            }
        }
    }
}