/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;
import larp.model.inventory.*;
import larp.model.*;
import java.util.*;
/**
 *
 * @author Andrew Poss
 */
public class Chest extends RoomObject{
    
    private static final String DEFAULT_IMG = "/img/chest/default.png";
    private static final String DEFAULT_NAME = "SomeChest";
    
    public Chest(){
        this(0,0,0,0,DEFAULT_IMG,true,DEFAULT_NAME);
    }
    
    public Chest(int xPos, int yPos, int hitWidth, int hitHeight, boolean blockable){
        this(xPos, yPos, hitWidth, hitHeight, DEFAULT_IMG, blockable, DEFAULT_NAME);
    }
    
   public Chest(int xPos, int yPos, int hitWidth, int hitHeight, String imgPath,
           boolean blockable, String name){
       super(blockable, name, xPos, yPos, hitWidth, hitHeight, imgPath);
   }
    
   Random rand = new Random();
    //returns an item
  public Item getItem() {
    
   ArrayList <Item> temp = DGame.getLoot();
      return temp.get(rand.nextInt(temp.size()));
  }
   
}
