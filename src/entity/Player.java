package entity;

import main.*;
import utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public int hasKey = 0;

    public BufferedImage debug_white;

    public boolean canMove = true;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenPosition.x = gp.screenWidth/2-(gp.tileSize/2);
        screenPosition.y = gp.screenHeight/2-(gp.tileSize/2);

        solidArea = new Rectangle(7*gp.scale, 12*gp.scale, 2*gp.scale, 3*gp.scale);

        SetDefaultValues();
        GetPlayerImage();
    }

    public void SetDefaultValues(){
        position.x = gp.tileSize * 23;
        position.y = gp.tileSize * 21;
        speed = 4;
        directionName = "down";
    }

    public void GetPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Walking sprites/boy_right_2.png"));
            debug_white = ImageIO.read(getClass().getResourceAsStream("/debug_white.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Update(){

        Vector2 direction = new Vector2();

        if(canMove) {

            if (keyHandler.upPressed) {
                directionName = "up";
                direction.y -= 1;
            }

            if (keyHandler.downPressed) {
                directionName = "down";
                direction.y += 1;
            }

            if (keyHandler.leftPressed) {
                directionName = "left";
                direction.x -= 1;
            }

            if (keyHandler.rightPressed) {
                directionName = "right";
                direction.x += 1;
            }

            gp.cChecker.CheckTile(this, direction);

            pickUpObject(gp.cChecker.CheckObject(this, direction, true));

            position.Add(new Vector2(direction.x * speed * GamePanel.deltaTime, direction.y * speed * GamePanel.deltaTime));

            // Handle player sprite animating
            if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
                spriteCounter++;
                if(spriteCounter > 10){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }else{
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }else{
                spriteNum = 1;
            }
        }
    }

    public void pickUpObject(int index){

        if(index != 999){

            String objectName = gp.AssetSetter.objects[index].name;

            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.AssetSetter.objects[index] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.AssetSetter.objects[index] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else{
                        gp.ui.showMessage("Its locked...");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.AssetSetter.objects[index] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }

        }
    }

    public void Draw(Graphics2D g2){

        BufferedImage image = null;

        switch (directionName){
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else{
                    image = up2;
                }

                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else{
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else{
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else{
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, (int)screenPosition.x, (int)screenPosition.y, gp.tileSize, gp.tileSize, null);
    }
}
