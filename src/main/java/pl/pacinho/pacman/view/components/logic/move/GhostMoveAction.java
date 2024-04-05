package pl.pacinho.pacman.view.components.logic.move;

import java.awt.event.ActionListener;

public interface GhostMoveAction extends ActionListener {

    void changeDirection();

    boolean isMoveAvailable();
}
