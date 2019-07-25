
package zombieterror.model;

import zombieterror.model.pawnmodels.LumberjackModel;
import zombieterror.model.pawnmodels.PolicemanModel;
import zombieterror.model.pawnmodels.GirlModel;
import zombieterror.model.pawnmodels.ZombieModel;
import zombieterror.model.pawnmodels.PawnModel;
import zombieterror.model.pawnmodels.NurseModel;
import zombieterror.model.pawnmodels.BankerModel;
import zombieterror.model.pawnmodels.MechanicModel;
import zombieterror.view.events.CustomEvent;
import java.awt.Point;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import zombieterror.model.pawnmodels.HumanPawnModel;
import zombieterror.ZombieTerror;

/**
 *
 * It contains all game rules and models of pawns and board. 
 */
public class GameLogic  {

    public enum stateOfGame //used to determine current phase of game
    {
    HumanStart, ZombieStart,  HumanTurn, ZombieTurn, ZombiePlacement, End;
    private stateOfGame next;
      static {
                HumanStart.next=ZombieStart;
                ZombieStart.next = HumanTurn;
                HumanTurn.next = ZombieTurn;
                ZombieTurn.next = ZombiePlacement;
                ZombiePlacement.next = HumanTurn;
                End.next = End;
             }

        public stateOfGame nextState()
        {
            return next;
        }
    }
    

    // MAIN GAME VARIABLES    
    private stateOfGame stateofgame; //curent phase of game
    private int endgametimer;   //how many rounds to zombie win
    private int zombiescore;    //zombie score for eating zombies
    private int humanscore;     //human score for escaping humans
    
    public int zombiesToSpawn;  //number of zombies to spwan at beging of Zombie Placement phase
    public int zombiesToMove;   // number of zombies to move during ZombieTurn 
    public boolean isfirstturn; //true if its first turn of game, false otherwise
    boolean endhasbegun;        //true if final countdown has begun

    public BoardModel boardModel; //model of board
    
    //Models of pawns
    public GirlModel girlModel;
    public BankerModel bankerModel;
    public LumberjackModel lumberjackModel;
    public PolicemanModel policemenModel;
    public NurseModel nurseModel;
    public MechanicModel mechanicModel;
    //Array of Zombies index is zombie id
    public ArrayList<ZombieModel> zombies;
    
    //currently focused pawn
    public PawnModel focusPawn;
    
    //flags used by zombie powers - true if used
    public boolean zombiepowertrap;
    public boolean zombiepowerawe;
    public boolean zombiepowerrampage;
    
    public GameLogic(BlockingQueue<CustomEvent> q) 
    {
        stateofgame = stateOfGame.HumanStart;
        endgametimer = 5;
        humanscore = 0;
        zombiescore=0;
        endhasbegun=false;
        
        zombiepowertrap=false;
        zombiepowerawe=false;
        zombiepowerrampage=false;
        
        zombiesToSpawn=0;
        zombiesToMove=0;
        isfirstturn=true;
        
        boardModel = new BoardModel();
        girlModel = new GirlModel();
        bankerModel = new BankerModel();
        mechanicModel = new MechanicModel();
        nurseModel = new NurseModel();
        policemenModel= new PolicemanModel();
        lumberjackModel = new LumberjackModel();
        zombies = new ArrayList<ZombieModel>(20);
    }
    public boolean hasEndBegun(){
        return endhasbegun;
    }
    public void startEnd(){
        endhasbegun=true;
    }
    
    
    public void decrementEndGameTimer()
    {
        endgametimer-=1;
    }
    public int getEndGameTimer(){
        return endgametimer;
    }
    
    public void increaseScore(int x)
    {
        switch (x) {
            case 0: break;
            case 1: this.humanscore=this.humanscore+1; break;
            case 2: this.humanscore=this.humanscore+2; break;
            case 3: this.humanscore=this.humanscore+3; break;
            default: System.err.print("Invalid sccore!"); 
        }
    }
    public int getHumanScore(){
        return humanscore;
    }
    
        public void increaseZombieScore(int x)
    {
        switch (x) {
            case 0: break;
            case 1: this.zombiescore=this.zombiescore+1; break;
            case 2: this.zombiescore=this.zombiescore+2; break;
            case 3: this.zombiescore=this.zombiescore+3; break;
            default: System.err.print("Invalid sccore!"); 
        }
    }
    public int getZombieScore(){
        return zombiescore;
    }
    
    public stateOfGame getState()
    {
        return stateofgame;
    }
    
    public void setEnd(){
        stateofgame=stateOfGame.End;
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
        for (int i=0;i<ZombieTerror.MAX_ZOMBIES;i++)
        {
            zombies.add(i,new ZombieModel(i));
            ZombieModel zm = (ZombieModel) zombies.get(i);
            if (i<10) zm.setAlive(true);
        }
    }
    /**
     * 
     * @param p //find zombie with this coordintes
     * @return This Zombie null otherwise
     */
    public ZombieModel findZombie(Point p){ 
        ZombieModel zm;
        for (int i=0; i< zombies.size();i++){
            zm = (ZombieModel)zombies.get(i);
            if (zm.getPointOnBoard()==null) continue;
            if ( zm.getPointOnBoard().equals( p))
                return zm;
        }
        return null; 
    }
    
       /**
        * Checks if pawn on coordintes prev can push zombie on coordintes next
        * @param prev - coordintes of pushing pawn
        * @param next - cordintes of pushed pawn
        * @return true if push can be performed false otherwise
        */
    public boolean isValidForHumanToPush(Point prev, Point next)
    {
            int xdelta, ydelta;
            xdelta = prev.x-next.x;
            ydelta = prev.y-next.y;
            

            ZombieModel zm = findZombie(next);

            if (zm!=null){

               if (xdelta == 0 && ydelta == 1 && findZombie(next).zombiedirection!=ZombieModel.zdirection.S)
               {
                
                   return this.boardModel.isValidDestenationToPush(new Point (prev.x, prev.y-2));
               }
                if (xdelta == 0 && ydelta == -1 && findZombie(next).zombiedirection!=ZombieModel.zdirection.N)
               {
                    return this.boardModel.isValidDestenationToPush(new Point (prev.x, prev.y+2));        
               }
               if (xdelta == 1 && ydelta == 0 && findZombie(next).zombiedirection!=ZombieModel.zdirection.E)
                {
                  return this.boardModel.isValidDestenationToPush(new Point (prev.x-2, prev.y));          
                }
                 if (xdelta == -1 && ydelta == 0 && findZombie(next).zombiedirection!=ZombieModel.zdirection.W)
                {
                return this.boardModel.isValidDestenationToPush(new Point (prev.x+2, prev.y));          
         
                }
            }
            return false;
        
    }
    /**
     * Checks if mechanicpawn on prev coordintes can push any human at next coordintes
     * @param prev - mechanic coordintes
     * @param next - coordintes where pushed pawn can be
     * @return true if push can be performed false otherwise
     */
        public boolean isValidForMechanicToPush(Point prev, Point next)
    {
            if (mechanicModel.getLifted()) return false;
            int xdelta, ydelta;
            xdelta = prev.x-next.x;
            ydelta = prev.y-next.y;

             if (this.boardModel.isOccupiedByHuman(next))
            {
               if (xdelta == 0 && ydelta == 1 )
               {
                   return this.boardModel.isValidDestenationToPush(new Point (prev.x, prev.y-2));
               }
                if (xdelta == 0 && ydelta == -1 )
               {
                    return this.boardModel.isValidDestenationToPush(new Point (prev.x, prev.y+2));        
               }
               if (xdelta == 1 && ydelta == 0 )
                {
                  return this.boardModel.isValidDestenationToPush(new Point (prev.x-2, prev.y));          
                }
                 if (xdelta == -1 && ydelta == 0 )
                {
                return this.boardModel.isValidDestenationToPush(new Point (prev.x+2, prev.y));          
                }
                
                        
            }
   
            return false;
        
    }
 
    
    /**
     * 
     * @param prev - coordintes of pushing pawn
     * @param next - coordintes of pushed pawn
     * @return Point new coordintes of pushed pawn
     */
    public Point calculetePush (Point prev, Point next)
    {
         int xdelta, ydelta;
            xdelta = prev.x-next.x;
            ydelta = prev.y-next.y;

                if (xdelta == 0 && ydelta == 1)
               {
                   return new Point (prev.x, prev.y-2);
               }
                if (xdelta == 0 && ydelta == -1 )
               {
                    return new Point (prev.x, prev.y+2);        
               }
               if (xdelta == 1 && ydelta == 0)
                {
                  return new Point (prev.x-2, prev.y);          
                }
                 if (xdelta == -1 && ydelta == 0 )
                {
                return new Point (prev.x+2, prev.y);          

                }
        return null;    
    }
    /**
     * Girl can every turn move once diagonally, therefore it need its own method 
     * to validate move
     * @param prev previous coordintes of girl pawn 
     * @param next - wanted coordintes of girl pawn
     * @return true if next can become new coordintes of girl false otherwise
     */
        public boolean isValidForGirlToMove (Point prev, Point next)
    {
        boolean stepx, stepy, stepa, stepb;
            stepx = abs(prev.x-next.x)==1;
            stepy = abs(prev.y-next.y)==1;
            stepa = abs(prev.x-next.x)==0;
            stepb = abs(prev.y-next.y)==0; 
            
           return (((stepx&&stepy&&!(girlModel.getSprinted()))||(stepx&&stepb)||(stepy&&stepa)) &&(boardModel.isFieldEmpty(next)));
            /*
         return ( (((stepx||stepy)&&!(stepx&&stepy))||(stepx&&stepy&&!(girlModel.getSprinted())))
                &&(boardModel.isFieldEmpty(next)));   */
    }
    /**
     * If girl move diagonally remember this fact in model
     * @param prev previous coordintes of girl pawn 
     * @param next - wanted coordintes of girl pawn
     */
        public void checkGirlSprint(Point prev, Point next)
        {
            boolean stepx, stepy;
            stepx = abs(prev.x-next.x)==1;
            stepy = abs(prev.y-next.y)==1; 
            
            if(stepx&&stepy) girlModel.activateSprint();
        }
        
    public boolean allZombiesOnBoard(){
        ZombieModel zm;
        for (int i=0;i<ZombieTerror.MAX_ZOMBIES;i++)
        {
            zm =(ZombieModel) zombies.get(i);
            if (zm.getAlive()&&!zm.isOnBoard()) return false;
        }
        return true;    
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


  
    public boolean isValidForBite(){
        if (focusPawn==null) return false;
        if (focusPawn.getClass().getSimpleName().equals("ZombieModel")){
            ZombieModel zm = (ZombieModel) focusPawn;
            Point zp = zm.getPointOnBoard();
            HumanPawnModel hm;
            switch (zm.zombiedirection){
                case N: hm = findHuman(new Point (zp.x,zp.y-1)); break;
                case W: hm = findHuman(new Point (zp.x-1,zp.y)); break;
                case S: hm = findHuman(new Point (zp.x,zp.y+1)); break;
                case E: hm = findHuman(new Point (zp.x+1,zp.y)); break;
                default: hm=null;
            }
            if (hm!=null) return true;
        }  
        return false;
    }
    
    public boolean isValidForMedkit(){
        if (focusPawn==null) return false;
        if (!focusPawn.getClass().getSimpleName().equals("ZombieModel"))
        {
        Point fp = focusPawn.getPointOnBoard();
        Point lp = nurseModel.getPointOnBoard();
        
            int xdelta, ydelta;
            xdelta = lp.x-fp.x;
            ydelta = lp.y-fp.y;
            
            if ((xdelta == 0 && ydelta == 1 )||(xdelta == 0 && ydelta == -1 )||
                (xdelta == 1 && ydelta == 0 )|| (xdelta == -1 && ydelta == 0 )||
                (xdelta == 0 && ydelta == 0))        
               {  
                    return true;
               }  
        }
        return false;
    }
 
    public boolean isValidForAxe (){
        if (focusPawn==null) return false;
        if (focusPawn.getClass().getSimpleName().equals("ZombieModel")){
            Point zp = focusPawn.getPointOnBoard();
            Point lp = lumberjackModel.getPointOnBoard();       
            int xdelta, ydelta;
            xdelta = lp.x-zp.x;
            ydelta = lp.y-zp.y;
            ZombieModel zm = (ZombieModel) focusPawn;
            if (xdelta == 0 && ydelta == 1 && zm.zombiedirection!=ZombieModel.zdirection.S){  
                return true;
            }
            if (xdelta == 0 && ydelta == -1 && zm.zombiedirection!=ZombieModel.zdirection.N){
                return true; 
            }
            if (xdelta == 1 && ydelta == 0 && zm.zombiedirection!=ZombieModel.zdirection.E){
                return true;          
            }
            if (xdelta == -1 && ydelta == 0 && zm.zombiedirection!=ZombieModel.zdirection.W){
                return true;        
            }
        }
        return false;
    }

    public boolean isValidForShoot() {
        if (!policemenModel.hasBullets()) return false;
        if (focusPawn==null) return false;
        if (focusPawn.getClass().getSimpleName().equals("ZombieModel")){
            ZombieModel zm = (ZombieModel) focusPawn;
            Point zp = focusPawn.getPointOnBoard();
            Point pp = policemenModel.getPointOnBoard();
            int xdelta, ydelta;
            xdelta = pp.x-zp.x;
            ydelta = pp.y-zp.y;
            if (xdelta == 0 && ydelta > 0 ){    
                for (int i =1;zp.y+i<pp.y;i++){
                    if (!boardModel.isFieldEmpty(new Point (pp.x, zp.y+i))) return false;
                }
                return true;
            }
            if (xdelta == 0 && ydelta < 0 ){
                for (int i =1;pp.y+i<zp.y;i++){
                    if (!boardModel.isFieldEmpty(new Point (pp.x, pp.y+i))) return false;
                }
                return true; 
            }
            if (xdelta > 0 && ydelta == 0){
                for (int i =1; zp.x+i<pp.x;i++){
                        if (!boardModel.isFieldEmpty(new Point (zp.x+i, pp.y))) return false;
                }
                return true;          
            }
            if (xdelta < 0  && ydelta == 0 ){
                for (int i =1; pp.x+i<zp.x;i++){
                    if (!boardModel.isFieldEmpty(new Point (pp.x+i, pp.y))) return false;
                }                       
                return true;        
            }
        }
        return false;
    }
        
    public boolean atLeastOneMoved(){
        if (girlModel.getActionPoints()!=3) return true;
        if (mechanicModel.getActionPoints()!=3) return true;
        if (nurseModel.getActionPoints()!=3) return true;
        if (lumberjackModel.getActionPoints()!=3) return true;
        if (bankerModel.getActionPoints()!=3) return true;
        return policemenModel.getActionPoints()!=3;

    }
        
    public int numberOfMovedHumans(){
        int i=0;
        if (girlModel.getActionPoints()!=3) i++;
        if (mechanicModel.getActionPoints()!=3) i++;
        if (nurseModel.getActionPoints()!=3) i++;
        if (lumberjackModel.getActionPoints()!=3) i++;
        if (bankerModel.getActionPoints()!=3) i++;
        if (policemenModel.getActionPoints()!=3) i++;
        return i;
    }

    public boolean isPointsNeighbour(Point prev, Point next){
        boolean stepx, stepy, stepa, stepb;
        stepx = abs(prev.x-next.x)==1;
        stepy = abs(prev.y-next.y)==1;

        stepa = abs(prev.x-next.x)==0;
        stepb = abs(prev.y-next.y)==0;            

        return ((stepa&&stepy)||(stepx&&stepb));        
    }
        
       
    public boolean shouldKillPawn (BoardModel.squereOccupation soq){
        
        switch (soq){
            case Girl: 
                return (!girlModel.getHp());
            case Banker:
                return (!bankerModel.getHp());
            case Lumberjack:
                return (!lumberjackModel.getHp());
            case Policemen:
                return (!policemenModel.getHp());
            case Nurse:
                return (!nurseModel.getHp());
            case Mechanic:
                return (!mechanicModel.getHp());
        }
        return false;
    }
    
    public HumanPawnModel findHuman(Point p){
        if (girlModel.getPointOnBoard().equals(p)) return girlModel;
        if (mechanicModel.getPointOnBoard().equals(p)) return mechanicModel;
        if (nurseModel.getPointOnBoard().equals(p)) return nurseModel;
        if (policemenModel.getPointOnBoard().equals(p)) return policemenModel;
        if (bankerModel.getPointOnBoard().equals(p)) return bankerModel;
        if (lumberjackModel.getPointOnBoard().equals(p)) return lumberjackModel;
        return null;
    }
    
    public boolean noRagingZombie(){
        ZombieModel zm;
        for (int i = 0; i< ZombieTerror.MAX_ZOMBIES; i++){
            zm = (ZombieModel) zombies.get(i);
            if (zm.zombieState==ZombieModel.zState.rage){
                return false;
            }
        }
        return true;
    }

    /**
     * Seeks and sets all zombies that should have changed state to rage
     */
    public void rageZombie(){
        
        for (int i=0; i<zombies.size();i++){
            ZombieModel zm = (ZombieModel) zombies.get(i);
            if (zm.getPointOnBoard() == null) continue;
            int zpx = zm.getPointOnBoard().x;
            int zpy = zm.getPointOnBoard().y;

            ZombieModel.zdirection zd = zm.zombiedirection;
            switch (zd){
                case N: 
                    System.out.println("JESTEM NA POLNOCY");
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy-1))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }     
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy-1))) {
                        break;
                    }  
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy-2))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       System.out.println("Powinienem byc tu");
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy-2))) {
                        break;
                    } 
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy-3))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy-3))) {
                        break;
                    }
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy-4))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    break;
                case S: 
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy+1))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }     
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy+1))) {
                        break;
                    }  
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy+2))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy+2))) {
                        break;
                    } 
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy+3))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx, zpy+3))) {
                        break;
                    }
                    if (boardModel.isOccupiedByHuman(new Point(zpx, zpy+4))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    break;
                case E: 
                    if (boardModel.isOccupiedByHuman(new Point(zpx+1, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }     
                    if (!boardModel.isFieldEmpty(new Point(zpx+1, zpy))) {
                        break;
                    }  
                    if (boardModel.isOccupiedByHuman(new Point(zpx+2, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx+2, zpy))) {
                        break;
                    } 
                    if (boardModel.isOccupiedByHuman(new Point(zpx+3, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx+3, zpy))) {
                        break;
                    }
                    if (boardModel.isOccupiedByHuman(new Point(zpx+4, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    break;
                case W: 
                    if (boardModel.isOccupiedByHuman(new Point(zpx-1, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }     
                    if (!boardModel.isFieldEmpty(new Point(zpx-1, zpy))) {
                        break;
                    }  
                    if (boardModel.isOccupiedByHuman(new Point(zpx-2, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx-2, zpy))) {
                        break;
                    } 
                    if (boardModel.isOccupiedByHuman(new Point(zpx-3, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }
                    if (!boardModel.isFieldEmpty(new Point(zpx-3, zpy))) {
                        break;
                    }
                    if (boardModel.isOccupiedByHuman(new Point(zpx-4, zpy))) {
                       zm.zombieState=ZombieModel.zState.rage;
                       break;
                    }      
            }
        }
        
    }
    public boolean isValidForZombieToSpawn (Point p, ZombieModel zm){
       if (!boardModel.isFieldEmpty(p)) return false; 
       if (zm.isOnBoard()) return false;
       if (zombiepowertrap) {zombiepowertrap=false; return true;}
       return boardModel.getSquere(p).squerestate == BoardModel.squereState.Spawn;
    }
    
}
    
   