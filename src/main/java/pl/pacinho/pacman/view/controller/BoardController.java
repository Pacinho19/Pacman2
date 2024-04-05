package pl.pacinho.pacman.view.controller;

import lombok.Getter;
import pl.pacinho.pacman.view.components.Board;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.Pacman;
import pl.pacinho.pacman.view.components.model.enums.PacmanDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

public class BoardController {
    private final Board board;
    private final int DELAY = 20;
    @Getter
    private Timer timer;
    private Pacman pacman;

    @Getter
    private boolean gameOver;

    public BoardController(Board board) {
        this.board = board;
        this.timer = new Timer(DELAY, board);
        this.pacman = board.getPacman();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE && !timer.isRunning()) {
            start();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            restart();
        } else if (timer.isRunning()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                pacman.setTempDirection(PacmanDirection.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                pacman.setTempDirection(PacmanDirection.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                pacman.setTempDirection(PacmanDirection.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                pacman.setTempDirection(PacmanDirection.RIGHT);
            }
        }
    }

    private void restart() {
        board.getGameBoard()
                .newBoard();
    }

    private void start() {
        if (gameOver) {
            restart();
            return;
        }

        timer.start();
        board.getGhosts()
                .forEach(Ghost::startTimer);
    }

    public void gameTick() {
        checkChangeDirection();

        boolean collisionsWithWallsInNextMove = pacman.getPacmanDirection() != null
                                                && pacman.checkCollisionsWithWallsInNextMove(board.getWalls(), pacman.getPacmanDirection().getDirection(), pacman);
        if (!collisionsWithWallsInNextMove)
            pacman.move();

        checkCollisionWithGhosts();

        collectPoints();
        refresh();
    }

    private void checkCollisionWithGhosts() {
        boolean collisionWithGhost = board.getGhosts()
                .stream()
                .anyMatch(g -> g.intersects(pacman.x, pacman.y, pacman.width, pacman.height));

        if (!collisionWithGhost)
            return;

        timer.stop();
        gameOver = true;
    }

    private void checkChangeDirection() {
        if (pacman.getPacmanDirection() == pacman.getTempDirection())
            return;

        boolean collisionsWithWallsInNextMove = pacman.getPacmanDirection() != null
                                                && pacman.checkCollisionsWithWallsInNextMove(board.getWalls(), pacman.getTempDirection().getDirection(), pacman);
        if (!collisionsWithWallsInNextMove)
            pacman.setPacmanDirection(pacman.getTempDirection());

    }

    private void collectPoints() {
        board.setPoints(
                board.getPoints()
                        .stream()
                        .filter(p -> !p.intersects(pacman))
                        .collect(Collectors.toList())
        );
    }


    private void refresh() {
        board.repaint();
        board.revalidate();
        board.validate();
    }

}
