/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

/**
 *
 * @author Andrew Poss
 */
public class Door extends RoomObject{
    
   private int nextRoomIndex;
    
    /**
     * This method returns the index of the next room
     * @return the room index
     */
  public int getNextRoom() {
    return nextRoomIndex;
  }
    
}
