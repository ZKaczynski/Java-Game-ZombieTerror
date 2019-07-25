
package zombieterror.controller.actions;

import zombieterror.view.GameLook;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.PawnModel;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.FocusEvent;
import zombieterror.view.pawnlabels.Zombie;
import javax.swing.ImageIcon;

/**
 * 
 * Handles FocusAction created by clicking on pawn. Unfocuses previous focused
 * pawn and focuses new
 */
public class FocusAction extends Action
{

    @Override
    public void performAction(CustomEvent ev, GameLogic gl, GameLook gk) {
        
    GameLogic gameLogic = gl;    
    GameLook gameLook = gk;    
    FocusEvent event = (FocusEvent) ev;
    
    if (gameLogic.focusPawn==null) ;
    
    else if (gameLogic.focusPawn.getClass().getSimpleName().equals("ZombieModel"))
    {
        ZombieModel zm = (ZombieModel) gameLogic.focusPawn;
        
              switch (zm.zombiedirection)
                {
                   
                    case N: gameLook.mainBoard.unFocusPawn(0);  break; 
                    case E: gameLook.mainBoard.unFocusPawn(1);  break; 
                    case S: gameLook.mainBoard.unFocusPawn(2); break;
                    case W: gameLook.mainBoard.unFocusPawn(3); break;
                    
                }
     
    }
    else
    {
         gameLook.mainBoard.unFocusPawn(0);
    }
           
    
    if ("Zombie".equals(event.getSource().getClass().getSimpleName()))
            {
                Zombie zombie =(Zombie) event.getSource();
                ZombieModel zm =(ZombieModel) gameLogic.zombies.get(zombie.getID());
                event.getSource().setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/FocusZombie.png")));

                
                switch (zm.zombiedirection)
                {
                   
                    case N:   break; 
                    case E: zombie.rotatePawn(1);  break; 
                    case S: zombie.rotatePawn(1); zombie.rotatePawn(1); break;
                    case W: zombie.rotatePawn(-1); break;
                    
                }
                gameLook.mainBoard.focusPawn = event.getSource();
                gameLogic.focusPawn =  (PawnModel) gameLogic.zombies.get(zombie.getID());
               
            }
    else {

                

        event.getSource().setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Focus"+event.getSource().getClass().getSimpleName()+".png")));
        gameLook.mainBoard.focusPawn = event.getSource();
    
        switch(event.getSource().getClass().getSimpleName())
        {
            case "Girl":   gameLogic.focusPawn =  gameLogic.girlModel;  break; 
            case "Banker":   gameLogic.focusPawn =  gameLogic.bankerModel; break; 
            case "Lumberjack":   gameLogic.focusPawn =  gameLogic.lumberjackModel; break; 
            case "Nurse":   gameLogic.focusPawn =  gameLogic.nurseModel; break; 
            case "Mechanic":   gameLogic.focusPawn =  gameLogic.mechanicModel; break; 
            case "Policeman":   gameLogic.focusPawn =  gameLogic.policemenModel; break; 
        }
    
    }
   
    }
    
}
