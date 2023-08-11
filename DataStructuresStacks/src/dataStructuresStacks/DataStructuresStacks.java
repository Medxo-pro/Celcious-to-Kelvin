/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataStructuresStacks;

import java.util.Stack;

/**
 *
 * @author Atmani
 */
public class DataStructuresStacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Stack<String> stack1 = new Stack<String>();
        stack1.push("5");
        stack1.push("4");
        stack1.push("3");
        stack1.push("2");
        stack1.push("1");
    
        //getIndexElementInStack(stack1, "2");
        //fetchFirstElemenStack(stack1);
        //reverseStack(stack1);
        
    }
     
    public static void fetchFirstElemenStack (Stack<String> stack1){
        System.out.println(stack1.peek());
    }
    
    public static void getIndexElementInStack (Stack<String> stack1, String element){ //index starts from 0
        System.out.println((stack1.search(element)-1));
    }
    
    public static void reverseStack (Stack<String> stack1){
        Stack<String> stack2  = new Stack<String>();
        System.out.println("Stack1" + stack1.empty());
        System.out.println("Stack2" + stack2.empty());
        System.out.println(stack1);
        while (stack1.empty() == false){
        String x = stack1.pop();
        stack2.push(x);
        System.out.println(x);
        }
        System.out.println("Stack2" + stack2.empty());
        System.out.println(stack2);
        while (stack2.empty() == false){
        String y = stack2.pop();
        System.out.println(y);
        }     
    }
    
    
    
    
}
