package badcode;

import java.util.ArrayList;
import java.util.List;

public class CleanRace {

    public String race(String namesInput, int rounds, int[] randomValues) {
        validateNamesInput(namesInput);

        List<String> names = parseNames(namesInput);

        validateMinimumCarCount(names);

        validateAttemptRoundCount(rounds);

        int[] positions = calculatePositions(rounds, randomValues, names);

        List<String> winners = findWinners(positions, names);
        return String.join(", ", winners) + "가 최종 우승했습니다.";
    }

    private static List<String> parseNames(String namesInput) {
        String[] nameArray = namesInput.split(",");
        List<String> names = new ArrayList<>();
        for (String name : nameArray) {
            String trimmed = name.trim();
            validateCarName(trimmed);
            names.add(trimmed);
        }
        return names;
    }

    private static List<String> findWinners(int[] positions, List<String> names) {
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

    private static int[] calculatePositions(int rounds, int[] randomValues, List<String> names) {
        int[] positions = new int[names.size()];
        int randomIndex = 0;
        for (int round = 0; round < rounds; round++) {
            for (int i = 0; i < names.size(); i++) {
                if (randomValues[randomIndex] >= 4) {
                    positions[i]++;
                }
                randomIndex++;
            }
        }
        return positions;
    }

    private static void validateAttemptRoundCount(int rounds) {
        if (rounds <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }

    private static void validateCarName(String trimmed) {
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (trimmed.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumCarCount(List<String> names) {
        if (names.size() < 2) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
    }

    private static void validateNamesInput(String namesInput) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
    }
}
