
package gamecontrolerdemo.view.pawns;

import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.ReleaseLumberjackPawnEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;


public class Lumberjack extends Pawn implements MouseMotionListener, MouseListener 
{
    
    public Lumberjack(String text, BlockingQueue<CustomEvent> q) {
        super(text, q);
    }
    
     public void mouseReleased(MouseEvent e) {
            
            
                 try {
            
             queue.put(new ReleaseLumberjackPawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
             
          } catch (InterruptedException ex) { }   
    
    
}
}