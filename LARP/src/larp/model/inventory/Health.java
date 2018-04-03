/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.inventory;

/**
 *
 * @author Andrew Poss
 */
public class Health extends Item{
    
    private int healthPoints;
    private static final String DEFAULT_IMG = "/img/health/default.png";
    private static final String DEFAULT_NAME = "SomePotion";
    
    public Health(){
        this(1,DEFAULT_NAME,0,0,0,0,DEFAULT_IMG);
    }
    
    public Health(int healPoints, String name, int xPos, int yPos, int hitWidth,
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
