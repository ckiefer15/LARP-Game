/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

import larp.model.ModelDefaults;
/**
 *
 * @author Andrew Poss
 */
public class Conflict extends RoomObject{
    
   private Character enemy;
   
   public Conflict(){
        this(0,0,0,0,ModelDefaults.ENEMY_IMG,true,ModelDefaults.ENEMY_NAME,null);
    }
    
    public Conflict(int xPos, int yPos, int hitWidth, int hitHeight, boolean blockable){
        this(xPos,yPos,hitWidth,hitHeight,ModelDefaults.ENEMY_IMG,blockable,ModelDefaults.ENEMY_NAME, null);
    }
    
   public Conflict(int xPos, int yPos, int hitWidth, int hitHeight, String imgPath,
           boolean blockable, String name, Character enemy){
       super(blockable, name, xPos, yPos, hitWidth, hitHeight, imgPath);
       this.enemy = enemy;
   }
   
    /**
     * This method returns the enemy the user's knight will have to battle
     * @return returns the enemy character
     */
  public Character getEnemy() {
    return enemy;
  }
    
}
