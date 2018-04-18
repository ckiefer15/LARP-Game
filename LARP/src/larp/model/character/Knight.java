/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.character;

import larp.model.ModelDefaults;
import larp.model.room.object.Door;
import larp.model.graphic.*;
import larp.model.inventory.*;
/**
 *
 * @author up6071fd
 */
public class Knight extends GameCharacter{
    
    
    private Weapon equippedWeapon;
    private Inventory inventory;
    private Sprite animation;
    
    public Knight(){
        equippedWeapon = null;
        inventory = null;
        animation = null;
    }
    
    public Knight(String name, int xPos, int yPos, int hitWidth, int hitHeight,
            String animPath, boolean testing){
        this(name,50,10,xPos,yPos,hitWidth,hitHeight,ModelDefaults.PLAYER_IMG,animPath, testing);
    }
    
    public Knight(String name, int hitPoints, int damage, String animPath,
            String imgPath, boolean testing){
        super(name, hitPoints, damage,imgPath, testing);
        animation = new Sprite();
        inventory = new Inventory();
        equippedWeapon = null;
    }
    
    public Knight(String name, int hitPoints, int damage, int xPos, int yPos,
            int hitWidth, int hitHeight, String imgPath, String animPath, boolean testing){
        super(name, hitPoints, damage,imgPath, testing);
        animation = new Sprite(xPos, yPos, hitWidth, hitHeight, animPath, testing);
        inventory = new Inventory();
        equippedWeapon = null;
    }
    
    public Weapon getEquippedWeapon(){
        return equippedWeapon;
    }
    
    public void useItem(Item item){
        if(item != null){
            if(item instanceof Weapon){
                Weapon temp = equippedWeapon;
                equippedWeapon = (Weapon)inventory.removeItem(item);
                if(equippedWeapon != null)
                    inventory.addItem(temp);
                else
                    equippedWeapon = temp;
            }
            else if(item instanceof Health){
                item = inventory.removeItem(item);
                if(item != null)
                    super.healHitPoints(((Health) item).getHealthPoints());
            }
        }
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
    
    public void changeRoom(Door nextDoor){
        int [] offset = nextDoor.getPlayerSpawnOffset();
        int [] doorVertices = nextDoor.getImage().getVertices();
        
        int updateX, updateY;
        
        if(offset[0] < doorVertices[0]){  //  Spawn Left of Door
            updateX = 0 - animation.getXOffset();
            updateY = 0 - (animation.getYOffset() / 2);
            animation.setDirection('l');
        }
        else if(offset[1] < doorVertices[1]){     //  Spawn Above Door
            updateX = 0 - (animation.getXOffset() / 2);
            updateY = 0 - animation.getYOffset();
            animation.setDirection('u');
        }
        else{
            if(offset[0] > doorVertices[2] + doorVertices[0]){    //  Spawn Right of Door
                updateX = 0;
                updateY = 0 - (animation.getYOffset() / 2);
                animation.setDirection('r');
            }
            else{   //  Spawn Below Door
                updateX = 0 - (animation.getXOffset() / 2);
                updateY = 0;
                animation.setDirection('d');
            }
        }
        
        animation.setXCoordinate(updateX + offset[0]);
        animation.setYCoordinate(updateY + offset[1]);
    }
    
    private void printRoomUpdate(int [] door, int [] offset, int updateX, int updateY){
        System.out.println("\nCharacter is at X = " + animation.getXCoordinate()
        + "    Y = " + animation.getYCoordinate());
        System.out.println("Character width: " + animation.getXOffset()
                            + "\nCharacter height: " + animation.getYOffset());
        System.out.println("Update X : " + updateX
                            + "\nUpdate Y: " + updateY);
        System.out.println("Door offset is: (" + offset[0] + "," + offset[1] + ")");
        System.out.println("Door vertices are {"
                + "\n(" + door[0] + "," + door[1] + ")"
                + "\n(" + door[2] + "," + door[1] + ")"
                + "\n(" + door[0] + "," + door[3] + ")"
                + "\n(" + door[2] + "," + door[3] + ")}\n\n");
        
    }
}
