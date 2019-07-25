
package zombieterror;

import zombieterror.view.GameLook;
import zombieterror.controller.GameController;
import zombieterror.model.GameLogic;
import zombieterror.view.events.CustomEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Zbyszek, vel Dremoro on Discord, vel esmfan on Steam
 * Main class of ZombieTerror game. It contains constants, game controler, view 
 * and gamelogic. It creates blocking queue of Events and passes it to controller 
 * and look.
 * Starts gamecontroler thread. Its duty is to consume events from blocking queue
 * and maps them to actions.
 */
public final class ZombieTerror {
    
    public final static int BOARD_HEIGHT = 12;
    public final static int BOARD_WIDTH = 16;
    public final static int MAX_ZOMBIES = 20;
    public final static int SQUARE_SIZE = 70;
    public static int[][] BOARD_GENESIS = { {2,2,2,2,2,2,2,2,2,2,2,2}, // [16] [12]   | Left
                                            {2,0,1,1,0,0,0,0,0,0,0,2}, //             | to
                                            {2,0,0,0,0,0,0,0,0,0,0,2}, //             V Right
                                            {2,0,0,0,0,0,0,0,0,0,0,2}, //             V
                                            {2,0,0,0,2,0,1,0,0,0,0,2},
                                            {2,2,1,1,1,0,0,0,0,0,0,2},
                                            {2,2,1,1,1,0,0,2,1,1,0,2},
                                            {2,0,0,0,0,0,1,0,1,1,0,2},
                                            {2,0,1,0,0,0,1,0,1,1,0,2},
                                            {2,0,0,2,1,0,0,0,2,2,0,2},
                                            {2,0,0,0,0,0,0,0,0,0,0,2},
                                            {2,0,0,0,0,0,1,0,0,0,0,2},
                                            {2,0,0,0,0,0,0,0,0,1,0,2},
                                            {2,0,0,0,1,0,0,0,0,0,0,2},
                                            {2,0,0,0,0,0,0,0,0,0,0,2},
                                            {2,2,2,2,2,3,3,3,2,2,2,2}
                                            // Top --->> to bootom               
};
    
    //0-NORMAL, 1-OBSTACLE, 2-SPAWN, 3-EXIT, 4-CANAL

    public static void main(String[] args){
        BlockingQueue <CustomEvent> queue= new ArrayBlockingQueue <>(100);
        GameLook glook = new GameLook(queue);
        GameLogic glog =  new GameLogic(queue);
        GameController gameController = new GameController(queue, glook, glog);
        new Thread(gameController).start(); 
    }                   
}
