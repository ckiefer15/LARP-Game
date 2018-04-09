/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import larp.model.DRoom;
import larp.model.SetupDGame;
import larp.model.ModelDefaults;
import larp.model.character.Knight;
import larp.model.graphic.*;
import larp.model.room.object.RoomObject;
/**
 *
 * @author up6071fd
 */
public class TestKnightSpawn {
    
    private Knight player;
    private ArrayList<DRoom> rooms;
    
    public TestKnightSpawn() {
        player = SetupDGame.initPlayer(true);
        rooms = SetupDGame.initRooms(true);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testSpawn(){
        Rectangle playerBox = player.getSprite().testingCollisionBounds();
        for(RoomObject obj : rooms.get(0).getBlockable()){
            if(obj.bounds.intersects(playerBox.getBoundsInParent()))
                fail("Player is intersecting with room object: " + obj.getName());
        }
    }
}
