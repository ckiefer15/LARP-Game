/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;
import larp.model.graphic.*;
/**
 *
 * @author Andrew Poss
 */
public class RoomObject{
    StaticImage image;
    boolean blockable;
    
    public RoomObject(){
        blockable = false;
        image = null;
    }
    
  public RoomObject(StaticImage image, boolean blockable){
      this.image = image;
      this.blockable = blockable;
           
  }  
    
  //returns the room image
  public StaticImage getImage() {
    return image;
  } 
  
  public boolean isBlockable(){
           
      return blockable;
  }
    
}
