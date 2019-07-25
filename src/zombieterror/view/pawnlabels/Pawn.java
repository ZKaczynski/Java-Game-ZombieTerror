
package zombieterror.view.pawnlabels;


import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.FocusEvent;
import zombieterror.view.events.MovePawnEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;

/**
 *
 * Abstract class for all pawns to inherit from it. Pawns are jlable object visible 
 * on screen. Fixed point is used to save last valid stationary positinon on screen.
 * User can move pawn as long left mouse button is clicked - label is draw anywhere on screen.
 * When user stops draging label, game controler checks if pawn can be drawn near current
 * position. If not Point fixed is used to draw on previous position.
 * Clicking on label creates FocusEvent to show user which pawn is currently active
 */
public abstract class Pawn extends JLabel implements MouseMotionListener, MouseListener
{
    protected Point fixed;
   
    BlockingQueue<CustomEvent> queue;
    
    Pawn(String text, BlockingQueue<CustomEvent> q)
    {
        super(text);
        this.queue=q;
        addMouseMotionListener(this);
        addMouseListener(this);
        
        Point local;
        local=getLocation();
        fixed = new Point((int)local.getX(),(int)local.getY() );

    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            queue.put(new MovePawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
            } catch (InterruptedException ex) { }   
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
             try {
             
             queue.put(new FocusEvent(this));
             
          } catch (InterruptedException ex) { }   
 
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) { 
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    public void  setFixedPosition(Point p)
    {
        fixed.x=p.x;
        fixed.y=p.y;
    }
    public Point getFixedPosition()
    {
        return new Point(fixed);
    }

    
}
