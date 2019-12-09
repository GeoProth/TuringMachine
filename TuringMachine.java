/*
* Geoff Prothero
* CS 4110 - Intro to Computer Theory
* Turing Machine
*/

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.*;
import java.util.ArrayList;



public class TuringMachine{
   HashMap<String, State> states = new HashMap<String, State>();
   private State initState;
   private String start;   //hash id of start state
   public TuringMachine(String filename){
     State map;
     String read;
     char toRead;//character to read
     String write;
     char toWrite;//character to write
     String dir;
     char toDir; //direction to move on tape
     String stateID;//hash ID of state
     String toState;//hash ID of next state
     String halt;
        
     try{
         File file = new File(filename);
         Scanner sc = new Scanner(file);
         sc.nextLine();
         int i = 0;
         //parse file for specific details
         while(sc.hasNextLine()){
      
            String line = sc.nextLine(); 
            if(line.length() > 6){
               String[] details = line.split(", ");
               stateID = details[0].toLowerCase();
               if(i == 0){//assign start hash identifier on first iteration
                  start = stateID;
               }      
               read = details[1];
               toRead = read.charAt(0);
               write = details[2];
               toWrite = write.charAt(0);
               dir = details[3];
               toDir = dir.charAt(0);
               toState = details[4].toLowerCase();
               
               State check = states.get(stateID);
               State next = states.get(toState); 
               
                              
               if(next == null){//if null, to State has not been created yet
                  next = new State();
                  states.put(toState, next);
               }
               
               if(check == null){//From State has not been created yet
                  if(stateID.equals(toState)){
                      map = new State(toRead, toWrite, toDir);   
                  }
                  else{
                      map = new State(toRead, toWrite, toDir, next);
                  }

                 states.put(stateID, map);

               }
               else{
                  check.addTransition(toRead, toWrite, toDir, next);
               }
               //System.out.println("Start: " + stateID + " read: " + read + " write: " + write + " Dir: " + dir + " toState: " + toState);
                     
              
            }//end if       
            else{
               halt = line.toLowerCase();
               map = new State();//halt state has no other values but its key
               states.put(halt, map);
               //System.out.println(halt);
              
            }
               
             i++;   
         }//end while   
    
      }catch(FileNotFoundException e){
         System.out.println("File Not Found");
         e.printStackTrace();
      }
      
      this.initState = states.get(start);
      
}//end method

// Method to create tape and test
public void Run(String tapeValue){
   //turn passed string into Tape
   Tape tape = new Tape(tapeValue);
  
   Test(tape, 0, tapeValue, this.initState);
      
   
}
//recursive function to run Simulation of Turing Machine
public void Test(Tape t, int i, String tapeValue, State s){
        //tape is rejected
     if(i > t.size() - 1 || i < 0 || s.getTran(t.getChar(i)) == false){
         printReject(tapeValue);
         return;
      }
      //tape is accepted
      else if(s.getToState(t.getChar(i)).tran == null){
         printAccept(tapeValue);
         return;
      }
      //recursion (Tape not finished or accepted)
      else{
      if(s.getTran(t.getChar(i)) == true){
         char temp = t.getChar(i);
         int dir = i + s.getDir(temp);
         t.setChar(i, s.getWrite(temp));
         s = s.getToState(temp);
         Test(t, dir, tapeValue, s);
         
      }
     }
        
  }

// Print Statement for accepting
public void printAccept(String tapeValue){
   System.out.println("Accepted: " + tapeValue);

}
// Print statement for rejecting
public void printReject(String tapeValue){
   System.out.println("Rejected: " + tapeValue);

}

   public static void main(String[] args){
       //check for file name to read from
   if(args.length < 2){
      System.out.println("no file passed");
      return;
   }
         String file = args[0]; // file name from cmd prompt
         String word = args[1];// word to be tested
         
         TuringMachine tm = new TuringMachine(file);
         
         tm.Run(word);

         
         System.exit(0);

   }//end main

}//end Turing Machine