
package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;
import java.awt.Point;


public class ReleaseZombiePawnEvent extends CustomEvent
{
private Point point;
    public ReleaseZombiePawnEvent(Pawn s, Point p) {
        super(s);
        point = p;
    }
    public Point getPoint()
    {
        return point;
    }
    
    
}
