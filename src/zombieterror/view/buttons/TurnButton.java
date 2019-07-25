
package zombieterror.view.buttons;

import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.EndTurnEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public final class TurnButton extends JButton implements ActionListener
{
    BlockingQueue<CustomEvent> queue;

    
    public  TurnButton(String s, BlockingQueue<CustomEvent> q)
    {
        super(s);
        queue=q;
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try 
        {                             
          queue.put(new EndTurnEvent());    
        } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }  
    }
    
}
