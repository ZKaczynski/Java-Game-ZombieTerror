package gamecontrolerdemo.model;


public class PolicemenModel extends PawnModel
{
    int numberOfBullts;
    
    PolicemenModel()
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
    

}