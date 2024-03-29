package dev.the456gamer.gamearena.tictactoe;

import dev.the456gamer.gamearena.tictactoe.actortype.ActorMethod;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;

public class Actor {

    private final GameSide side;
    private ActorMethod activeMethod;
    private int moveDelay = 100;

    public Actor(GameSide side, ActorMethod activeMethod) {
        this.side = side;
        this.activeMethod = activeMethod;
    }

    public GameSide getSide() {
        return side;
    }

    public ActorMethod getActiveMethod() {
        return activeMethod;
    }

    public void setActiveMethod(ActorMethod activeMethod) {
        this.activeMethod = activeMethod;
    }

    // only used for AI actors, but it's set on a player side level.
    public int getMoveDelay() {
        return moveDelay;
    }

    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }
}
