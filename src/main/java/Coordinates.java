import java.util.Objects;

public class Coordinates {

    int x, y; // x - horizontal from left to right, y - vertical from up to down

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates getCoordinatesByIndex(int index) {
        return new Coordinates(index % Board.BOARD_SIZE, index / Board.BOARD_SIZE);
    }

    public static int getIndexByCoordinates(Coordinates c) {
        return c.y * (Board.BOARD_SIZE) + c.x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
