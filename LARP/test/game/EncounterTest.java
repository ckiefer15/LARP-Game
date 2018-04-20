/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import larp.model.SetupDGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import larp.model.*;
import java.util.ArrayList;
import larp.model.character.Knight;
import larp.model.room.object.Chest;
import larp.model.room.object.Conflict;
import larp.model.room.object.RoomObject;

/**
 *
 * @author vf0975bh
 */
public class EncounterTest {

    DGame encounterGameTest;

    public EncounterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        encounterGameTest = SetupDGame.setupGame(true);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBattle() {
        Conflict testConflict;
        Knight character;
        Battle testBattle;

        for (RoomObject tempObj : encounterGameTest.getCurrentRoom().getRoomObjects()) {
            if (tempObj instanceof Conflict) {

                 testConflict = (Conflict)tempObj;
                 testBattle = encounterGameTest.initBattle(testConflict);

            if (testBattle.getConflict() != testConflict) {
                fail("Conflict not properly initialized");
            }
            if (testBattle.getEnemy() != testConflict.getEnemy()) {
                fail("Enemy was not properly initialized");

            }
            if (testBattle.getPlayer() != encounterGameTest.getPlayer()) {
                fail("Character was not properly initialized");
            }
            }
        }
    }
}
