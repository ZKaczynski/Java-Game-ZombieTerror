
package gamecontrolerdemo.model;


public class GirlModel extends PawnModel
{
    boolean hasSprinted;
    
    GirlModel()
    {
        super();
        hasSprinted=false;
    }
    
    public void activateSprint()
    {
        hasSprinted = true;
    }
    
    public void resetSprint()
    {
        hasSprinted=false;
    }
}
