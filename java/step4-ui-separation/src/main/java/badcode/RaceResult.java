package badcode;

import java.util.List;

public class RaceResult {

    private final List<String> winnerNames;
    private final int maxPosition;
    private final int[] randomValues;

    public RaceResult(List<String> winnerNames, int maxPosition, int[] randomValues) {
        validate(winnerNames, maxPosition, randomValues);
        this.winnerNames = winnerNames;
        this.maxPosition = maxPosition;
        this.randomValues = randomValues;
    }

    private void validate(List<String> winnerNames, int maxPosition, int[] randomValues) {
        if (winnerNames.isEmpty()){
            throw new IllegalArgumentException("우승자 이름 리스트의 길이가 0일 수 없습니다.");
        }
        if (maxPosition < 0 ){
            throw new IllegalArgumentException("최대 이동 거리가 음수일 수 없습니다.");
        }
        if (randomValues.length < 1){
            throw new IllegalArgumentException("난수 배열의 길이가 0일 수 없습니다.");
        }
    }


    public List<String> getWinnerNames() {
        return winnerNames;
    }

    public int getMaxPosition() {
        return maxPosition;
    }

    public int[] getRandomValues() {
        return randomValues;
    }
}
