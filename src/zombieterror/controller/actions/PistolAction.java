
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.BoardModel;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.CustomEvent;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * This action handles activation of human ability pistol action. 
 * Checks if focused zombie can be killed. If yes reduces lumberjack action points,
 * sets zombie model to default, makes zombie label invisible and sets its lokation 
 * to spawn
 * 
 */
public class PistolAction extends Action
{

        @Override
        public void performAction(CustomEvent event, GameLogic gl, GameLook gk) 
    {
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        switch (gameLogic.getState())
        {
            case HumanTurn:
                
               if (gameLogic.policemenModel.hasActionPoints()&&gameLogic.isValidForShoot())
               {
                gameLogic.policemenModel.useActionPoint();
                int points = gameLogic.policemenModel.getActionPoints();
                boolean isFull = gameLogic.policemenModel.getHp();

                gameLook.mainBoard.reduceActionPoints(points, "Policemen",isFull );
                 
                ZombieModel zm =(ZombieModel) gameLogic.focusPawn;
                
              
                gameLook.mainBoard.killZombie(zm.getId());
                
                Point p = zm.getPointOnBoard();
                gameLogic.boardModel.getSquere(p).updateSquere(BoardModel.squereOccupation.Empty);
                
                zm.setAlive(false);
                zm.setPointOnBoard(new Point (-1,-1));
                
                gameLogic.policemenModel.activateBullet();
                int i = gameLogic.policemenModel.getBullets();
                gameLook.mainBoard.policemenIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images//Interface//IconPoliceman"+i+".png")));
                if (i==0) gameLook.mainBoard.policemenIcon.setEnabled(false);
                
                
                gameLogic.zombiesToSpawn++;
                }
 
        }
        
        }
        
    }
    
    

