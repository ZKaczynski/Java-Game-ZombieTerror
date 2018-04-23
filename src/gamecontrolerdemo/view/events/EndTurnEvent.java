
package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;


public class EndTurnEvent extends CustomEvent
        
{

    public EndTurnEvent(Pawn s) {
        super(s);
    }

    
}
