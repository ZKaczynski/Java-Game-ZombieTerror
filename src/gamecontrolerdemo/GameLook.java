
package gamecontrolerdemo;

import gamecontrolerdemo.view.events.CustomEvent;
import java.awt.Point;
import java.util.concurrent.BlockingQueue;
import javax.swing.JFrame;


public class GameLook extends JFrame   
{
    private final int FRAME_HEIGHT = 875;
    private final int FRAME_WIDTH = 1400;
    
    private BlockingQueue <CustomEvent>  queue;
        
    public Board mainBoard;
    
    GameLook(BlockingQueue<CustomEvent> q) 
    {
       this.queue=q;
     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("ZOMBIE TERROR");
        add(mainBoard = new Board(queue));
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        
      
    }
    
    
    


    

}
