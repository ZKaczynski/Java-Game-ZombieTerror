package zombieterror.model.pawnmodels;


public class MechanicModel extends HumanPawnModel
{
    private boolean hasLifted;
    
    public MechanicModel()
    {
        super();
        hasLifted=false;
    }
    
    public boolean getLifted()
    {
        return hasLifted;
    }
    
    public void activateLift()
    {
        hasLifted = true;
    }
    
        public void resetPush()
    {
        hasLifted=false;
    }
    

}