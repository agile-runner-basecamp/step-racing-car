package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {

    @DisplayName("playRound를 실행하면 자동차가 이동하지만 결과를 예측할 수 없다")
    @Test
    void playRound_isNotPredictable() {
        Cars cars = new Cars(List.of("kim", "lee", "park"));

        // 😱 이 테스트는 랜덤에 의존하므로 결과를 보장할 수 없습니다!
        cars.playRound(new RandomNumberGenerator());

        // 검증이 불가능 — 랜덤이라 어떤 결과가 나올지 모름
        assertThat(cars.findWinners()).containsExactlyInAnyOrder("kim","lee","park");


        // TODO: MovingStrategy 인터페이스를 도입하여,
        // 테스트에서는 고정된 값을 주입할 수 있도록 리팩토링하세요.
        // 예:
        // Race race = new Race(List.of("kim", "lee"), new FixedMovingStrategy(4));
        // race.playRound();
        // assertThat(race.findWinners()).containsExactlyInAnyOrder("kim", "lee");
    }

    @DisplayName("초기 상태에서는 모든 자동차가 우승자이다")
    @Test
    void findWinners_initialState() {
        Cars cars = new Cars(List.of("kim", "lee", "park"));
        List<String> winners = cars.findWinners();

        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }
}
