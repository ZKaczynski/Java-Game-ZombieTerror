
package zombieterror.controller.actions;

import zombieterror.view.events.ReleaseBankerPawnEvent;
import zombieterror.view.events.CustomEvent;
import zombieterror.model.GameLogic;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.view.GameLook;
import java.awt.Point;
import javax.swing.ImageIcon;


public class ReleaseBankerPawnAction extends Action 
{

    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
        
    ReleaseBankerPawnEvent event = (ReleaseBankerPawnEvent) ev;
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
                squereOccupation soq = squereOccupation.Banker;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                if(gameLogic.bankerModel.isOnBoard())
                {                                   
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.bankerModel.getPointOnBoard(), soq2  ) ;
                }
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.bankerModel.setPointOnBoard(modelCoordinates);
                gameLogic.bankerModel.setOnBoard(true);
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
            
            if(!gameLogic.isPointsNeighbour(gameLogic.bankerModel.getPointOnBoard(), modelCoordinates)){
                             event.getSource().setLocation(event.getSource().getFixedPosition());

                return;
            }
            
            if(gameLogic.bankerModel.hasActionPoints()&&gameLogic.boardModel.isValidForHumanToMove(gameLogic.bankerModel.getPointOnBoard(), modelCoordinates))
            {
                
                gameLogic.bankerModel.useActionPoint();
                
                
                event.getSource().setLocation(lookCoordinates);
                squereOccupation soq = squereOccupation.Banker;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.bankerModel.getPointOnBoard(), soq2  ) ;
                
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.bankerModel.setPointOnBoard(modelCoordinates);
                
                int points = gameLogic.bankerModel.getActionPoints();
                boolean isFull = gameLogic.bankerModel.getHp();

                gameLook.mainBoard.reduceActionPoints(points, "Banker",isFull);        

                break;
            }
            //System.out.println("NIE DA Się RUSZAC");
            // else 
            if (gameLogic.bankerModel.hasActionPoints()&& gameLogic.isValidForHumanToPush(gameLogic.bankerModel.getPointOnBoard(),modelCoordinates ))
            {
                gameLogic.bankerModel.useActionPoint();
                int points = gameLogic.bankerModel.getActionPoints();       //Ustawia punkty akcji
                                boolean isFull = gameLogic.bankerModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Banker",isFull);   
                
                Point pushPoint = gameLogic.calculetePush(gameLogic.bankerModel.getPointOnBoard(),modelCoordinates);
                //System.out.println(pushPoint+"PUSHPOINT");
                
                gameLogic.findZombie(modelCoordinates).setPointOnBoard(pushPoint);
               // System.out.println("ZNALAZlEM ZOMBIAKA DO PCHNIĘCIA O KOR:"+modelCoordinates+"  "+
              //  "I DAJE MU NOWE KOORDYNATY "+ pushPoint);
                
                squereOccupation soq = gameLogic.boardModel.getSquere(modelCoordinates).getSquereOccupation();
             //   System.out.println(soq);
                gameLogic.boardModel.updateTile(pushPoint, soq);
                
                Point lookPushPoint = new Point (0,0);
                
                lookPushPoint.x = (pushPoint.x+2)*70;
                lookPushPoint.y = (pushPoint.y)*70;
                gameLook.mainBoard.findZombiePawn(lookCoordinates).setLocation(lookPushPoint);
                gameLook.mainBoard.findZombiePawn(lookCoordinates).setFixedPosition(lookPushPoint);
                
                event.getSource().setLocation(lookCoordinates);
                event.getSource().setFixedPosition(lookCoordinates);
                
                soq = squereOccupation.Banker;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                               
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.bankerModel.getPointOnBoard(), soq2  ) ;
                
                
                
                
                gameLogic.bankerModel.setPointOnBoard(modelCoordinates);
                
                
               // gameLogic.show();
                
               // System.out.println("WSPOLRZEDNE BANKIERA NAONIEC"+gameLogic.bankerModel.getPointOnBoard());
               // System.out.println("WSPOLRZEDNE ZOMBIAKA NA KONIEC"+gameLogic.findZombie(new Point(2,0)));
                break;
            }
            // System.out.println("NIE DA Się Pchnąc");
            //else 
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
