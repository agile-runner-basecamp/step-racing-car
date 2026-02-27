package badcode;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRace {

    public void run(List<String> names, int rounds, int[] randomValues) {
        if (names == null || names.size() < 2) {
            System.out.println("[ERROR] 자동차는 2대 이상이어야 합니다.");
            return;
        }
        for (String name : names) {
            if (name.length() > 5) {
                System.out.println("[ERROR] 자동차 이름은 5자를 초과할 수 없습니다: " + name);
                return;
            }
        }

        int[] positions = new int[names.size()];
        int randomIndex = 0;

        System.out.println("\n실행 결과");

        for (int round = 0; round < rounds; round++) {
            for (int i = 0; i < names.size(); i++) {
                if (randomValues[randomIndex] >= 4) {
                    positions[i]++;
                }
                randomIndex++;
            }
            for (int i = 0; i < names.size(); i++) {
                System.out.print(names.get(i) + " : ");
                for (int j = 0; j < positions[i]; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
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
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }
}
