package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceTest {

    @DisplayName("playRound를 실행하면 자동차가 이동하지만 결과를 예측할 수 없다")
    @Test
    void playRound_isNotPredictable() {
        // TODO: MovingStrategy 인터페이스를 도입하여,
        // 테스트에서는 고정된 값을 주입할 수 있도록 리팩토링하세요.
        // 예:
         Race race = new Race(List.of("kim", "lee"), List.of(new FixedMovingStrategy(3), new FixedMovingStrategy(4)));
         race.playRound();
         assertThat(race.findWinners()).containsExactlyInAnyOrder("lee");
    }

    @DisplayName("초기 상태에서는 모든 자동차가 우승자이다")
    @Test
    void findWinners_initialState() {
        Race race = new Race(List.of("kim", "lee", "park"), List.of(new FixedMovingStrategy(3), new FixedMovingStrategy(4), new RandomMovingStrategy()));
        List<String> winners = race.findWinners();

        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }
}
