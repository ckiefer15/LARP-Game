/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.inventory;
import larp.model.graphic.*;

/**
 *
 * @author Andrew Poss
 */
public class Item {
    
    private StaticImage image;
    
    /**
     * This method returns the static image of an inventory item 
     * @return The static image of and item 
     */
  public StaticImage getImage() {
    return image;
  }
}
