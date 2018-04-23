
package gamecontrolerdemo;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Zbyszek
 */
public class ExitButton extends JButton implements ActionListener
{
    public ExitButton(String s)
    {
        super(s);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     System.exit(0);
    }


}
