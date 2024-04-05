package pl.pacinho.pacman;

import pl.pacinho.pacman.view.GameBoard;

import java.awt.*;

public class Pacman {

    private static final int LEVEL = 1;

    public static void main(String[] args) {
        new GameBoard(LEVEL, new Dimension(820, 820))
                .start();
    }
}
