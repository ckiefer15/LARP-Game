/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.character;

import larp.model.graphic.*;
import larp.model.inventory.*;
/**
 *
 * @author up6071fd
 */
public class Knight extends Character{
    
    private Weapon equippedWeapon;
    private Inventory inventory;
    private Sprite animation;
    
    public Knight(){
        equippedWeapon = null;
        inventory = null;
        animation = null;
    }
    
    public Knight(String name, int hitPoints, int damage, String animPath){
        super(name, hitPoints, damage);
        animation = new Sprite();
        inventory = new Inventory();
        equippedWeapon = null;
    }
    
    public Weapon getEquippedWeapon(){
        return equippedWeapon;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
    
    public Sprite getSprite(){
        return animation;
    }
    
    public int getDamage(){
        if(equippedWeapon == null)
            return super.getDamage();
        return equippedWeapon.getWeaponDamage() + super.getDamage();
    }
}
