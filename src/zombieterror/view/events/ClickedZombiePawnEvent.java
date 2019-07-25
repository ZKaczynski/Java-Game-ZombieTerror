/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;
import java.awt.Point;

/**
 *
 * Created when on click on Zombie. It tells controler which zombie is curretlny 
 * dragged and can be rotated
 * 
 */
public class ClickedZombiePawnEvent extends PawnEvent
{
    Point point;
    public ClickedZombiePawnEvent(Pawn s, Point p) 
    {
        super(s);
        point = p;
    }
    
        public Point getPoint()
    {
        return point;
    }
    
}
