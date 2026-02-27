package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceGameTest {

    @DisplayName("자동차들이 이동한 후 우승자를 찾을 수 있다")
    @Test
    void findWinners() {
        RaceGame game = new RaceGame(List.of("kim", "lee", "park"));
        game.moveAll(new int[] { 4, 3, 5 });

        List<String> winners = game.findWinners();
        assertThat(winners).containsExactlyInAnyOrder("kim", "park");
    }

    @DisplayName("모두 같은 위치이면 모두 우승자이다")
    @Test
    void allWinners() {
        RaceGame game = new RaceGame(List.of("kim", "lee", "park"));
        game.moveAll(new int[] { 4, 4, 4 });

        List<String> winners = game.findWinners();
        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }

    @DisplayName("getCars()로 외부에서 자동차 목록을 직접 조작할 수 있다 — 위험!")
    @Test
    void exposedInternalList() {
        RaceGame game = new RaceGame(List.of("kim", "lee"));
        List<Car> cars = game.getCars();

        // 😱 외부에서 내부 컬렉션을 직접 조작할 수 있음!
        cars.clear();

        // 내부 상태가 망가짐
        assertThat(game.getCars()).isEmpty();
        // TODO: 일급 컬렉션으로 감싸서 이런 조작을 불가능하게 만드세요.
    }
}
