package dev.the456gamer.gamearena.tictactoe.actortype.artificial.nondeterministic;


import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * GameSide that plays random moves, the moves are not deterministic, unlike other Players
 * (excluding HumanPlayer)
 */
public class RandomRandomActorMethod extends AiActorMethod {

    public RandomRandomActorMethod(TicTacToeGame game) {
        super(game, "Random AI", "An Ai that plays random moves, Using non-deterministic RNG");
    }

    @Override
    public Move getNextMove(BoardState state) {
        return state.getValidMoves().get((int) (Math.random() * state.getValidMoves().size()));
    }
}
