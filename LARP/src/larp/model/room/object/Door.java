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
    
    private static final String DEFAULT_IMG = "/img/door/default.png";
    private static final String DEFAULT_NAME = "SomeDoor";
    private static final int DEFAULT_ROOM = 0;
    
   private int nextRoomIndex;
   
   public Door(){
        this(DEFAULT_ROOM,true,DEFAULT_NAME,0,0,0,0,DEFAULT_IMG);
    }
    
    public Door(int xPos, int yPos, int hitWidth, int hitHeight, boolean blockable){
        this(DEFAULT_ROOM,blockable,DEFAULT_NAME,xPos,yPos,hitWidth,hitHeight,DEFAULT_IMG);
    }
    
   public Door(int nextRoom, boolean blockable, String name, int xPos, int yPos,
           int hitWidth, int hitHeight, String imgPath){
       super(blockable, name, xPos, yPos, hitWidth, hitHeight, imgPath);
       if(nextRoom >= 0)
        nextRoomIndex = nextRoom;
       else
           nextRoomIndex = DEFAULT_ROOM;
   }
   
    /**
     * This method returns the index of the next room
     * @return the room index
     */
  public int getNextRoom() {
    return nextRoomIndex;
  }
    
}
