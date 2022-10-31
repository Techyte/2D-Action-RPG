package entity;

import utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public Vector2 position = new Vector2();
    public Vector2 screenPosition = new Vector2();
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public String directionName;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
}
