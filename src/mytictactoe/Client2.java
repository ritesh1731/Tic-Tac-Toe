/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mytictactoe.Game.gamewindow;
import packets.ClientPlayPacket;
import packets.GameEndPacket;
import packets.UpdatePacket;




public class Client2 extends Game implements Runnable{
     Socket socket;
     private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
   Thread t1;
    public Client2() throws IOException{
         super("Player 2",2);
         t1=new Thread(this);
         t1.start();
         }
    public void run(){
        try {
            socket=new Socket("localhost",44444);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            
            Object obj=null;
           while(true){
                obj=inputStream.readObject();
               if(obj instanceof UpdatePacket){
                   UpdatePacket obj1=(UpdatePacket)obj;
                   int [][]arr=obj1.getFields();
                   int player=obj1.getCurrentPlayer();
                   updateFields(arr,player);
                   gamewindow.repaint();
               }
               if(obj instanceof GameEndPacket){
                   GameEndPacket packet=(GameEndPacket)obj;
                   showWinner(packet.getWinner());
                   break;
               }
               
               obj=null;
           }
   
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             close();
         } catch (IOException ex) {
             Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
         }
            }
        
      
    
    public static void repaint(){
        gamewindow.repaint();
    }
    public void sendPacket(Object object){
             try {
            
            outputStream.reset();
            outputStream.writeObject(object);
            outputStream.flush();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   
    }
     public void close() throws IOException{
         window.dispose();
        socket.close();
        inputStream.close();
        outputStream.close();
        
    }


}
