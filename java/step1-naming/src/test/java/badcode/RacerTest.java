package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RacerTest {

    @DisplayName("기어 값이 4 이상이면 후진한다")
    @Test
    void moveBackward_whenGearIsGreaterThanOrEqualToFour() {
        // given

        // when
        Racer racer = new Racer();

        // then
        assertThat(racer.isReverse(4)).isTrue();
    }

    @DisplayName("기어 값이 4 미만이면 후진하지 않는다")
    @Test
    void notMoveBackward_whenGearIsLessThanFour() {
        // given

        // when
        Racer racer = new Racer();

        // then
        assertThat(racer.isReverse(3)).isFalse();
    }

    @DisplayName("이름이 5자 이하이면 유효하다")
    @Test
    void isRacerNameValid_whenNameLengthIsFiveOrLess() {
        // given

        // when
        Racer racer = new Racer();

        // then
        assertThat(racer.isRacerNameValid("kim")).isTrue();
    }


    @DisplayName("이름이 5자를 초과하면 유효하지 않다")
    @Test
    void isRacerNameInvalid_whenNameLengthExceedsFive() {
        // given

        // when
        Racer racer = new Racer();

        // then
        assertThat(racer.isRacerNameValid("abcdef")).isFalse();
    }

    @DisplayName("최고 주행 거리에 도달한 레이서 목록을 반환한다")
    @Test
    void returnFinishedRacers_whenRacersHaveMaxDistance() {
        // given

        // when
        Racer racer = new Racer();

        Map<String, Integer> drivingDistance = new LinkedHashMap<>();
        drivingDistance.put("kim", 3);
        drivingDistance.put("lee", 2);
        drivingDistance.put("park", 3);

        List<String> finishedRacers = racer.findFinishedRacers(drivingDistance);

        // then
        assertThat(finishedRacers).containsExactlyInAnyOrder("kim", "park");
    }
}
