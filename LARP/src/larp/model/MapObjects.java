package larp.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import larp.model.graphic.Graphic;

public class MapObjects extends Graphic {
    public Rectangle bounds;

    public MapObjects(){
        super(50,50,75,75);
        bounds = new Rectangle(350, 350, 75, 75);
    }
    public MapObjects(int x, int y,int xOff, int yOff){
        super(x, y, xOff, yOff);
        bounds = new Rectangle(x, y,xOff, yOff);
        
    }
}
