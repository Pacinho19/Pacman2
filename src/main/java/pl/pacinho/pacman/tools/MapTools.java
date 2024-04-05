package pl.pacinho.pacman.tools;

import pl.pacinho.pacman.model.Position;

import java.awt.*;

public class MapTools {
    public static Position getArrayPosition(Rectangle rect) {
        int x =  (int) Math.floor(rect.x / rect.width);
        int y = (int) Math.floor(rect.y / rect.height);

        return new Position(x, y);
    }
}
