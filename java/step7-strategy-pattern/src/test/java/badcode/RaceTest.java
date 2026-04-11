package badcode;

import badcode.movingStrategy.FixedMovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceTest {

    @DisplayName("고정된 이동값으로 라운드를 진행할 수 있다")
    @Test
    void playRoundWithFixedValue() {
        // given
        Race race = new Race(List.of("kim", "lee"), new FixedMovingStrategy(new int[]{4, 4}));

        // when
        race.playRound();
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("kim", "lee");
    }

    @DisplayName("모두 같은 위치이면 모두 우승자이다")
    @Test
    void allWinnersWhenSame() {
        // given
        Race race = new Race(List.of("kim", "lee", "park"), new FixedMovingStrategy(new int[]{4, 4, 4}));

        // when
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }
}
