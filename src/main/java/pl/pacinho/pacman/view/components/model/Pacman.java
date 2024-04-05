package pl.pacinho.pacman.view.components.model;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pl.pacinho.pacman.config.GameProperties;
import pl.pacinho.pacman.utils.ImageUtils;
import pl.pacinho.pacman.view.components.model.enums.PacmanDirection;

import javax.swing.*;
import java.awt.*;

@Getter
public class Pacman extends Rectangle implements MoveObject {

    private ImageIcon imageIcon;

    private PacmanDirection pacmanDirection;
    @Setter
    private PacmanDirection tempDirection;

    @SneakyThrows
    public Pacman() {
        this.x = -1;
        this.y = -1;
        imageIcon = ImageUtils.getImage("pacman-right.gif");
        this.width = GameProperties.CELL_WIDTH;
        this.height = GameProperties.CELL_HEIGHT;
    }

    public void move() {
        if (pacmanDirection == null)
            return;

        this.x += pacmanDirection.getDirection().getXOffset();
        this.y += pacmanDirection.getDirection().getYOffset();
    }


    public void setPacmanDirection(PacmanDirection pacmanDirection) {
        this.pacmanDirection = pacmanDirection;
        this.imageIcon = pacmanDirection.getIcon();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
