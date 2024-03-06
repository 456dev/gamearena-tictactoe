package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * GameSide that always plays the same moves, given the same board state.
 * moves are effectively random, as it doesn't try to win or lose
 */
public class DeterministicRandomActorMethod extends AiActorMethod {

    public DeterministicRandomActorMethod() {
        super("Deterministic Random AI", "An seemingly random Ai, with some determinism");
    }

    @Override
    public Move getNextMove(BoardState state) {
        if (state.getValidMoves().isEmpty()) {
            return null;
        }
        // hash of each board state?
        int seed = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                seed = seed * 3;
                seed += state.getTileState(i,j).ordinal();
            }
        }
        return state.getValidMoves().get(seed % state.getValidMoves().size());

    }
}
