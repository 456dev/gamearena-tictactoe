package dev.the456gamer.gamearena.tictactoe.player.artificial;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;

/**
 * a player that always plays perfectly
 */
public class PerfectAiActor extends AiActor {

    public PerfectAiActor() {
        super("Perfect AI", "An Ai that always plays perfectly");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // TODO: Implement perfect AI
        return null;
    }
}
