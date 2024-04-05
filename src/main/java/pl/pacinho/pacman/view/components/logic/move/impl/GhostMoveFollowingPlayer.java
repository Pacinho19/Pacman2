package pl.pacinho.pacman.view.components.logic.move.impl;

import pl.pacinho.pacman.view.components.logic.move.GhostMove;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.Pacman;
import pl.pacinho.pacman.view.components.model.Wall;
import pl.pacinho.pacman.view.components.model.enums.Direction;

import java.util.List;

public class GhostMoveFollowingPlayer extends GhostMove {

    private final Ghost ghost;
    private final Pacman pacman;
    private final List<Wall> walls;

    public GhostMoveFollowingPlayer(Ghost ghost, Pacman pacman, List<Wall> walls) {
        super(ghost);
        this.ghost = ghost;
        this.pacman = pacman;
        this.walls = walls;
    }


    @Override
    public void changeDirection() {
        setDirectionBasedOnPlayerLocation();
    }

    private void setDirectionBasedOnPlayerLocation() {
        int distanceY = pacman.y - ghost.y;
        int distanceX = pacman.x - ghost.x;

        if (distanceY > pacman.height) {
            if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.DOWN, ghost) && getDirection() != Direction.UP)
                setDirection(Direction.DOWN);
            else {
                if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.RIGHT, ghost) && getDirection() != Direction.LEFT)
                    setDirection(Direction.RIGHT);
                else if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.LEFT, ghost) && getDirection() != Direction.RIGHT)
                    setDirection(Direction.LEFT);
                else
                    setDirection(Direction.UP);
            }

        } else if (distanceY != 0 && distanceY < pacman.height) {
            if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.UP, ghost) && getDirection() != Direction.DOWN)
                setDirection(Direction.UP);
            else {
                if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.RIGHT, ghost) && getDirection() != Direction.LEFT)
                    setDirection(Direction.RIGHT);
                else if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.LEFT, ghost) && getDirection() != Direction.RIGHT)
                    setDirection(Direction.LEFT);
                else
                    setDirection(Direction.DOWN);
            }
        } else if (distanceX > pacman.width) {
            if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.RIGHT, ghost) && getDirection() != Direction.LEFT)
                setDirection(Direction.RIGHT);
            else {
                if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.UP, ghost) && getDirection() != Direction.DOWN)
                    setDirection(Direction.UP);
                else if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.DOWN, ghost) && getDirection() != Direction.UP)
                    setDirection(Direction.DOWN);
                else
                    setDirection(Direction.LEFT);
            }
        } else if (distanceX != 0 && distanceX < pacman.width) {
            if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.LEFT, ghost) && getDirection() != Direction.RIGHT)
                setDirection(Direction.LEFT);
            else {
                if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.UP, ghost) && getDirection() != Direction.DOWN)
                    setDirection(Direction.UP);
                else if (!ghost.checkCollisionsWithWallsInNextMove(walls, Direction.UP, ghost) && getDirection() != Direction.UP)
                    setDirection(Direction.DOWN);
                else
                    setDirection(Direction.RIGHT);
            }
        }
    }


    @Override
    public boolean isMoveAvailable() {
        return !ghost.checkCollisionsWithWallsInNextMove(walls, getDirection(), ghost);
    }

}
