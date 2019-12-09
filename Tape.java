/*
* Geoff Prothero
* CS 4110 - Intro to Computer Theory
* Turing Machine
*/
import java.util.*;
import java.util.List;
import java.util.Vector;
import java.lang.String;
import java.util.HashMap;
import java.util.ArrayList;

public class Tape{
   ArrayList<Character> tape = new ArrayList<Character>();
   // turn string into array list of chars;
   public Tape(String str){
         
      for(char c : str.toCharArray()){
         tape.add(c);
      }
      tape.add('_');
    
   }
   public int size(){
      return this.tape.size();
   }
   public boolean match(int i, char c){
      if(tape.get(i) == c){
         return true;
      }
      return false;
   }
   //return Character @ i
   public char getChar(int i){
      return tape.get(i);
   }
   //replace Character @ i
   public void setChar(int i, char c){
      tape.set(i, c);
   
   }

}