package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.TicTacToeGame;
import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;

public class TopLeftAiActorMethod extends AiActorMethod {

    public TopLeftAiActorMethod(TicTacToeGame game) {
        super(game, "Silly AI", "An Ai that plays top left to bottom right as squares are available");
    }


    @Override
    public Move getNextMove(BoardState state) {
        return state.getValidMoves().get(0);
    }
}
