package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;

public abstract class CustomEvent {

    private final Pawn source;

    CustomEvent(Pawn s) 
    {
        source = s;
    }
    

        
    public Pawn getSource() 
    {
        return source;
    }

}
