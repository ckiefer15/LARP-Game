/*
 * Right now this class represents just the player's sprite as it is hardcoded to find
 * the proper images. This class handles the sprite animation by iterating through
 * the image arrays that contain the frames for each direction.
 */
package larp.model.graphic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;

/**
 *
 * @author up6071fd
 */
public class Sprite extends Graphic{
    
    private Image [] left;
    private Image [] right;
    private Image [] up;
    private Image [] down;
    private int frame;
    private char direction;
    
    public Sprite(){
        super(100,100,20,20);
        left = new Image[4];
        right = new Image[4];
        up = new Image[4];
        down = new Image[4];
        
        for(int i = 0; i < left.length; i++){
            left[i] = new Image("img/player/sprite/L" + (i + 1) + ".png");
            right[i] = new Image("img/player/sprite/R" + (i + 1) + ".png");
            up[i] = new Image("img/player/sprite/U" + (i + 1) + ".png");
            down[i] = new Image("img/player/sprite/D" + (i + 1) + ".png");
        }
        vertices[2] = (int)left[0].getWidth();
        vertices[3] = (int)left[0].getHeight();
        direction = 'r';
        frame = 0;
    }
    
    public Sprite(int xPos, int yPos, int hitWidth, int hitHeight,
            String animPath, boolean testing){
        super(xPos, yPos, hitWidth, hitHeight);
        left = new Image[4];
        right = new Image[4];
        up = new Image[4];
        down = new Image[4];
        
        System.out.println("File path: " + new File("").getAbsolutePath());
        
        if(!testing){
            for(int i = 0; i < left.length; i++){
                left[i] = new Image(animPath + "/L" + (i + 1) + ".png");
                right[i] = new Image(animPath + "/R" + (i + 1) + ".png");
                up[i] = new Image(animPath + "/U" + (i + 1) + ".png");
                down[i] = new Image(animPath + "/D" + (i + 1) + ".png");
            }
            vertices[2] = (int)left[0].getWidth();
            vertices[3] = (int)left[0].getHeight();
        }
        direction = 'r';
        frame = 0;
    }
    
    public Image move(){
        if(frame + 1 < left.length)
            frame++;
        else
            frame = 0;
        return getFrame();
    }
    
    public Image stay(){
        frame = 0;
        return getFrame();
    }
    
    private Image getFrame(){
        switch(direction){
            case 'l':
                return left[frame];
            case 'r':
                return right[frame];
            case 'u':
                return up[frame];
            default:
                return down[frame];
        }
    }
    
    public void setDirection(char dir){
        if(dir == 'l' || dir == 'r' || dir == 'u' || dir == 'd')
            direction = dir;
    }
    
    /*
        The if statements must be < or > and never <= or >= as that would cause
        issues with proper direction setting. Example being if incoming x,y is
        5,0 and one of the y if statements is <= 0 then the direction will be set
        to the y direction even though it didn't change.
    */
    public void updateCoord(int x, int y){
        vertices[0] += x;
        vertices[1] += y;
        if(x > 0)
            direction = 'r';
        else if(x < 0)
            direction = 'l';
        else if(y > 0)
            direction = 'd';
        else if(y < 0)
            direction = 'u';
    }

    public Rectangle getCollisionBounds() {
        
        return new Rectangle(getXCoordinate(), getYCoordinate(), (int)left[0].getWidth(), (int)left[0].getHeight());
    }
    
    public Rectangle testingCollisionBounds(){
        return new Rectangle(getXCoordinate(),getYCoordinate(),getXOffset(),getYOffset());
    }
}
