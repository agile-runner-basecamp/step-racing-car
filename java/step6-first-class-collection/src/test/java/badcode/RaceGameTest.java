package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RaceGameTest {

    @DisplayName("자동차들이 이동한 후 우승자를 찾을 수 있다")
    @Test
    void shouldFindWinnersWithDifferentPositions() {
        // given
        RaceGame game = new RaceGame(List.of("kim", "lee", "park"));
        Cars cars = game.getCars();

        // when
        cars.moveAll(new int[] { 4, 3, 5 });
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("kim", "park");
    }

    @DisplayName("모두 같은 위치이면 모두 우승자이다")
    @Test
    void shouldAllBeWinnersWhenPositionsSame() {
        // given
        RaceGame game = new RaceGame(List.of("kim", "lee", "park"));
        Cars cars = game.getCars();

        // when
        cars.moveAll(new int[] { 4, 4, 4 });
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }

    @DisplayName("getCars()로 외부에서 자동차 목록을 직접 조작할 수 없다")
    @Test
    void shouldThrowWhenModifyingInternalList() {
        // given
        RaceGame game = new RaceGame(List.of("kim", "lee"));
        List<Car> cars = game.getCars().getCars();

        // when & then
        assertThatThrownBy(cars::clear).isInstanceOf(UnsupportedOperationException.class);
    }
}
