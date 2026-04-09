package badcode;

import badcode.value_object.CarName;
import badcode.value_object.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RawCarTest {

    @DisplayName("자동차는 이름을 가진다")
    @Test
    void create_validName_storesName() {
        // given
        String carName = "kim";

        // when
        RawCar car = new RawCar(carName);

        // then
        assertThat(car.getName()).isEqualTo(carName);
    }

    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void create_nameTooLong_throwsException() {
        // given
        String tooLongName = "abcdef";

        // when & then
        assertThatThrownBy(() -> new RawCar(tooLongName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 비어있으면 예외가 발생한다")
    @Test
    void create_emptyName_throwsException() {
        // given
        String emptyName = "";

        // when & then
        assertThatThrownBy(() -> new RawCar(emptyName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("랜덤 값이 4 이상이면 자동차가 전진한다")
    @Test
    void move_randomValueGreaterThan4_increasesPosition() {
        // given
        RawCar car = new RawCar("kim");
        int movableValue = 4;

        // when
        car.move(movableValue);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @DisplayName("랜덤 값이 3 이하이면 자동차가 정지한다")
    @Test
    void move_randomValueLessThan3_keepsPosition() {
        // given
        RawCar car = new RawCar("kim");
        int immovableValue = 3;

        // when
        car.move(immovableValue);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @DisplayName("같은 이름을 가진 CarName 값 객체는 동등하다")
    @Test
    void carName_sameName_returnsEqual() {
        // given
        CarName carName1 = CarName.of("kim");
        CarName carName2 = CarName.of("kim");

        // when & then
        assertThat(carName1).isEqualTo(carName2);
    }

    @DisplayName("Position 값 객체의 increase() 메서드는 1 증가된 새 객체를 반환한다")
    @Test
    void increase_position0_returnsPosition1() {
        // given
        Position currentPosition = Position.of(0);
        Position expectedPosition = Position.of(1);

        // when
        Position increasedPosition = currentPosition.increase();

        // then
        assertThat(increasedPosition).isEqualTo(expectedPosition);
    }

    @DisplayName("음수 값으로 Position을 생성하려고 하면 예외가 발생한다")
    @Test
    void createPosition_negativeValue_throwsException() {
        // given
        int negativeValue = -1;

        // when & then
        assertThatThrownBy(() -> Position.of(negativeValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
