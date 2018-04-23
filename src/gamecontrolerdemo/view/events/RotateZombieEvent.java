
package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;
import java.awt.Point;


public class RotateZombieEvent extends CustomEvent 
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
