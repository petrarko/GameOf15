import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchSolutionTest {

    @Test
    void searchSolution() {
        SearchSolution solution = new SearchSolution();
        String ss = "15 14 13 12\n" +
                "11 10 9 8\n" +
                "7 6 5 0\n" +
                "3 2 1 4";
        Board board = Board.parseInput(ss);
        assertThat(solution.checkSolutionExists(board)).isTrue();
        System.out.println(solution.sequence);
    }


    //    @Test
    void searchSolution2() {
        SearchSolution solution = new SearchSolution();
        String ss = "15 13 14 12\n" +
                "11 10 9 8\n" +
                "7 6 5 0\n" +
                "3 2 1 4";
        Board board = Board.parseInput(ss);
        assertThat(solution.checkSolutionExists(board)).isTrue();
        System.out.println(solution.sequence);
    }
}