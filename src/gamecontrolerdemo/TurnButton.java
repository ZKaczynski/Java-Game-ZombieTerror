
package gamecontrolerdemo;

import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.EndTurnEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.JButton;


public class TurnButton extends JButton implements ActionListener
{
    BlockingQueue<CustomEvent> queue;

    
    TurnButton(String s, BlockingQueue<CustomEvent> q)
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
          queue.put(new EndTurnEvent(null));    
        } catch (InterruptedException ex) { }  
    }
    
}
