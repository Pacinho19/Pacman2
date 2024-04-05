package pl.pacinho.pacman.view.components.model;

import pl.pacinho.pacman.model.Position;
import pl.pacinho.pacman.view.components.model.enums.ObjectType;

public record GameObject(int value, Position position, ObjectType objectType) {
}
