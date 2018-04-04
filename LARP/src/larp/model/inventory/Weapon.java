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
public class Weapon extends Item {
    
    private int weaponDamage;
    
    public Weapon(){
        this(1,ModelDefaults.WEAPON_NAME,0,0,0,0,ModelDefaults.WEAPON_IMG);
    }
    
    public Weapon(int damage, String name, int xPos, int yPos, int hitWidth,
            int hitHeight, String imgPath){
        super(name, xPos, yPos, hitWidth, hitHeight, imgPath);
        if(damage > 0)
            weaponDamage = damage;
        else
            weaponDamage = 1;
    }
  /**
   * This method returns the amount of damage a weapon will deliver
   * @return returns the weapon damage
   */  
  public int getWeaponDamage() {
    return weaponDamage;
  }
    
}
