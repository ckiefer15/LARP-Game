/*
 *
 */
package larp.model.room.object;

import larp.model.inventory.*;
import larp.model.*;
import java.util.*;

/**
 *
 * @author Andrew Poss
 */
public class Chest extends RoomObject {

    private Item loot;

    public Chest() {
        this(0, 0, 0, 0, ModelDefaults.CHEST_IMG, true, ModelDefaults.CHEST_NAME, false);
    }

    public Chest(int xPos, int yPos, int hitWidth, int hitHeight, boolean blockable, boolean testing) {
        this(xPos, yPos, hitWidth, hitHeight, ModelDefaults.CHEST_IMG, blockable, ModelDefaults.CHEST_NAME,
                testing);
    }

    public Chest(int xPos, int yPos, int hitWidth, int hitHeight, String imgPath,
            boolean blockable, String name, boolean testing) {
        super(blockable, name, xPos, yPos, hitWidth, hitHeight, imgPath, testing);
        loot = null;
    }

    //returns an item
    public Item getItem() {
        if (loot == null) {
            ArrayList<Item> temp = DGame.getLoot();
            Random rand = new Random();
            loot = temp.get(rand.nextInt(temp.size()));
            return loot;
        }
        return loot;
    }

}
