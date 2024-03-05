package dev.the456gamer.gamearena.tictactoe.player.artificial;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;

/**
 * Player that actively tries to lose the game.
 */
public class StupidAiActor extends AiActor {

    public StupidAiActor() {
        super("Stupid AI", "An Ai that actively tries to lose the game");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // essentially the same as PerfectAiActor, but with the opposite goal (sort list worst to best ig)
    }
}
