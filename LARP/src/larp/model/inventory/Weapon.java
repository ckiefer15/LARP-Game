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
public class Weapon extends Item {
    
    
    private int weaponDamage;
    
    
  /**
   * This method returns the amount of damage a weapon will deliver
   * @return returns the weapon damage
   */  
  public int getWeaponDamage() {
    return weaponDamage;
  }
    
}
