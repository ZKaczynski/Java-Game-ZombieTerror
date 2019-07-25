
package zombieterror.view.pawnlabels;

import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.ReleasePolicemenPawnEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.JOptionPane;

/**
 *
 * Extends Pawn. Creates ReleasePolcemenPawnEvent so controler can calculate new
 * position for label
 * 
 */
public final class Policeman extends Pawn implements MouseMotionListener, MouseListener
{
    
    public Policeman(String text, BlockingQueue<CustomEvent> q) {
        super(text, q);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            queue.put(new ReleasePolicemenPawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
            } catch (InterruptedException ex) {   
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}    
    }
    
}
