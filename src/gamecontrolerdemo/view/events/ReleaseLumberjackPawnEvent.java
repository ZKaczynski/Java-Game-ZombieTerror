package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;
import java.awt.Point;


public class ReleaseLumberjackPawnEvent extends CustomEvent
{
    private Point point;
    public ReleaseLumberjackPawnEvent(Pawn s, Point p) {
        super(s);
        point = p;
    }
    
    public Point getPoint()
    {
        return point;
    }
}