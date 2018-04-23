
package gamecontrolerdemo.view.pawns;



import gamecontrolerdemo.view.events.ClickedZombiePawnEvent;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.ReleaseZombiePawnEvent;
import gamecontrolerdemo.view.events.RotateZombieEvent;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;
import javax.swing.ImageIcon;


public class Zombie extends Pawn implements MouseMotionListener, MouseListener, MouseWheelListener 
{

    private int zombieID;
    
    public Zombie (String s,BlockingQueue<CustomEvent> q, int id)
    {
        super(s, q);
        zombieID=id;
        addMouseWheelListener(this);
    }
    
    public int getID()
    {
       return zombieID;
    }


    
        @Override
    public void mouseReleased(MouseEvent e) 
    {
        try {
            
             queue.put(new ReleaseZombiePawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
             
          } catch (InterruptedException ex) { }  
        
        
        
    }
            @Override
    public void mousePressed(MouseEvent e) 
    {
        try {
            
             queue.put(new ClickedZombiePawnEvent(this, new Point (MouseInfo.getPointerInfo().getLocation())));
             
          } catch (InterruptedException ex) { }  
        
        
        
    }
    
    
    
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
         int notches = e.getWheelRotation();
         
             try {
            
        queue.put(new RotateZombieEvent(this, notches ));
             
          } catch (InterruptedException ex) { }  
        
        
        
   //  int notches = e.getWheelRotation();
     
     
     
     
     /*
     if (notches>0)
     {
      ImageIcon iconLogo = rotateImageIcon((ImageIcon) this.getIcon(), 90.0);
      this.setIcon(iconLogo);
    //  direction= (direction+1)%4;
     }
          else
     {
      ImageIcon iconLogo = rotateImageIcon((ImageIcon) this.getIcon(), -90.0);
      this.setIcon(iconLogo);
     // direction= (direction-1)%4;
     }
       */ 
    }
    
    public void rotatePawn(int notches)
    {
        if (notches>0)
     {
      ImageIcon iconLogo = rotateImageIcon((ImageIcon) this.getIcon(), 90.0);
      this.setIcon(iconLogo);
     }
          else
     {
      ImageIcon iconLogo = rotateImageIcon((ImageIcon) this.getIcon(), -90.0);
      this.setIcon(iconLogo);
     
     }
         
    }
    
    
   private ImageIcon rotateImageIcon(ImageIcon picture, double angle) {
        int w = picture.getIconWidth();
        int h = picture.getIconHeight();
        int type = BufferedImage.TYPE_INT_RGB;  
        BufferedImage image = new BufferedImage(h, w, type);
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(picture.getImage(), at, null);
        g2.dispose();
        picture = new ImageIcon(image);
 
        return picture;
    }
    
   
    
}
