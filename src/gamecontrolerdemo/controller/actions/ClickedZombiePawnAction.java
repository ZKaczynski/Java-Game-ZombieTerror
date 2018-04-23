
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.model.ZombieModel;
import gamecontrolerdemo.view.events.ClickedZombiePawnEvent;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.pawns.Zombie;


public class ClickedZombiePawnAction extends Action
{
        @Override
        public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) 
    {
        ClickedZombiePawnEvent event = (ClickedZombiePawnEvent) ev;
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        
        Zombie z = (Zombie) event.  getSource();
        
        switch (gameLogic.getState())
        {
            case HumanStart: break;
            
            case ZombieStart:
                 System.out.println("ClickesZ:"+z.getID());
                ZombieModel zm =(ZombieModel) gameLogic.zombies.get(z.getID());
                zm.setMoving(true);
                System.out.println("UP");
                break;
            case HumanTurn:    
            
            break;    
            case ZombieTurn:    
            
            break;    
            case ZombiePlacement:    
                
                
        }
        
        
    }
    
    
    
    
    
    
    
    
}
