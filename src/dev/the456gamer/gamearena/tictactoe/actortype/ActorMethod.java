package dev.the456gamer.gamearena.tictactoe.actortype;


import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import java.util.concurrent.TimeoutException;

/**
 * A Generic GameSide that can make moves in a game.
 */
public interface ActorMethod {

    String getName();

    String getTooltip();

    /**
     * Get the next move for the gameSide this shouldn't be called unless there is at least 1 valid
     * move.
     * <p>
     * This Method is blocking, should be a different thread or something
     *
     * @param state the current state of the board
     * @return the next move for the gameSide
     */
    Move getNextMove(BoardState state) throws TimeoutException;

//    public Actor getBackingActor();


}
