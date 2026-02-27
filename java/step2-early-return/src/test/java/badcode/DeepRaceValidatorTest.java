package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeepRaceValidatorTest {

    @DisplayName("랜덤 값이 4 이상이면 전진한다")
    @Test
    void moveForward() {
        DeepRaceValidator validator = new DeepRaceValidator();
        int result = validator.move("kim", 4, 0);
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("랜덤 값이 3 이하이면 정지한다")
    @Test
    void stay() {
        DeepRaceValidator validator = new DeepRaceValidator();
        int result = validator.move("kim", 3, 0);
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("이름이 null이면 예외가 발생한다")
    @Test
    void nullName() {
        DeepRaceValidator validator = new DeepRaceValidator();
        assertThatThrownBy(() -> validator.move(null, 4, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void longName() {
        DeepRaceValidator validator = new DeepRaceValidator();
        assertThatThrownBy(() -> validator.move("abcdef", 4, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("랜덤 값이 범위를 벗어나면 예외가 발생한다")
    @Test
    void invalidRandomValue() {
        DeepRaceValidator validator = new DeepRaceValidator();
        assertThatThrownBy(() -> validator.move("kim", 10, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
