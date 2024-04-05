package pl.pacinho.pacman.tools;

import pl.pacinho.pacman.model.Position;
import pl.pacinho.pacman.utils.FileUtils;
import pl.pacinho.pacman.view.components.model.GameObject;
import pl.pacinho.pacman.view.components.model.enums.ObjectType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Levels {

    private static final String BASE_PATH = "levels/";

    public static GameObject[][] get(int level) {
        File levelFile = FileUtils.getFileFromResources(BASE_PATH + level);

        List<String> rows = FileUtils.readLines(levelFile);

        List<GameObject[]> list = new ArrayList<>();
        for (int y = 0; y < rows.size(); y++) {

            String line = rows.get(y);
            String[] split = line.split(" ");

            GameObject[] arr = new GameObject[split.length];
            for (int x = 0; x < split.length; x++) {
                int id = Integer.parseInt(split[x]);
                arr[x] = new GameObject(id, new Position(x, y), ObjectType.fromIndex(id));
            }
            list.add(arr);

        }
        return list.toArray(new GameObject[0][]);

    }
}
