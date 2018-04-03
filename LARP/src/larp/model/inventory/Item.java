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
    private String name;
    
    public Item(String name, String imgPath){
        this(name,0,0,0,0,imgPath);
    }
    
    public Item(String name, int xPos, int yPos, int hitWidth, int hitHeight,
            String imgPath){
        image = StaticImage.makeImage(xPos, yPos, hitWidth, hitHeight, imgPath);
        if(name != null && name.length() > 0)
            this.name = name;
        else
            this.name = "NO_NAME";
    }
    
    /**
     * This method returns the static image of an inventory item 
     * @return The static image of and item 
     */
  public StaticImage getImage() {
    return image;
  }
}
