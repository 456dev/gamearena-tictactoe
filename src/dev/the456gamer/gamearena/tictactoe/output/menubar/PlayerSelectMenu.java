package dev.the456gamer.gamearena.tictactoe.output.menubar;

import dev.the456gamer.gamearena.tictactoe.Actor;
import dev.the456gamer.gamearena.tictactoe.board.state.GameSide;
import dev.the456gamer.gamearena.tictactoe.output.GameWindow;
import javax.swing.JMenu;

/**
 * a menu for selecting how a gameSide is controlled 2 instances of this should be created, one for
 * each game gameSide
 * <p>
 * should allow selecting between each class type show id, hover text config ai move delay
 * <p>
 * TODO highlight while its that players turn
 *  draw icon ●, 🔴, ○, ⬤, ⭘ 🔵
 */
// symbol pair? ⭕❌
public class PlayerSelectMenu {

    private final JMenu menu;
    private final GameWindow window;
    private final GameSide side;

    public PlayerSelectMenu(GameWindow window, GameSide side) {
        this.window = window;
        this.side = side;
        // only null until first update, then never after (hopefully)
        // since its linked to game (to set the settings), it cant be pre-set.
        this.actor = null;
        menu = new JMenu("Player");


    }

    private Actor actor;

    public int getAiMoveDelay() {
        return 0;
    }

    public void setAiMoveDelay(int delay) {

    }


    public JMenu getMenu() {
        return menu;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void refresh() {
        setActor(window.getGame().getPlayerWithSide(side));
        menu.setText(side.getSymbol() + ": " + actor.getActiveMethod().getName());
        menu.setToolTipText(actor.getActiveMethod().getTooltip());
    }
}
