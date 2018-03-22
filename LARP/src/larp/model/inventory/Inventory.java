/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.inventory;
import java.util.*;

/**
 *
 * @author Andrew Poss
 */
public class Inventory {
    
    private ArrayList<Item> inventory;
    
    /**
     * This method returns the knight's inventory items
     * @return the ArrayList of items
     */
    public ArrayList getArrayList(){
       
        return inventory;
    }
    
    /**
     * This method returns an iterator for the knight's inventory
     * @return returns the iterator for the inventory  
     */
    public Iterator getIterator(){
       
        return inventory.iterator();
    }
    
}
