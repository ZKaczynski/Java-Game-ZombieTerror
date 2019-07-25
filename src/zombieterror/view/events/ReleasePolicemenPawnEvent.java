package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;
import java.awt.Point;


public class ReleasePolicemenPawnEvent extends PawnEvent
{
    private Point point;
    public ReleasePolicemenPawnEvent(Pawn s, Point p) {
        super(s);
        point = p;
    }
    
    public Point getPoint()
    {
        return point;
    }
}