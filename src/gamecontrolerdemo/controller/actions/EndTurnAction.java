
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.view.events.CustomEvent;



public class EndTurnAction extends Action
{
        public void performAction(CustomEvent event, GameLogic gl, GameLook gk) 
    {
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        
         switch (gameLogic.getState())
        {
            case HumanStart: 
                if (gameLogic.allHumansOnBoard())
                {
                    gameLogic.nextState();
                    gameLogic.setUpZombies();
                    gameLook.mainBoard.spwanZombies();
                    gameLook.mainBoard.repaint();
                    
                }
                
                break;
            
            case ZombieStart:
                if (gameLogic.allStartingZombiesOnBoard())
                {
                    System.out.println("KOniec Tury");
                    gameLogic.nextState();
                }
                break;
             
            
            case HumanTurn:    
                 gameLogic.nextState();
                 break;
            
            case ZombieTurn:  
                gameLogic.nextState();
             break;
            
            case ZombiePlacement:    
                gameLogic.nextState();
                 break;
            
        }
         
    }
    
    
}
