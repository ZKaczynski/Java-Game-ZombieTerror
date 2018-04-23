
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.model.BoardModel.squereOccupation;
import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.GlobalConstants;
import gamecontrolerdemo.view.events.*;
import java.awt.Point;


public class ReleasePolicemenPawnAction extends Action implements GlobalConstants
{
    
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
    ReleasePolicemenPawnEvent event = (ReleasePolicemenPawnEvent) ev;
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


    
    if (gameLook.mainBoard.hasCorrectCoordinates(lookCoordinates))
    {
        
        switch (gameLogic.getState())
        {
            case HumanStart: 
                if (gameLogic.boardModel.isValidForHumanToStart(modelCoordinates))
                {
                event.getSource().setLocation(lookCoordinates);
                squereOccupation soq = squereOccupation.Policemen;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                if(gameLogic.policemenModel.isOnBoard())
                {                                   
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.policemenModel.getPointOnBoard(), soq2  ) ;
                }
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.policemenModel.setPointOnBoard(modelCoordinates);
                gameLogic.policemenModel.setOnBoard(true);
                }
                else  
                {
                    event.getSource().setLocation(event.getSource().getFixedPosition());
                }

                ;
            
            
            break;
            case ZombieStart:
                
            event.getSource().setLocation(event.getSource().getFixedPosition());
            break;    
            case HumanTurn: ;
            //TO_DO
            break;
            case ZombieTurn: ;
            event.getSource().setLocation(event.getSource().getFixedPosition());
            break;
            case ZombiePlacement: ;
            event.getSource().setLocation(event.getSource().getFixedPosition());
        }
        
    }
    else 
    {
        
        event.getSource().setLocation(event.getSource().getFixedPosition());
        
    }
    
    
    
        
        
    }
    

    
}
