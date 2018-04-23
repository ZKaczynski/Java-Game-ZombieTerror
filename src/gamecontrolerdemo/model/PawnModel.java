
package gamecontrolerdemo.model;

import java.awt.Point;


public class PawnModel 
{
    private boolean onboard;
    private Point pointonboard;
    private int actionpointsleft;
    
    public PawnModel()
    {
        onboard = false;
        actionpointsleft = 0;
        
    }
    
    public void setOnBoard(boolean b)
    {
        onboard=b;
    }
    
    public boolean isOnBoard()
    { 
        return onboard;
    }
    
    public void setPointOnBoard(Point p)
    {
        pointonboard=p;
    }
    public Point getPointOnBoard()
    {
        return pointonboard;
    }
    
    public void setActionPoints(int p)
    {
        actionpointsleft = p;
    }
    
    public boolean hasActionPoints()
    {
        return actionpointsleft!=0;
    }
    public void useActionPoint()
    {
        actionpointsleft-=1;
    }
}
