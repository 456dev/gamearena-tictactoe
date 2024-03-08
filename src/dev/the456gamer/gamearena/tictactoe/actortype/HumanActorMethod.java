package dev.the456gamer.gamearena.tictactoe.actortype;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.output.GridCoordinate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A gameSide that is controlled by a human.
 */
public class HumanActorMethod extends BaseActorMethod {

    CompletableFuture<GridCoordinate> gridCoordCompletableFuture;

    public HumanActorMethod() {
        super();
    }

    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public String getTooltip() {
        return "A Real Life Player :O";
    }

    private Move fetchValidMove(BoardState state) throws TimeoutException {
        gridCoordCompletableFuture = new CompletableFuture<>();
        GridCoordinate gridCoordinate;
        try {
            gridCoordinate = gridCoordCompletableFuture.get(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
        for (Move move : state.getValidMoves()) {
            if (move.x == gridCoordinate.x() && move.y == gridCoordinate.y()) {
                gridCoordCompletableFuture = null;
                return move;
            }
        }
        return null;
    }

    @Override
    public Move getNextMove(BoardState state) throws TimeoutException {
        if (state.getValidMoves().isEmpty()) {
            return null;
        }
        Move move = null;
        while (move == null) {
            move = fetchValidMove(state);
        }

        return move;
    }
    // needs to handle clicking on tiles -> gets active gameSide -> if instance of human gameSide -> make move (location)
    // needs to support waiting for that event handler for long periods of time

    /**
     * Contextless function designed to be called from another thread (event handler) so that
     * getNextMove can return.
     *
     * @param x xpos of the grid to move to
     * @param y ypos of the grid to move to
     */
    public void makeMove(int x, int y) {
//        System.out.println("human move request to (%d,%d)".formatted(x,y));
        if (gridCoordCompletableFuture != null && !gridCoordCompletableFuture.isDone()) {
            gridCoordCompletableFuture.complete(new GridCoordinate(x, y));
        } else {
            System.out.println(
                "ignoring human move request to (%d,%d), completable future not active".formatted(x,
                    y));
        }
    }


}
