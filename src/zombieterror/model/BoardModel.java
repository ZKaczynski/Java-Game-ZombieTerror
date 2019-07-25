
package zombieterror.model;

import zombieterror.model.pawnmodels.ZombieModel;
import java.awt.Point;
import static java.lang.Math.abs;
import zombieterror.ZombieTerror;

/**
 *
 * Is logical representation of all Squeres of board.
 */
public final class BoardModel 
{
    public enum squereOccupation //who is on Squere?
    {
        Girl, Lumberjack, Mechanic, Nurse, Banker, Policemen, ZombieN, ZombieE, ZombieS, ZombieW, Empty   
    }
        
    public enum squereState //what type of Squere is it?
    {
        Normal, Obstacle, Spawn, Exit, Canal     
    }
        
    /**
     * It is container for all information about Squere
     */
    public class Squere
    {
        private squereOccupation squereoccupation;    
        final squereState squerestate;

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
    
    
    private   Squere [][] board; // 2d table of Squeres of game
    
    public BoardModel()
    {
        
        //Create model of board using constants in ZombieTerror
        board = new Squere  [ZombieTerror.BOARD_WIDTH] [ZombieTerror.BOARD_HEIGHT] ;
        for (int i=0; i<ZombieTerror.BOARD_WIDTH; i++)
        {   
            for (int j=0; j<ZombieTerror.BOARD_HEIGHT; j++)
            {
                
                   switch (ZombieTerror.BOARD_GENESIS[i][j]){
                   case 0 : board [i][j]=new Squere(squereOccupation.Empty, squereState.Normal);  break;
                    case 1 : board [i][j]=new Squere(squereOccupation.Empty, squereState.Obstacle);  break;
                     case 2 : board [i][j]=new Squere(squereOccupation.Empty, squereState.Spawn);  break;
                      case 3 : board [i][j]=new Squere(squereOccupation.Empty, squereState.Exit);  break;
                       case 4 : board [i][j]=new Squere(squereOccupation.Empty, squereState.Canal);  break;
               } 
                

  
            }
        } 
    }

    public boolean isFieldEmpty(Point p)
    {   
         if (p.x<0||p.x>ZombieTerror.BOARD_WIDTH-1||p.y<0||p.y>ZombieTerror.BOARD_HEIGHT-1) return false;
        return (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Empty
                &&(board[p.x][p.y]).getSquereState()!=squereState.Obstacle;
    }
    
    public Squere getSquere(Point p)
    {
        return board [p.x][p.y];
    }
    
    public void updateTile(Point p, squereOccupation soq )
    {
        getSquere(p).updateSquere(soq);
    }
    /**
     * 
     * @param p Point on board
     * @return true if human pawn can be laced at the begining
     */
        public boolean isValidForHumanToStart (Point p)
    {
         return (p.x==0&&(isFieldEmpty(p)));   
    }
        
    public boolean isValidForHumanToMove (Point prev, Point next)
    {
        boolean stepx, stepy;
            stepx = abs(prev.x-next.x)==1;
            stepy = abs(prev.y-next.y)==1;
            
         return ( ((stepx||stepy)&&!(stepx&&stepy))
                &&(isFieldEmpty(next)));   
    }
    
    public boolean isValidDestenationToPush (Point p){
        
        if ((p.x<0)||(p.x>15)||(p.y<0)||p.y>11) return false;
        
        return this.isFieldEmpty(p);
    }
    

    public boolean isOccupiedByHuman (Point p)
    {
        if (p.x<0||p.x>ZombieTerror.BOARD_WIDTH-1||p.y<0||p.y>ZombieTerror.BOARD_HEIGHT-1) return false;

        return ((board[p.x][p.y]).getSquereOccupation()==squereOccupation.Girl ||
                (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Lumberjack ||   
                (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Banker ||
                (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Nurse ||
                (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Mechanic ||
                (board[p.x][p.y]).getSquereOccupation()==squereOccupation.Policemen 
                ); 
    }
    /**
     * 
     * @return how many zombies should be spawned 
     * (1 zombie for each half of board with humans)
     */
        public int calculateZombiesToSpawn(){
            int count=0;
            boolean leftside=false, rightside=false;
            for (int i=0; i<ZombieTerror.BOARD_WIDTH/2; i++){   
                for (int j=0; j<ZombieTerror.BOARD_HEIGHT; j++){
                    if (isOccupiedByHuman(new Point(i,j))) { leftside = true;break;}
                }
                if (leftside) break;
            }
            for (int i=ZombieTerror.BOARD_WIDTH/2; i<ZombieTerror.BOARD_WIDTH; i++){   
                for (int j=0; j<ZombieTerror.BOARD_HEIGHT; j++){
                    if (isOccupiedByHuman(new Point(i,j))) { rightside = true;break;}
                }
                if (rightside) break;
            }        
            if (leftside) count++;
            if (rightside) count++;
            return count;
        }
        
        public boolean isValidForZombieToStart (Point p, ZombieModel zm){
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
       
        public boolean isValidForZombieToMove(Point next, ZombieModel zm){
           Point prev = zm.getPointOnBoard();
           ZombieModel.zdirection zd = zm.zombiedirection;
           
            if (!zm.getPointOnBoard().equals(next))
            {
           if (!isFieldEmpty(next)) return false;
            }
           int stepx = next.x - prev.x;
           int stepy = next.y - prev.y;
           /////////////////////////
           //2 steps ahead, no turns?
           if (stepx==2&&stepy==0&&zd==ZombieModel.zdirection.E&&getSquere(prev).squereoccupation==squereOccupation.ZombieE)
               return isFieldEmpty(new Point (prev.x+1, prev.y));
           if (stepx==-2&&stepy==0&&zd==ZombieModel.zdirection.W&&getSquere(prev).squereoccupation==squereOccupation.ZombieW)
               return isFieldEmpty(new Point (prev.x-1, prev.y));         
           if (stepx==0&&stepy==2&&zd==ZombieModel.zdirection.S&&getSquere(prev).squereoccupation==squereOccupation.ZombieS)
               return isFieldEmpty(new Point (prev.x, prev.y+1));         
           if (stepx==0&&stepy==-2&&zd==ZombieModel.zdirection.N&&getSquere(prev).squereoccupation==squereOccupation.ZombieN)
               return isFieldEmpty(new Point (prev.x, prev.y-1));
           ///////////////////////////////////
           //1 step ahead, then turn left or right?
           if (stepx==1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieE&&
                   (zd==ZombieModel.zdirection.N||zd==ZombieModel.zdirection.S)) return true;
           if (stepx==-1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieW&&
                   (zd==ZombieModel.zdirection.N||zd==ZombieModel.zdirection.S)) return true;
           if (stepx==0&&stepy==1&&getSquere(prev).squereoccupation==squereOccupation.ZombieS&&
                   (zd==ZombieModel.zdirection.W||zd==ZombieModel.zdirection.E)) return true;
           if (stepx==0&&stepy==-1&&getSquere(prev).squereoccupation==squereOccupation.ZombieN&&
                    (zd==ZombieModel.zdirection.W||zd==ZombieModel.zdirection.E)) return true;      
           ///////////////////////////////////////
            //turn left or right then 1 step ahead?
           if (stepx==1&&stepy==0&&zd==ZombieModel.zdirection.E&&
                   (getSquere(prev).squereoccupation==squereOccupation.ZombieN||
                   getSquere(prev).squereoccupation==squereOccupation.ZombieS)) return true;
           if (stepx==-1&&stepy==0&&zd==ZombieModel.zdirection.W&&
                   (getSquere(prev).squereoccupation==squereOccupation.ZombieN||
                   getSquere(prev).squereoccupation==squereOccupation.ZombieS)) return true;
           if (stepx==0&&stepy==1&&zd==ZombieModel.zdirection.S&&
                   (getSquere(prev).squereoccupation==squereOccupation.ZombieW||
                   getSquere(prev).squereoccupation==squereOccupation.ZombieE)) return true;           
           if (stepx==0&&stepy==-1&&zd==ZombieModel.zdirection.N&&
                   (getSquere(prev).squereoccupation==squereOccupation.ZombieW||
                   getSquere(prev).squereoccupation==squereOccupation.ZombieE)) return true;
           // one step ahead, and then human on way?
           if (stepx==1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieE&&
                zd==ZombieModel.zdirection.E){
                squereOccupation sq = getSquere(new Point(prev.x+2,prev.y)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
           }
           if (stepx==-1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieW&&
                zd==ZombieModel.zdirection.W){
                squereOccupation sq = getSquere(new Point(prev.x-2,prev.y)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
           }           
           if (stepx==0&&stepy==1&&getSquere(prev).squereoccupation==squereOccupation.ZombieS&&
                zd==ZombieModel.zdirection.S){
                squereOccupation sq = getSquere(new Point(prev.x,prev.y+2)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
           }       
           if (stepx==0&&stepy==-1&&getSquere(prev).squereoccupation==squereOccupation.ZombieN&&
                zd==ZombieModel.zdirection.N){
                squereOccupation sq = getSquere(new Point(prev.x,prev.y-2)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
           }                 
           ////////////////////////////
           // turn left or right to see human?
           if (stepx==0&&stepy==0){
               
                boolean sq = isOccupiedByHuman(new Point(prev.x+1,prev.y));
                if (sq&&zm.zombiedirection==ZombieModel.zdirection.E){
                    if (getSquere(prev).squereoccupation==squereOccupation.ZombieN||
                        getSquere(prev).squereoccupation==squereOccupation.ZombieS    )
                    return true;
                }
                    
                sq = isOccupiedByHuman(new Point(prev.x-1,prev.y));
                if (sq&&zm.zombiedirection==ZombieModel.zdirection.W){
                    if (getSquere(prev).squereoccupation==squereOccupation.ZombieN||
                        getSquere(prev).squereoccupation==squereOccupation.ZombieS    )
                    return true;
                }
                sq = isOccupiedByHuman(new Point(prev.x,prev.y+1));
                if (sq&&zm.zombiedirection==ZombieModel.zdirection.S){
                    if (getSquere(prev).squereoccupation==squereOccupation.ZombieE||
                        getSquere(prev).squereoccupation==squereOccupation.ZombieW    )
                    return true;
                }
                sq = isOccupiedByHuman(new Point(prev.x,prev.y-1));
                if (sq&&zm.zombiedirection==ZombieModel.zdirection.N){
                    if (getSquere(prev).squereoccupation==squereOccupation.ZombieE||
                        getSquere(prev).squereoccupation==squereOccupation.ZombieW    )
                    return true;
                }
           }
              
           
           return false;
        }
        

    public boolean isValidForRageZombie(Point next, ZombieModel zm, boolean hasrampage){
            
           Point prev = zm.getPointOnBoard();
           ZombieModel.zdirection zd = zm.zombiedirection;
           if (!isFieldEmpty(next)) return false;
           
           int stepx = next.x - prev.x;
           int stepy = next.y - prev.y;
           //////////////////////////
           //RAMPAGE?
           if (hasrampage){
           if (stepx==3&&stepy==0&&zd==ZombieModel.zdirection.E&&getSquere(prev).squereoccupation==squereOccupation.ZombieE)
               return isFieldEmpty(new Point (prev.x+1, prev.y));
           if (stepx==-3&&stepy==0&&zd==ZombieModel.zdirection.W&&getSquere(prev).squereoccupation==squereOccupation.ZombieW)
               return isFieldEmpty(new Point (prev.x-1, prev.y));         
           if (stepx==0&&stepy==3&&zd==ZombieModel.zdirection.S&&getSquere(prev).squereoccupation==squereOccupation.ZombieS)
               return isFieldEmpty(new Point (prev.x, prev.y+1));         
           if (stepx==0&&stepy==-3&&zd==ZombieModel.zdirection.N&&getSquere(prev).squereoccupation==squereOccupation.ZombieN)
               return isFieldEmpty(new Point (prev.x, prev.y-1));           }
           /////////////////////////
           //2 steps ahead, no turns?
           if (stepx==2&&stepy==0&&zd==ZombieModel.zdirection.E&&getSquere(prev).squereoccupation==squereOccupation.ZombieE)
               return isFieldEmpty(new Point (prev.x+1, prev.y));
           if (stepx==-2&&stepy==0&&zd==ZombieModel.zdirection.W&&getSquere(prev).squereoccupation==squereOccupation.ZombieW)
               return isFieldEmpty(new Point (prev.x-1, prev.y));         
           if (stepx==0&&stepy==2&&zd==ZombieModel.zdirection.S&&getSquere(prev).squereoccupation==squereOccupation.ZombieS)
               return isFieldEmpty(new Point (prev.x, prev.y+1));         
           if (stepx==0&&stepy==-2&&zd==ZombieModel.zdirection.N&&getSquere(prev).squereoccupation==squereOccupation.ZombieN)
               return isFieldEmpty(new Point (prev.x, prev.y-1));
           // one step ahead, and then human on way?
           if (stepx==1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieE&&
                zd==ZombieModel.zdirection.E){
                squereOccupation sq = getSquere(new Point(prev.x+2,prev.y)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
           }
            if (stepx==-1&&stepy==0&&getSquere(prev).squereoccupation==squereOccupation.ZombieW&&
                zd==ZombieModel.zdirection.W){
                squereOccupation sq = getSquere(new Point(prev.x-2,prev.y)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
            }           
            if (stepx==0&&stepy==1&&getSquere(prev).squereoccupation==squereOccupation.ZombieS&&
                zd==ZombieModel.zdirection.S){
                squereOccupation sq = getSquere(new Point(prev.x,prev.y+2)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
            }               
            if (stepx==0&&stepy==-1&&getSquere(prev).squereoccupation==squereOccupation.ZombieN&&
                zd==ZombieModel.zdirection.N){
                squereOccupation sq = getSquere(new Point(prev.x,prev.y-2)).squereoccupation;
                if(sq == squereOccupation.Banker|| sq ==squereOccupation.Girl||
                       sq==squereOccupation.Lumberjack|| sq==squereOccupation.Mechanic||
                       sq==squereOccupation.Nurse||sq==squereOccupation.Policemen) return true;
            }               
            
            return false;        }
        
}