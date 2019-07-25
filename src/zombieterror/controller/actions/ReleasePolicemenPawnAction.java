
package zombieterror.controller.actions;

import zombieterror.view.events.ReleasePolicemenPawnEvent;
import zombieterror.view.events.CustomEvent;
import zombieterror.model.GameLogic;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.view.GameLook;

import java.awt.Point;


public class ReleasePolicemenPawnAction extends Action 
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
    
    modelCoordinates = calculateModelCoordintes (lookCoordinates);


    
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
            
            if(!gameLogic.isPointsNeighbour(gameLogic.policemenModel.getPointOnBoard(), modelCoordinates)) 
            {
            event.getSource().setLocation(event.getSource().getFixedPosition());
            return;
            }
            if(gameLogic.policemenModel.hasActionPoints()&&gameLogic.boardModel.isValidForHumanToMove(gameLogic.policemenModel.getPointOnBoard(), modelCoordinates))
            {
                
                gameLogic.policemenModel.useActionPoint();
                int points = gameLogic.policemenModel.getActionPoints();
                boolean isFull = gameLogic.policemenModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Policemen", isFull);  
                
                
                squereOccupation soq = squereOccupation.Policemen;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.policemenModel.getPointOnBoard(), soq2  ) ;
                
                event.getSource().setLocation(lookCoordinates);
                event.getSource().setFixedPosition(lookCoordinates);
                
                gameLogic.policemenModel.setPointOnBoard(modelCoordinates);


                break;
            }
            if (gameLogic.policemenModel.hasActionPoints()&& gameLogic.isValidForHumanToPush(gameLogic.policemenModel.getPointOnBoard(),modelCoordinates ))
            {
                gameLogic.policemenModel.useActionPoint();
                int points = gameLogic.policemenModel.getActionPoints();       //Ustawia punkty akcji
                                boolean isFull = gameLogic.policemenModel.getHp();

                gameLook.mainBoard.reduceActionPoints(points, "Policemen", isFull);   
                
                Point pushPoint = gameLogic.calculetePush(gameLogic.policemenModel.getPointOnBoard(),modelCoordinates);

                gameLogic.findZombie(modelCoordinates).setPointOnBoard(pushPoint);
                
                squereOccupation soq = gameLogic.boardModel.getSquere(modelCoordinates).getSquereOccupation();
                gameLogic.boardModel.updateTile(pushPoint, soq);
                
                Point lookPushPoint = new Point (0,0);
                
                lookPushPoint.x = (pushPoint.x+2)*70;
                lookPushPoint.y = (pushPoint.y)*70;
                gameLook.mainBoard.findZombiePawn(lookCoordinates).setLocation(lookPushPoint);
                gameLook.mainBoard.findZombiePawn(lookCoordinates).setFixedPosition(lookPushPoint);
                
                event.getSource().setLocation(lookCoordinates);
                event.getSource().setFixedPosition(lookCoordinates);
                
                soq = squereOccupation.Policemen;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.policemenModel.getPointOnBoard(), soq2  ) ;

                gameLogic.policemenModel.setPointOnBoard(modelCoordinates);
                
                
                break;
            }

             event.getSource().setLocation(event.getSource().getFixedPosition());
            
            
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
