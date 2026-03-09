package badcode;

public class Position {
    private final int position;

    private Position(int position) {
        this.position = position;
    }

    public static Position from(int position) {
        return new Position(position);
    }

    public Position next() {
        return new Position(position + 1);
    }
}
