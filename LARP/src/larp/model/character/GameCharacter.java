/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.character;

import larp.model.graphic.StaticImage;
import javafx.scene.image.Image;
/**
 *
 * @author up6071fd
 */
public class GameCharacter {
    
    private int hitPoints;
    private int damage;
    private String name;
    private StaticImage image;
    
    /**
     * Default constructor instantiates needed attributes and variables.
     */
    public GameCharacter(){
        this("Bobby",9001,9001,null);
    }
    
    /**
     * This constructor will instantiate all attributes that are passed for the
     * character object.
     * @param name The name of the Character.
     * @param hitPoints How many hit points the Character has before dying.
     * @param damage How much base damage the Character does with each attack.
     */
    public GameCharacter(String name, int hitPoints, int damage, String imgPath){
        this.name = name;
        this.hitPoints = hitPoints;
        this.damage = damage;
        if(imgPath != null && imgPath.length() > 0)
            image = new StaticImage(imgPath);
        else
            image = new StaticImage();
    }
    
    public String getName(){
        return name;
    }
    
    public int getHitPoints(){
        return hitPoints;
    }
    
    /**
     * Heal the Character by the passed amount. This method will ensure healing
     * amount is greater than zero, if not then nothing is changed.
     * @param healing The amount to add to Character's hit points.
     */
    public void healHitPoints(int healing){
        if(healing <= 0)
            return;
        hitPoints += healing;
    }
    
    /**
     * Applies damage done to the Character's hit points. If hit points drop to
     * or below zero the character is dead and true will be returned, otherwise
     * false will be returned.
     * @param attackDamage The amount of damage to be subtracted to the Character's hit points.
     * @return Returns 'True' if character is dead, 'False' if Character still lives.
     */
    public boolean takeHit(int attackDamage){
        if(attackDamage >= hitPoints){
            hitPoints = 0;
            return true;
        }
        hitPoints -= attackDamage;
        return false;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public Image getImage(){
        return image.getStaticImage();
    }
}
