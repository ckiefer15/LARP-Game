/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp.model;

import larp.model.character.*;
import larp.model.inventory.*;
import larp.model.room.object.Conflict;
import larp.model.graphic.StaticImage;
/**
 *
 * @author up6071fd
 */
public class Battle {
    
    public static final int LOST = 0;
    public static final int WON = 1;
    public static final int RAN = 2;
    private static final double HIT_CHANCE = 0.6;
    private static final double RUN_CHANCE = 0.3;
    private static final double SUCCESS = 1.0;
    
    private Conflict conflict;
    private GameCharacter enemy;
    private Knight player;
    private int playerDamage;
    private int enemyDamage;
    
    public Battle(Conflict conflict, Knight player){
        this.conflict = conflict;
        this.player = player;
        this.enemy = conflict.getEnemy();
        playerDamage = player.getDamage();
        enemyDamage = enemy.getDamage();
    }
    
    /**
     * The player will attempt to attack the enemy first and, if the enemy
     * still lives after the attack, the enemy will attempt to retaliate. If the
     * player wins and the conflict was the final boss then the game status will
     * have changed to TRUE indicating the player won the game as well.
     * @return Returns either WON, LOSE, or another integer which means the battle
     * is still on going.
     */
    public int attack(){
        if(Math.random() + HIT_CHANCE >= SUCCESS)
            if(enemy.takeHit(playerDamage)){
                DGame.getInstance().cleanupBattle(conflict);
                return 1;
            }
        return enemyAttack();
    }
    
    private int enemyAttack(){
        if(Math.random() + HIT_CHANCE >= SUCCESS)
            if(player.takeHit(enemyDamage))
                return 0;
        return -1;
    }
    
    /**
     * The player will attempt to run away from the conflict, if unsuccessful the
     * enemy will have a chance to attack.
     * @return Returns either RAN, LOSE, or another integer which means the conflict
     * is still ongoing.
     */
    public int run(){
        if(Math.random() + RUN_CHANCE >= SUCCESS)
            return 2;
        return enemyAttack();
    }
    
    /**
     * The player will heal themselves if healing items are available in their
     * inventory. No attack will happen and the conflict will continue.
     */
    public void heal(){
        player.heal();
    }
    
    public StaticImage getEnemyStaticImage(){
        return enemy.getStaticImage();
    }
    
    public StaticImage getPlayerStaticImage(){
        return player.getStaticImage();
    }
    
    public Conflict getConflict(){
        return conflict;
    }
}
