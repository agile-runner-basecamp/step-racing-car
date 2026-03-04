package badcode;

public class Position {
    private final int position;

    public Position(int position) {
        this.position = position;
    }

    public static Position of(int position) {
        return new Position(position);
    }

    public Position plus(int number) {
        return new Position(position + number);
    }
}
