
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.model.ZombieModel;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.RotateZombieEvent;
import gamecontrolerdemo.view.pawns.Zombie;


public class RotateZombieAction extends Action
{
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
                 System.out.println(zm.zombiedirection);
                }
                else
                {
                   zm.zombiedirection=zm.zombiedirection.previousZDirection();
                   System.out.println(zm.zombiedirection);

                }
                zombie.rotatePawn(event.getNothces());
               }
                break;
            case HumanTurn:    
            
                break;
            case ZombieTurn:    
            
                break;
            case ZombiePlacement:    
                
                
        }
    
     }
    
}
