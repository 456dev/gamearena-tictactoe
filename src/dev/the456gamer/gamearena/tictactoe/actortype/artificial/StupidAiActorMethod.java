package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * GameSide that actively tries to lose the game.
 */
public class StupidAiActorMethod extends AiActorMethod {

    public StupidAiActorMethod(TicTacToeGame game) {
        super(game, "Stupid AI", "An Ai that actively tries to lose the game");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // essentially the same as PerfectAiActorMethod, but with the opposite goal (sort list worst to best ig)
        return null;
    }
}
