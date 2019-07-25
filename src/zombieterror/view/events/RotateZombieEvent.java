
package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;
import java.awt.Point;


public class RotateZombieEvent extends PawnEvent 
{
    int notches;
    public RotateZombieEvent(Pawn s, int n ) 
    {
        super(s);
        notches = n;    
    }
    
    public int getNothces()
    {
        return notches;
    }
    
}
