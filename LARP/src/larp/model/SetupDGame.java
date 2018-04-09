/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.character.*;
import larp.model.graphic.*;
import larp.model.inventory.*;
import larp.model.room.object.*;
import larp.GameScreenController;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author up6071fd
 */
public interface SetupDGame {
    
    static public final int TILE_SIZE = GameScreenController.TILE_SIZE;
    static public final int PLAYER_X = 150;
    static public final int PLAYER_Y = 150;
    static public final int PLAYER_XOFF = 20;
    static public final int PLAYER_YOFF = 20;
    static public final int PLAYER_HEALTH = 50;
    static public final int PLAYER_DAMAGE = 10;
    
    public static DGame setupGame(boolean testing){
        
        //  Initialize rooms
        ArrayList<DRoom> rooms = initRooms(testing);
        //  Initialize loot
        ArrayList<Item> loot = initLoot(testing);
        //  Instantiate player
        Knight player = initPlayer(testing);
        //  Instantiate game
        DGame game = new DGame(loot,rooms,player);
        //  Add starting items to player's inventory
        initInventory(player,testing);
        
        return game;
    }
    
    public static ArrayList<DRoom> initRooms(boolean testing){
        ArrayList<DRoom> rooms = new ArrayList<>();
        
        ArrayList<RoomObject> objects;
        //==============Build Start Room=============
        objects = new ArrayList<>();
        objects.add(new RoomObject(true,"Thingy1",0,0,20 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        //objects.add(new RoomObject(true,"Thingy2",400,400,50,50,null));
        //objects.add(new RoomObject(true,"Thingy3",9 * TILE_SIZE,14 * TILE_SIZE,4 * TILE_SIZE,4 * TILE_SIZE,null));
        //objects.add(new RoomObject(true,"Thingy4",200,500,50,50,null));
        objects.add(new Door("mainL",'r',1,"battleR",0,300,100,100,testing));
        objects.add(new Door("mainB",'u',1,"battleT",200,550,100,100,testing));
        rooms.add(new DRoom(objects,"/img/DungeonMap2.png",testing));
        
        //==============Build _____ Room==================
        objects = new ArrayList<>();
        objects.add(new Door("battleR",'l',0,"mainL",600,100,100,100,testing));
        objects.add(new Door("battleT",'d',0,"mainB",100,10,100,100,testing));
        rooms.add(new DRoom(objects,"/img/FightMap.png",testing));
        
        return rooms;
    }
    
    /**
     * Use this method to instantiate the available loot items for DGame.
     * @return Returns an ArrayList of loot items to pass to DGame.
     */
    public static ArrayList<Item> initLoot(boolean testing){
        ArrayList<Item> loot = new ArrayList<>();
        
        loot.add(new Health());
        
        return loot;
    }
    
    /**
     * Use this method to setup the player's initial inventory items. Make sure
     * DGame is instantiated first!
     */
    public static void initInventory(Knight player, boolean testing){
        if(DGame.getInstance() != null){
            player.addItemToInventory(DGame.getLoot(ModelDefaults.HEALTH_NAME));
        }
        else{
            player.addItemToInventory(new Health());
        }
    }
    
    public static Knight initPlayer(boolean testing){
        return new Knight("Bobby",PLAYER_HEALTH,PLAYER_DAMAGE,PLAYER_X,PLAYER_Y,
                    PLAYER_XOFF,PLAYER_YOFF,null,"/img/player/sprite",testing);
    }
}
