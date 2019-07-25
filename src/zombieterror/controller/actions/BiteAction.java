
package zombieterror.controller.actions;

import java.awt.Point;
import javax.swing.ImageIcon;
import zombieterror.model.BoardModel;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.HumanPawnModel;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.GameLook;
import zombieterror.view.events.CustomEvent;

/**
 *
 * This Action handles acticvation of Zombie bite ability. 
 * Bite can be perdormed only in during the Zombie Turn. Zombie that bites has to 
 * have an action point and havent bitten yet this turn. It have to be adject to
 * human pawn and be adject to it. If human can be bitten its calculteted if its 
 * wounded or killed.
 */
public class BiteAction extends Action {

    @Override
    public void performAction(CustomEvent event, GameLogic gl, GameLook gk){

        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        switch (gameLogic.getState()){
            case ZombieTurn:
                if (gameLogic.isValidForBite()){
                    ZombieModel zm = (ZombieModel) gameLogic.focusPawn;
                    if (zm.getBitten()==true) break;
                    else{
                    Point zp = zm.getPointOnBoard();
                    HumanPawnModel hm;
                    switch (zm.zombiedirection){
                case N: hm = gameLogic.findHuman(new Point (zp.x,zp.y-1)); break;
                case W: hm = gameLogic.findHuman(new Point (zp.x-1,zp.y)); break;
                case S: hm = gameLogic.findHuman(new Point (zp.x,zp.y+1)); break;
                case E: hm = gameLogic.findHuman(new Point (zp.x+1,zp.y)); break;
                default: hm=null;
            }
                    switch(hm.getClass().getSimpleName()){
                        case "GirlModel":
                        if (gameLogic.girlModel.getHp()){
                            gameLogic.girlModel.setHp(false);
                            int points = gameLogic.girlModel.getActionPoints();
                            boolean isFull = gameLogic.girlModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Girl", isFull); 
                        }    
                        else{
                            gameLogic.girlModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.girlModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.girlModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.girlPawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.girlInterface.setIcon(icon);
                            gameLook.mainBoard.girlIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(3);
                        }
                        break;
                        case "NurseModel":
                        if (gameLogic.nurseModel.getHp()){
                            gameLogic.nurseModel.setHp(false);
                            int points = gameLogic.nurseModel.getActionPoints();
                            boolean isFull = gameLogic.nurseModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Nurse", isFull); 
                        }    
                        else{
                            gameLogic.nurseModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.nurseModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.nurseModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.nursePawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.nurseInterface.setIcon(icon);
                            gameLook.mainBoard.nurseIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(2);

                        }
                        break;
                        case "MechanicModel":
                        if (gameLogic.mechanicModel.getHp()){
                            gameLogic.mechanicModel.setHp(false);
                            int points = gameLogic.mechanicModel.getActionPoints();
                            boolean isFull = gameLogic.mechanicModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Mechanic", isFull); 
                        }    
                        else{
                            gameLogic.mechanicModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.mechanicModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.mechanicModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.mechanicPawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.mechanicInterface.setIcon(icon);    
                            gameLook.mainBoard.mechanicIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(3);
                        }
                        break;
                        case "PolicemanModel":
                        if (gameLogic.policemenModel.getHp()){
                            gameLogic.policemenModel.setHp(false);
                            int points = gameLogic.policemenModel.getActionPoints();
                            boolean isFull = gameLogic.policemenModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Policemen", isFull); 
                        }    
                        else{
                            gameLogic.policemenModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.policemenModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.policemenModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.policemanPawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.policemenInterface.setIcon(icon);    
                            gameLook.mainBoard.policemenIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(1);
                        }
                        break;
                        case "LumberjackModel":
                        if (gameLogic.lumberjackModel.getHp()){
                            gameLogic.lumberjackModel.setHp(false);
                            int points = gameLogic.lumberjackModel.getActionPoints();
                            boolean isFull = gameLogic.lumberjackModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Lumberjack", isFull); 
                        }    
                        else{
                            gameLogic.lumberjackModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.lumberjackModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.lumberjackModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.lumberjackPawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.lumberjackInterface.setIcon(icon);    
                            gameLook.mainBoard.lumberjackIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(1);
                        }
                        break;
                        case "BankerModel":
                        if (gameLogic.bankerModel.getSuitcase()){
                            gameLogic.bankerModel.activateSuitcase();
                            gameLook.mainBoard.bankerIcon.setEnabled(false);
                        }    
                            
                        else if (gameLogic.bankerModel.getHp()){
                            gameLogic.bankerModel.setHp(false);
                            int points = gameLogic.bankerModel.getActionPoints();
                            boolean isFull = gameLogic.bankerModel.getHp();
                            gameLook.mainBoard.reduceActionPoints(points, "Banker", isFull); 
                        }    
                        else{
                            gameLogic.bankerModel.setOnBoard(false);
                            gameLogic.boardModel.updateTile(gameLogic.bankerModel.getPointOnBoard(), BoardModel.squereOccupation.Empty);
                            gameLogic.bankerModel.setPointOnBoard(new Point (-1,-1));
                            gameLook.mainBoard.bankerPawn.setVisible(false);
                            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ActionPointsdead.png"));
                            gameLook.mainBoard.bankerInterface.setIcon(icon);    
                            gameLook.mainBoard.bankerIcon.setEnabled(false);
                            gameLogic.zombiesToSpawn++;
                            gameLogic.increaseZombieScore(2);
                        }                        
                        
                            
                    }
                    
                    if (zm.getActionPoints()==1){
                        gameLogic.zombiesToMove--;
                        gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                    }
                    zm.setBitten(true);
                    zm.zombieState=ZombieModel.zState.normal;
                    
                    
                    }
                }
                break;
        }
    }
}