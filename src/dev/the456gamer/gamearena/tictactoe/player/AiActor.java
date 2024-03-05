package dev.the456gamer.gamearena.tictactoe.player;

public abstract class AiActor implements Actor {

    private final String name;
    private final String tooltip;

    public AiActor(String name, String tooltip) {
        this.name = name;
        this.tooltip = tooltip;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public String getName() {
        return name;
    }

    //    getMoveDelay()
//    setMoveDelay()
}
