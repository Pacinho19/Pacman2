package pl.pacinho.pacman.view.components.logic.move.impl;

import pl.pacinho.pacman.utils.RandomUtils;
import pl.pacinho.pacman.view.components.logic.move.GhostMove;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.Wall;
import pl.pacinho.pacman.view.components.model.enums.Direction;

import java.util.List;
import java.util.stream.Stream;

public class GhostMoveRandom extends GhostMove {

    private final Ghost ghost;
    private final List<Wall> walls;

    public GhostMoveRandom(Ghost ghost, List<Wall> walls) {
        super(ghost);
        this.ghost = ghost;
        this.walls = walls;
    }


    @Override
    public void changeDirection() {
        if (getDirection() == null) {
            initDirection();
            return;
        }

        if (isMoveAvailable())
            return;

        randomAvailableDirection();
    }

    private void randomAvailableDirection() {
        List<Direction> availableDirections = Stream.of(Direction.values())
                .filter(dir -> !ghost.checkCollisionsWithWallsInNextMove(walls, dir, ghost))
                .toList();

        Direction direction;
        if (availableDirections.size() == 1)
            direction = availableDirections.get(0);
        else {
            int i = RandomUtils.randomInt(0, availableDirections.size()-1);
            direction = availableDirections.get(i);
        }

        setDirection(direction);
    }

    @Override
    public boolean isMoveAvailable() {
        return !ghost.checkCollisionsWithWallsInNextMove(walls, getDirection(), ghost);
    }

    private void initDirection() {
        Direction direction = Stream.of(Direction.values())
                .filter(dir -> !ghost.checkCollisionsWithWallsInNextMove(walls, dir, ghost))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid ghost position. Cannot move!"));

        setDirection(direction);
    }
}
