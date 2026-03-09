package badcode;

import java.util.ArrayList;
import java.util.List;

public class RaceGame {
//    private List<Car> cars;
    private Cars cars2;
    public RaceGame(List<String> names) {
        cars2 = Cars.from(names);
    }

    public void run(){
        cars2.getCars();
        cars2.findWinners();
    }

    // 😱 내부 컬렉션을 그대로 반환 — 외부에서 직접 조작 가능!

}
