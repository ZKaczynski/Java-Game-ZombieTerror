
package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;
import java.awt.Point;

/**
 *
 * Created when draging any pawn. It passes to controler info which pawn
 * is dragged and its current position
 * 
 */
public class MovePawnEvent extends PawnEvent
{
    private final Point currentMousePosition;
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