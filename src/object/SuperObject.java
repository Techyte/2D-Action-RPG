package object;

import main.GamePanel;
import utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public Vector2 worldPosition;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public void draw(Graphics2D g2, GamePanel gp){

        Vector2 screenPosition = new Vector2(worldPosition.x - gp.player.position.x + gp.player.screenPosition.x, worldPosition.y - gp.player.position.y + gp.player.screenPosition.y);

        // If the object is beyond the view bounds (plus a bit extra just in case)
        if(worldPosition.x + gp.tileSize > (int)gp.player.position.x - gp.player.screenPosition.x &&
                worldPosition.x - gp.tileSize < (int)gp.player.position.x + gp.player.screenPosition.x &&
                worldPosition.y + gp.tileSize > (int)gp.player.position.y - gp.player.screenPosition.y &&
                worldPosition.y - gp.tileSize < (int)gp.player.position.y + gp.player.screenPosition.y){

            g2.drawImage(image, (int)screenPosition.x, (int)screenPosition.y, gp.tileSize, gp.tileSize, null);
        }
    }
}
