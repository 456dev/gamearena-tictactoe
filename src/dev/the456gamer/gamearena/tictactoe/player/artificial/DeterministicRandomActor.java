package dev.the456gamer.gamearena.tictactoe.player.artificial;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;

/**
 * Player that always plays the same moves, given the same board state.
 * moves are effectively random, as it doesn't try to win or lose
 */
public class DeterministicRandomActor extends AiActor {

    public DeterministicRandomActor() {
        super("Deterministic Random AI", "An seemingly random Ai, with some determinism");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // hash of each board state?
        return null;
    }
}
