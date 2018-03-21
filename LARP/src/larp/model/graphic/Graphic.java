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
    
    protected int x, y, xOff, yOff;
    
    public Graphic(){
        this(0,0,0,0);
    }
    
    public Graphic(int x, int y){
        this(x,y,0,0);
    }
    
    public Graphic(int x, int y, int xOff, int yOff){
        this.x = x;
        this.y = y;
        this.xOff = xOff;
        this.yOff = yOff;
    }
    
    public int getXCoordinate(){
        return x;
    }
    
    public void setXCoordinate(int x){
        this.x = x;
    }
    
    public int getYCoordinate(){
        return y;
    }
    
    public void setYCoordinate(int y){
        this.y = y;
    }
    
    public int getXOffset(){
        return xOff + x;
    }
    
    public void setXOffset(int xOff){
        this.xOff = xOff;
    }
    
    public int getYOffset(){
        return yOff + y;
    }
    
    public void setYOffset(int yOff){
        this.yOff = yOff;
    }
}
