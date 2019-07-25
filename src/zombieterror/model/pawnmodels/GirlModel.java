
package zombieterror.model.pawnmodels;


public class GirlModel extends HumanPawnModel
{
    private boolean hasSprinted;
    
    public GirlModel()
    {
        super();
        hasSprinted=false;
    }
    
    public boolean getSprinted()
    {
        return hasSprinted;
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
