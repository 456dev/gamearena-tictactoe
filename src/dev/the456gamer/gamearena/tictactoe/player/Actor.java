package dev.the456gamer.gamearena.tictactoe.player;


import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * A Generic Player that can make moves in a game.
 */
public interface Actor {

    public String getName();
    public String getTooltip();

    /**
     * Get the next move for the player
     * this shouldnt be called unless there is at least 1 valid move.
     * @param state the current state of the board
     * @return the next move for the player
     */
    public Move getNextMove(BoardState state);


}
