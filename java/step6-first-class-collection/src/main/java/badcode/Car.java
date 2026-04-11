package badcode;

public class Car {
    private String name;
    private int position;

    private Car(String name) {
        validate(name);
        this.name = name;
        this.position = 0;
    }

    public static Car of(String name){
        return new Car(name);
    }

    public void move(int randomValue) {
        if (randomValue >= 4) {
            this.position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    private void validate(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }
}
