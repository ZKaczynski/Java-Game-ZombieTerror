
package gamecontrolerdemo.controller.actions;

import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.GameLook;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.MovePawnEvent;
import java.awt.Point;


public class MovePawnAction extends Action 
{
    public MovePawnAction()
    {
        super();
    }
    
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) {
    GameLook gameLook = gk;    
    MovePawnEvent event = (MovePawnEvent) ev;
    
    
    int x = event.getCurrentMousePosition().x; // -  gameLook.mainBoard.getLocation().x ) ;
    int y = event.getCurrentMousePosition().y; // -  gameLook.mainBoard.getLocation().y ) ;
    
   //System.out.println(x+"   "+y);
 
    
    event.getSource().setPawn(new Point(x,y+25));
        
        
    }
    
    
}
