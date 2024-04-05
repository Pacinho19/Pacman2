package pl.pacinho.pacman.view.components.tools;

import pl.pacinho.pacman.model.Neighbor;
import pl.pacinho.pacman.model.Pair;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.enums.Direction;

import java.util.List;
import java.util.Map;

public class NeighborUtils {

    private static final Map<Direction, Pair<Integer, Integer>> NEIGHBORS = Map.ofEntries(
            Map.entry(Direction.LEFT, new Pair<>(-1, 0)),
            Map.entry(Direction.UP, new Pair<>(0, -1)),
            Map.entry(Direction.DOWN, new Pair<>(0, 1)),
            Map.entry(Direction.RIGHT, new Pair<>(1, 0))
    );

    public static List<Neighbor> getNeighbors(int row, int col, GameObject[][] map) {
        return NEIGHBORS.entrySet()
                .stream()
                .filter(entry -> isValidNeighbor(row, col, entry.getValue(), map.length, map[0].length))
                .map(entry -> new Neighbor(entry.getKey(), map[row + entry.getValue().value()][col + entry.getValue().key()]))
                .toList();
    }

    private static boolean isValidNeighbor(int y, int x, Pair<Integer, Integer> offset, int height, int width) {
        int newX = x + offset.key();
        int newY = y + offset.value();

        if (newY < 0 || newY >= height)
            return false;

        if (newX < 0 || newX >= width)
            return false;

        return true;
    }
}
