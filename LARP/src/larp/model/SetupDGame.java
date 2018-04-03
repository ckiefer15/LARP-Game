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
/**
 *
 * @author up6071fd
 */
public interface SetupDGame {
    
    static public final int TILE_SIZE = GameScreenController.TILE_SIZE;
    
    static final Knight player =
            new Knight("Bobby",50,10,100,100,20,20,null,"/img/player/sprite");
    static final GameCharacter enemy =
            new GameCharacter("Hank",50,10,null);
    
    public static DGame setupGame(){
        DGame game;
        ArrayList<DRoom> rooms = initRooms();
        game = new DGame(rooms,player);
        
        return game;
    }
    
    public static ArrayList<DRoom> initRooms(){
        ArrayList<DRoom> rooms = new ArrayList<>();
        
        ArrayList<RoomObject> objects;
        //==============Build Start Room=============
        objects = new ArrayList<>();
        objects.add(new RoomObject(true,"Thingy1",0,0,20 * TILE_SIZE,4 * TILE_SIZE,null));
        objects.add(new RoomObject(true,"Thingy2",400,400,50,50,null));
        objects.add(new RoomObject(true,"Thingy3",9 * TILE_SIZE,14 * TILE_SIZE,4 * TILE_SIZE,4 * TILE_SIZE,null));
        objects.add(new RoomObject(true,"Thingy4",200,500,50,50,null));
        rooms.add(new DRoom(objects,"/img/DungeonMap2.png"));
        
        //==============Build _____ Room==================
        
        
        return rooms;
    }
}
