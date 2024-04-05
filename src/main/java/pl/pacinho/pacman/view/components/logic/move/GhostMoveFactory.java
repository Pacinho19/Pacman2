package pl.pacinho.pacman.view.components.logic.move;

import pl.pacinho.pacman.view.components.logic.move.impl.GhostMoveFollowingPlayer;
import pl.pacinho.pacman.view.components.logic.move.impl.GhostMoveRandom;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.Pacman;
import pl.pacinho.pacman.view.components.model.Wall;

import java.util.List;

public class GhostMoveFactory {
    public static GhostMove getGhostMoveType(Ghost ghost, List<Wall> walls, Pacman pacman) {
        switch (ghost.getGhostType()) {
            case YELLOW, RED -> {
                return new GhostMoveFollowingPlayer(ghost, pacman, walls);
            }
            case PINK, GREEN -> {
                return new GhostMoveRandom(ghost, walls);
            }
        }
        return null;
    }
}
