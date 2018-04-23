/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecontrolerdemo.view.events;

import gamecontrolerdemo.view.pawns.Pawn;
import java.awt.Point;


public class ClickedZombiePawnEvent extends CustomEvent
{
    Point point;
    public ClickedZombiePawnEvent(Pawn s, Point p) 
    {
        super(s);
        point = p;
    }
    
        public Point getPoint()
    {
        return point;
    }
    
}
