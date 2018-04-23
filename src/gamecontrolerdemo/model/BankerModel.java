package gamecontrolerdemo.model;


public class BankerModel extends PawnModel
{
    boolean hasSuitcase;
    
    BankerModel()
    {
        super();
        hasSuitcase=true;
    }
    
    public void activateSuitcase()
    {
        hasSuitcase = false;
    }
    

}
