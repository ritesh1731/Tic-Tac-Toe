/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packets;

import java.io.Serializable;

/**
 *
 * @author AYAN
 */
public class GameEndPacket implements Serializable{
    
    private int winner;
    
    public GameEndPacket(int winner ){
        this.winner = winner;
    }
    
    public int getWinner() {
        return winner;
    }
}
