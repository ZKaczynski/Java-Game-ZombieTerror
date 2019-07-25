
package zombieterror.view.pawnlabels;

import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.ReleaseBankerPawnEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;
import java.awt.MouseInfo;
import javax.swing.JOptionPane;

/**
 *
 * Extends Pawn. Creates ReleaseBankerPawnEvent so controler can calculate new
 * position for label
 * 
 */
public final class Banker extends Pawn implements MouseMotionListener, MouseListener 
{
    
    public Banker(String text, BlockingQueue<CustomEvent> q) 
    {
        super(text, q);
    }
    
    public void mouseReleased(MouseEvent e) {
        try {
            queue.put(new ReleaseBankerPawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
            } catch (InterruptedException ex) {   
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}     
    }
    
}
