
package zombieterror.controller.actions;

import java.awt.Point;
import javax.swing.ImageIcon;
import zombieterror.ZombieTerror;
import zombieterror.model.BoardModel;
import zombieterror.view.GameLook;
import zombieterror.model.GameLogic;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.view.events.CustomEvent;

/**
 *
 * This action handles EndTurnEvent. Every time action is performed its checks 
 * current phase of game and if turn can be ended in current state of game.
 */
public class EndTurnAction extends Action{
    public void performAction(CustomEvent event, GameLogic gl, GameLook gk){
        GameLogic gameLogic = gl;
        GameLook gameLook = gk;
        switch (gameLogic.getState()){
            case HumanStart: 
                if (gameLogic.allHumansOnBoard())
                {
                    gameLogic.nextState();
                    gameLogic.setUpZombies();
                    gameLook.mainBoard.spwanZombies();
                    gameLook.mainBoard.repaint();
                    
                }
                
                break;
            
            case ZombieStart:
                if (gameLogic.allStartingZombiesOnBoard())
                {
                    gameLook.mainBoard.spawnZombieInterface();
                    gameLook.mainBoard.spawnHumanInterface();
                    gameLogic.nextState();
                    
                }
                break;
             
            case HumanTurn:
                if (gameLogic.atLeastOneMoved()){
                    /**
                     * at the end of human turn all humans on exit tiles are 
                     * evacuted and gamescore is calculetd.
                     */
                    for (int i=0; i<ZombieTerror.BOARD_WIDTH;i++){
                        for (int j=0; j<ZombieTerror.BOARD_HEIGHT; j++){
                            if (gameLogic.boardModel.getSquere(new Point(i,j)).getSquereState()==BoardModel.squereState.Exit){
                                switch (gameLogic.boardModel.getSquere(new Point (i,j)).getSquereOccupation()){
                                    case Banker:
                                        gameLogic.bankerModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.bankerModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.bankerModel.setActionPoints(3);
                                        gameLook.mainBoard.bankerPawn.setVisible(false);
                                        gameLook.mainBoard.bankerInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.bankerIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.bankerModel.getHp()){
                                        gameLogic.increaseScore(2);
                                        }
                                        else{
                                        gameLogic.increaseScore(1);    
                                        }
                                        break;
                                    case Girl:
                                        gameLogic.girlModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.girlModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.girlModel.setActionPoints(3);
                                        gameLook.mainBoard.girlPawn.setVisible(false);
                                        gameLook.mainBoard.girlInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.girlIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.girlModel.getHp()){
                                        gameLogic.increaseScore(3);
                                        }
                                        else{
                                        gameLogic.increaseScore(2);    
                                        }
                                        break;
                                    case Lumberjack:
                                        gameLogic.lumberjackModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.lumberjackModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.lumberjackModel.setActionPoints(3);
                                        gameLook.mainBoard.lumberjackPawn.setVisible(false);
                                        gameLook.mainBoard.lumberjackInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.lumberjackIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.lumberjackModel.getHp()){
                                        gameLogic.increaseScore(1);
                                        }
                                        else{
                                        gameLogic.increaseScore(0);    
                                        }                                        
                                        break; 
                                    case Nurse:
                                        gameLogic.nurseModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.nurseModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.nurseModel.setActionPoints(3);
                                        gameLook.mainBoard.nursePawn.setVisible(false);
                                        gameLook.mainBoard.nurseInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.nurseIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.nurseModel.getHp()){
                                        gameLogic.increaseScore(2);
                                        }
                                        else{
                                        gameLogic.increaseScore(1);    
                                        }                                        
                                        break;      
                                    case Mechanic:
                                        gameLogic.mechanicModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.mechanicModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.mechanicModel.setActionPoints(3);
                                        gameLook.mainBoard.mechanicPawn.setVisible(false);
                                        gameLook.mainBoard.mechanicInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.mechanicIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.mechanicModel.getHp()){
                                        gameLogic.increaseScore(3);
                                        }
                                        else{
                                        gameLogic.increaseScore(2);    
                                        }                                        
                                        break;    
                                    case Policemen:
                                        gameLogic.policemenModel.setOnBoard(false);
                                        gameLogic.boardModel.updateTile(new Point (i,j), BoardModel.squereOccupation.Empty);
                                        gameLogic.policemenModel.setPointOnBoard(new Point(-1,-1));
                                        gameLogic.policemenModel.setActionPoints(3);
                                        gameLook.mainBoard.policemanPawn.setVisible(false);
                                        gameLook.mainBoard.policemenInterface.setIcon(new ImageIcon
                                        (getClass().getClassLoader().getResource("Images//Interface//Actionpointssafe.png")));
                                        gameLook.mainBoard.policemenIcon.setEnabled(false);
                                        gameLook.mainBoard.repaint();
                                        if (gameLogic.policemenModel.getHp()){
                                        gameLogic.increaseScore(1);
                                        }
                                        else{
                                        gameLogic.increaseScore(0);    
                                        }                                        
                                        break;                                          
                                }
                                if (gameLogic.getHumanScore()>4){
                                    gameLogic.setEnd();
                                    gameLook.mainBoard.humansWon();
                                }
                                        
                                        
                            }
                                
                        }
                    }
                    
                    
                    
                    
                    
                    
                    
                    gameLogic.nextState();
                    gameLogic.zombiesToMove+=gameLogic.numberOfMovedHumans();
                    gameLogic.zombiesToMove+=1;
                    gameLogic.rageZombie();
                    if (gameLogic.isfirstturn){
                    gameLook.mainBoard.setFirstZombieInfo(gameLogic.zombiesToMove);
                    gameLogic.isfirstturn=false;
                    }
                    else{
                        gameLook.mainBoard.setZombieInfo(gameLogic.zombiesToMove);
                    }
                    
                    for (int i=0; i<ZombieTerror.MAX_ZOMBIES;i++){
                        ZombieModel zm;
                        zm = (ZombieModel) gameLogic.zombies.get(i);
                        zm.setActionPoints(1);
                        zm.setBitten(false);
                        
                    }
                    
                }
                
                
                break;
            
            case ZombieTurn:
                
                if (gameLogic.zombiesToMove==0){
                    
                    if (gameLogic.getZombieScore()>7){
                        gameLogic.setEnd();
                        gameLook.mainBoard.zombiesWon();
                        return;
                    }
                    
                    
                    gameLogic.zombiesToSpawn+=gameLogic.boardModel.calculateZombiesToSpawn();
                    
                    for (int i=0; i<ZombieTerror.MAX_ZOMBIES;i++){
                    
                    if (gameLogic.zombiesToSpawn==0) break;
                    
                    ZombieModel zm = (ZombieModel) gameLogic.zombies.get(i);
                    if (zm.getAlive()==false){
                        zm.zombiedirection=ZombieModel.zdirection.N;
                        zm.zombieState=ZombieModel.zState.normal;
                        zm.setAlive(true);
                        zm.setActionPoints(1);
                        zm.setBitten(false);
                        zm.setOnBoard(false);
                        gameLook.mainBoard.spawnZombie(zm.getId());
                        gameLogic.zombiesToSpawn--;
                    }  
                    }    
                    
                gameLogic.nextState();
                }
             break;
            
            case ZombiePlacement:
                if (gameLogic.allZombiesOnBoard()){
                    /**
                     * resets human abilities and acrion points
                     */
                    
                    if (gameLogic.bankerModel.isOnBoard()){
                        gameLogic.bankerModel.setActionPoints(3);
                        boolean isFull = gameLogic.bankerModel.getHp();
                        gameLook.mainBoard.reduceActionPoints(3, "Banker", isFull); 
                    }
                    if (gameLogic.nurseModel.isOnBoard()){
                        gameLogic.nurseModel.setActionPoints(3);
                        boolean isFull = gameLogic.nurseModel.getHp();
                        gameLook.mainBoard.reduceActionPoints(3, "Nurse", isFull); 
                    } 
                    if (gameLogic.girlModel.isOnBoard()){
                        gameLogic.girlModel.setActionPoints(3);
                        boolean isFull = gameLogic.girlModel.getHp();
                        gameLook.mainBoard.reduceActionPoints(3, "Girl", isFull); 
                        gameLogic.girlModel.resetSprint();
                        gameLook.mainBoard.girlIcon.setEnabled(true);
                    }
                    if (gameLogic.policemenModel.isOnBoard()){
                        gameLogic.policemenModel.setActionPoints(3);
                        boolean isFull = gameLogic.policemenModel.getHp();
                        gameLook.mainBoard.reduceActionPoints(3, "Policemen", isFull); 
                    }
                    if (gameLogic.lumberjackModel.isOnBoard()){
                        gameLogic.lumberjackModel.setActionPoints(3);
                        boolean isFull = gameLogic.lumberjackModel.getHp();
                        gameLook.mainBoard.reduceActionPoints(3, "Lumberjack", isFull); 
                    } 
                    if (gameLogic.mechanicModel.isOnBoard()){
                    gameLogic.mechanicModel.setActionPoints(3);
                    boolean isFull = gameLogic.mechanicModel.getHp();
                    gameLook.mainBoard.reduceActionPoints(3, "Mechanic", isFull); 
                    gameLogic.mechanicModel.resetPush();
                    gameLook.mainBoard.mechanicIcon.setEnabled(true);
                    }
                    
                    if (gameLogic.hasEndBegun()){
                        gameLogic.decrementEndGameTimer();
                        gameLook.mainBoard.continueZombieTerror(gameLogic.getEndGameTimer());
                        int ccc = gameLogic.getEndGameTimer();
                        if (ccc==0){
                           gameLook.mainBoard.zombiesWon();
                            gameLogic.setEnd();
                           
                            return;
                        }
                        
                    }
                    else {
                    int countzombies=0;
                    for (int i=0;i<ZombieTerror.MAX_ZOMBIES; i++){
                        ZombieModel zm = (ZombieModel) gameLogic.zombies.get(i);
                        if (zm.isOnBoard()) countzombies++; 
                    }
                    if (countzombies==ZombieTerror.MAX_ZOMBIES){
                        gameLook.mainBoard.beginZombieTerror();

                        gameLogic.startEnd();
                        
                    }
                    }
                gameLogic.nextState();
                }

                 break;
            
        }
         
    }
    
}

