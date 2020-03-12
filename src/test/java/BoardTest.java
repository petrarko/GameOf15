import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {
    private static final String OPTIMAL = "15 14 13 12\n" +
            "11 10 9 8\n" +
            "7 6 5 4\n" +
            "3 2 1 0";


    @Test
    void testParsing() {
        Board board = Board.parseInput(OPTIMAL);
        assertThat(board.gameIsDone()).isEqualTo(true);
    }

    @Test
    void testRandom() {
        System.out.println(Board.generateRandom());
    }

    @Test
    void testMakeMove() {
        Board board = Board.parseInput(
                "15 14 13 12\n" +
                        "11 10 0 8\n" +
                        "7 6 5 4\n" +
                        "3 2 1 9");
        Board board2 = board.makeMove(Direction.DOWN);
        System.out.println(board2);
        System.out.println(board2.zeroCoordinates);

        System.out.println(board);
        System.out.println(board.zeroCoordinates);
    }
}