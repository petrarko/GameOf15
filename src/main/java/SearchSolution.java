import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.*;

public class SearchSolution {

    Queue<Direction> sequence = Collections.asLifoQueue(new ArrayDeque<>());
    List<Board> stateHistory = new ArrayList<>();

    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();
        String ss = "15 13 14 12\n" +
                "11 10 9 8\n" +
                "7 6 5 0\n" +
                "3 2 1 4";
        Board board = Board.parseInput(ss);
        boolean b = solution.checkSolutionExists(board);
        System.out.println(b);
        System.out.println(solution.sequence);
    }

    public boolean checkSolutionExists(Board board) {
        Board currentBoard = board;
        ProgressBar pb = new ProgressBar("Test", CombinatoricsUtils.factorial(16));
        pb.start();
        do {
            pb.step();
            if (currentBoard.gameIsDone()) {
                return true;
            }
            stateHistory.add(currentBoard.clone());
            Direction direction = chooseNextDirection(currentBoard);
            if (direction == null) {
                Direction lastDirection = sequence.poll();
                currentBoard = currentBoard.revertDirection(lastDirection);
            } else {
                sequence.add(direction);
                currentBoard = currentBoard.makeMove(direction);
            }
        } while (!sequence.isEmpty());
        pb.stop();
        System.out.println(stateHistory);
        return false;
    }

    private Direction chooseNextDirection(Board currentBoard) {
        for (Direction d : Direction.values()) {
            if (currentBoard.directionExists(d) && !stateHistory.contains(currentBoard.makeMove(d))) {
                return d;
            }
        }
        return null;
    }
}
