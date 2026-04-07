package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MassiveRaceTest {

    @DisplayName("2라운드 경주에서 가장 많이 전진한 자동차가 우승한다")
    @Test
    void tA() {
        MassiveRace r = new MassiveRace();
        int[] rv = { 4, 3, 4, 4, 3, 4 };
        String result = r.race("kim,lee,park", 2, rv);
        assertThat(result).isEqualTo("kim, park가 최종 우승했습니다.");
    }

    @DisplayName("모든 자동차가 같은 거리를 이동하면 전원 우승한다")
    @Test
    void tB() {
        MassiveRace r = new MassiveRace();
        int[] rv = { 4, 4, 4 };
        String result = r.race("kim,lee,park", 1, rv);
        assertThat(result).isEqualTo("kim, lee, park가 최종 우승했습니다.");
    }

    @DisplayName("자동차가 1대뿐이면 예외가 발생한다")
    @Test
    void tC() {
        MassiveRace r = new MassiveRace();
        assertThatThrownBy(() -> r.race("kim", 1, new int[] { 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void tD() {
        MassiveRace r = new MassiveRace();
        assertThatThrownBy(() -> r.race("kim,abcdef", 1, new int[] { 4, 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
