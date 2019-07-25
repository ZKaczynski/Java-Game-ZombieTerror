
package zombieterror.controller.actions;

import zombieterror.view.events.ReleaseMechanicPawnEvent;
import zombieterror.view.events.CustomEvent;
import zombieterror.model.GameLogic;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.view.GameLook;
import zombieterror.model.pawnmodels.PawnModel;
import java.awt.Point;


public class ReleaseMechanicPawnAction extends Action 
{
    
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
    ReleaseMechanicPawnEvent event = (ReleaseMechanicPawnEvent) ev;
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
                squereOccupation soq = squereOccupation.Lumberjack;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                if(gameLogic.mechanicModel.isOnBoard())
                {                                   
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.mechanicModel.getPointOnBoard(), soq2  ) ;
                }
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.mechanicModel.setPointOnBoard(modelCoordinates);
                gameLogic.mechanicModel.setOnBoard(true);
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
            
            if(!gameLogic.isPointsNeighbour(gameLogic.mechanicModel.getPointOnBoard(), modelCoordinates)){
             event.getSource().setLocation(event.getSource().getFixedPosition());                
                return;
            }

            
            
             if(gameLogic.mechanicModel.hasActionPoints()&&gameLogic.boardModel.isValidForHumanToMove(gameLogic.mechanicModel.getPointOnBoard(), modelCoordinates))
            {
                
                gameLogic.mechanicModel.useActionPoint();
                
                
                event.getSource().setLocation(lookCoordinates);
                squereOccupation soq = squereOccupation.Mechanic;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.mechanicModel.getPointOnBoard(), soq2  ) ;
                
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.mechanicModel.setPointOnBoard(modelCoordinates);
                
                int points = gameLogic.mechanicModel.getActionPoints();
                boolean isFull = gameLogic.mechanicModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Mechanic", isFull);        

                break;
            }
             else if (gameLogic.mechanicModel.hasActionPoints()&& gameLogic.isValidForHumanToPush(gameLogic.mechanicModel.getPointOnBoard(),modelCoordinates ))
            {
                gameLogic.mechanicModel.useActionPoint();
                int points = gameLogic.mechanicModel.getActionPoints();       //Ustawia punkty akcji
                boolean isFull = gameLogic.mechanicModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Mechanic", isFull);   
                
                Point pushPoint = gameLogic.calculetePush(gameLogic.mechanicModel.getPointOnBoard(),modelCoordinates);
              
                
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
                
                soq = squereOccupation.Mechanic;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.mechanicModel.getPointOnBoard(), soq2  ) ;
                
                
                
                
                gameLogic.mechanicModel.setPointOnBoard(modelCoordinates);
                

                break;
            }
            else if (gameLogic.mechanicModel.hasActionPoints()&& gameLogic.isValidForMechanicToPush(gameLogic.mechanicModel.getPointOnBoard(),modelCoordinates ))
            {
                gameLogic.mechanicModel.useActionPoint();
                int points = gameLogic.mechanicModel.getActionPoints();       //Ustawia punkty akcji
                boolean isFull = gameLogic.mechanicModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Mechanic", isFull);   
                
                gameLogic.mechanicModel.activateLift();
                
                
                Point pushPoint = gameLogic.calculetePush(gameLogic.mechanicModel.getPointOnBoard(),modelCoordinates);
                 
                squereOccupation sq = gameLogic.boardModel.getSquere(modelCoordinates).getSquereOccupation();
                PawnModel pm = null;
                switch (sq){
                    case Banker: pm = gameLogic.bankerModel; break;
                     case Girl: pm = gameLogic.girlModel; break;
                      case Lumberjack: pm = gameLogic.lumberjackModel; break;
                       case Nurse: pm = gameLogic.nurseModel; break;
                        case Policemen: pm = gameLogic.policemenModel;
                        default: System.err.print("KRYTYK!!!!");
                }
               pm.setPointOnBoard(pushPoint); 
               
              
           
                gameLogic.boardModel.updateTile(pushPoint, sq);
                
                Point lookPushPoint = new Point (0,0);
                
                lookPushPoint.x = (pushPoint.x+2)*70;
                lookPushPoint.y = (pushPoint.y)*70;
                
                    switch (sq){
                    case Banker: 
                        gameLook.mainBoard.bankerPawn.setLocation(lookPushPoint);
                        gameLook.mainBoard.bankerPawn.setFixedPosition(lookPushPoint);
                        break;
                
                    case Girl: 
                        gameLook.mainBoard.girlPawn.setLocation(lookPushPoint);
                        gameLook.mainBoard.girlPawn.setFixedPosition(lookPushPoint);
                        break;                        
                        
                    case Lumberjack: 
                        gameLook.mainBoard.lumberjackPawn.setLocation(lookPushPoint);
                        gameLook.mainBoard.lumberjackPawn.setFixedPosition(lookPushPoint);
                        break;                    
                       
                        
                    case Nurse: 
                        gameLook.mainBoard.nursePawn.setLocation(lookPushPoint);
                        gameLook.mainBoard.nursePawn.setFixedPosition(lookPushPoint);
                        break;
                        
                    case Policemen: 
                        gameLook.mainBoard.policemanPawn.setLocation(lookPushPoint);
                        gameLook.mainBoard.policemanPawn.setFixedPosition(lookPushPoint);
                        break;          
                        
                     default: System.err.print("KRYTYK!!!!");    
                }
                

                event.getSource().setLocation(lookCoordinates);
                event.getSource().setFixedPosition(lookCoordinates);
                
                sq = squereOccupation.Mechanic;
                gameLogic.boardModel.updateTile(modelCoordinates, sq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.mechanicModel.getPointOnBoard(), soq2  ) ;

                gameLogic.mechanicModel.setPointOnBoard(modelCoordinates);
                    
               if (gameLogic.mechanicModel.getLifted()==true)
                {
                    gameLook.mainBoard.mechanicIcon.setEnabled(false);
                }
                
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
