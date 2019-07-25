/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombieterror.model.pawnmodels;


public abstract class HumanPawnModel extends PawnModel  {
    
    private boolean fullHp;
    
    HumanPawnModel(){
        fullHp = true;
    }
    
    public boolean getHp()
    {
        return fullHp;
    }
    
    public void setHp(boolean b)
    {
        fullHp = b;
    }
    
}
