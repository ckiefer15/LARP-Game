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
    
    
  /**
   * This method returns the amount of points a health inventory item will have
   * @return returns the health points of an inventory item
   */  
  public int getHealthPoints() {
    return healthPoints;
  }
    
}
