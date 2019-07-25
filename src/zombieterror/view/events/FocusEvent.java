
package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;

/**
 *
 * Created clicking Pawn. It passes to controler info which pawn is curently focused
 * 
 */
public class FocusEvent extends PawnEvent
{
    public FocusEvent(Pawn s) 
    {
        super(s);
       
    }

            
}