
package zombieterror.model.pawnmodels;

import java.awt.Point;


public abstract class PawnModel 
{
    private boolean onboard;
    private Point pointonboard;
    protected int actionpointsleft;
    
    public PawnModel()
    {
        onboard = false;
        actionpointsleft = 3;
        
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
    public int getActionPoints()
    {
        return actionpointsleft;
    }
    
}
