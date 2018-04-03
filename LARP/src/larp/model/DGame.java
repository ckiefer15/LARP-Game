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
            int temp = door.getNextRoom();
            if(temp < rooms.size() && temp >= 0){
                currentRoom = rooms.get(temp);
                return currentRoom;
            }
        }
        return currentRoom;
    }
}
