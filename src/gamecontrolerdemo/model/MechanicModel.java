package gamecontrolerdemo.model;


public class MechanicModel extends PawnModel
{
    boolean hasLifted;
    
    MechanicModel()
    {
        super();
        hasLifted=false;
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