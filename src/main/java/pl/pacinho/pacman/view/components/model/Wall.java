package pl.pacinho.pacman.view.components.model;

import lombok.Getter;

import java.awt.*;

@Getter
public class Wall extends Rectangle {

    private int col;
    private int row;

    public Wall(int col, int row,  int x, int y, int width, int height) {
        super(x, y, width, height);
        this.col = col;
        this.row = row;
    }
}
