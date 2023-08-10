/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Atmani
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, //ROW THEN COLUMN
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        
       System.out.println("1 | 4 | 7");
       System.out.println("- + - + -");
       System.out.println("2 + 5 + 8");
       System.out.println("- + - + -");
       System.out.println("3 + 6 + 9");
       
        
       printGameBoard(gameBoard);
   
       while(true){ 
       String result;
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter your plac  ement (1-9) ");
       int playerPos =  scan.nextInt();
       while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
           System.out.println("Position taken! Enter a correct position");
           playerPos =  scan.nextInt();
       }
       
       placePiece(gameBoard, playerPos, "player");
       result = checkWinner();
       if (result.length() > 0){
          System.out.println(result);
          break;
       }
       
       Random rand = new Random(); 
       int cpuPos = rand.nextInt(9)+1;
       while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
           System.out.println("Position taken! Enter a correct position");
           cpuPos = rand.nextInt(9)+1;
       }
       
       placePiece(gameBoard, cpuPos, "cpu");

       printGameBoard(gameBoard);
       if (result.length() > 0){
          System.out.println(result);
          break;
       }
        
       
    }
       
       
    }
    
    public static void printGameBoard(char[][] gameBoard){
       for(char[] row : gameBoard){ 
           for (char c : row){
               System.out.print(c);
           }
           System.out.println();
       } 
    }
    
    public static void placePiece(char[][] gameBoard, int pos, String user){
        
        char symbol = ' ';
        if (user.equals("player")){
          symbol = 'X'; 
          playerPositions.add(pos);
        } else if (user.equals("cpu")){
            symbol = 'O';
          cpuPositions.add(pos);
        }
        
        switch(pos){
           case 1:
             gameBoard[0][0] = symbol;
             break;
           case 2:
             gameBoard[2][0] = symbol;
             break;
           case 3:
             gameBoard[4][0] = symbol;
             break;
           case 4:
             gameBoard[0][2] = symbol;
             break;
           case 5:
             gameBoard[2][2] = symbol;
             break;
           case 6:
             gameBoard[4][2] = symbol;
             break;
           case 7:
             gameBoard[0][4] = symbol;
             break;
           case 8:
             gameBoard[2][4] = symbol;
             break;
           case 9:
             gameBoard[4][4] = symbol;
             break;
           default:
             break;
   
       }
    } 
    
    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        
        List Dia1 = Arrays.asList(1, 5, 9);
        List Dia2 = Arrays.asList(7, 5, 3);
        
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(Dia1);
        winning.add(Dia2);
        
        for (List l : winning){
            if (playerPositions.containsAll(l)){
              return "congratulations";  
            } else if (cpuPositions.containsAll(l)){
                return "cpu wins";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "tie";
            }
        }
        return "";
    } 
        
    
   
} 
