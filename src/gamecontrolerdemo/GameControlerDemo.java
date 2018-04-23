
package gamecontrolerdemo;

import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.view.events.CustomEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class GameControlerDemo {

    public static void main(String[] args)
    {
    BlockingQueue <CustomEvent> queue= new ArrayBlockingQueue <>(100);
    GameLook glook = new GameLook(queue);
    GameLogic glog =  new GameLogic(queue);
    GameController gameController = new GameController(queue, glook, glog);
    new Thread(gameController).start(); 
    }        
            
}
