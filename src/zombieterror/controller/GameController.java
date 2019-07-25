
package zombieterror.controller;

import zombieterror.controller.actions.AbilityClickedAction;
import zombieterror.model.GameLogic;
import zombieterror.controller.actions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import zombieterror.view.GameLook;
import zombieterror.view.events.CustomEvent;

/**
 * Binds GameLogic and GameLook. Events from Look are mapped by creataeMaping 
 * method to Aktions. 
 * 
 */
public class GameController implements Runnable{
    private final BlockingQueue<CustomEvent> events;
    private final Map<String, Action> mapping;
    private final GameLogic gameLogic;
    private final GameLook gameLook;
    
    public GameController(BlockingQueue<CustomEvent> events, GameLook gameLook, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gameLook = gameLook;
        this.events = events;       
        mapping = new LinkedHashMap<>();
        createMapping();
    }
    
    private  void createMapping() {
      mapping.put("MovePawnEvent", new MovePawnAction());  
      mapping.put("ReleaseGirlPawnEvent", new ReleaseGirlPawnAction() );
      mapping.put("ReleaseBankerPawnEvent", new ReleaseBankerPawnAction() );
      mapping.put("ReleaseNursePawnEvent", new ReleaseNursePawnAction() );
      mapping.put("ReleasePolicemenPawnEvent", new ReleasePolicemenPawnAction() );
      mapping.put("ReleaseMechanicPawnEvent", new ReleaseMechanicPawnAction() );
      mapping.put("ReleaseLumberjackPawnEvent", new ReleaseLumberjackPawnAction() );
      mapping.put("EndTurnEvent", new EndTurnAction());
      mapping.put("ReleaseZombiePawnEvent", new ReleaseZombiePawnAction());
      mapping.put("ClickedZombiePawnEvent", new ClickedZombiePawnAction());
      mapping.put("RotateZombieEvent", new RotateZombieAction());
      mapping.put("AbilityClickedEvent", new AbilityClickedAction());
      mapping.put("FocusEvent", new FocusAction());
      mapping.put("AxeEvent", new AxeAction());
      mapping.put("PistolEvent", new PistolAction());
      mapping.put("BiteEvent", new BiteAction());
      mapping.put("MedkitEvent", new MedkitAction());    
    }
    
    
    @Override
    public void run() {
        try {
            while (true){
                CustomEvent event = events.take();
                Action action = mapping.get(event.getClass().getSimpleName());
                action.performAction(event, gameLogic, gameLook);
            }
        } catch (InterruptedException ex) {
                System.err.print("Controller error "+ex);
                System.exit(-1);
            }
    }
    
    
    
}
