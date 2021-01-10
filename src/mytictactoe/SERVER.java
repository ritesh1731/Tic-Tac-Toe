package mytictactoe;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mytictactoe.Game;
import packets.ClientPlayPacket;
import packets.GameEndPacket;
import packets.UpdatePacket;

public class SERVER {
     public int matrix[][];
    private ObjectOutputStream outputStream1,outputStream2;
    private ObjectInputStream inputStream1, inputStream2;
    private int playerturn=1;
    public SERVER() throws IOException{
        matrix=new int[3][3];
         for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matrix[i][j]=0;
            }
        }
        System.out.println("SERVER RUNNING");
        ServerSocket ss1=new ServerSocket(55555);
        ServerSocket ss2=new ServerSocket(44444);
        Socket s1=ss1.accept();
        System.out.println("Client 1 connected ");
       Socket s2=ss2.accept();
        System.out.println("Client 2 connected ");
        
         outputStream1 = new ObjectOutputStream(s1.getOutputStream());
            inputStream1 = new ObjectInputStream(s1.getInputStream());
         outputStream2 = new ObjectOutputStream(s2.getOutputStream());
            inputStream2 = new ObjectInputStream(s2.getInputStream());
   int winner;
        
        
        Object object1=null,object2=null;
        boolean running =true;
        while(running){
            try {
                if(playerturn==1){
                object1 = inputStream1.readObject();  
                }
                else if(playerturn==2){
                object2=inputStream2.readObject();
                }
                else{
                    continue;
                }
                if(object1 instanceof ClientPlayPacket){
                    ClientPlayPacket obj=(ClientPlayPacket)object1;
                    UpdateMatrix(obj.getX(),obj.getY(),1);
                    playerturn=2;
                     outputStream1.reset();
                     outputStream2.reset();
                    outputStream1.writeObject(new UpdatePacket(this.matrix,2));
                    outputStream2.writeObject(new UpdatePacket(this.matrix,2));
                    outputStream1.flush();
                    outputStream2.flush();
                    
                    }
                if(object2 instanceof ClientPlayPacket){
                    ClientPlayPacket obj2=(ClientPlayPacket)object2;
                    UpdateMatrix(obj2.getX(),obj2.getY(),2);
                    playerturn=1;
               
                     outputStream1.reset();
                     outputStream2.reset();
                    outputStream1.writeObject(new UpdatePacket(this.matrix,1));
                    outputStream2.writeObject(new UpdatePacket(this.matrix,1));
                     outputStream1.flush();
                    outputStream2.flush();
                    
                    }
                
                object1=null;
                object2=null;
                winner=CheckWin();
                if(winner==1){
                    System.out.println("winner is player 1");
                    outputStream1.reset();
                     outputStream2.reset();
                    outputStream1.writeObject(new GameEndPacket(1));
                    outputStream2.writeObject(new GameEndPacket(1));
                    break;
                }
                else if(winner==2){
                    System.out.println("winner is player 2");
                    outputStream1.reset();
                     outputStream2.reset();
                    outputStream1.writeObject(new GameEndPacket(2));
                    outputStream2.writeObject(new GameEndPacket(2));
                    break;
                }
                else if(winner==4){
                    System.out.println("draw");
                    outputStream1.reset();
                     outputStream2.reset();
                    outputStream1.writeObject(new GameEndPacket(4));
                    outputStream2.writeObject(new GameEndPacket(4));
                    break;
                
                }
            } catch (EOFException | SocketException e){
                running = false;
            } catch (IOException ex) {
                Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }                    
    }
     
    
    
   public void UpdateMatrix(int x,int y,int no){
       matrix[x][y]=no;
   } 
   
   
   
   
   
  public int CheckWin(){
      //Player 1
      int winner=0;
      for(int j=0;j<3;j++){
      if(matrix[j][0]==1&&matrix[j][1]==1&&matrix[j][2]==1){
          winner=1;
          return winner;
      }
      }
      for(int i=0;i<3;i++){
          if(matrix[0][i]==1&&matrix[1][i]==1&&matrix[2][i]==1){
              winner=1;
              return winner;
          }
      }
      if(matrix[0][0]==1&&matrix[1][1]==1&&matrix[2][2]==1){
          winner=1;
          return winner;
      }
      if(matrix[0][2]==1&&matrix[1][1]==1&&matrix[2][0]==1){
          winner=1;
          return winner;
      }
      //PLAYER 2
      for(int j=0;j<3;j++){
      if(matrix[j][0]==2&&matrix[j][1]==2&&matrix[j][2]==2){
          winner=2;
          return winner;
      }
      }
      for(int i=0;i<3;i++){
          if(matrix[0][i]==2&&matrix[1][i]==2&&matrix[2][i]==2){
              winner=2;
              return winner;
          }
      }
      if(matrix[0][0]==2&&matrix[1][1]==2&&matrix[2][2]==2){
          winner=2;
          return winner;
      }
      if(matrix[0][2]==2&&matrix[1][1]==2&&matrix[2][0]==2){
          winner=2;
          return winner;
      }
      int flag=0;
      for(int i=0;i<3;i++){
          for(int j=0;j<3;j++){
              if(matrix[i][j]==0){
                  flag=1;
                  break;
              }
          }
      }
      if(flag==0){
          return 4;
      }
      
      return winner;
  }
    
    
    
}
