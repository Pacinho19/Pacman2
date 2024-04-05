package pl.pacinho.pacman.view.components;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.pacman.config.GameProperties;
import pl.pacinho.pacman.tools.Levels;
import pl.pacinho.pacman.utils.ImageUtils;
import pl.pacinho.pacman.view.GameBoard;
import pl.pacinho.pacman.view.components.logic.move.GhostMoveFactory;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.Ghost;
import pl.pacinho.pacman.view.components.model.Wall;
import pl.pacinho.pacman.view.components.model.enums.GhostType;
import pl.pacinho.pacman.view.components.model.enums.ObjectType;
import pl.pacinho.pacman.view.components.model.Pacman;
import pl.pacinho.pacman.view.components.tools.NeighborUtils;
import pl.pacinho.pacman.view.controller.BoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    @Getter
    private final GameBoard gameBoard;
    private final BoardController controller;
    private final Dimension dimension = GameProperties.BOARD_DIMENSION;
    private final int CELL_HEIGHT;
    private final int CELL_WIDTH;

    private final GameObject[][] map;

    @Getter
    private Pacman pacman;

    @Getter
    private List<Wall> walls;

    @Getter
    private List<Ghost> ghosts;

    @Setter
    @Getter
    private List<Ellipse2D> points;

    private ImageIcon gameOver;
    private ImageIcon start;

    public Board(int level, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.CELL_WIDTH = GameProperties.CELL_WIDTH;
        this.CELL_HEIGHT = GameProperties.CELL_HEIGHT;
        this.map = Levels.get(level);

        init();

        this.setLayout(new FlowLayout());

        this.setBackground(Color.BLACK);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
        this.setSize(dimension);

        this.setDoubleBuffered(true);

        this.controller = new BoardController(this);
    }

    private void init() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.keyPressed(e);
            }
        });

        pacman = new Pacman();
        walls = new ArrayList<>();
        points = new ArrayList<>();
        ghosts = new ArrayList<>();

        initObjects();
        initImages();
    }

    private void initImages() {
        this.gameOver = ImageUtils.getImage("game-over2.gif");
        this.start = ImageUtils.getImage("start.png");
    }

    private void initObjects() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                GameObject gameObject = map[i][j];
                if (gameObject.objectType() == ObjectType.WALL) {
                    initWalls(i, j);
                } else if (gameObject.objectType() == ObjectType.POINT) {
                    initPoints(i, j);
                } else if (gameObject.objectType() == ObjectType.PACMAN) {
                    initPacman(i, j);
                } else if (gameObject.objectType() == ObjectType.GHOST) {
                    initGhosts(gameObject.value(), i, j);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
    }

    private void drawMap(Graphics2D g2d) {
        drawWalls(g2d);
        fixWalls(g2d);
        drawPoints(g2d);
        drawPacman(g2d);
        drawGhosts(g2d);

        if (controller.isGameOver())
            drawGameOver(g2d);

        if (!controller.getTimer().isRunning() && !controller.isGameOver())
            drawStartScreen(g2d);

    }

    private void drawStartScreen(Graphics2D g2d) {
        g2d.drawImage(start.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private void fixWalls(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        walls.forEach(wall -> NeighborUtils.getNeighbors(wall.getRow(), wall.getCol(), map)
                .stream()
                .filter(neigh -> neigh.gameObject().objectType() == ObjectType.WALL)
                .forEach(neigh -> {
                            switch (neigh.direction()) {
                                case UP -> g2d.drawRect(wall.x + 1, wall.y, wall.width - 2, 1);
                                case DOWN -> g2d.drawRect(wall.x + 1, wall.y + wall.height + 1, wall.width - 2, 1);
                                case LEFT -> g2d.drawRect(wall.x, wall.y + 1, 1, wall.height - 2);
                                case RIGHT -> g2d.drawRect(wall.x + wall.width, wall.y + 1, 1, wall.height - 2);
                            }
                        }
                ));

    }

    private void drawGameOver(Graphics2D g2d) {
        int width = (int) (this.getWidth() * 0.5);
        int height = (int) (this.getHeight() * 0.5);
        g2d.drawImage(gameOver.getImage(), (this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height, null);
    }

    private void drawGhosts(Graphics2D g2d) {
        ghosts.forEach(ghost -> g2d.drawImage(ghost.getImageIcon().getImage(), (int) ghost.getX(), (int) ghost.getY(), (int) ghost.getWidth(), (int) ghost.getHeight(), this)
        );
    }

    private void drawWalls(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        walls.forEach(
                wall -> g2d.drawRect(wall.getCol() * CELL_WIDTH, wall.getRow() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT)
        );
    }

    private void drawPoints(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        points.forEach(
                g2d::fill
        );

    }

    private void drawPacman(Graphics2D g2d) {
//        if (pacman.getDirection() != null)
//            g2d.rotate(Math.toRadians(pacman.getDirection().getRotation()), pacman.getCenterX(), pacman.getCenterY()); //centerX and centerY is your center of rotation.
        g2d.drawImage(pacman.getImageIcon().getImage(), (int) pacman.getX(), (int) pacman.getY(), (int) pacman.getWidth(), (int) pacman.getHeight(), this);
    }

    private void initPacman(int i, int j) {
        pacman.setPosition(j * CELL_WIDTH, i * CELL_HEIGHT);
    }

    private void initWalls(int i, int j) {
        walls.add(
                new Wall(j, i, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT)
        );
    }

    private void initPoints(int i, int j) {
        Rectangle2D rect = getCellRectangle(i, j);
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + 5, centerY + 5);
        points.add(
                circle
        );
    }

    private void initGhosts(int value, int i, int j) {
        Ghost ghost = createGhost(i, j, GhostType.fromValue(value));
        ghost.setGhostMoveLogic(GhostMoveFactory.getGhostMoveType(ghost, walls, pacman));
        ghosts.add(ghost);
    }

    private Ghost createGhost(int i, int j, GhostType ghostType) {
        return new Ghost(ghostType, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }


    private Rectangle2D getCellRectangle(int i, int j) {
        return new Rectangle2D.Double(j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.gameTick();
    }

}
