package main;

import object.OBJ_Key;
import utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gameFinished){

            String text = "You found the treasure!";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            Vector2 textPosition = new Vector2(gp.screenWidth/2 - textLength/2, gp.screenHeight/2 - (gp.tileSize*3));
            g2.drawString(text, textPosition.x, textPosition.y);

            text = "Time: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            textPosition = new Vector2(gp.screenWidth/2 - textLength/2, gp.screenHeight/2 + (gp.tileSize*4));
            g2.drawString(text, textPosition.x, textPosition.y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            textPosition = new Vector2(gp.screenWidth/2 - textLength/2, gp.screenHeight/2 + (gp.tileSize*2));
            g2.drawString(text, textPosition.x, textPosition.y);


        }else{

            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            playTime += (double) 1/60;

            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);

            if(messageOn){

                g2.setFont(g2.getFont().deriveFont(30));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

        if(gameFinished){
            gp.gameThread = null;
        }
    }
}
