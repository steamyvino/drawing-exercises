/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

import java.util.Stack;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Maciek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      
     
      Scanner in = new Scanner(System.in);
        
      Board<Integer,Stack> board = new Board();     
      Deck deck = new Deck();
      Hand hand = new Hand(deck);
      
      board.put(1, new UpperCardStack());
      board.put(2, new UpperCardStack());
      board.put(3, new LowerCardStack());
      board.put(4, new LowerCardStack());
      View view = new View(hand,deck,board);
//      while(!deck.isEmpty())
//      {
//      System.out.println("----------"+(deck.size()+hand.getSize())+"-----------");
//      board.display();
//      System.out.println("-------"+"Your Hand"+"-------");
//      hand.display();
//      System.out.println("");
//      System.out.println("----------");
//      System.out.println("chooseCard");
//      int choosenCard = in.nextInt();
//      System.out.println("chooseDeck");
//      int choosenDeck = in.nextInt();
//      hand.chooseCard(choosenCard, (Stack)board.get(choosenDeck));
//      }
    
    }
    
    
    
}

class UpperCardStack<T> extends Stack
{
    int actual = 0;
    
    UpperCardStack()
    {
     super.push(new Integer(1));
    }
    
   @Override
   public Object push(Object item) {
       
       actual = ((Integer)this.peek());
       
      
       if((item instanceof Integer)&&((actual<((Integer)item))||actual-((Integer)item)==10))
       {
           System.out.println("Added "+ item);
           super.push(item);
           return item;
       }
        else 
       {
           System.out.println("can't add "+ item);
           return null;
           
       }   
       
       
    }
 
}

class LowerCardStack<T> extends Stack
{
    int actual = 0;
    
    LowerCardStack()
    {
     super.push(new Integer(100));
    }
    
   @Override
   public Object push(Object item) {
       
       actual = ((Integer)this.peek());
 
      
       if((item instanceof Integer)&&((actual>((Integer)item))||actual-((Integer)item)==-10))
       {
           System.out.println("Added "+ item);
           super.push(item);
           return item;
       }
        else 
       {
           System.out.println("can't add "+ item);
           return null;
           
       }      
    }
 
}

class Deck extends Stack
{
    Deck()
    {
        for(int i=2; i<100;i++)
        {
            this.push(new Integer(i));
          
        }
        Collections.shuffle(this);
        int i=0;
        
    }
}

class Hand<T> extends LinkedList
{
     private final int CAP=8; 
     int[] handCards = new int[CAP];
     Deck deck;
     Hand(Deck deck)
     {
         this.deck=deck;
         for(int i=0;i<CAP;i++)
             this.add(deck.pop());
     }
     
     void display()
     {
         for(int i=0;i<CAP;i++)
         {
             if(this.get(i)==null)
                 System.out.print(" X ");
             else
                 System.out.print(this.get(i)+" ");
         }
     }   
     void chooseCard(Integer card,Stack stack)
     {
         if(this.contains(card))
         {
            if(stack.push(card)!=null)
            {
                 System.out.println("DODANO");
                 int index=this.indexOf(card);
                 this.set(this.indexOf(card), null);
            }
                 
            refill();
            
         }
   
         
     }
     void refill()
     {
         int blanks=0;
         int[] indexes = new int[2];
         for(int i=0;i<CAP;i++)
         {
             if((this.get(i))==null)
             {
                
                 indexes[blanks++]=i;
             }
             if(blanks==2)
             {
                 System.out.println("FILLING");
                 this.set(indexes[0],deck.pop());
                 this.set(indexes[1],deck.pop());
                 blanks=0;
             }
             
                 
                 
         }
     }
     
     int getSize()
     {
         int size=0;
         for(int i=0;i<CAP;i++)
         {
            if((this.get(i))!=null)
             {
                 size++;
                 
             }
         }
         return size;
     }
     int getCard(int i)
     {
         return (Integer)this.get(i);
     }
     
}

class Board<T,S> extends TreeMap
{
    void display()
    {
        
        System.out.print("Stack 1: "+((Stack)this.get(1)).peek()+"    ");
        System.out.println("Stack 2: "+((Stack)this.get(2)).peek()+" ");
        System.out.print("Stack 3: "+((Stack)this.get(3)).peek()+"  ");
        System.out.println("Stack 4: "+((Stack)this.get(4)).peek()+" ");
    }
}

