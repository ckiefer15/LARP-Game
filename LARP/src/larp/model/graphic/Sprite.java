/*
 * Right now this class represents just the player's sprite as it is hardcoded to find
 * the proper images. This class handles the sprite animation by iterating through
 * the image arrays that contain the frames for each direction.
 */
package larp.model.graphic;

import javafx.scene.image.Image;
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
        super(50,50,45,60);
        left = new Image[4];
        right = new Image[4];
        up = new Image[4];
        down = new Image[4];
        
        for(int i = 0; i < left.length; i++){
            left[i] = new Image("/img/sprite/player/L" + (i + 1) + ".png");
            right[i] = new Image("/img/sprite/player/R" + (i + 1) + ".png");
            up[i] = new Image("/img/sprite/player/U" + (i + 1) + ".png");
            down[i] = new Image("/img/sprite/player/D" + (i + 1) + ".png");
        }
        this.xOff = (int)left[0].getWidth();
        this.yOff = (int)left[0].getHeight();
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
    
    /*
        The if statements must be < or > and never <= or >= as that would cause
        issues with proper direction setting. Example being if incoming x,y is
        5,0 and one of the y if statements is <= 0 then the direction will be set
        to the y direction even though it didn't change.
    */
    public void updateCoord(int x, int y){
        this.x += x;
        this.y += y;
        if(x > 0)
            direction = 'r';
        else if(x < 0)
            direction = 'l';
        else if(y > 0)
            direction = 'd';
        else if(y < 0)
            direction = 'u';
    }
}
