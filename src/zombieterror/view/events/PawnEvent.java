/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombieterror.view.events;

import zombieterror.view.pawnlabels.Pawn;

/**
 *
 * @author Zbyszek
 */
public abstract class PawnEvent extends CustomEvent {
    
    private final Pawn source;

    PawnEvent(Pawn s) 
    {
        source = s;
    }
    

        
    public Pawn getSource() 
    {
        return source;
    }
    
}
