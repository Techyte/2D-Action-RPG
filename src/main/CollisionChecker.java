package main;

import entity.Entity;
import utils.Vector2;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void CheckTile(Entity entity, Vector2 direction){

        int entityLeftWorldX = (int)(entity.position.x + direction.x) + entity.solidArea.x;
        int entityRightWorldX = (int)(entity.position.x + direction.x) + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = (int)(entity.position.y + direction.y) + entity.solidArea.y;
        int entityBottomWorldY = (int)(entity.position.y + direction.y) + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow;
        int entityBottomRow;

        int tileNum1, tileNum2;

        // Checks collision going up
        if(direction.y < 0){
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                direction.y = 0;
            }
        }

        // Checks collision going down
        if(direction.y > 0){
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                direction.y = 0;
            }
        }

        entityTopRow = entityTopWorldY/gp.tileSize;
        entityBottomRow = entityBottomWorldY/gp.tileSize;

        // Checks collision going left
        if(direction.x < 0){
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                direction.x = 0;
            }
        }

        // Checks collision going right
        if(direction.x > 0){
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                direction.x = 0;
            }
        }
    }

    public int CheckObject(Entity entity, Vector2 direction, boolean player){

        int index = 999;

        for (int i = 0; i < gp.AssetSetter.objects.length; i++){

            if(gp.AssetSetter.objects[i] != null) {

                Rectangle entitySolidArea = new Rectangle(0, 0, 48, 48);
                Rectangle objectSolidArea = new Rectangle(0, 0, gp.AssetSetter.objects[i].solidArea.width, gp.AssetSetter.objects[i].solidArea.height);

                entitySolidArea.x += entity.position.x;
                entitySolidArea.y += entity.position.y;

                objectSolidArea.x = (int) gp.AssetSetter.objects[i].worldPosition.x + gp.AssetSetter.objects[i].solidArea.x;
                objectSolidArea.y = (int) gp.AssetSetter.objects[i].worldPosition.y + gp.AssetSetter.objects[i].solidArea.y;

                if(direction.y < 0){
                    entitySolidArea.y -= entity.speed;
                    if (entitySolidArea.intersects(objectSolidArea)) {
                        if(player){
                            index = i;
                            if(gp.AssetSetter.objects[i].collision){
                                direction.y = 0;
                            }
                        }
                    }
                }

                if(direction.y > 0){
                    entitySolidArea.y += entity.speed;
                    if (entitySolidArea.intersects(objectSolidArea)) {
                        if(player){
                            index = i;
                            if(gp.AssetSetter.objects[i].collision){
                                direction.y = 0;
                            }
                        }
                    }
                }

                if(direction.x < 0){
                    entitySolidArea.x -= entity.speed;
                    if (entitySolidArea.intersects(objectSolidArea)) {
                        if(player){
                            index = i;
                            if(gp.AssetSetter.objects[i].collision){
                                direction.x = 0;
                            }
                        }
                    }
                }

                if(direction.x > 0){
                    entitySolidArea.x += entity.speed;
                    if (entitySolidArea.intersects(objectSolidArea)) {
                        if(player){
                            index = i;
                            if(gp.AssetSetter.objects[i].collision){
                                direction.x = 0;
                            }
                        }
                    }
                }
            }
        }

        return index;
    }
}
