package badcode;

import java.util.ArrayList;
import java.util.List;

public class MassiveRace {

    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_THRESHOLD = 4;

    public String race(String namesInput, int rounds, int[] randomValues) {
        validateInput(namesInput, rounds);
        List<String> names = parseNames(namesInput);
        int[] positions = playRounds(rounds, randomValues, names);
        List<String> winners = findWinner(positions, names);
        return formatResult(winners);
    }

    private static String formatResult(List<String> winners) {
        return String.join(", ", winners) + "가 최종 우승했습니다.";
    }

    private static List<String> findWinner(int[] positions, List<String> names) {
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

    private static int[] playRounds(int rounds, int[] randomValues, List<String> names) {
        int[] positions = new int[names.size()];
        int randomIndex = 0;
        for (int round = 0; round < rounds; round++) {
            for (int i = 0; i < names.size(); i++) {
                if (randomValues[randomIndex] >= MOVE_THRESHOLD) {
                    positions[i]++;
                }
                randomIndex++;
            }
        }
        return positions;
    }

    private static List<String> parseNames(String namesInput) {
        String[] nameArray = namesInput.split(",");
        List<String> names = new ArrayList<>();
        for (String name : nameArray) {
            names.add(name.trim());
        }
        return names;
    }

    private static void validateInput(String namesInput, int rounds) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        String[] nameArray = namesInput.split(",");
        if (nameArray.length < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
        for (String name : nameArray) {
            String trimmed = name.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
            }
            if (trimmed.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
            }
        }
        if (rounds <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
