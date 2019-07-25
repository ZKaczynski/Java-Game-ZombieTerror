
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.GameLogic;
import zombieterror.view.events.AbilityClickedEvent;
import zombieterror.view.events.CustomEvent;

/**
 * 
 * This action handles activation of ZombiePowers. They can be activeted only 
 * durnig zombie turn.
 */
public class AbilityClickedAction extends Action {
    @Override
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) {
    
        AbilityClickedEvent event = (AbilityClickedEvent) ev;
        String eventName = event.getSource();
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;        
        
        if (gameLogic.getState()==GameLogic.stateOfGame.ZombieTurn){ 
            switch (eventName){
                case "plague": //can deploy 2 more zombies 
                    gameLogic.zombiesToSpawn+=2;
                    gameLook.mainBoard.plagueIcon.setEnabled(false);
                    break;
                case "trap": // sets flag allowing to place zombie anywhere
                    gameLogic.zombiepowertrap=true;
                    gameLook.mainBoard.trapIcon.setEnabled(false);
                    break;
                case "bloodlust": // can move 2 more zombies
                    gameLogic.zombiesToMove+=2;
                    gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                    gameLook.mainBoard.bloodlustIcon.setEnabled(false);
                    break;
                case "awe":       // sets flag allowing to move rage zombie with normal rules
                    gameLogic.zombiepowerawe=true;
                    gameLook.mainBoard.aweIcon.setEnabled(false);
                    break;                
                case "rampage":        // sets flag allowing to move zombie by 3 tiles
                    gameLogic.zombiepowerrampage=true;
                    gameLook.mainBoard.rampageIcon.setEnabled(false);
                    break;                  
            }
        }    
    
    
        }
    }