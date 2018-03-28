/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

/**
 *
 * @author Andrew Poss
 */
public class Conflict extends RoomObject{
    
   private Character enemy;
    
    /**
     * This method returns the enemy the user's knight will have to battle
     * @return returns the enemy character
     */
  public Character getEnemy() {
    return enemy;
  }
    
}
