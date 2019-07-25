
package zombieterror.controller.actions;

import java.awt.Point;
import zombieterror.model.GameLogic;
import zombieterror.view.GameLook;
import zombieterror.view.events.CustomEvent;

/**
 *
 * Action is abstract parent class to all other Acions in game. It has one method
 * performAcrion that reacts to events in a proper way depending on curren logic
 * and look. 
 */
public abstract class Action {

    /**
     *
     * @param event - Event that makes controler to create this action
     * @param gl - current game logic 
     * @param gk - current gme look
     */
    public abstract void performAction(CustomEvent event, GameLogic gl, GameLook gk); 

    /**
     * Translates Point representing coordintaes on screen to point representing
     * coordinates on 16x12 board
     * @param lookCoordintes - coordintes on screesn
     * @return coordintes on 16x12 board
     */
    public Point calculateModelCoordintes(Point lookCoordintes){
    Point modelPoint = new Point(lookCoordintes.x,lookCoordintes.y);
    modelPoint.x = modelPoint.x/70-2;
    modelPoint.y = modelPoint.y/70;        
    return modelPoint;
    }

    
}
