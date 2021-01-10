/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

import Gamegui.GameWindow;
import Gamegui.Window;
import javax.swing.JOptionPane;




public abstract class Game {
    public static GameWindow gamewindow;
    static Window window;
    public static int player;
    public static int currplayer;
    public int [][] fields;
    
    public Game(String title,int no){
        fields=new int[3][3];
        currplayer=1;
        player=no;
        gamewindow=new GameWindow(this);
        window=new Window(title);
        window.add(gamewindow);
    }
    public boolean isMyTurn(){
        if(currplayer==player){
            return true;
        }
        else{
            return false;
        }
    }
    public void updateFields(int [][] fields,int player){
        this.fields=fields;
        this.currplayer=player;
    }
    public int[][] getFields(){
        return fields;
    }
    public abstract void sendPacket(Object obj);
    public void showWinner(int winner){
        if(winner==4){
            JOptionPane.showMessageDialog(null, "draw");
        }
        else{
        JOptionPane.showMessageDialog(null, "winner is player"+winner);
    }
    }
   // public abstract void sendpacket(Object obj);
    //public abstract void receivePacket(Object obj);
}
