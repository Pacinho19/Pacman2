package pl.pacinho.pacman.view.components.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {

    UP(0, -4),
    DOWN(0, 4),
    LEFT(-4, 0),
    RIGHT(4, 0);

    @Getter
    private final int xOffset, yOffset;

}
