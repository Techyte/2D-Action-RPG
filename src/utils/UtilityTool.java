package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage origonal, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width, height, origonal.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(origonal, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
