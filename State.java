/*
* Geoff Prothero
* CS 4110 - Intro to Computer Theory
* Turing Machine
*/
import java.util.*;
import java.util.Vector;
import java.lang.String;
import java.util.HashMap;


//class State to be used as a linked list
public class State{  
   HashMap<Character, Transition>  steps = new HashMap<>();
   Transition tran;
  // State halt;
   
   //constructor for halt state. (has no transition)
   public State(){
      tran = null;
   
   }
   //constructor for instance of to State pointing back to itself
   public State(char r, char w, char dir){
      this.addTransition(r, w, dir, this);
   }
   //constructor for instance of to State pointing to another State
   public State(char r, char w, char dir, State st){
      this.addTransition(r, w, dir, st);
      
   }
   //add transitions to state
   public void addTransition(char r, char w, char dir, State st){
      tran = new Transition(w, dir, st);
   
      steps.put(r, tran);
   }
   //check for match between tape char and read char from State
   public boolean getTran(char r){
      if(steps.containsKey(r)){
         return true;
      }
      return false;
   }
   //return new Character to replace on Tape
   public char getWrite(char r){
       return steps.get(r).getWrite(); 
   }
   public void setWrite(Tape t, int i, char w){
      t.setChar(i, w);
   }
   //return direction to move on Tape
   public int getDir(char r){
      return steps.get(r).getDir();
   }
   
   //return key of next state to move to
   public State getToState(char r){
      return steps.get(r).getNext();
   }
   public State getState(){
      return this;
   }
   
   
  
}