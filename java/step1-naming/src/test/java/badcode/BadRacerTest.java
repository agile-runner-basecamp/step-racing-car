package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BadRacerTest {

    @DisplayName("테스트1")
    @Test
    void t1() {
        BadRacer r = new BadRacer();
        assertThat(r.doM(4)).isTrue();
    }

    @DisplayName("테스트2")
    @Test
    void t2() {
        BadRacer r = new BadRacer();
        assertThat(r.doM(3)).isFalse();
    }

    @DisplayName("테스트3")
    @Test
    void t3() {
        BadRacer r = new BadRacer();
        assertThat(r.chk("kim")).isTrue();
    }

    @DisplayName("테스트4")
    @Test
    void t4() {
        BadRacer r = new BadRacer();
        assertThat(r.chk("abcdef")).isFalse();
    }

    @DisplayName("테스트5")
    @Test
    void t5() {
        BadRacer r = new BadRacer();
        Map<String, Integer> c = new LinkedHashMap<>();
        c.put("kim", 3);
        c.put("lee", 2);
        c.put("park", 3);
        List<String> w = r.doW(c);
        assertThat(w).containsExactlyInAnyOrder("kim", "park");
    }
}
