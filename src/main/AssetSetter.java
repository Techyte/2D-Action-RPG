package main;

import object.*;
import utils.Vector2;

public class AssetSetter {

    public SuperObject objects[] = new SuperObject[10];

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void SetObject(){
        objects[0] = new OBJ_Key();
        objects[0].worldPosition = new Vector2(23 * gp.tileSize, 7 * gp.tileSize);

        objects[1] = new OBJ_Key();
        objects[1].worldPosition = new Vector2(23 * gp.tileSize, 40 * gp.tileSize);

        objects[2] = new OBJ_Key();
        objects[2].worldPosition = new Vector2(38 * gp.tileSize, 8 * gp.tileSize);

        objects[3] = new OBJ_Door();
        objects[3].worldPosition = new Vector2(10 * gp.tileSize, 11 * gp.tileSize);

        objects[4] = new OBJ_Door();
        objects[4].worldPosition = new Vector2(8 * gp.tileSize, 28 * gp.tileSize);

        objects[5] = new OBJ_Door();
        objects[5].worldPosition = new Vector2(12 * gp.tileSize, 22 * gp.tileSize);

        objects[6] = new OBJ_Chest();
        objects[6].worldPosition = new Vector2(10 * gp.tileSize, 7 * gp.tileSize);

        objects[7] = new OBJ_Boots();
        objects[7].worldPosition = new Vector2(37 * gp.tileSize, 42 * gp.tileSize);
    }
}
