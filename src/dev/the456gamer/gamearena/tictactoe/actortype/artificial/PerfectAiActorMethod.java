package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

/**
 * a gameSide that always plays perfectly
 */
public class PerfectAiActorMethod extends AiActorMethod {

    public PerfectAiActorMethod() {
        super("Perfect AI", "An Ai that always plays perfectly");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // TODO: Implement perfect AI
        return null;
    }
}
