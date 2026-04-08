package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    // TODO 2
    @DisplayName("Position을 1만큼 증가시킬 수 있다")
    @Test
    void next() {
        // given
        Position position = Position.from("0");

        // when
        Position result = position.next();

        // then
        assertThat(result).isEqualTo(Position.from("1"));
    }

    // TODO 3
    @DisplayName("Position은 음수가 될 수 없다")
    @Test
    void negativePosition() {
        assertThatThrownBy(() -> Position.from("-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}