package pl.pacinho.pacman.view.components.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pacinho.pacman.utils.ImageUtils;

import javax.swing.*;

@RequiredArgsConstructor
public enum PacmanDirection {

    UP(Direction.UP, 270, ImageUtils.getImage("pacman-up.gif")),
    DOWN(Direction.DOWN, 90, ImageUtils.getImage("pacman-down.gif")),
    LEFT(Direction.LEFT, 180, ImageUtils.getImage("pacman-left.gif")),
    RIGHT(Direction.RIGHT, 0, ImageUtils.getImage("pacman-right.gif"));

    @Getter
    private final Direction direction;
    @Getter
    private final int rotation;
    @Getter
    private final ImageIcon icon;
}
