package badcode;

public class CarName {

    private final String carname;

    public CarName(String carname) {
        if (carname == null || carname.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (carname.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
        this.carname = carname;
    }

    public static CarName of(String name) {
        return new CarName(name);

    }
}
