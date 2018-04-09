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
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author up6071fd
 */
public class DRoom implements Iterable{
    
    ArrayList<RoomObject> roomObjects;
    ArrayList<RoomObject> blockableObjects;
    StaticImage background;
    
    public DRoom(ArrayList<RoomObject> roomObjects, String imgPath, boolean testing){
        this(roomObjects,0,0,0,0,imgPath,testing);
    }
    
    public DRoom(ArrayList<RoomObject> roomObjects, int xPos, int yPos,
            int width, int height, String imgPath, boolean testing){
        
        this.roomObjects = roomObjects;
        blockableObjects = buildBlockableList(roomObjects);
        background = StaticImage.makeImage(xPos, yPos, width, height, imgPath,testing);
    }
    
    private ArrayList<RoomObject> buildBlockableList(ArrayList<RoomObject> objects){
        ArrayList<RoomObject> blockable = new ArrayList<>();
        for(RoomObject obj : objects){
            if(obj.isBlockable())
                blockable.add(obj);
        }
        return blockable;
    }
    
    public StaticImage getImage(){
        return background;
    }
    
    public ArrayList<RoomObject> getRoomObjects(){
        return roomObjects;
    }
    
    public ArrayList<RoomObject> getBlockable(){
        return blockableObjects;
    }
    
    public Iterator iterator(){
        return roomObjects.iterator();
    }
    
    public void removeRoomObject(RoomObject obj){
        roomObjects.remove(obj);
    }
}
