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
    
    public DGame(){
        LOOT = new ArrayList<>();
    }
    
    public ArrayList getLoot(){
        return LOOT;
    }
}
