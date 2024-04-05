package pl.pacinho.pacman.view;

import pl.pacinho.pacman.config.GameProperties;
import pl.pacinho.pacman.config.Properties;
import pl.pacinho.pacman.view.components.Board;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {

    private final int LEVEL;

    public GameBoard(int LEVEL, Dimension dimension) {
        this.LEVEL = LEVEL;
        this.setTitle(Properties.NAME + " " + Properties.VERSION);

        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
        this.setSize(dimension);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        initComponents();
        initView();
    }

    private void initView() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.add(board);
    }

    private void initComponents() {
        this.board = new Board(LEVEL, this);
        board.setFocusable(true);
        board.requestFocusInWindow();
    }

    public void start() {
        this.setVisible(true);
    }

    private Board board;

    public void newBoard() {
        this.getContentPane().removeAll();

        this.board = new Board(LEVEL, this);

        this.initView();
        this.repaint();

        pack();

        board.setFocusable(true);
        board.requestFocusInWindow();
    }
}
