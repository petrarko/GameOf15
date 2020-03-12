import java.util.Arrays;

public enum Direction {
    LEFT,
    RIGHT,
    DOWN,
    UP;

    public static Direction getInitialDirection() {
        return values()[0];
    }

    public static int getDeltaByDirection(Direction d, int index) {
        return switch (d) {
            case LEFT -> -1;
            case RIGHT -> 1;
            case DOWN -> Board.BOARD_SIZE;
            case UP -> -Board.BOARD_SIZE;
        };
    }

    public static Coordinates getCoordinatesByDirection(Direction d, Coordinates c) {
        int xx = c.x;
        int yy = c.y;
        switch (d) {
            case LEFT -> xx--;
            case RIGHT -> xx++;
            case DOWN -> yy++;
            case UP -> yy--;
        }
        ;
        return new Coordinates(xx, yy);
    }

    public static boolean areCoordinatesCorrect(Coordinates c) {
        return !(c.x < 0 || c.x >= Board.BOARD_SIZE || c.y < 0 || c.y >= Board.BOARD_SIZE);
    }

    public Direction nextDirection() {
        int index = Arrays.binarySearch(values(), this) + 1;
        return index >= values().length ? null : values()[index];
    }


}
