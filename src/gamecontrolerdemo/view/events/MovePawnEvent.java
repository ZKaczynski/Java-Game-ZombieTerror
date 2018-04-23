
package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;
import java.awt.Point;


public class MovePawnEvent extends CustomEvent
{
    private Point currentMousePosition;
    public MovePawnEvent(Pawn s,Point p ) 
    {
        super(s);
        currentMousePosition=p;  
    }
    
    public Point getCurrentMousePosition() 
    {
        return currentMousePosition;
    }
    
    
            
}