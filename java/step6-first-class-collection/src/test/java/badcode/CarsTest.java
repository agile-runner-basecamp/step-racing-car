package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsTest {

    @DisplayName("자동차들이 이동한 후 우승자를 찾을 수 있다")
    @Test
    void findWinners() {
        Cars cars = Cars.from(List.of("kim", "lee", "park"));
        cars.moveAll(new int[] { 4, 3, 5 });

        List<String> winners = cars.findWinners();
        assertThat(winners).containsExactlyInAnyOrder("kim", "park");
    }

    @DisplayName("모두 같은 위치이면 모두 우승자이다")
    @Test
    void allWinners() {
        Cars cars = Cars.from(List.of("kim", "lee", "park"));
        cars.moveAll(new int[] { 4, 4, 4 });

        List<String> winners = cars.findWinners();
        assertThat(winners).containsExactlyInAnyOrder("kim", "lee", "park");
    }

    @DisplayName("getCars()로 반환된 리스트를 조작해도 원본은 변하지 않는다")
    @Test
    void exposedInternalList() {
        Cars cars = Cars.from(List.of("kim", "lee"));
        List<Car> copy = cars.getCars();

        // 😱 외부에서 내부 컬렉션을 직접 조작할 수 있음!
        // 🤗 이제는 내부 컬렉션이 아니라 복사본을 조작하도록 했음!
        copy.clear();

        // 내부 상태가 망가짐
        // 이제 내부는 보호됨
        assertThat(cars.getCars()).hasSize(2);
    }
}
