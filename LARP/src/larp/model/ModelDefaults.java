/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.character.GameCharacter;
/**
 *
 * @author up6071fd
 */
public interface ModelDefaults {
    
    //=================Room Object Defaults============
    public static final String ROOM_OBJ_IMG = "/img/blank.png";
    public static final String ROOM_OBJ_NAME = "SomeObject";
    public static final String DOOR_IMG = "/img/door/default.png";
    public static final String DOOR_NAME = "SomeDoor";
    public static final String CHEST_IMG = "/img/chest/default.png";
    public static final String CHEST_NAME = "SomeChest";
    public static final String CONFLICT_IMG = "/img/enemy/default.png";
    public static final String CONFLICT_NAME = "SomeBattle";
    
    //=============Item Defaults========================
    public static final String HEALTH_IMG = "/img/health/default.png";
    public static final String HEALTH_NAME = "Potion";
    public static final String WEAPON_IMG = "/img/weapon/default.png";
    public static final String WEAPON_NAME = "Sword";
    
    //==============Character Defaults====================
    public static final String PLAYER_IMG = "/img/player/default.png";
    public static GameCharacter ENEMY_OBJ = new GameCharacter("Hank",50,10,"/img/enemy/default.png",false);
}
