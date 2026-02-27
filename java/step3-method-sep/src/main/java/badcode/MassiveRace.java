package badcode;

import java.util.ArrayList;
import java.util.List;

public class MassiveRace {

    public String race(String namesInput, int rounds, int[] randomValues) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
        }
        String[] nameArray = namesInput.split(",");
        if (nameArray.length < 2) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
        for (String name : nameArray) {
            String trimmed = name.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
            }
            if (trimmed.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
            }
        }
        if (rounds <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
        List<String> names = new ArrayList<>();
        for (String name : nameArray) {
            names.add(name.trim());
        }
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
        return String.join(", ", winners) + "가 최종 우승했습니다.";
    }
}
