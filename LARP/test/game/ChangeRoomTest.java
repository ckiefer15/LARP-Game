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

import larp.model.*;
import larp.model.character.*;
import larp.model.room.object.*;
import java.util.ArrayList;
/**
 *
 * @author up6071fd
 */
public class ChangeRoomTest {
    
    DGame game;
    DRoom room;
    Knight player;
    
    public ChangeRoomTest() {
        game = SetupDGame.setupGame(true);
        room = game.getCurrentRoom();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void changeRoom(){
        Door tempDoor = null;
        
        //  Look for a door in the current room
        for(RoomObject obj : room.getRoomObjects()){
            if(obj instanceof Door){
                tempDoor = (Door)obj;
                break;
            }
        }
        
        if(tempDoor != null){
            //  Call change room in DGame and pass the found door
            DRoom tempRoom = game.changeRoom(tempDoor);
            if(tempRoom == room)
                fail("The 'currentRoom' in game didn't change!");
        }
        else
            fail("There is no door to test in the starting room!");
    }
}
