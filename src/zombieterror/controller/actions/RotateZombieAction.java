
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.RotateZombieEvent;
import zombieterror.view.pawnlabels.Zombie;

/**
 *This action handles Rotation of Zombie. Only moving zombies can be rotated in
 * zombies rounds.
 */
public class RotateZombieAction extends Action
{
     @Override
     public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) {
      GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        RotateZombieEvent event = (RotateZombieEvent) ev;
        Zombie zombie =(Zombie) event.getSource();
         ZombieModel zm =(ZombieModel) gameLogic.zombies.get(zombie.getID());
        
         switch (gameLogic.getState())
        {
            case HumanStart: break;
            case ZombieStart:
               if (zm.getMoving())
               {
                if (event.getNothces()>0)
                {
                 zm.zombiedirection=zm.zombiedirection.nextZDirection();
             
                }
                else
                {
                   zm.zombiedirection=zm.zombiedirection.previousZDirection();
                  

                }
                zombie.rotatePawn(event.getNothces());
               }
                break;
            case HumanTurn:    
            
                break;
            case ZombieTurn:
               if (zm.getMoving())
               {
                if (event.getNothces()>0)
                {
                 zm.zombiedirection=zm.zombiedirection.nextZDirection();
              
                }
                else
                {
                   zm.zombiedirection=zm.zombiedirection.previousZDirection();
                 

                }
                zombie.rotatePawn(event.getNothces());
               }
            
                break;
            case ZombiePlacement:    
               if (zm.getMoving()&&!zm.isOnBoard())
               {
                if (event.getNothces()>0)
                {
                 zm.zombiedirection=zm.zombiedirection.nextZDirection();
              
                }
                else
                {
                   zm.zombiedirection=zm.zombiedirection.previousZDirection();
                  

                }
                zombie.rotatePawn(event.getNothces());
               }
                
        }
    
     }
    
}
