package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CleanRaceTest {

    @DisplayName("여러 라운드 진행 시 최대 위치의 자동차가 우승한다")
    @Test
    void race_multipleRounds_returnsWinnersWithMaxPosition() {
        // given
        CleanRace r = new CleanRace();
        int[] rv = { 4, 3, 4, 4, 3, 4 };

        // when
        String result = r.race("kim,lee,park", 2, rv);

        // then
        assertThat(result).isEqualTo("kim, park가 최종 우승했습니다.");
    }

    @DisplayName("한 라운드에서 모두 이동하면 모든 자동차가 공동 우승한다")
    @Test
    void race_singleRound_allMove_returnsAllWinners() {
        // given
        CleanRace r = new CleanRace();
        int[] rv = { 4, 4, 4 };

        // when
        String result = r.race("kim,lee,park", 1, rv);

        // then
        assertThat(result).isEqualTo("kim, lee, park가 최종 우승했습니다.");
    }

    @DisplayName("자동차가 1대이면 예외가 발생한다")
    @Test
    void race_singleCar_throwsException() {
        // given
        CleanRace r = new CleanRace();

        // when & then
        assertThatThrownBy(() -> r.race("kim", 1, new int[] { 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void race_nameTooLong_throwsException() {
        // given
        CleanRace r = new CleanRace();

        // when & then
        assertThatThrownBy(() -> r.race("kim,abcdef", 1, new int[] { 4, 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

