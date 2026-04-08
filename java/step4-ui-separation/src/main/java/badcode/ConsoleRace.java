package badcode;

import java.util.List;

public class ConsoleRace {

    private final PlayRace playRace = new PlayRace();

    public void run(List<String> names, int rounds, int[] randomValues) {
        try {
            playRace.validateInput(names);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return;
        }
        int[][] roundResults = playRace.playRounds(names, rounds, randomValues);
        int[] finalPositions = roundResults[roundResults.length - 1];
        List<String> winners = playRace.findWinners(names, finalPositions);

        printRoundResults(names, roundResults);
        printWinners(winners);
    }

    private void printRoundResults(List<String> names, int[][] roundResults) {
        System.out.println("\n실행 결과");
        for (int[] positions : roundResults) {
            for (int i = 0; i < names.size(); i++) {
                System.out.print(names.get(i) + " : ");
                for (int j = 0; j < positions[i]; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void printWinners(List<String> winners) {
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }
}
