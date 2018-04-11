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
    
    public Chest(){
        this(0,0,0,0,ModelDefaults.CHEST_IMG,true,ModelDefaults.CHEST_NAME,false);
    }
    
    public Chest(int xPos, int yPos, int hitWidth, int hitHeight, boolean blockable, boolean testing){
        this(xPos, yPos, hitWidth, hitHeight, ModelDefaults.CHEST_IMG, blockable, ModelDefaults.CHEST_NAME,
                testing);
    }
    
   public Chest(int xPos, int yPos, int hitWidth, int hitHeight, String imgPath,
           boolean blockable, String name, boolean testing){
       super(blockable, name, xPos, yPos, hitWidth, hitHeight, imgPath,testing);
   }
    
   Random rand = new Random();
    //returns an item
  public Item getItem() {
    
   ArrayList <Item> temp = DGame.getLoot();
      return temp.get(rand.nextInt(temp.size()));
  }
   
}
