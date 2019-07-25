package zombieterror.model.pawnmodels;


public class PolicemanModel extends HumanPawnModel
{
    int numberOfBullts;
    
    public PolicemanModel()
    {
        super();
        numberOfBullts=4;
    }
    
    public void activateBullet()
    {
        numberOfBullts-=1;
    }
    
    public boolean hasBullets()
    {
        return numberOfBullts!=0;
    }
    public int getBullets()
    {
        return numberOfBullts;
    }

}