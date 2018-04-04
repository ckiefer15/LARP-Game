/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model.room.object;

import larp.model.ModelDefaults;
/**
 *
 * @author Andrew Poss
 */
public class Door extends RoomObject{
    
    private static final int DEFAULT_ROOM = 0;
    private static final int SAFETY_OFFSET = 10;
    
   private String nextDoorName;
   private int nextRoomIndex;
   private int [] spawnOffset;
   
   public Door(){
        this(DEFAULT_ROOM,"start",'u',ModelDefaults.DOOR_NAME,0,0,0,0,ModelDefaults.DOOR_IMG);
    }
    
    public Door(int xPos, int yPos, int hitWidth, int hitHeight){
        this(DEFAULT_ROOM,"start",'u',ModelDefaults.DOOR_NAME,xPos,yPos,hitWidth,hitHeight,ModelDefaults.DOOR_IMG);
    }
    
    public Door(int nextRoom, String nextDoorName, char playerSpawn,
           String name, int xPos, int yPos, int hitWidth, int hitHeight){
        this(nextRoom,nextDoorName,playerSpawn,name,xPos,yPos,hitWidth,hitHeight,ModelDefaults.DOOR_IMG);
    }
    
   public Door(int nextRoom, String nextDoorName, char playerSpawn,
           String name, int xPos, int yPos, int hitWidth, int hitHeight, String imgPath){
       
       super(true, name, xPos, yPos, hitWidth, hitHeight, imgPath);
       
       initPlayerSpawnOffset(playerSpawn,xPos,yPos,hitWidth,hitHeight);
       
       if(nextDoorName != null && nextDoorName.length() >= 0)
        this.nextDoorName = nextDoorName;
       else
           this.nextDoorName = "start";
       if(nextRoom >= 0)
           this.nextRoomIndex = nextRoom;
       else
           this.nextRoomIndex = DEFAULT_ROOM;
   }
   
   private void initPlayerSpawnOffset(char playerSpawn, int xPos, int yPos,
           int hitWidth, int hitHeight){
       playerSpawn = Character.toLowerCase(playerSpawn);
       spawnOffset = new int[2];
       
       if(playerSpawn == 'u' || playerSpawn == 'd'){
           spawnOffset[0] = (2 * xPos + hitWidth) / 2;
           if(playerSpawn == 'u')
               spawnOffset[1] = yPos - SAFETY_OFFSET;
           else
               spawnOffset[1] = yPos + hitHeight + SAFETY_OFFSET;
       }
       else{
           spawnOffset[1] = (2 * yPos + hitHeight) / 2;
           if(playerSpawn == 'l')
               spawnOffset[0] = xPos - SAFETY_OFFSET;
           else
               spawnOffset[0] = xPos + hitWidth + SAFETY_OFFSET;
       }
   }
   
    /**
     * This method returns the index of the next room
     * @return the room index
     */
  public String getNextDoor() {
    return nextDoorName;
  }
    
  public int getNextRoom(){
      return nextRoomIndex;
  }
  
  public int [] getPlayerSpawnOffset(){
      return spawnOffset;
  }
}
