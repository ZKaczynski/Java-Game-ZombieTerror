
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.BoardModel;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import static zombieterror.model.pawnmodels.ZombieModel.zdirection.N;
import zombieterror.view.events.CustomEvent;
import java.awt.Point;

/**
 * This action handles activation of human ability axe action. 
 * Checks if focused zombie can be killed. If yes reduces lumberjack action points,
 * sets zombie model to default, makes zombie label invisible and sets its lokation 
 * to spawn
 * 
 */
public class AxeAction extends Action{
    @Override
    public void performAction(CustomEvent event, GameLogic gl, GameLook gk) {
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        if ((gameLogic.getState()== GameLogic.stateOfGame.HumanTurn)&&
            gameLogic.lumberjackModel.hasActionPoints()&&gameLogic.isValidForAxe()){
                gameLogic.lumberjackModel.useActionPoint();
                int points = gameLogic.lumberjackModel.getActionPoints();
                boolean isFull = gameLogic.lumberjackModel.getHp();
                gameLook.mainBoard.reduceActionPoints(points, "Lumberjack", isFull );
                ZombieModel zm =(ZombieModel) gameLogic.focusPawn;
                gameLook.mainBoard.killZombie(zm.getId());
                Point p = zm.getPointOnBoard();
                gameLogic.boardModel.getSquere(p).updateSquere(BoardModel.squereOccupation.Empty);
                zm.setAlive(false);
                zm.setOnBoard(false);
                zm.setPointOnBoard(new Point (-1,-1));
                zm.zombiedirection = N;
                gameLogic.focusPawn=null;
                          
        }    
    }        
}
    
    

