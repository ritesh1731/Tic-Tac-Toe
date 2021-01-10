/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gamegui;

import game.res.Resources;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import mytictactoe.Client1;
import mytictactoe.Client2;
import mytictactoe.Game;
import mytictactoe.SERVER;
import packets.ClientPlayPacket;

/**
 *
 * @author AYAN
 */
public class GameWindow extends JPanel implements MouseListener{
    public int x,y,ch;
    private Game game;
    private Client1 c1;
    private Client2 c2;
    public GameWindow(Game game){
        addMouseListener(this);
        this.game=game;
    }
    
    @Override
    public synchronized void paint(Graphics g){
         Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(10));

        for(int x = 200; x<=400; x +=200){
            g2D.drawLine(x, 0, x, 600);
        }
        
        for(int y = 200; y<=400; y +=200){
            g2D.drawLine(0, y, 600, y);
        }
        int [][]fields=game.getFields();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(fields[i][j]==1){
                    g2D.drawImage(Resources.letters[0], i*200+25, j*200+25, 200 - 50, 200 - 50, null);
                }
                if(fields[i][j]==2){
                  g2D.drawImage(Resources.letters[1], i*200+25, j*200+25, 200 - 50, 200 - 50, null);
                }
            }
        }
        
       
    }
    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        
        if(game.isMyTurn()){
            game.sendPacket(new ClientPlayPacket(e.getX()/200,e.getY()/200));
        }
        else{
            System.out.println("not your turn ");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
      }

    @Override
    public void mouseReleased(MouseEvent e) {
       }

    @Override
    public void mouseEntered(MouseEvent e) {
        }

    @Override
    public void mouseExited(MouseEvent e) {
        }
    
    
}
