/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

import Gamegui.Window;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MyTicTacToe {

    public static void main(String[] args) throws IOException, InterruptedException {
          Scanner input=new Scanner(System.in);
          System.out.println("enter 1 for server , 2 for clien"
                  + "t1 and 3 for client2 ");
                          
          int no=input.nextInt();
          switch(no){
              case 1:new SERVER();
                  break;
              case 2:new Client1();
                  break;
              case 3:new Client2();
                  break;  
              default:System.out.println("enter correct number");
          }
    }
    
}

