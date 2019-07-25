
package zombieterror.controller.actions;

import zombieterror.model.GameLogic;
import zombieterror.view.GameLook;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.MovePawnEvent;
import java.awt.Point;

/**
 *
 * This action moves pawn labels around game look. Its only sets new location of 
 * label on screen because all logic of checking if new coordintes fo pawn are 
 * valid in game/have sense on sceen is done when releasig pawn, ergo it does 
 * nothing it logic in current version. In newer versions it can be changed.
 */
public class MovePawnAction extends Action {    
    @Override
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) {
        GameLook gameLook = gk;    
        MovePawnEvent event = (MovePawnEvent) ev;
        GameLogic gameLogic =  gl;
        if (gameLogic.getState()==GameLogic.stateOfGame.End) return;
        int x = event.getCurrentMousePosition().x -  gameLook.mainBoard.getLocationOnScreen().x;   ;
        int y = event.getCurrentMousePosition().y - gameLook.mainBoard.getLocationOnScreen().y  ;
        event.getSource().setLocation(new Point(x-35,y-35));
        gameLook.mainBoard.setComponentZOrder(event.getSource(), 0);
        
    }
    
    
}
