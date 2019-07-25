
package zombieterror.model.pawnmodels;


public class ZombieModel extends PawnModel
{
    public enum zState
    {
        normal, rage
    }
    
    public enum zdirection
    {
        N,E,W,S;
        
         private zdirection next;
         private zdirection previous;
          static {
                    N.next=E;
                    E.next = S;
                    S.next = W;
                    W.next = N;
                   
                    N.previous=W;
                    W.previous=S;
                    S.previous=E;
                    E.previous=N;      
                 }
        
            public zdirection nextZDirection()
            {
                return next;
            }
            public zdirection previousZDirection()
            {
                return previous;
            }
            
        
    }
    
    private boolean isMoving;
    public zState zombieState;
    boolean hasTurned;
    public zdirection zombiedirection;
    private final int zombieModelId;
    private boolean isAlive;
    private boolean hadbitten;
    public ZombieModel(int id)
    {
        super();
        zombieModelId=id;
        hasTurned=false;
        isMoving = false;
        zombieState = zState.normal;
        zombiedirection = zdirection.N;
        isAlive=false;
        actionpointsleft=1;
        hadbitten=false;
    }
        public boolean getBitten()
    {
        return hadbitten;
    }
    public void setBitten(boolean b)
    {
        hadbitten = b;
    }
    
    public boolean getAlive()
    {
        return isAlive;
    }
    public void setAlive(boolean b)
    {
        isAlive = b;
    }
    
    public int getId()
    {
        return zombieModelId;
    }
    
    public void activateTurn()
    {
        hasTurned = true;
    }
    
    public void resetTurn()
    {
        hasTurned=false;
    }
    
    public boolean getMoving()
    {
        return isMoving;
    }
    public void setMoving (boolean b)
    {
        isMoving = b;
    }
    
}
