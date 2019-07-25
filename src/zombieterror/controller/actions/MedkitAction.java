
package zombieterror.controller.actions;

import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.HumanPawnModel;
import zombieterror.view.GameLook;
import zombieterror.view.events.CustomEvent;

/** 
 * This action handles MedkitEvent. It check if focuse human can be healed.
 * If yes it give it hp and shows change in interface.
 * 
 */
public class MedkitAction extends Action {
         @Override
        public void performAction(CustomEvent event, GameLogic gl, GameLook gk) 
    {
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        switch (gameLogic.getState())
        {
            case HumanTurn:
               
                if (gameLogic.nurseModel.hasActionPoints()&&gameLogic.isValidForMedkit()){
                    HumanPawnModel fm = (HumanPawnModel) gameLogic.focusPawn;
                    switch (fm.getClass().getSimpleName()){
                        case "GirlModel":
                            if (!gameLogic.girlModel.getHp()){
                                gameLogic.girlModel.setHp(true);
                                int points = gameLogic.girlModel.getActionPoints();
                                boolean isFull = gameLogic.girlModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Girl", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;
                        case "NurseModel":
                            if (!gameLogic.nurseModel.getHp()){
                                gameLogic.nurseModel.setHp(true);
                                int points = gameLogic.nurseModel.getActionPoints();
                                boolean isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;  
                        case "MechanicModel":
                            if (!gameLogic.mechanicModel.getHp()){
                                gameLogic.mechanicModel.setHp(true);
                                int points = gameLogic.mechanicModel.getActionPoints();
                                boolean isFull = gameLogic.mechanicModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Mechanic", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;                    
                        case "LumberjackModel":
                            if (!gameLogic.lumberjackModel.getHp()){
                                gameLogic.lumberjackModel.setHp(true);
                                int points = gameLogic.lumberjackModel.getActionPoints();
                                boolean isFull = gameLogic.lumberjackModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Lumberjack", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;
                        case "PolicemanModel":
                            if (!gameLogic.policemenModel.getHp()){
                                gameLogic.policemenModel.setHp(true);
                                int points = gameLogic.policemenModel.getActionPoints();
                                boolean isFull = gameLogic.policemenModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Policemen", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;    
                        case "BankerModel":
                            if (!gameLogic.bankerModel.getHp()){
                                gameLogic.bankerModel.setHp(true);
                                int points = gameLogic.bankerModel.getActionPoints();
                                boolean isFull = gameLogic.bankerModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Banker", isFull);
                                
                                gameLogic.nurseModel.useActionPoint();
                                points = gameLogic.nurseModel.getActionPoints();
                                isFull = gameLogic.nurseModel.getHp();
                                gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull);
                            }
                            break;                             
                            
                    }
                }
                
                break;
        }        
    }
}        
