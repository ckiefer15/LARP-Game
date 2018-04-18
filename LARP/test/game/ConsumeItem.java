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

import larp.model.character.Knight;
import larp.model.inventory.*;
import larp.model.*;
import java.util.ArrayList;
/**
 *
 * @author up6071fd
 */
public class ConsumeItem {
    
    private Knight player;
    private Health potion, elixir;
    
    public ConsumeItem() {
        potion = new Health("Potion",10,0,0,0,0,null,true);
        elixir = new Health("Elixir",100,0,0,0,0,null,true);
        player = new Knight("Bob",50,10,0,0,0,0,null,null,true);
        player.addItemToInventory(potion);
        player.addItemToInventory(elixir);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player.useItem(elixir);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConsume(){
        player.takeHit(potion.getHealthPoints() + 5);
        player.useItem(potion);
        if(player.getHitPoints() != player.getMaxHitPoints() - 5)
            fail("Player didn't heal properly; Max Hit Points = " + player.getMaxHitPoints()
                + "; After Damage = " + (player.getMaxHitPoints() - (potion.getHealthPoints() + 5))
                + "; Healing Applied = " + potion.getHealthPoints()
                + "; Current Health = " + player.getHitPoints());
        else
            testNonexistentHealthConsume(potion, player.getHitPoints());
    }
    
    public void testNonexistentHealthConsume(Item item, int currentHealth){
        player.useItem(item);
        if(currentHealth != player.getHitPoints())
            fail("Player has consumed an nonexistent " + item.getName() + " item!");
    }
    
    @Test
    public void testOverdose(){
        player.takeHit(player.getMaxHitPoints() / 2);
        player.useItem(elixir);
        if(player.getHitPoints() > player.getMaxHitPoints())
            fail("Player has overhealed! Max Hit Points = " + player.getMaxHitPoints()
            + "; Current Hit Points = " + player.getHitPoints());
        else{
            player.takeHit(player.getMaxHitPoints() / 2);
            testNonexistentHealthConsume(elixir, player.getHitPoints());
        }
    }
}
