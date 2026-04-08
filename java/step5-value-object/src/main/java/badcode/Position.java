package badcode;

import java.util.Objects;

public class Position {
    private final int position;

    private Position(int position) {
        if (position < 0) throw new IllegalArgumentException("위치는 0 이상이어야 합니다.");
        this.position = position;
    }

    public static Position from(String position) {
        return new Position(Integer.parseInt(position));
    }

    public Position next() {
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
