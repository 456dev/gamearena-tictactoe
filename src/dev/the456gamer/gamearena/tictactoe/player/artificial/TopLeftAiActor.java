package dev.the456gamer.gamearena.tictactoe.player.artificial;

import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.player.AiActor;

public class TopLeftAiActor extends AiActor {

    public TopLeftAiActor() {
        super("Silly AI", "An Ai that plays top left to bottom right as squares are available");
    }


    @Override
    public Move getNextMove(BoardState state) {
        return state.getValidMoves().get(0);
    }
}
