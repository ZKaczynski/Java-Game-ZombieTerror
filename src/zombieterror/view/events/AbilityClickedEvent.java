
package zombieterror.view.events;

/**
 *
 * Created by Zombie power buttons on click. Passes name of ability to controler.
 */
public class AbilityClickedEvent extends CustomEvent {
    
   private final String sourcename;
    
    public AbilityClickedEvent( String s) {
       
        sourcename = s;
        
    }
    public String getSource()
    {
        return sourcename;
    }
    
}
