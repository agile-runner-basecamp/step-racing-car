package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: 이 테스트 클래스를 어떻게 짜야 할지 막막할 것입니다!
// ConsoleRace가 값을 반환하는 대신 화면에 출력(System.out)만 하고 있기 때문입니다.
// 도메인 로직을 분리하여, 반환값으로 검증할 수 있도록 리팩토링하세요.
class ConsoleRaceTest {

    @DisplayName("출력(System.out)에 의존하는 코드는 테스트하기 어렵다")
    @Test
    void cannotTestConsoleOutput() {

        // given
        ConsoleRace race = new ConsoleRace();
        List<String> names = List.of("kim", "lee", "park");

        int[] randomValues = { 4, 3, 5, 4, 4, 5 };

        // when
        // 실행은 되지만 결과를 검증할 방법이 없습니다!
        race.run(names, 2, randomValues);
        // RaceResult result = race.play(names, 2, randomValues);

        // then
        // assertThat(result.getWinnerNames()).containsExactlyInAnyOrder("kim", "park");
    }

    @DisplayName("출력(System.out)에 의존하는 코드는 테스트하기 어렵다")
    @Test
    void canTestConsoleOutput() {

        // given
        ConsoleRace race = new ConsoleRace();
        List<String> names = List.of("kim", "lee", "park");

        // 맨 아래 줄 테스트코드가 통과하려면 주어진 randomValues로는 가능한 방법이 없음.
        // 따라서, { 4, 3, 5, 4, 4, 3 } -> { 4, 3, 5, 4, 4, 5 } 으로, 마지막 숫자를 옳은 값으로 변경하여 진행함.
        int[] randomValues = { 4, 3, 5, 4, 4, 5 };

        // when
        race.run(names, 2, randomValues);
        RaceResult result = race.play(names, 2, randomValues);

        // then
        assertThat(result.getWinnerNames()).containsExactlyInAnyOrder("kim", "park");
    }
}
