import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Board {

    static final int BOARD_SIZE = 4;
    static final int[] FINAL_STATE = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    Coordinates zeroCoordinates = new Coordinates(0, 0);
    int[] table;

    public Board(int[] table) {
        this.table = table;
    }

    public Board() {
        this.table = new int[BOARD_SIZE * BOARD_SIZE];
    }

    public Board(int[] t, Coordinates c) {
        this.table = t;
        this.zeroCoordinates = c;
    }

    public static Board generateRandom() {
        Board board = new Board();
        List<Integer> ints = Arrays.asList(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        Collections.shuffle(ints);
        int index = -1;
        for (int i : ints) {
            board.table[++index] = i;
        }
        board.updateZero();
        return board;
    }

    public static Board parseInput(String s) {
        int index = -1;
        Board board = new Board();
        for (String line : s.split("\n")) {
            for (String el : line.split(" ")) {
                board.table[++index] = Integer.valueOf(el);
            }
        }
        board.updateZero();
        return board;
    }

    public Board clone() {
        return new Board(this.table.clone(), new Coordinates(this.zeroCoordinates.x, this.zeroCoordinates.y));
    }

    public void updateZero() {
        zeroCoordinates = Coordinates.getCoordinatesByIndex(ArrayUtils.indexOf(table, 0));
    }

    public boolean directionExists(Direction d) {
        Coordinates coordinatesByDirection = Direction.getCoordinatesByDirection(d, zeroCoordinates);
        return Direction.areCoordinatesCorrect(coordinatesByDirection);
    }

    public Direction nextPossibleDirection(Direction d) {
        if (d == null) {
            d = Direction.getInitialDirection();
            if (directionExists(d)) {
                return d;
            }
        }
        Direction curDirection = d;
        while ((curDirection = curDirection.nextDirection()) != null) {
            if (directionExists(curDirection)) {
                return curDirection;
            }
        }
        return null;
    }


    public Board makeMove(Direction d) {
        Coordinates toReplaceCoordinate = Direction.getCoordinatesByDirection(d, zeroCoordinates);
        int indToReplace = Coordinates.getIndexByCoordinates(toReplaceCoordinate);
        int indOfZero = Coordinates.getIndexByCoordinates(zeroCoordinates);
        Board clone = this.clone();
        clone.table[indOfZero] = clone.table[indToReplace];
        clone.table[indToReplace] = 0;
        clone.zeroCoordinates = toReplaceCoordinate;

        return clone;

    }


    public boolean gameIsDone() {
        return Arrays.equals(table, FINAL_STATE);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                s.append(table[Coordinates.getIndexByCoordinates(new Coordinates(j, i))]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return Arrays.equals(table, board.table);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }


    public Board revertDirection(Direction lastDirection) {
        return switch (lastDirection) {
            case LEFT -> makeMove(Direction.RIGHT);
            case RIGHT -> makeMove(Direction.LEFT);
            case DOWN -> makeMove(Direction.UP);
            case UP -> makeMove(Direction.DOWN);
        };
    }
}
