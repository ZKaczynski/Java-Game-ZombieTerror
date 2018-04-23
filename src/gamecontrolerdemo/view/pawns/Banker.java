
package gamecontrolerdemo.view.pawns;

import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.ReleaseBankerPawnEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;
import java.awt.MouseInfo;

public class Banker extends Pawn implements MouseMotionListener, MouseListener 
{
    
    public Banker(String text, BlockingQueue<CustomEvent> q) 
    {
        super(text, q);
    }
    
            public void mouseReleased(MouseEvent e) {
            
            
                 try {
            
             queue.put(new ReleaseBankerPawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
             
          } catch (InterruptedException ex) { }   
    
    }
    
}
