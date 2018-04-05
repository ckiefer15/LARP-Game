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
public class Knight extends GameCharacter{
    
    private static final String DEFAULT_IMG = "/img/player/default.png";
    
    private Weapon equippedWeapon;
    private Inventory inventory;
    private Sprite animation;
    
    public Knight(){
        equippedWeapon = null;
        inventory = null;
        animation = null;
    }
    
    public Knight(String name, int xPos, int yPos, int hitWidth, int hitHeight,
            String animPath){
        this(name,50,10,xPos,yPos,hitWidth,hitHeight,DEFAULT_IMG,animPath);
    }
    
    public Knight(String name, int hitPoints, int damage, String animPath,
            String imgPath){
        super(name, hitPoints, damage,imgPath);
        animation = new Sprite();
        inventory = new Inventory();
        equippedWeapon = null;
    }
    
    public Knight(String name, int hitPoints, int damage, int xPos, int yPos,
            int hitWidth, int hitHeight, String imgPath, String animPath){
        super(name, hitPoints, damage,imgPath);
        animation = new Sprite(xPos, yPos, hitWidth, hitHeight, animPath);
        inventory = new Inventory();
        equippedWeapon = null;
    }
    
    public Weapon getEquippedWeapon(){
        return equippedWeapon;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
    
    public void heal(){
        super.healHitPoints(inventory.heal());
    }
    
    public boolean addItemToInventory(Item item){
        return inventory.addItem(item);
    }
    
    public Sprite getSprite(){
        return animation;
    }
    
    @Override
    public int getDamage(){
        if(equippedWeapon == null)
            return super.getDamage();
        return equippedWeapon.getWeaponDamage() + super.getDamage();
    }
}
