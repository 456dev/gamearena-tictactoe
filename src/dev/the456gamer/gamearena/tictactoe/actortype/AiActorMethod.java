package dev.the456gamer.gamearena.tictactoe.actortype;

public abstract class AiActorMethod extends BaseActorMethod {

    private final String name;
    private final String tooltip;

    public AiActorMethod(String name, String tooltip) {
        super();
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
}
