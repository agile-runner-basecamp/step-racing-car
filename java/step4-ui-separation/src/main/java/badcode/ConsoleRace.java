package badcode;

import java.util.List;

public class ConsoleRace {


    public void run(List<String> names, int rounds, int[] randomValues) {

        validateNameCount(names);

        RaceResult raceResult = play(names, rounds, randomValues);

        System.out.println("\n실행 결과");
        System.out.println(String.join(", ", raceResult.getWinnerNames()) + "가 최종 우승했습니다.");
    }

    public RaceResult play(List<String> names, int rounds, int[] randomValues) {
        Cars cars = Cars.from(names.stream().map(Car::new));


        int randomIndex = 0;
        // 차 한 대마다 해당 턴의 랜덤인덱스 값이 4 이상이면 1만큼 앞으로 나가는 로직을 라운드 수만큼 반복한다.
        // 차 인덱스 i와 별개로 랜덤인덱스는 매 턴마다 순환한다
        for (int round = 0; round < rounds; round++) { // 라운드 수만큼 반복

            cars.executeRound(randomValues, randomIndex);

            printDistanceViews(cars);

            randomIndex = randomIndex + cars.getSize();
        }

        // 최대거리 산출
        int maxPosition = cars.getMaxPosition();

        // 최대거리 달성한 차 골라냄
        List<String> winnerNames = cars.getWinnerNames();

        cars.initializePositions();

        return new RaceResult(winnerNames, maxPosition, randomValues);
    }

    private static void printDistanceViews(Cars cars) {
        // "차이름:---------------" 처럼 거리만큼 -가 길어지도록 출력
        cars.getDistanceViews().forEach(System.out::println);
        System.out.println();
    }

    private static void validateNameCount(List<String> names) {
        if (names == null || names.size() < 2) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
    }

}
