/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.inventory.Item;
import java.util.ArrayList;
/**
 *
 * @author up6071fd
 */
public class DGame {
    
    private final ArrayList<Item> LOOT;
    private static DGame instance = null;
    
    protected DGame(){
        LOOT = new ArrayList<>();
    }
    
    public static DGame getInstance(){
        if(instance == null)
            instance = new DGame();
        return instance;
    }
    
    private ArrayList instanceLoot(){
        return LOOT;
    }
    
    public static ArrayList getLoot(){
        if(instance == null)
            return null;
        return instance.instanceLoot();
    }
}
