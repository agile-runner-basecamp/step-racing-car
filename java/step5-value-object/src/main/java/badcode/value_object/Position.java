package badcode.value_object;

import java.util.Objects;

public class Position {

    private final int position;

    public Position(int position) {
        validate(position);
        this.position = position;
    }

    public static Position of(int position) {
        return new Position(position);
    }

    public void validate(int position) {

        if (position < 0) {
            throw new IllegalArgumentException("값이 0 이하일 수 없습니다.");
        }

    }

    public int getPosition() {
        return position;
    }

    public Position increase() {
        return new Position(position + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }
}
