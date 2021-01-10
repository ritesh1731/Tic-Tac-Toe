/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packets;

import java.io.Serializable;

public class ClientPlayPacket implements Serializable{
    
    private int x;
    private int y;
    
    public ClientPlayPacket(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
}
