package pl.pacinho.pacman.view.components.logic.move;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.enums.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Getter
public abstract class GhostMove implements GhostMoveAction {

    private final Ghost ghost;

    @Setter
    private Direction direction;

    private Timer timer;

    public GhostMove(Ghost ghost) {
        this.ghost = ghost;
        this.timer = new Timer(ghost.getGhostType().getSpeed(), this);
        this.direction = Direction.UP;
    }

    public void move() {
        if (direction == null)
            return;

        ghost.x += direction.getXOffset();
        ghost.y += direction.getYOffset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeDirection();
        if (isMoveAvailable())
            move();
    }
}
