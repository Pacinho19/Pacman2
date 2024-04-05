package pl.pacinho.pacman.view.components.model;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pl.pacinho.pacman.config.GameProperties;
import pl.pacinho.pacman.utils.ImageUtils;
import pl.pacinho.pacman.view.components.logic.move.GhostMove;
import pl.pacinho.pacman.view.components.logic.move.impl.GhostMoveRandom;
import pl.pacinho.pacman.view.components.model.enums.GhostType;

import javax.swing.*;
import java.awt.*;

@Getter
public class Ghost extends Rectangle implements MoveObject {

    private ImageIcon imageIcon;
    private final GhostType ghostType;

    @Setter
    private GhostMove ghostMoveLogic;

    @SneakyThrows
    public Ghost(GhostType ghostType, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.ghostType = ghostType;
        this.width = GameProperties.CELL_WIDTH;
        this.height = GameProperties.CELL_HEIGHT;

        imageIcon = ImageUtils.getImage(ghostType.getName() + ".gif");
    }

    public void startTimer() {
        ghostMoveLogic.getTimer()
                .start();
    }
}
