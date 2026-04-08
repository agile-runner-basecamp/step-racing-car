package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayRaceTest {

    @DisplayName("여러 라운드 진행 시 라운드별 위치가 정확히 기록된다")
    @Test
    void recordsPositionsPerRound() {
        PlayRace playRace = new PlayRace();
        List<String> names = List.of("kim", "lee", "park");
        int rounds = 2;
        int[] randomValues = {4, 3, 5, 4, 4, 3};

        int[][] results = playRace.playRounds(names, rounds, randomValues);

        assertThat(results[0]).containsExactly(1, 0, 1);
        assertThat(results[1]).containsExactly(2, 1, 1);
    }

    @DisplayName("가장 멀리 이동한 자동차가 우승한다")
    @Test
    void findWinnersWithMaxDistance() {
        PlayRace playRace = new PlayRace();
        List<String> names = List.of("kim", "lee", "park");
        int[] finalPositions = {2, 1, 2};

        List<String> winners = playRace.findWinners(names, finalPositions);

        assertThat(winners).containsExactlyInAnyOrder("kim", "park");
    }

    @DisplayName("자동차가 2대 미만이면 예외가 발생한다")
    @Test
    void throwExceptionWhenLessThanTwoCars() {
        PlayRace playRace = new PlayRace();

        assertThatThrownBy(() -> playRace.validateInput(List.of("kim")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void throwExceptionWhenNameExceedsMaxLength() {
        PlayRace playRace = new PlayRace();

        assertThatThrownBy(() -> playRace.validateInput(List.of("kim", "abcdef")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}