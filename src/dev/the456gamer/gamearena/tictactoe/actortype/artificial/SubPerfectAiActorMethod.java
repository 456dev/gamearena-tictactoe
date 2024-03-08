package dev.the456gamer.gamearena.tictactoe.actortype.artificial;

import dev.the456gamer.gamearena.tictactoe.actortype.AiActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.Move;
import dev.the456gamer.gamearena.tictactoe.board.ScoredMove;
import dev.the456gamer.gamearena.tictactoe.board.WinLocations;
import dev.the456gamer.gamearena.tictactoe.board.state.BoardState;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.board.state.GameState;
import java.util.Comparator;

/**
 * a gameSide that always plays perfectly
 */
public class SubPerfectAiActorMethod extends AiActorMethod {

    public SubPerfectAiActorMethod() {
        super("Not quite Perfect AI", "An Ai that almost always plays perfectly");
    }

    @Override
    public Move getNextMove(BoardState state) {
        // logic
        // for each valid move, if it results in a win, take it
        // else look at how many ways left you can win include the square:
        // because more open = harder to block
        // higher is better

        // if no moves result in a win, take the highest score
        for (Move move : state.getValidMoves()) {
            if (move.getBoardState().getGameState() == (state.getSideToMove() == GameSide.X
                ? GameState.X_WON : GameState.O_WON)) {
                System.out.println("Winning move found");
                return move;
            }
        }

        // TODO doesnt handle case where opponent is 1 move away from wining
        // cant reasonably look ahead ?

        // no winners.
        return state.getValidMoves().stream()
            .map(move -> new ScoredMove(move,
                    (int) WinLocations.getWinLocationsThroughPoint(move.x, move.y)
                        .stream()
                        .map(winLocations -> !winLocations.isBlocked(state))
                        .count()
                )
            ).max(Comparator.comparingInt(ScoredMove::score))
            .map(ScoredMove::move).orElse(null);

    }


}
