package badcode;

 public class CarName {
    // 1. value object
    private final String carName;

    private CarName(String carName) {
        if (carName == null || carName.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (carName.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }

        this.carName = carName;
    }

    // 디자인패턴
    // 정적 팩토리 메서드 패턴
    public static CarName of(String carName) {
        return new CarName(carName);
    }

    public static CarName from(String carName) {
        return new CarName(carName);
    }
}