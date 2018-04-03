/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.graphic;

import javafx.scene.image.Image;
/**
 * A static image with positional and boundary coordinates.
 * 
 * @author up6071fd
 */
public class StaticImage extends Graphic{
    
    private String path;
    private Image image;
    
    /**
     * Sets everything to null and does nothing else.
     */
    public StaticImage(){
        super();
        path = "NO IMAGE";
        image = null;
    }
    
    public StaticImage(String imgPath){
        this(0,0,0,0,imgPath);
    }
    
    /**
     * This constructor will used the passed x and y coordinates and path but will
     * set the x offset and y offset both to 0.
     * @param x The x coordinate for positioning the image
     * @param y The y coordinate for positioning the image
     * @param path The relative or absolute path to the image to be used
     */
    public StaticImage(int x, int y, String path){
        this(x, y, 0, 0, path);
    }
    
    /**
     * This constructor will instantiate everything for this StaticImage object.
     * @param x The x coordinate for positioning the image
     * @param y The y coordinate for positioning the image
     * @param xOff The x offset to define boundaries for other purposes
     * @param yOff The y offset to define boundaries for other purposes
     * @param path The relative or absolute path to the image to be used
     */
    public StaticImage(int x, int y, int xOff, int yOff, String path){
        super(x,y,xOff,yOff);
        this.path = path;
        image = new Image(path);
    }
    
    /**
     * The method will return this StaticImage object's file path.
     * @return Returns a String object.
     */
    public String getPath(){
        return path;
    }
    
    /**
     * The method will return this StaticImage object's JavaFX Image object.
     * @return Returns a JavaFX Image object.
     */
    public Image getStaticImage(){
        return image;
    }
    
    /**
     * Handles StaticImage input verification and returns the appropriate
     * StaticImage object based on the input. xPos AND yPos must be positive or
     * will default to 0 for both, hitWidth and hitHeight must be positive or
     * will default to 0 for both. Null or empty string for image path will result
     * in a default image being used.
     * @param xPos The x position coordinate for the image.
     * @param yPos The y position coordinate for the image.
     * @param hitWidth The hit box width represented for
     * @param hitHeight
     * @param imgPath
     * @return Returns an instantiated StaticImage object based on input.
     */
    public static StaticImage makeImage(int xPos, int yPos, int hitWidth, int hitHeight,
            String imgPath){
        if(imgPath != null && imgPath.length() > 0){
            if(xPos >= 0 && yPos >= 0){
                if(hitWidth >= 0 && hitHeight >= 0)
                    return new StaticImage(xPos, yPos, hitWidth, hitHeight,
                    imgPath);
                else
                    return new StaticImage(xPos, yPos, imgPath);
            }
            else
                return new StaticImage(imgPath);
        }
        else{
            return new StaticImage();
        }
    }
}
