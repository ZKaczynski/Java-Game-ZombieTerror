
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.BoardModel;
import zombieterror.model.BoardModel.squereOccupation;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.ReleaseZombiePawnEvent;
import zombieterror.view.pawnlabels.Zombie;
import java.awt.Point;
import javax.swing.ImageIcon;

public class ReleaseZombiePawnAction extends Action 
{
      @Override
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

    modelCoordinates = calculateModelCoordintes (lookCoordinates);

        
        Zombie z = (Zombie) event.getSource();
        ZombieModel zm =(ZombieModel) gameLogic.zombies.get(z.getID());
        
        
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
                
                if (gameLogic.boardModel.isValidForZombieToStart(modelCoordinates, zm)){
                    event.getSource().setLocation(lookCoordinates);
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    if(zm.isOnBoard()){                                   
                        BoardModel.squereOccupation soq2 = BoardModel.squereOccupation.Empty;
                        gameLogic.boardModel.updateTile(zm.getPointOnBoard(), soq2  ) ;
                    }
                    event.getSource().setFixedPosition(lookCoordinates);
                    zm.setPointOnBoard(modelCoordinates);
                    zm.setOnBoard(true);    
                }
                else {
                    event.getSource().setLocation(event.getSource().getFixedPosition());
                }    
                zm.setMoving(false);


                break;
            case HumanTurn:    
            
             event.getSource().setLocation(event.getSource().getFixedPosition());    
                
            break;
            
            case ZombieTurn: 
                if ((gameLogic.boardModel.isValidForZombieToMove(modelCoordinates, zm)) &&
                     (zm.getActionPoints()!=0)&& gameLogic.zombiepowerawe  &&
                        gameLogic.zombiesToMove>0
                        ){
                                        event.getSource().setFixedPosition(lookCoordinates);
                    event.getSource().setLocation(lookCoordinates);
                    BoardModel.squereOccupation soq2 = BoardModel.squereOccupation.Empty;
                    gameLogic.boardModel.updateTile(zm.getPointOnBoard(), soq2  ) ;
                    zm.setPointOnBoard(modelCoordinates);
                    
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    zm.setMoving(false);
                    zm.zombieState=ZombieModel.zState.normal;
                    zm.setActionPoints(0);
                    gameLogic.zombiesToMove-=1;
                    gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                    gameLogic.zombiepowerawe=false;
                    
                }
                
                else if ((gameLogic.boardModel.isValidForRageZombie(modelCoordinates, zm, gameLogic.zombiepowerrampage))&&
                    (zm.getActionPoints()!=0)&&(zm.zombieState==ZombieModel.zState.rage)&&
                        gameLogic.zombiesToMove>0
                        ){
                    event.getSource().setFixedPosition(lookCoordinates);
                    event.getSource().setLocation(lookCoordinates);
                    BoardModel.squereOccupation soq2 = BoardModel.squereOccupation.Empty;
                    gameLogic.boardModel.updateTile(zm.getPointOnBoard(), soq2  ) ;
                    zm.setPointOnBoard(modelCoordinates);
                    
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    zm.setMoving(false);
                    zm.zombieState=ZombieModel.zState.normal;
                    zm.setActionPoints(0);
                    gameLogic.zombiesToMove-=1;
                    gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                    
                }

                else if((gameLogic.boardModel.isValidForZombieToMove(modelCoordinates, zm)) &&
                     (zm.getActionPoints()!=0)&&   (
                     (zm.zombieState==ZombieModel.zState.normal&&gameLogic.noRagingZombie())  
                        )&&
                        gameLogic.zombiesToMove>0
                        ){
                    event.getSource().setFixedPosition(lookCoordinates);
                    event.getSource().setLocation(lookCoordinates);
                    BoardModel.squereOccupation soq2 = BoardModel.squereOccupation.Empty;
                    gameLogic.boardModel.updateTile(zm.getPointOnBoard(), soq2  ) ;
                    zm.setPointOnBoard(modelCoordinates);
                    
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    zm.setMoving(false);
                    zm.zombieState=ZombieModel.zState.normal;
                    zm.setActionPoints(0);
                    gameLogic.zombiesToMove-=1;
                    gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                }
                else{
                Zombie zp = (Zombie) event.getSource(); 
                

                
                zp.setLocation(event.getSource().getFixedPosition()); 
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Zombie.png"));
                zp.setIcon(icon);
                Point modp = calculateModelCoordintes(event.getSource().getFixedPosition());
                switch (gameLogic.boardModel.getSquere(modp).getSquereOccupation()){
                    case ZombieN: zm.zombiedirection=ZombieModel.zdirection.N; break;
                    case ZombieE: zm.zombiedirection=ZombieModel.zdirection.E; zp.rotatePawn(1);  break; 
                    case ZombieS: zm.zombiedirection=ZombieModel.zdirection.S; zp.rotatePawn(1); zp.rotatePawn(1); break;
                    case ZombieW: zm.zombiedirection=ZombieModel.zdirection.W; zp.rotatePawn(-1); break;    
                    
                    default:System.out.println("CRITICAL ERROR!!!lolxd");
                }
                
                zm.setMoving(false);
                break;

                }
                
            
            break;    
            case ZombiePlacement:    
                
                if (gameLogic.isValidForZombieToSpawn(modelCoordinates, zm)){
                    event.getSource().setLocation(lookCoordinates);
                    gameLogic.boardModel.updateTile(modelCoordinates, current  ) ;
                    event.getSource().setFixedPosition(lookCoordinates);
                    zm.setPointOnBoard(modelCoordinates);
                    zm.setOnBoard(true);    
                }
                else {
                    event.getSource().setLocation(event.getSource().getFixedPosition());
                    // zm.setOnBoard(false); 
                }    
                zm.setMoving(false);        }
    }
             else 
    {
        
        event.getSource().setLocation(event.getSource().getFixedPosition());
        
    }
            
    }
    
    
    
}
