/*
* Geoff Prothero
* CS 4110 - Intro to Computer Theory
* Turing Machine
*/
import java.util.*;
import java.util.Vector;
import java.lang.String;
import java.util.HashMap;
import java.util.Vector;


class Transition{
   //char read;
   char write;
   char direction;
   State next;
   
   public Transition(){//halt state, no transtion
      this.write = ' ';
      this.direction = ' ';
      this.next = null;
   }
   
   public Transition(char w, char dir, State st){
      this.write = w;
      this.direction = dir;
      this.next = st;
   
   }
   
   public char getWrite(){
      return write;
   }
   
   public int getDir(){
      if(this.direction == 'L'){
         return -1;
      }
      else{
         return 1;
      }
   }

   public State getNext(){
      return next;
   }
   
   

}