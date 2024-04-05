package pl.pacinho.pacman.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageUtils {

    public static ImageIcon getImage(String fileName) {
        return new ImageIcon(ImageUtils.class.getClassLoader().getResource("img/" + fileName).getFile());
    }

    public static ImageIcon getScaledImage(String fileName, Dimension dimension) {
        return getScaledImage(
                getImage(fileName),
                dimension
        );
    }

    public static ImageIcon getScaledImage(ImageIcon imageIcon, Dimension dimension) {
        Image image = imageIcon.getImage();
        imageIcon.setImage(image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH));
        return imageIcon;
    }
}
