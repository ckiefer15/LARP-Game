/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import larp.model.character.*;
import larp.model.inventory.*;
/**
 *
 * @author up6071fd
 */
public class SwapWeaponTest {
    
    private Knight player;
    private Weapon sword, axe;
    
    public SwapWeaponTest() {
        sword = new Weapon(10,"Sword",0,0,0,0,null,true);
        axe = new Weapon(20,"Axe",0,0,0,0,null,true);
        player = new Knight("Bob",50,10,0,0,0,0,null,null,true);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        while(player.getInventory().getArrayList().size() > 0){
            player.removeItemFromInventory(sword);
            player.removeItemFromInventory(axe);
        }
        player.addItemToInventory(sword);
        player.addItemToInventory(axe);
        player.setEquippedWeapon(null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void emptyHandSwapTest(){
        if(player.getEquippedWeapon() == null){
            player.useItem(sword);
            if(player.getEquippedWeapon() == sword){
                if(player.getInventory().removeItem(sword) != null)
                    fail("The equipped sword is still in inventory!");
            }
            else{
                if(player.getInventory().removeItem(sword) != null)
                    fail("The sword wasn't equipped and is still in inventory!");
                else
                    fail("The sword wasn't equipped and is no longer in inventory!");
            }
        }
        else
            fail("The emptyHandSwapTest wasn't initialized properly and player's"
                    + " equipped weapon is not null!");
    }
    
    @Test
    public void equippedWeaponSwapTest(){
        emptyHandSwapTest();
        player.useItem(axe);
        if(player.getEquippedWeapon() == axe){
            if(player.removeItemFromInventory(axe) != null){
                if(player.removeItemFromInventory(sword) != null)
                    fail("Player equipped axe, axe STILL IN inventory, sword was moved to inventory!");
                else
                    fail("Player equipped axe, axe STILL IN inventory, sword WASN'T moved to inventory!");
            }
            else{
                if(player.removeItemFromInventory(sword) == null)
                    fail("Player equipped axe, axe was removed from inventory, sword WASN'T moved to inventory!");
            }
        }
        else{
            if(player.removeItemFromInventory(axe) != null){
                if(player.removeItemFromInventory(sword) != null)
                    fail("Player DID NOT equip axe, axe STILL IN inventory, sword was moved to inventory!");
                else
                    fail("Player DID NOT equip axe, axe STILL IN inventory, sword WASN'T moved to inventory!");
            }
            else{
                if(player.removeItemFromInventory(sword) == null)
                    fail("Player DID NOT equip axe, axe was removed from inventory, sword WASN'T moved to inventory!");
            }
        }
    }
}
