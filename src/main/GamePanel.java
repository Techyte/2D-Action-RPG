package main;

import entity.*;
import object.SuperObject;
import tile.TileManager;
import utils.Vector2;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    /// WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    boolean musicOn = true;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter AssetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Sound se = new Sound();
    public Sound music = new Sound();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);

    public static float deltaTime = 0;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){

        AssetSetter.SetObject();

        playMusic(0);
    }

    public void StartGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        deltaTime = 0;
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(deltaTime >= 1){
                update();
                repaint();
                deltaTime--;
                drawCount++;
            }

            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public Vector2 convertToWorldPos(Vector2 screenPos){
        return new Vector2(screenPos.x * tileSize, screenPos.y * tileSize);
    }

    public Vector2 convertToWorldPos(float x, float y){
        return new Vector2(x * tileSize, y * tileSize);
    }

    public Vector2 convertToScreenPos(Vector2 worldPos){
        return new Vector2(worldPos.x - player.position.x + player.screenPosition.x, worldPos.y - player.position.y + player.screenPosition.y);
    }

    public Vector2 convertToTileCord(Vector2 pixelPos){
        return new Vector2(pixelPos.x * tileSize, pixelPos.y * tileSize);
    }

    public boolean isInsideScreenBounds(Vector2 pos){

        if(pos.x + tileSize > (int)player.position.x - player.screenPosition.x &&
                pos.x - tileSize < (int)player.position.x + player.screenPosition.x &&
                pos.y + tileSize > (int)player.position.y - player.screenPosition.y &&
                pos.y - tileSize < (int)player.position.y + player.screenPosition.y){
            return true;
        }else{
            return false;
        }
    }

    public void update(){
        player.Update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileManager.draw(g2);

        // OBJECT
        for (int i = 0; i < AssetSetter.objects.length; i++){
            if(AssetSetter.objects[i] != null) {
                AssetSetter.objects[i].draw(g2, this);
            }
        }

        // PLAYER
        player.Draw(g2);

        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){

        if(musicOn) {
            music.setFile(i);
            music.play();
            music.loop();
        }
    }

    public void stopMusic(){

        if(musicOn) {
            music.stop();
        }
    }

    public void playSE(int i){

        if(musicOn) {
            se.setFile(i);
            se.play();
        }
    }
}