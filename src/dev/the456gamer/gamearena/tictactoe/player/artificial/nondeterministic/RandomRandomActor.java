package dev.the456gamer.gamearena.tictactoe.player.artificial.nondeterministic;


import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;

/**
 * Player that plays random moves,
 * the moves are not deterministic, unlike other Players (excluding HumanPlayer)
 *
 */
public class RandomRandomActor extends AiActor {

    public RandomRandomActor(String name, String tooltip) {
        super("Random AI", "An Ai that plays random moves, Using non-deterministic RNG");
    }

    @Override
    public Move getNextMove(BoardState state) {
        return state.getValidMoves().get((int) (Math.random() * state.getValidMoves().size()));
    }
}
