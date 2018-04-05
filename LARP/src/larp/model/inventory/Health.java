/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.inventory;

import larp.model.ModelDefaults;
/**
 *
 * @author Andrew Poss
 */
public class Health extends Item{
    
    private int healthPoints;
    
    public Health(){
        this(ModelDefaults.HEALTH_NAME,20,0,0,0,0,ModelDefaults.HEALTH_IMG);
    }
    
    public Health(String name, int healPoints, int xPos, int yPos, int hitWidth,
            int hitHeight, String imgPath){
        super(name, xPos, yPos, hitWidth, hitHeight, imgPath);
        if(healPoints > 0)
            healthPoints = healPoints;
        else
            healthPoints = 1;
    }
    
  /**
   * This method returns the amount of points a health inventory item will have
   * @return returns the health points of an inventory item
   */  
  public int getHealthPoints() {
    return healthPoints;
  }
    
}
