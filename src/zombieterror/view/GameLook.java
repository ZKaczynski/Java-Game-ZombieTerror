
package zombieterror.view;

import zombieterror.view.events.CustomEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * Frame where all is drawn.
 */
public final class GameLook extends JFrame  {
    private final int FRAME_HEIGHT = 875;
    private final int FRAME_WIDTH = 1400;
    
    private final BlockingQueue <CustomEvent>  queue;
        
    public Board mainBoard;
    
    
    public GameLook(BlockingQueue<CustomEvent> q) {
        queue=q;
        mainBoard = new Board(queue);
        
        Dimension expectedDimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        mainBoard.setPreferredSize(expectedDimension);
        mainBoard.setMaximumSize(expectedDimension);
        mainBoard.setMinimumSize(expectedDimension);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(mainBoard);     
        box.add(Box.createVerticalGlue());
        add(box);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("ZOMBIE TERROR");
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
 
        
    }

}
