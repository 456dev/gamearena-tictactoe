package dev.the456gamer.gamearena.tictactoe.actortype;


import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * A Generic GameSide that can make moves in a game.
 */
public interface ActorMethod {

    public String getName();

    public String getTooltip();

    /**
     * Get the next move for the gameSide this shouldnt be called unless there is at least 1 valid
     * move.
     * <p>
     * This Method is blocking, should be a different thread or smth
     *
     *
     * @param state the current state of the board
     * @return the next move for the gameSide
     */
    public Move getNextMove(BoardState state);
    
//    public Actor getBackingActor();


}
