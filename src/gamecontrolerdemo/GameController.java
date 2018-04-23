
package gamecontrolerdemo;

import gamecontrolerdemo.model.GameLogic;
import gamecontrolerdemo.controller.actions.Action;
import gamecontrolerdemo.controller.actions.ClickedZombiePawnAction;
import gamecontrolerdemo.controller.actions.EndTurnAction;
import gamecontrolerdemo.controller.actions.MovePawnAction;
import gamecontrolerdemo.controller.actions.ReleaseBankerPawnAction;
import gamecontrolerdemo.controller.actions.ReleaseGirlPawnAction;
import gamecontrolerdemo.controller.actions.ReleaseLumberjackPawnAction;
import gamecontrolerdemo.controller.actions.ReleaseMechanicPawnAction;
import gamecontrolerdemo.controller.actions.ReleaseNursePawnAction;
import gamecontrolerdemo.controller.actions.ReleasePolicemenPawnAction;
import gamecontrolerdemo.controller.actions.ReleaseZombiePawnAction;
import gamecontrolerdemo.controller.actions.RotateZombieAction;
import gamecontrolerdemo.view.events.CustomEvent;
import gamecontrolerdemo.view.events.MovePawnEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


public class GameController implements Runnable{
    private final BlockingQueue<CustomEvent> events;
    private final Map<String, Action> mapping;
    private GameLogic gameLogic;
    private GameLook gameLook;
    
    public GameController(BlockingQueue<CustomEvent> events, GameLook gameLook, GameLogic gameLogic) 
    {
        this.gameLogic = gameLogic;
        this.gameLook = gameLook;
        this.events = events;       
        mapping = new LinkedHashMap<>();
        createMapping();
    }
    
    private  void createMapping() 
    {
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
    }
    
    
    @Override
    public void run() 
    {
       try {
           while (true)
           {
               CustomEvent event = events.take();
               Action action = mapping.get(event.getClass().getSimpleName());
               action.performAction(event, gameLogic, gameLook);


           }
      } catch (InterruptedException ex) {}

        

    
    }
    
    
    
}
