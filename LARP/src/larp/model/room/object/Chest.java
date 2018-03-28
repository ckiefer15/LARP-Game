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
    
   
   Random rand = new Random();
    //returns an item
  public Item getItem() {
    
   ArrayList <Item> temp = DGame.getLoot();
      return temp.get(rand.nextInt(temp.size()));
  }
   
}
