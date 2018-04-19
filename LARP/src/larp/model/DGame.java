/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.inventory.Item;
import larp.model.room.object.*;
import larp.model.character.*;
import java.util.ArrayList;
/**
 *
 * @author up6071fd
 */
public class DGame {
    
    private static DGame instance = null;
    private final ArrayList<Item> LOOT;
    private ArrayList<DRoom> rooms;
    private Knight player;
    private DRoom currentRoom;
    private boolean gameStatus;
    
    protected DGame(){
        LOOT = new ArrayList<>();
    }
    
    public DGame(ArrayList<DRoom> rooms, Knight player){
        this(null,rooms,player);
    }
    
    public DGame(ArrayList<Item> loot, ArrayList<DRoom> rooms, Knight player){
        LOOT = loot;
        this.rooms = rooms;
        this.player = player;
        currentRoom = rooms.get(0);
        gameStatus = false;
    }
    
    /**
     * Get the set static instance for DGame, may be null if not set.
     * @return Returns the instance of DGame or null if no instance is set.
     */
    public static DGame getInstance(){
        return instance;
    }
    
    /**
     * Used to set the static instance of DGame so other classes can reference it.
     * @param game The game object to set the static instance to.
     */
    public static void setInstance(DGame game){
        instance = game;
    }
    
    private ArrayList instanceLoot(){
        return LOOT;
    }
    
    /**
     * Get the entire list of available game loot.
     * @return Returns a java.util.ArrayList listing the available game loot, or
     * returns null if no instance of DGame is set.
     */
    public static ArrayList getLoot(){
        if(instance == null)
            return null;
        return instance.instanceLoot();
    }
    
    /**
     * Use to statically search the current game's LOOT list for a specific item
     * by name.
     * @param itemName The item name to search for in LOOT list.
     * @return Returns the found Item object or null if the Item isn't in the list.
     */
    public static Item getLoot(String itemName){
        if(instance == null)
            return null;
        return instance.searchLoot(itemName);
    }
    
    private Item searchLoot(String itemName){
        for(Item item : LOOT){
            if(item.getName().equalsIgnoreCase(itemName))
                return item;
        }
        return null;
    }
    
    /**
     * Get the current room the player is in.
     * @return Returns the room the player is currently in.
     */
    public DRoom getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * This method returns the player's Knight object.
     * @return Returns the Knight object representing the player in game.
     */
    public Knight getPlayer(){
        return player;
    }
    
    /**
     * Get the status of the currently running game.
     * @return Returns TRUE if player has won the game, false if not.
     */
    public boolean getGameStatus(){
        return gameStatus;
    }
    
    /**
     * Pass the DGame object a door and it will look for and return the next room,
     * setting the current room to the new room. If the door passed is null or
     * the room the door points to doesn't exist the current room the player is
     * in will be returned.
     * @param door The door in the current room that points to the next room.
     * @return Returns either the next room and sets current room to it OR returns
     * the current room the player is in if there is an issue with the door.
     */
    public DRoom changeRoom(Door door){
        if(door != null){
            int nRoom = door.getNextRoom();
            String nDoor = door.getNextDoor();
            if(nRoom < rooms.size() && nRoom >= 0){
                DRoom tempRoom;
                tempRoom = rooms.get(nRoom);
                if(tempRoom != null){
                    for(RoomObject obj : tempRoom.getRoomObjects()){
                        if(obj instanceof Door && obj.getName().equalsIgnoreCase(nDoor)){
                            currentRoom = tempRoom;
                            player.changeRoom((Door)obj);
                            return currentRoom;
                        }
                    }
                }
            }
        }
        return currentRoom;
    }
    
    /**
     * Retrieve a Battle object using the passed conflict to setup the battle. A
     * Battle object handles the model manipulation and leaves action operations
     * up to the owner of the Battle object.
     * @param conflict The conflict that will become a battle!
     * @return Returns an instantiated Battle object.
     */
    public Battle initBattle(Conflict conflict){
        return new Battle(conflict, player);
    }
    
    protected void cleanupBattle(Conflict conflict){
        if(conflict.isFinalBoss()){
            gameStatus = true;
            return;
        }
        player.addItemToInventory(LOOT.get((int)(Math.random() * LOOT.size() + 0.5)));
        currentRoom.removeRoomObject(conflict);
    }
    
    /**
     * Open a chest, store the item in player's inventory, and remove chest from
     * room. If the player's inventory is full when the chest is opened the item
     * will remain in the chest and the chest will not be removed from the room.
     * @param chest The chest the player interacted with.
     * @return Returns the item found in the chest or null if the player inventory is full.
     */
    public Item openChest(Chest chest){
        Item item = chest.getItem();
        if(player.addItemToInventory(item)){
            currentRoom.removeRoomObject(chest);
            return item;
        }
        return null;
    }
}
