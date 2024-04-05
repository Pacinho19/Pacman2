package pl.pacinho.pacman.view.components.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum GhostType {

    YELLOW(4, 30, "ghost"),
    RED(5, 30, "ghost2"),
    PINK(6, 40, "ghost3"),
    GREEN(7, 40, "ghost4");

    @Getter
    private final int id;
    @Getter
    private final int speed;
    @Getter
    private final String name;

    public static GhostType fromValue(int value) {
        return Stream.of(GhostType.values())
                .filter(g -> g.id == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ghost identifier: " + value));
    }
}
