package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RawCarTest {

    @DisplayName("자동차는 이름을 가진다")
    @Test
    void hasName() {
        RawCar car = new RawCar("kim");
        assertThat(car.getName()).isEqualTo("kim");
    }

    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void nameTooLong() {
        assertThatThrownBy(() -> new RawCar("abcdef"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 비어있으면 예외가 발생한다")
    @Test
    void emptyName() {
        assertThatThrownBy(() -> new RawCar(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("랜덤 값이 4 이상이면 전진한다")
    @Test
    void moveForward() {
        RawCar car = new RawCar("kim");
        car.move(4);
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @DisplayName("랜덤 값이 3 이하이면 정지한다")
    @Test
    void stay() {
        RawCar car = new RawCar("kim");
        car.move(3);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    // TODO: 원시값을 포장(Value Object)한 뒤 아래 테스트를 추가하세요:
    // - CarName의 동등성(equals) 검증: 같은 이름이면 같은 객체
    // - Position의 increase() 동작 검증
    // - Position이 음수가 될 수 없다는 검증
}
