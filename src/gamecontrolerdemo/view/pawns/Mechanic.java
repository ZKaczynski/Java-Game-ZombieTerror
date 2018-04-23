
package gamecontrolerdemo.view.pawns;

import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.ReleaseMechanicPawnEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;


public class Mechanic extends Pawn implements MouseMotionListener, MouseListener
{
    
    public Mechanic(String text, BlockingQueue<CustomEvent> q) {
        super(text, q);
    }
 public void mouseReleased(MouseEvent e) {
            
            
                 try {
            
             queue.put(new ReleaseMechanicPawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
             
          } catch (InterruptedException ex) { }   
    
    
}
}