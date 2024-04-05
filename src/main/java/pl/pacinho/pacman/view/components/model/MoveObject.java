package pl.pacinho.pacman.view.components.model;

import pl.pacinho.pacman.view.components.model.enums.Direction;

import java.awt.*;
import java.util.List;


public interface MoveObject {

    default Integer[] getNextPosition(Direction direction, int x, int y) {
        if (direction == null)
            return null;

        return new Integer[]{
                x + direction.getXOffset(),
                y + direction.getYOffset()
        };
    }

    default boolean checkCollisionsWithWallsInNextMove(List<Wall> walls, Direction direction, Rectangle rectangle) {
        if(direction==null)
            return false;

        Integer[] pos = getNextPosition(direction, (int) rectangle.getX(), (int) rectangle.getY());
        if (pos == null)
            return false;

        return walls
                .stream()
                .anyMatch(w -> w.intersects(new Rectangle(pos[0], pos[1], rectangle.width, rectangle.height)));
    }

}
