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
    public static final int MAX_SIZE = 15;
    
    public Inventory(){
        inventory = new ArrayList<>();
    }
    
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
    
    /**
     * Search the inventory for a Health item and if found remove it from inventory
     * and return the amount of health the item provides.
     * @return Returns the amount of points to heal or 0 if no health item.
     */
    public int heal(){
        Item temp;
        for(int i = 0; i < inventory.size(); ++i){
            temp = inventory.get(i);
            if(temp instanceof Health){
                inventory.remove(i);
                return ((Health)temp).getHealthPoints();
            }
        }
        return 0;
    }
    
    /**
     * Add an item to the inventory list.
     * @param item The item to be added to inventory.
     * @return Returns true if item added or false if there's no space left.
     */
    public boolean addItem(Item item){
        if(MAX_SIZE > inventory.size() && item != null)
            return inventory.add(item);
        return false;
    }
    
    public Item removeItem(Item item){
        if(inventory.remove(item))
            return item;
        return null;
    }
}
