package pl.pacinho.pacman.view.components.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public enum ObjectType {

    EMPTY(List.of(0)),
    WALL(List.of(1)),
    POINT(List.of(2)),
    PACMAN(List.of(3)),
    GHOST(List.of(4, 5, 6, 7));

    private final List<Object> identifiers;

    public static ObjectType fromIndex(Integer id) {
        return Arrays.stream(ObjectType.values())
                .filter(ot -> ot.identifiers.contains(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid level object index"));
    }
}
