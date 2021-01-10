/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYAN
 */
public class Bakchodi {
    public static void main(String[] args) throws InterruptedException {
        int x=0;
        Thread t1=new Thread(new Runnable(){
            public synchronized void run(){
                int i=0;
                while(true){
                    System.out.println(i);
                    i+=2;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Bakchodi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        Thread t2=new Thread(new Runnable(){
           public synchronized void run(){
               int i=1;
                while(true){
                    System.out.println(i);
                    i+=2;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Bakchodi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           } 
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
