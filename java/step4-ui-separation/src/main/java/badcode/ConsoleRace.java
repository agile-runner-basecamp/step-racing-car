package badcode;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRace {

    // 차 이름 리스트, 라운드 수, 랜덤한 수 배열을 받아서
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

        int[] positions = new int[names.size()]; // 차 위치 배열 생성
        int randomIndex = 0; // 랜덤배열 첫번째 값부터 꺼내쓰기 위한 인덱스

        System.out.println("\n실행 결과"); //TODO

        // 차 한 대마다 해당 턴의 랜덤인덱스 수가 4 이상이면 1만큼 앞으로 나가는 로직을 라운드 수만큼 반복한다.
        // 차 인덱스 i와 별개로 랜덤인덱스는 매 턴마다 순환한다
        for (int round = 0; round < rounds; round++) { // 라운드 수만큼 반복
            for (int i = 0; i < names.size(); i++) { // 차 한 대당 인덱스 i 부여
                if (randomValues[randomIndex] >= 4) { // 랜덤배열에서 랜덤인덱스 위치의 수가 4 이상이면
                    positions[i]++; // i 라는 차에 거리 1 추가
                }
                randomIndex++; // 랜덤인덱스 순환
            }

            // "차이름:---------------" 처럼 거리만큼 -가 길어지도록 함.
            for (int i = 0; i < names.size(); i++) { // 차 한 대당 인덱스 i 부여
                System.out.print(names.get(i) + " : ");
                for (int j = 0; j < positions[i]; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }

        // 최대거리 산출
        int maxPosition = 0;
        for (int position : positions) {
            if (position > maxPosition) {
                maxPosition = position;
            }
        }

        // 최대거리 달성한 차 골라냄
        List<String> winners = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (positions[i] == maxPosition) {
                winners.add(names.get(i));
            }
        }

        // TODO
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }
}
