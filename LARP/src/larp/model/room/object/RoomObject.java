/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

import larp.model.graphic.*;
import larp.model.ModelDefaults;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author Andrew Poss
 */
public class RoomObject {
    
    public Rectangle bounds;
    
    StaticImage image;
    boolean blockable;
    String name;

    public RoomObject(StaticImage image, boolean blockable) {
        this.image = image;
        this.blockable = blockable;
        name = "No Name";
    }

    public RoomObject() {
        this(true,ModelDefaults.ROOM_OBJ_NAME,0,0,0,0,ModelDefaults.ROOM_OBJ_IMG,false);
    }
    
    public RoomObject(int xPos, int yPos, int hitWidth, int hitHeight, boolean testing){
        this(true,ModelDefaults.ROOM_OBJ_NAME,xPos,yPos,hitWidth,hitHeight,ModelDefaults.ROOM_OBJ_IMG, testing);
    }
    
    /**
     * Use this method to customize the setup of the room object. All inputs are
     * tested and set to defaults if input is invalid.
     * @param xPos The x coordinate for positioning the room object.
     * @param yPos The y coordinate for positioning the room object.
     * @param hitWidth The width of the hit box that represents this object.
     * @param hitHeight The height of the hit box that represents this object.
     * @param imgPath The path that points to the exact image file for this object.
     * @param blockable True means this object should block character's Knight,
     * false will not block. This isn't enforced by the room object and should
     * be enforced by what ever front end uses this model.
     * @param name The name of the object, primarily used for debugging.
     */
    public RoomObject(boolean blockable, String name, int xPos, int yPos,
            int hitWidth, int hitHeight, String imgPath, boolean testing){
        if(imgPath != null && imgPath.length() > 5)
            image = StaticImage.makeImage(xPos, yPos, hitWidth, hitHeight, imgPath, testing);
        else
            image = StaticImage.makeImage(xPos, yPos, hitWidth, hitHeight, ModelDefaults.ROOM_OBJ_IMG, testing);
            
        if(name != null && name.length() > 0)
            this.name = name;
        else
            this.name = ModelDefaults.ROOM_OBJ_NAME;
        this.blockable = blockable;
        bounds = new Rectangle(xPos, yPos, hitWidth, hitHeight);
    }
    
    //returns the room image
    public StaticImage getImage() {
        return image;
    }
    
    public String getName(){
        return name;
    }

    public boolean isBlockable() {

        return blockable;
    }
    
    public String toString(){
        return "RoomObject name: " + name +
                "\nBlockable?: " + blockable +
                "\nImage path: " + image.getPath();
    }
}
