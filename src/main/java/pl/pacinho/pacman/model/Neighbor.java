package pl.pacinho.pacman.model;

import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.enums.Direction;

public record Neighbor(Direction direction, GameObject gameObject) {
}
