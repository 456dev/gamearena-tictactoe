package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.actortype.ActorTypeStore;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class PlayerSelectItem {

    private final ActorTypeStore method;
    private final JRadioButtonMenuItem menuItem;

    public PlayerSelectItem(ActorTypeStore method) {
        this.method = method;
        menuItem = new JRadioButtonMenuItem(method.getActorMethod().getName());
        menuItem.setToolTipText(method.getActorMethod().getTooltip());
        menuItem.setActionCommand(method.name());
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public ActorTypeStore getMethod() {
        return method;
    }
}
