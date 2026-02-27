package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MassiveRaceTest {

    @DisplayName("테스트A")
    @Test
    void tA() {
        MassiveRace r = new MassiveRace();
        int[] rv = { 4, 3, 4, 4, 3, 4 };
        String result = r.race("kim,lee,park", 2, rv);
        assertThat(result).isEqualTo("kim, park가 최종 우승했습니다.");
    }

    @DisplayName("테스트B")
    @Test
    void tB() {
        MassiveRace r = new MassiveRace();
        int[] rv = { 4, 4, 4 };
        String result = r.race("kim,lee,park", 1, rv);
        assertThat(result).isEqualTo("kim, lee, park가 최종 우승했습니다.");
    }

    @DisplayName("테스트C")
    @Test
    void tC() {
        MassiveRace r = new MassiveRace();
        assertThatThrownBy(() -> r.race("kim", 1, new int[] { 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("테스트D")
    @Test
    void tD() {
        MassiveRace r = new MassiveRace();
        assertThatThrownBy(() -> r.race("kim,abcdef", 1, new int[] { 4, 4 }))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
