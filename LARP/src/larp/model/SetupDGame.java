/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.character.*;
import larp.model.inventory.*;
import larp.model.room.object.*;
import larp.GameScreenController;
import java.util.ArrayList;
/**
 *
 * @author up6071fd
 */
public interface SetupDGame {
    
    static public final int TILE_SIZE = GameScreenController.TILE_SIZE;
    static public final int PLAYER_X = 13 * TILE_SIZE;
    static public final int PLAYER_Y = 1 * TILE_SIZE;
    static public final int PLAYER_XOFF = 20;
    static public final int PLAYER_YOFF = 20;
    static public final int PLAYER_HEALTH = 250;
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
        DGame.setInstance(game);
        //  Add starting items to player's inventory
        initInventory(player,testing);
        
        return game;
    }
    
    public static ArrayList<DRoom> initRooms(boolean testing){
        ArrayList<DRoom> rooms = new ArrayList<>();
        
        ArrayList<RoomObject> objects;
        //==============Build Start Room=============
        objects = new ArrayList<>();
        //objects.add(new RoomObject(true,"Thingy1",x off,y off,length,height,null,testing));
        objects.add(new RoomObject(true,"Thingy1",0,0,12 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy2",14 * TILE_SIZE,0,6 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy3",0,0,2 * TILE_SIZE,6 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy4",18 * TILE_SIZE,0,2 * TILE_SIZE,11 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy5",0,8 * TILE_SIZE,2 * TILE_SIZE,12 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",8 * TILE_SIZE,0,2 * TILE_SIZE,8 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy7",10 * TILE_SIZE,6 * TILE_SIZE,2 * TILE_SIZE,3 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy8",11 * TILE_SIZE,7 * TILE_SIZE,2 * TILE_SIZE,3 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy9",12 * TILE_SIZE,8 * TILE_SIZE,2 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",14 * TILE_SIZE,9 * TILE_SIZE,1 * TILE_SIZE,2 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",19 * TILE_SIZE,11 * TILE_SIZE,1 * TILE_SIZE,1 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",2 * TILE_SIZE,11 * TILE_SIZE,4 * TILE_SIZE,3 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",5 * TILE_SIZE,7 * TILE_SIZE,1 * TILE_SIZE,7 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",4 * TILE_SIZE,11 * TILE_SIZE,1 * TILE_SIZE,7 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",4 * TILE_SIZE,15 * TILE_SIZE,5 * TILE_SIZE,3 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",7 * TILE_SIZE,9 * TILE_SIZE,1 * TILE_SIZE,5 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",8 * TILE_SIZE,11 * TILE_SIZE,5 * TILE_SIZE,3 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",0,17 * TILE_SIZE,3 * TILE_SIZE,2 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6", 3 * TILE_SIZE,19 * TILE_SIZE,17 * TILE_SIZE,1 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",10 * TILE_SIZE,18 * TILE_SIZE,10 * TILE_SIZE,1 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",18 * TILE_SIZE,17 * TILE_SIZE,2 * TILE_SIZE,1 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",16 * TILE_SIZE,13 * TILE_SIZE,4 * TILE_SIZE,2 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",17 * TILE_SIZE, 12 * TILE_SIZE,2 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        objects.add(new RoomObject(true,"Thingy6",18 * TILE_SIZE, 13 * TILE_SIZE,2 * TILE_SIZE,4 * TILE_SIZE,null,testing));
        
        GameCharacter skeleton = new GameCharacter("Skeleton", 20, 10, "/img/enemy/default.png", testing);
        objects.add(new Conflict("Skeleton Battle", skeleton, false, 15 * TILE_SIZE, 8 * TILE_SIZE, 1 * TILE_SIZE, 1 * TILE_SIZE, null, testing));
 
        objects.add(new Chest(2 * TILE_SIZE, 14 * TILE_SIZE,2 * TILE_SIZE, 1 * TILE_SIZE, true, testing));
        
        objects.add(new Door("mainL",'r',1,"battleR",0,6 * TILE_SIZE,1*TILE_SIZE,2*TILE_SIZE,null,testing));
        rooms.add(new DRoom(objects,"/img/DungeonMap2.png",testing));
        
        //==============Build _____ Room==================
        objects = new ArrayList<>();
        objects.add(new Door("battleR",'l',0,"mainL",600,100,100,100,null,testing));
        rooms.add(new DRoom(objects,"/img/FightMap.png",testing));
        return rooms;
    }
    
    /**
     * Use this method to instantiate the available loot items for DGame.
     * @return Returns an ArrayList of loot items to pass to DGame.
     */
    public static ArrayList<Item> initLoot(boolean testing){
        ArrayList<Item> loot = new ArrayList<>();
        
        loot.add(new Health("Red Potion", 25, 0,0,0,0, "/img/health/potionRed.png", testing));
        loot.add(new Health("Blue Potion", 50, 0,0,0,0, "/img/health/potionBlue.png", testing));
        loot.add(new Health("Green Potion", 100, 0,0,0,0, "/img/health/potionGreen.png", testing));
        loot.add(new Weapon(15, "Axe", 0,0,0,0,"/img/weapon/axe.png", testing));
        loot.add(new Weapon(20, "Double Axe", 0,0,0,0,"/img/weapon/axeDouble.png", testing));
        loot.add(new Weapon(20, "Hammer", 0,0,0,0,"/img/weapon/hammer.png", testing));
        loot.add(new Weapon(25, "Sword", 0,0,0,0,"/img/weapon/sword.png", testing));
        loot.add(new Weapon(35, "Upgraded Axe", 0,0,0,0,"/img/weapon/upg_axe.png", testing));
        loot.add(new Weapon(40, "Upgraded Hammer", 0,0,0,0,"/img/weapon/upg_hammer.png", testing));
        loot.add(new Weapon(50, "Upgraded Sword", 0,0,0,0,"/img/weapon/upg_sword.png", testing));
        loot.add(new Weapon(10, "Wooden Sword", 0,0,0,0,"/img/weapon/woodenSword.png", testing));
        
        
        return loot;
    }
    
    /**
     * Use this method to setup the player's initial inventory items. Make sure
     * DGame is instantiated first!
     */
    public static void initInventory(Knight player, boolean testing){
        if(DGame.getInstance() != null){
            player.addItemToInventory(DGame.getLoot("Red Potion"));
            player.addItemToInventory(DGame.getLoot("Red Potion"));
            player.addItemToInventory(DGame.getLoot("Red Potion"));
            player.addItemToInventory(DGame.getLoot("Sword"));
            player.addItemToInventory(DGame.getLoot("Hammer"));
            player.addItemToInventory(DGame.getLoot("Upgraded Sword"));
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
