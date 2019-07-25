
package zombieterror.view.buttons;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
/**
 *
 * A Button creating on click an event that closes the program
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
