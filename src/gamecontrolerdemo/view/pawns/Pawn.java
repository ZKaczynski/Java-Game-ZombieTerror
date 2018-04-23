
package gamecontrolerdemo.view.pawns;


import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.MovePawnEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;


public class Pawn extends JLabel implements MouseMotionListener, MouseListener
{
    protected Point fixed;
    protected Point local;
   
    BlockingQueue<CustomEvent> queue;
    
    Pawn(String text, BlockingQueue<CustomEvent> q)
    {
        super(text);
        this.queue=q;
        addMouseMotionListener(this);
        addMouseListener(this);
        
        Point local = new Point();
        local=this.getLocation();
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   // settlePawn();  
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
            
    
    /*
    public void settlePawn()
    {
    local=this.getLocation();
    if (local.getX()>140 && local.getX()<1200 && local.getY()<800 && local.getY()>0)
    {
        int x,y;
        x=((int)local.getX()+35)- (((int)local.getX()+35)%70);
        y= ((int)local.getY()+35)-(((int)local.getY()+35)%70);
        this.setLocation(x, y);
        fixedX=x;
        fixedY=y;
     
    }
    else 
    {
         this.setLocation(fixedX, fixedY);
    }
*/
    
  //  }
    
        public void setPawn (Point point)
    {
        this.setLocation((int)point.getX()-285,(int)point.getY()-140);
    }
    
    
}
