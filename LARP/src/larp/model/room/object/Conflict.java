/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

import larp.model.ModelDefaults;
import larp.model.character.GameCharacter;
/**
 *
 * @author Andrew Poss
 */
public class Conflict extends RoomObject{
    
   private GameCharacter enemy;
   private boolean finalBoss;
   
   public Conflict(){
        this(ModelDefaults.CONFLICT_NAME,ModelDefaults.ENEMY_OBJ,true,
                0,0,0,0,ModelDefaults.CONFLICT_IMG);
    }
   
   public Conflict(int xPos, int yPos, int hitWidth, int hitHeight){
       this(ModelDefaults.CONFLICT_NAME,ModelDefaults.ENEMY_OBJ,true,xPos,
               yPos,hitWidth,hitHeight,ModelDefaults.CONFLICT_IMG);
   }
    
   public Conflict(String name, GameCharacter enemy, boolean finalBoss, int xPos,
           int yPos, int hitWidth, int hitHeight, String imgPath){
       super(true, name, xPos, yPos, hitWidth, hitHeight, imgPath);
       this.enemy = enemy;
       this.finalBoss = finalBoss;
   }
   
    /**
     * This method returns the enemy the user's knight will have to battle
     * @return returns the enemy character
     */
  public GameCharacter getEnemy() {
    return enemy;
  }
    
  public boolean isFinalBoss(){
      return finalBoss;
  }
}
