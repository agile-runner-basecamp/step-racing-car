package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BadRacerTest {

    @DisplayName("랜덤 값이 4 이상이면 움직일 수 있다")
    @Test
    void moveForward_whenRandomValueIsAboveThreshold() {
        BadRacer r = new BadRacer();
        assertThat(r.canMove(4)).isTrue();
    }

    @DisplayName("랜덤 값이 4 미만이면 움직일 수 없다")
    @Test
    void cantMoveForward_whenRandomValueIsBelowThreshold() {
        BadRacer r = new BadRacer();
        assertThat(r.canMove(3)).isFalse();
    }

    @DisplayName("이름이 1자 이상 5자 이하이면 유효하다")
    @Test
    void validNameWithinMaxLength() {
        BadRacer r = new BadRacer();
        assertThat(r.isValidName("kim")).isTrue();
    }

    @DisplayName("이름이 5자를 초과하면 유효하지 않다")
    @Test
    void invalidNameExceedingMaxLength() {
        BadRacer r = new BadRacer();
        assertThat(r.isValidName("abcdef")).isFalse();
    }

    @DisplayName("가장 멀리 이동한 자동차가 우승자다")
    @Test
    void getWinnerWithMaxDistance() {
        BadRacer racer = new BadRacer();
        Map<String, Integer> car = new LinkedHashMap<>();
        car.put("kim", 3);
        car.put("lee", 2);
        car.put("park", 3);
        List<String> w = racer.getWinners(car);
        assertThat(w).containsExactlyInAnyOrder("kim", "park");
    }
}
