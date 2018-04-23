
package gamecontrolerdemo.model;

import gamecontrolerdemo.GlobalConstants;
//import static gamecontrolerdemo.model.BoardModel.squereOccupation.ZombieN;
import java.awt.Point;


public class BoardModel 
{
    public enum squereOccupation 
    {
        Girl, Lumberjack, Mechanic, Nurse, Banker, Policemen, ZombieN, ZombieE, ZombieS, ZombieW, Empty   
    }
        
    enum squereState 
    {
        Normal, Obstacle, Spawn, Exit, Canal     
    }
        
    public class Squere
    {
        private squereOccupation squereoccupation;    
        private final squereState squerestate;

        public Squere(squereOccupation sqo,squereState sqs)
        {
            squereoccupation = sqo;
            squerestate = sqs;
        }

        public void updateSquere(squereOccupation sqo)
        {
            squereoccupation=sqo;
        }

        public squereOccupation getSquereOccupation()
        {
            return squereoccupation;
        }

        public squereState getSquereState()
        {
            return squerestate;
        }
        
    }    
    
    
    private   Squere [][] board;
    
    public BoardModel()
    {
         board = new Squere  [16] [12] ;
        System.out.println(16+"||"+12);
        for (int i=0; i<16; i++)
        {   
            for (int j=0; j<12; j++)
            {
              if ((i==0)||(j==0)||(i==11)||(j==15))
                 board[i][j]= new Squere(squereOccupation.Empty, squereState.Spawn); 
                

              else board[i][j]= new Squere(squereOccupation.Empty, squereState.Normal);
              
              board [15][5]=new Squere(squereOccupation.Empty, squereState.Exit);
              board [15][6]=new Squere(squereOccupation.Empty, squereState.Exit);
              board [15][7]=new Squere(squereOccupation.Empty, squereState.Exit);
            }
        } 
    }
    
    public boolean isFieldEmpty(Point p)
    {
        if ((board[p.x][p.y]).getSquereOccupation()==squereOccupation.Empty
                &&(board[p.x][p.y]).getSquereState()!=squereState.Obstacle)
        {
          return true;  
        }
        else return false;
    }
    
    private Squere getSquere(Point p)
    {
        return board [p.x][p.y];
    }
    
    public void updateTile(Point p, squereOccupation soq )
    {
        getSquere(p).updateSquere(soq);
    }
    
        public boolean isValidForHumanToStart (Point p)
    {
         return (p.x==0&&(isFieldEmpty(p)));   
    }
    
        
        public boolean isValidForZombieToStart (Point p, ZombieModel zm)
        {
            int counter = 0; 
            if (!isFieldEmpty(p)) return false; 
                        
            if (p.x<8)     
            {
                if (zm.getPointOnBoard()!=null) 
                {if (zm.getPointOnBoard().x<8) return true;}   
                for (int i=0; i<12; i++)
                {   
                    for (int j=0; j<16/2; j++)
                    {
                        if ((board [j][i].getSquereOccupation()==squereOccupation.ZombieN)||
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieS)||
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieW)||    
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieE)    
                        ) counter+=1;
                    }
                }
                return counter<5;
            }
            else 
            {   if (zm.getPointOnBoard()!=null) 
                {if (zm.getPointOnBoard().x>7) {return true;}   }
                for (int i=0; i<12; i++)
                {   
                    for (int j=16/2; j<16; j++)
                    {
                        if ((board [j][i].getSquereOccupation()==squereOccupation.ZombieN)||
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieS)||
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieW)||    
                        (board [j][i].getSquereOccupation()==squereOccupation.ZombieE)    
                        ) {counter+=1;}
                    }
                }
                return counter<5;
            }        
           
        }
}