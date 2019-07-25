
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.ClickedZombiePawnEvent;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.pawnlabels.Zombie;

/**
 * 
 * This action is used to determines if ZombieLabel is moving or not.
 * Only dragged zombies can be rotated in theie turn. 
 */
public class ClickedZombiePawnAction extends Action{
    @Override
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
        ClickedZombiePawnEvent event = (ClickedZombiePawnEvent) ev;
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        
        Zombie z = (Zombie) event.  getSource();
        ZombieModel zm =(ZombieModel) gameLogic.zombies.get(z.getID());
        switch (gameLogic.getState())
        {
            case HumanStart: break;
            
            case ZombieStart:
                zm.setMoving(true);
                break;
            case HumanTurn:                
            case ZombieTurn:    
                 zm.setMoving(true);
            break;    
            case ZombiePlacement: 
                 zm.setMoving(true);
                
                
        }
        
        
    }
    
    
    
    
    
    
    
    
}
