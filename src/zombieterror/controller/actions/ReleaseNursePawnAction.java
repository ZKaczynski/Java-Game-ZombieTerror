
package zombieterror.controller.actions;

import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.ReleaseNursePawnEvent;
import zombieterror.model.GameLogic;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.view.GameLook;

import java.awt.Point;


public class ReleaseNursePawnAction extends Action 
{
    
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
    ReleaseNursePawnEvent event = (ReleaseNursePawnEvent) ev;
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
                squereOccupation soq = squereOccupation.Nurse;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                if(gameLogic.nurseModel.isOnBoard())
                {                                   
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.nurseModel.getPointOnBoard(), soq2  ) ;
                }
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.nurseModel.setPointOnBoard(modelCoordinates);
                gameLogic.nurseModel.setOnBoard(true);
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
           
            if(!gameLogic.isPointsNeighbour(gameLogic.nurseModel.getPointOnBoard(), modelCoordinates)){
                event.getSource().setLocation(event.getSource().getFixedPosition());
                return;
            }
            if(gameLogic.nurseModel.hasActionPoints()&&gameLogic.boardModel.isValidForHumanToMove(gameLogic.nurseModel.getPointOnBoard(), modelCoordinates))
            {
                
                gameLogic.nurseModel.useActionPoint();
                int points = gameLogic.nurseModel.getActionPoints();
                boolean isFull = gameLogic.nurseModel.getHp();

                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);  
                
                
                squereOccupation soq = squereOccupation.Nurse;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.nurseModel.getPointOnBoard(), soq2  ) ;
                
                event.getSource().setLocation(lookCoordinates);
                event.getSource().setFixedPosition(lookCoordinates);
                
                gameLogic.nurseModel.setPointOnBoard(modelCoordinates);


                break;
            }
            if (gameLogic.nurseModel.hasActionPoints()&& gameLogic.isValidForHumanToPush(gameLogic.nurseModel.getPointOnBoard(),modelCoordinates ))
            {
                gameLogic.nurseModel.useActionPoint();
                int points = gameLogic.nurseModel.getActionPoints();       //Ustawia punkty akcji
                boolean isFull = gameLogic.nurseModel.getHp();
                
                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);   
                
                Point pushPoint = gameLogic.calculetePush(gameLogic.nurseModel.getPointOnBoard(),modelCoordinates);

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
                
                soq = squereOccupation.Nurse;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.nurseModel.getPointOnBoard(), soq2  ) ;

                gameLogic.nurseModel.setPointOnBoard(modelCoordinates);
                
                
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
