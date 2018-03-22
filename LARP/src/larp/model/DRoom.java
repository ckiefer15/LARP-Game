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
    StaticImage background;
    
    public DRoom(){
        
    }
    
    public ArrayList<RoomObject> getRoomObjects(){
        return roomObjects;
    }
    
    public Iterator iterator(){
        return roomObjects.iterator();
    }
}
