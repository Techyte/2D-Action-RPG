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
        objects[0] = new OBJ_Key(gp);
        objects[0].worldPosition = gp.convertToWorldPos(23, 7);

        objects[1] = new OBJ_Key(gp);
        objects[1].worldPosition = gp.convertToWorldPos(23, 40);

        objects[2] = new OBJ_Key(gp);
        objects[2].worldPosition = gp.convertToWorldPos(38, 8);

        objects[3] = new OBJ_Door(gp);
        objects[3].worldPosition = gp.convertToWorldPos(10, 12);

        objects[4] = new OBJ_Door(gp);
        objects[4].worldPosition = gp.convertToWorldPos(8, 28);

        objects[5] = new OBJ_Door(gp);
        objects[5].worldPosition = gp.convertToWorldPos(12, 23);

        objects[6] = new OBJ_Chest(gp);
        objects[6].worldPosition = gp.convertToWorldPos(10, 8);

        objects[7] = new OBJ_Boots(gp);
        objects[7].worldPosition = gp.convertToWorldPos(37, 42);
    }
}
