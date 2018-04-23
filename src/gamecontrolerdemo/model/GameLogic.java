
package gamecontrolerdemo.model;

import gamecontrolerdemo.GlobalConstants;
import gamecontrolerdemo.view.events.CustomEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;


public class GameLogic  implements GlobalConstants
{


           public enum stateOfGame 
        {
        HumanStart, ZombieStart,  HumanTurn, ZombieTurn, ZombiePlacement;
        private stateOfGame next;
          static {
                    HumanStart.next=ZombieStart;
                    ZombieStart.next = HumanTurn;
                    HumanTurn.next = ZombieTurn;
                    ZombieTurn.next = ZombiePlacement;
                    ZombiePlacement.next = HumanTurn;
                 }
        
            public stateOfGame nextState()
            {
                return next;
            }
        }
    

    // MAIN GAME VARIABLES    
    private stateOfGame stateofgame;
    private int endgametimer;
    private int humanscore;
    
    //HUMAN PAWN POWERS
    private int numberOfBullets;    //policemen power
    private boolean hasSuitcase;    //banker power
    private boolean hasSprinted;    //girl power
    private boolean hasLifted;      //mechanic power

    //HUMAN GLOBAL POWERS
    private boolean canRegroup;
    
    //ZOMBIE GLOBAL POWERS
    
    //WHO IS ON BOARD?
    public boolean isGirlOnBoard;
    
    public BoardModel boardModel;
    
    public GirlModel girlModel;
    public BankerModel bankerModel;
    public LumberjackModel lumberjackModel;
    public PolicemenModel policemenModel;
    public NurseModel nurseModel;
    public MechanicModel mechanicModel;
    public ArrayList zombies;
    
    public GameLogic(BlockingQueue<CustomEvent> q) 
    {
        this.stateofgame = stateOfGame.HumanStart;
        this.endgametimer = 0;
        this.humanscore = 0;
        
        
        isGirlOnBoard=false;
        
        boardModel = new BoardModel();
        girlModel = new GirlModel();
        bankerModel = new BankerModel();
        mechanicModel = new MechanicModel();
        nurseModel = new NurseModel();
        policemenModel= new PolicemenModel();
        lumberjackModel = new LumberjackModel();
        zombies = new ArrayList(20);
    }
    
    public void incrementEndGameTimer()
    {
        this.endgametimer=this.endgametimer+1;
    }
    
    public void increaseScore(int x)
    {
        switch (x) {
            case 1: this.humanscore=this.humanscore+1;
            case 2: this.humanscore=this.humanscore+2;
            case 3: this.humanscore=this.humanscore+3;
            default: System.err.print("Invalid sccore!"); 
        }
    }
    
    public stateOfGame getState()
    {
        return stateofgame;
    }
    
    public void nextState()
    {
        stateofgame=stateofgame.nextState();
    }
    
    public boolean allHumansOnBoard()
    {
     return (lumberjackModel.isOnBoard()&& girlModel.isOnBoard()&&bankerModel.isOnBoard()&&
             mechanicModel.isOnBoard()&& nurseModel.isOnBoard()
             &&policemenModel.isOnBoard());   
        
    }
    public void setUpZombies()
    {
        for (int i=0;i<10;i++)
        {
            zombies.add(i,new ZombieModel());
        }

    }
    
    public boolean allStartingZombiesOnBoard()
    {
        ZombieModel zm;
        for (int i=0;i<10;i++)
        {
            zm =(ZombieModel) zombies.get(i);
            if (!zm.isOnBoard()) return false;
        }
        return true;
    }



    
    
            
}


