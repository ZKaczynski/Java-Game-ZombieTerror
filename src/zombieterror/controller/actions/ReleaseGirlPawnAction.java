
package zombieterror.controller.actions;

import zombieterror.view.events.ReleaseGirlPawnEvent;
import zombieterror.view.events.CustomEvent;
import zombieterror.model.GameLogic;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.view.GameLook;
import java.awt.Point;


public class ReleaseGirlPawnAction extends Action 
{
    
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
    ReleaseGirlPawnEvent event = (ReleaseGirlPawnEvent) ev;
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
                squereOccupation soq = squereOccupation.Girl;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                if(gameLogic.girlModel.isOnBoard())
                {                                   
                               
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.girlModel.getPointOnBoard(), soq2  ) ;
                }
                event.getSource().setFixedPosition(lookCoordinates);
                gameLogic.girlModel.setPointOnBoard(modelCoordinates);
                gameLogic.girlModel.setOnBoard(true);
                

                
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
            case HumanTurn: 
                /*
            if(!gameLogic.isPointsNeighbour(gameLogic.girlModel.getPointOnBoard(), modelCoordinates)){
                System.out.println("HHOHOOH");
                           event.getSource().setLocation(event.getSource().getFixedPosition());

                return;  
            }
*/
            
               if(gameLogic.girlModel.hasActionPoints()&&gameLogic.isValidForGirlToMove(gameLogic.girlModel.getPointOnBoard(), modelCoordinates))
            {   
               
                gameLogic.checkGirlSprint(gameLogic.girlModel.getPointOnBoard(), modelCoordinates);
                
                gameLogic.girlModel.useActionPoint();
                int points = gameLogic.girlModel.getActionPoints();
                boolean isFull = gameLogic.girlModel.getHp();

                gameLook.mainBoard.reduceActionPoints(points, "Girl", isFull); 
                
                

                squereOccupation soq = squereOccupation.Girl;
                gameLogic.boardModel.updateTile(modelCoordinates, soq  ) ;
                                                
                             
                squereOccupation soq2 = squereOccupation.Empty;
                gameLogic.boardModel.updateTile(gameLogic.girlModel.getPointOnBoard(), soq2  ) ;
                gameLogic.girlModel.setPointOnBoard(modelCoordinates);
                
                event.getSource().setFixedPosition(lookCoordinates);
                event.getSource().setLocation(lookCoordinates);
                
                if (gameLogic.girlModel.getSprinted()==true) gameLook.mainBoard.girlIcon.setEnabled(false);
       

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
