package zombieterror.model.pawnmodels;


public class BankerModel extends HumanPawnModel
{
    private boolean hasSuitcase;
    
    public BankerModel()
    {
        super();
        hasSuitcase=true;
    }
    
    public void activateSuitcase()
    {
        hasSuitcase = false;
    }
    
    public boolean getSuitcase(){
        return hasSuitcase;
    }
    

}
