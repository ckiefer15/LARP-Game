/*
 * This class is a super class of all objects that will be drawn by the canvas,
 * holding the x and y coordinates plus the offsets for each coordinate.
 */
package larp.model.graphic;

/**
 *
 * @author up6071fd
 */
public class Graphic {
    
    protected int [] vertices;
    
    public Graphic(){
        this(0,0,0,0);
    }
    
    public Graphic(int x, int y){
        this(x,y,0,0);
    }
    
    public Graphic(int x, int y, int xOff, int yOff){
        vertices = new int[4];
        vertices [0] = x;
        vertices [1] = y;
        vertices [2] = xOff;
        vertices [3] = yOff;
    }
    
    public int getXCoordinate(){
        return vertices[0];
    }
    
    public void setXCoordinate(int x){
        vertices[0] = x;
    }
    
    public int getYCoordinate(){
        return vertices[1];
    }
    
    public void setYCoordinate(int y){
        vertices[1] = y;
    }
    
    public int getXOffset(){
        return vertices[2];
    }
    
    public void setXOffset(int xOff){
        vertices[2] = xOff;
    }
    
    public int getYOffset(){
        return vertices [3];
    }
    
    public void setYOffset(int yOff){
        vertices[3] = yOff;
    }
    
    public int [] getVertices(){
        return vertices;
    }
}
