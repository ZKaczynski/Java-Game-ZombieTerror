
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.model.BoardModel;
import gamecontrolerdemo.model.BoardModel.squereOccupation;
import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.model.ZombieModel;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.ReleaseZombiePawnEvent;
import gamecontrolerdemo.view.pawns.Zombie;
import java.awt.Point;

public class ReleaseZombiePawnAction extends Action 
{
      public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
        ReleaseZombiePawnEvent event = (ReleaseZombiePawnEvent) ev;
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        
        Point lookCoordinates =event.getPoint();
        Point modelCoordinates;

        lookCoordinates.x = lookCoordinates.x - gameLook.mainBoard.getLocationOnScreen().x; 
        lookCoordinates.y = lookCoordinates.y - gameLook.mainBoard.getLocationOnScreen().y; 
        lookCoordinates.x =lookCoordinates.x -(lookCoordinates.x%70);
        lookCoordinates.y =lookCoordinates.y -(lookCoordinates.y%70);

        modelCoordinates= new Point (lookCoordinates.x,lookCoordinates.y);
        modelCoordinates.x = modelCoordinates.x/70-2;
        modelCoordinates.y = modelCoordinates.y/70;
      
        
        Zombie z = (Zombie) event.getSource();
        ZombieModel zm =(ZombieModel) gameLogic.zombies.get(z.getID());
        
          //  System.out.println(modelCoordinates.x+"||||:"+modelCoordinates.y);
        
        squereOccupation current = null;
        switch(zm.zombiedirection)
        {
            case N: current=squereOccupation.ZombieN; break;
             case E: current=squereOccupation.ZombieE; break;
              case W: current=squereOccupation.ZombieW; break;
               case S: current=squereOccupation.ZombieS; break;
                default: System.out.println("DIRECTION ERROR");
        }
        
      
        
            if (gameLook.mainBoard.hasCorrectCoordinates(lookCoordinates))
    {
        
        switch (gameLogic.getState())
        {
            case HumanStart: break;
            
            case ZombieStart:
                
                if (gameLogic.boardModel.isValidForZombieToStart(modelCoordinates, zm))
                {
                    event.getSource().setLocation(lookCoordinates);
                  //  BoardModel.squereOccupation soq = BoardModel.squereOccupation.Policemen;
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    if(zm.isOnBoard())
                    {                                   
                        BoardModel.squereOccupation soq2 = BoardModel.squereOccupation.Empty;
                        gameLogic.boardModel.updateTile(zm.getPointOnBoard(), soq2  ) ;
                    }
                    event.getSource().setFixedPosition(lookCoordinates);
                    zm.setPointOnBoard(modelCoordinates);
                    zm.setOnBoard(true);
                    
                }
                else  
                {
                    event.getSource().setLocation(event.getSource().getFixedPosition());
                    
                }    
                zm.setMoving(false);
                System.out.println("DOWN");

                
                
                
                
                
                
                
                
            
                break;
            case HumanTurn:    
            
             event.getSource().setLocation(event.getSource().getFixedPosition());    
                
            break;    
            case ZombieTurn:    
            
            break;    
            case ZombiePlacement:    
                
            event.getSource().setLocation(event.getSource().getFixedPosition());        
        }
    }
             else 
    {
        
        event.getSource().setLocation(event.getSource().getFixedPosition());
        
    }
            
    }
    
    
    
}
