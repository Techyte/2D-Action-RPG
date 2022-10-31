package tile;

import main.GamePanel;
import utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/assets/maps/world01.txt");
    }

    public void getTileImage(){
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/water.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/assets/Background tiles/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path){
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();
                while (col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            Vector2 worldPosition = new Vector2(worldCol * gp.tileSize, worldRow * gp.tileSize);
            Vector2 screenPosition = new Vector2(worldPosition.x - gp.player.position.x + gp.player.screenPosition.x, worldPosition.y - gp.player.position.y + gp.player.screenPosition.y);

            // If the tile is beyond the view bounds (plus a bit extra just in case)
            if(worldPosition.x + gp.tileSize > (int)gp.player.position.x - gp.player.screenPosition.x &&
                    worldPosition.x - gp.tileSize < (int)gp.player.position.x + gp.player.screenPosition.x &&
                    worldPosition.y + gp.tileSize > (int)gp.player.position.y - gp.player.screenPosition.y &&
                    worldPosition.y - gp.tileSize < (int)gp.player.position.y + gp.player.screenPosition.y){

                g2.drawImage(tiles[tileNum].image, (int)screenPosition.x, (int)screenPosition.y, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
