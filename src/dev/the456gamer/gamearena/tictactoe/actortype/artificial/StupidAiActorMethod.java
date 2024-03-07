package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.ScoredMove;
import dev.the456gamer.gamearena.tictactoe.board.WinLocations;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * GameSide that actively tries to lose the game.
 */
public class StupidAiActorMethod extends AiActorMethod {

    public StupidAiActorMethod() {
        super("Stupid AI", "An Ai that actively tries to lose the game");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // ignore winning moves.
        List<Move> winningMoves = new ArrayList<>();

        // store winning moves for later: last resort
        for (Move move : state.getValidMoves()) {
            if (move.getBoardState().getGameState() == (state.getSideToMove() == GameSide.X
                ? GameState.X_WON : GameState.O_WON)) {
                winningMoves.add(move);
            }
        }

        // no winners.
        return state.getValidMoves().stream()
            .map(move -> new ScoredMove(move,
                    (int) WinLocations.getWinLocationsThroughPoint(move.x, move.y)
                        .stream()
                        .map(winLocations -> !winLocations.isBlocked(state))
                        .count()
                )
            ).min(Comparator.comparingInt(ScoredMove::score))
            .map(ScoredMove::move).orElse(winningMoves.stream().findAny().orElse(null));

    }
}
