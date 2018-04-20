/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import larp.model.*;
import larp.model.character.Knight;
import larp.model.inventory.*;
import larp.model.room.object.*;

/*

/**
 *
 * @author vf0975bh
 */
public class ChestTest {

    DGame testGame;
    //  Item testingItem = new Item("Red Potion", "/img/health/potionRed.png", true);
    // ArrayList<Inventory> testingInventory = new ArrayList<>();
    // Inventory testInventory = new Inventory();

    public ChestTest() {
        // testRoom = SetupDGame.getCurrentRoom();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testGame = SetupDGame.setupGame(true);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testChest() {
        Chest chest;
        int previousSize = testGame.getPlayer().getInventory().getArrayList().size();
        DRoom currentRoom = testGame.getCurrentRoom();
        // ArrayList<RoomObject> roomObj = currentRoom.getRoomObjects();

        for (RoomObject tempObj : currentRoom.getRoomObjects()) {
            if (tempObj instanceof Chest) {
                chest = (Chest) tempObj;
                testGame.openChest(chest);
                if (previousSize >= testGame.getPlayer().getInventory().getArrayList().size()) {
                    fail("Chest did not add item to player's inventory. Previous size: " + previousSize
                            + "; Current size: " + testGame.getPlayer().getInventory().getArrayList().size());
                }
                if (currentRoom.getRoomObjects().contains(chest)) {
                    fail("Chest has not been removed from the room!");
                }
                break;
            }
        }

        /*for(int i = 0; i < roomObj.size(); ++i){
         
     }*/
    }

    @Test
    public void fullInventory() {
        Item testingItem = new Item("Red Potion", "/img/health/potionRed.png", true);
        for (int i = 0; i < Inventory.MAX_SIZE; ++i) {
            testGame.getPlayer().addItemToInventory(testingItem);
        }
        Chest chest;
        DRoom currentRoom = testGame.getCurrentRoom();
        // ArrayList<RoomObject> roomObj = currentRoom.getRoomObjects();

        for (RoomObject tempObj : currentRoom.getRoomObjects()) {
            if (tempObj instanceof Chest) {
                chest = (Chest) tempObj;
                if(testGame.openChest(chest) != null){
                    fail("Player's inventory was full and an item was still added!");
                }
                if(chest.getItem() == null){
                    fail("Item was not saved in chest after trying to open!");
                }
                if (!currentRoom.getRoomObjects().contains(chest)) {
                    fail("Chest was removed from room but player inventory was full!");
                }
                break;
            }
        }
    }
}
