
package zombieterror.view;

import zombieterror.view.buttons.TurnButton;
import zombieterror.view.buttons.ExitButton;
import zombieterror.view.events.AxeEvent;
import zombieterror.view.pawnlabels.Banker;
import zombieterror.view.pawnlabels.Policeman;
import zombieterror.view.pawnlabels.Nurse;
import zombieterror.view.pawnlabels.Lumberjack;
import zombieterror.view.pawnlabels.Zombie;
import zombieterror.view.pawnlabels.Mechanic;
import zombieterror.view.pawnlabels.Girl;
import zombieterror.view.events.CustomEvent;
import zombieterror.view.events.PistolEvent;
import zombieterror.view.pawnlabels.Pawn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import zombieterror.ZombieTerror;
import zombieterror.view.events.AbilityClickedEvent;
import zombieterror.view.events.BiteEvent;
import zombieterror.view.events.MedkitEvent;

/**
 * 
 * At Board object are drawn and containd all pawns and board squeres. 
 */
public final class Board extends JPanel{  
    private final BlockingQueue <CustomEvent>  queue; 
  
    public JLabel endLabel; //info who won
   
    //Labels of human pawns
    public Girl girlPawn;
    public Lumberjack lumberjackPawn;
    public Mechanic mechanicPawn;
    public Nurse nursePawn;
    public Banker bankerPawn;
    public Policeman policemanPawn;

    private Zombie[] zombie_list; //table of Zombie pawns
    private final JButton endOfTurnButton; // button ending turn
    private final ExitButton exitButton; 
    
    //interface labels  informing about hp and ap
    public JLabel girlInterface;
    public JLabel lumberjackInterface;
    public JLabel mechanicInterface;
    public JLabel nurseInterface;
    public JLabel bankerInterface;
    public JLabel policemenInterface;
    
    //interface labels informing about pawn abilities and usage of them
    public JButton girlIcon;
    public JButton lumberjackIcon;
    public JButton mechanicIcon;
    public JButton nurseIcon;
    public JButton bankerIcon;
    public JButton policemenIcon;
    
    //buttons of zombie abilities
    public JButton plagueIcon;
    public JButton aweIcon;
    public JButton trapIcon;
    public JButton bloodlustIcon;
    public JButton rampageIcon;
    public JButton biteIcon;

    public JLabel zombieInfo; //label informing about zombies to move
    
    private int currentNumberOfZombies;  
    
    public Pawn focusPawn;      //who is now focused
    
    public JLabel terrorWarning; //interface informing about end game timer
    public JLabel terrorInfo;   //interface informing about how many turn to end
            
    public Board (BlockingQueue<CustomEvent> q){
        queue=q;
        
        setDoubleBuffered(true);
        setLayout(null);
         
        ImageIcon icon;
        
        //Spawning human pawns

        girlPawn = new Girl("", queue);
        girlPawn.setBounds(ZombieTerror.SQUARE_SIZE/2, ZombieTerror.SQUARE_SIZE*2, ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        girlPawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, ZombieTerror.SQUARE_SIZE*2));
        
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Girl.png"));
        girlPawn.setIcon(icon);
        add(girlPawn);
          
        lumberjackPawn = new Lumberjack("", queue);
        lumberjackPawn.setBounds(ZombieTerror.SQUARE_SIZE/2, (int) (ZombieTerror.SQUARE_SIZE*3.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        lumberjackPawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, (int) (ZombieTerror.SQUARE_SIZE*3.5)));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Lumberjack.png"));
        lumberjackPawn.setIcon(icon);
        add(lumberjackPawn);


        mechanicPawn = new Mechanic("", queue);
        mechanicPawn.setBounds(ZombieTerror.SQUARE_SIZE/2, ZombieTerror.SQUARE_SIZE*5, ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        mechanicPawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, (int)(ZombieTerror.SQUARE_SIZE*5)));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Mechanic.png"));
        mechanicPawn.setIcon(icon);
        add(mechanicPawn);

        nursePawn = new Nurse("", queue);
        nursePawn.setBounds(ZombieTerror.SQUARE_SIZE/2, (int) (ZombieTerror.SQUARE_SIZE*6.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        nursePawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, (int)(ZombieTerror.SQUARE_SIZE*6.5)));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Nurse.png"));
        nursePawn.setIcon(icon);
        add(nursePawn);

        bankerPawn = new Banker("", queue);
        bankerPawn.setBounds(ZombieTerror.SQUARE_SIZE/2, ZombieTerror.SQUARE_SIZE*8, ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        bankerPawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, (int)(ZombieTerror.SQUARE_SIZE*8)));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Banker.png"));
        bankerPawn.setIcon(icon);
        add(bankerPawn);

        policemanPawn = new Policeman("", queue);
        policemanPawn.setBounds(ZombieTerror.SQUARE_SIZE/2, (int) (ZombieTerror.SQUARE_SIZE*9.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        policemanPawn.setFixedPosition(new Point(ZombieTerror.SQUARE_SIZE/2, (int)(ZombieTerror.SQUARE_SIZE*9.5)));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Policeman.png"));
        policemanPawn.setIcon(icon);
        add(policemanPawn);
          

        endOfTurnButton = new TurnButton("", queue);
        endOfTurnButton.setBounds(ZombieTerror.SQUARE_SIZE*9, 840, ZombieTerror.SQUARE_SIZE*2, ZombieTerror.SQUARE_SIZE/2);
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/EndTurnIcon.png"));
        endOfTurnButton.setIcon(icon);

        add(endOfTurnButton);
        
        
        exitButton = new ExitButton("");
        exitButton.setBounds(ZombieTerror.SQUARE_SIZE*18,ZombieTerror.SQUARE_SIZE*12, ZombieTerror.SQUARE_SIZE*2, ZombieTerror.SQUARE_SIZE/2);
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/ExitIcon.png"));
        exitButton.setIcon(icon);
        add(exitButton);
        
    }
    /**
     * Spawns zombie with id on board
     * @param id zombie i
     */
    public void spawnZombie(int id){

            zombie_list [id].setBounds((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*0.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
            zombie_list [id].setFixedPosition(new Point((int)(ZombieTerror.SQUARE_SIZE*18.5), (int) (ZombieTerror.SQUARE_SIZE*0.5)));
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Zombie.png"));
            zombie_list [id].setIcon(icon);
            
            zombie_list [id].setVisible(true);
            setComponentZOrder(zombie_list [id],0);
    }
    /**
     * Spawns 10 starting zombies
     */
    public void spwanZombies (){
        currentNumberOfZombies=10;
        
        zombie_list = new Zombie [ZombieTerror.MAX_ZOMBIES];  
        for (int i=0; i<ZombieTerror.MAX_ZOMBIES; i++)
        {
            zombie_list [i] = new Zombie ("", queue, i );
            zombie_list [i].setBounds((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*1.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
            zombie_list [i].setFixedPosition(new Point((int)(ZombieTerror.SQUARE_SIZE*18.5), (int) (ZombieTerror.SQUARE_SIZE*1.5)));
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Zombie.png"));
            zombie_list [i].setIcon(icon);
            
           if (i>9) { zombie_list [i].setVisible(false);}
            add( zombie_list [i]);   
        } 
    }
    /**
     * Spawns zombie ability buttons and label showning zombies to move
     */
    public void spawnZombieInterface()
    {
        plagueIcon = new JButton();
        plagueIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18.00),(int) (ZombieTerror.SQUARE_SIZE*3), (int)(ZombieTerror.SQUARE_SIZE*2.0), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        plagueIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/zombiepower1.png")));
        plagueIcon.addActionListener((ActionEvent e) -> {
        try {
            queue.put(new AbilityClickedEvent("plague"));   
            } catch (InterruptedException ex) {   
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}
        });
        add(plagueIcon);
        
        
        aweIcon = new JButton();
        aweIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18),(int) (ZombieTerror.SQUARE_SIZE*4), (int)(ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        aweIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/zombiepower2.png")));
                aweIcon.addActionListener((ActionEvent e) -> {
            try {
                
                queue.put(new AbilityClickedEvent("awe"));
                
            } catch (InterruptedException ex) {   
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}
        });
        add(aweIcon);
        
        trapIcon = new JButton();
        trapIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18),(int) (ZombieTerror.SQUARE_SIZE*5), (int)(ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        trapIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/zombiepower3.png")));
        trapIcon.addActionListener((ActionEvent e) -> {
        try {
            queue.put(new AbilityClickedEvent("trap"));   
            } catch (InterruptedException ex) {   
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}
        });        
        add(trapIcon);
        
        bloodlustIcon = new JButton();
        bloodlustIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18),(int) (ZombieTerror.SQUARE_SIZE*6), (int)(ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        bloodlustIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/zombiepower4.png")));
        bloodlustIcon.addActionListener((ActionEvent e) -> {
        try {
            queue.put(new AbilityClickedEvent("bloodlust"));   
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE);}
        });      
        add(bloodlustIcon);
        
        rampageIcon = new JButton();
        rampageIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18),(int) (ZombieTerror.SQUARE_SIZE*7), (int)(ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        rampageIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/zombiepower5.png")));
        rampageIcon.addActionListener((ActionEvent e) -> {
        try {
            queue.put(new AbilityClickedEvent("rampage"));   
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }
        });           add(rampageIcon);
        
        biteIcon = new JButton();
        biteIcon.setBounds((int)(ZombieTerror.SQUARE_SIZE*18),(int) (ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*2), (int)(ZombieTerror.SQUARE_SIZE*1.0));
        biteIcon.setIcon( new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/BiteIcon.png")));
        add(biteIcon);
                biteIcon.addActionListener((ActionEvent e) -> {
            try {
                
                queue.put(new BiteEvent());
                
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }
        });
        
    }
    /**
     * Spwans human icons and interfaces labels
     */
    public void spawnHumanInterface(){
        //////////////////////////////////////
        // GIRL
        ////////////////
        girlInterface = new JLabel();
        girlInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*1.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        girlInterface.setIcon(icon);
        
        add( girlInterface);
        
        girlIcon = new JButton();
        girlIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*1.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconGirl.png"));
        girlIcon.setIcon(icon);
        girlIcon.setBorderPainted(false);
        add( girlIcon);
        
        //////////////////////////////////////
        //LUMBER
        //////////////////////////////////
        lumberjackInterface = new JLabel();
        lumberjackInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*2.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        lumberjackInterface.setIcon(icon);
        lumberjackInterface.setVisible(true);
        add( lumberjackInterface);
        
        lumberjackIcon = new JButton();
        lumberjackIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*2.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconLumberjack.png"));
        lumberjackIcon.setIcon(icon);
        lumberjackIcon.addActionListener((ActionEvent e) -> {
            try {
                
                queue.put(new AxeEvent());
                
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }
        });

        add( lumberjackIcon);    
        
        //////////////////////////////////////
        //MECHANIC
        //////////////////////////////////////
        mechanicInterface = new JLabel();
        mechanicInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*3.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        mechanicInterface.setIcon(icon);
        mechanicInterface.setVisible(true);
        add( mechanicInterface);
        mechanicIcon = new JButton();
        mechanicIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*3.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconMechanic.png"));
        mechanicIcon.setBorderPainted(false);
        mechanicIcon.setIcon(icon);
        add( mechanicIcon);
        //////////////////////////////////////
        //NURSE
        ////////////////////////////////////
        nurseInterface = new JLabel();
        nurseInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*4.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        nurseInterface.setIcon(icon);
        nurseInterface.setVisible(true);
        add( nurseInterface);
        nurseIcon = new JButton();
        nurseIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*4.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconNurse.png"));
        nurseIcon.setIcon(icon);
        nurseIcon.addActionListener((ActionEvent e) -> {
            try {
                
                queue.put(new MedkitEvent());
                
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }
        });
        
        add( nurseIcon);
        //////////////////////////////////////
        // BANKER
        /////////////////////////////////
        bankerInterface = new JLabel();
        bankerInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*5.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        bankerInterface.setIcon(icon);
        bankerInterface.setVisible(true);
        add( bankerInterface);
        
        bankerIcon = new JButton();
        bankerIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*5.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconBanker.png"));
        bankerIcon.setIcon(icon);
        bankerIcon.setBorderPainted(false);
        add( bankerIcon);
        
                //////////////////////////////////////
                //POLICEMEN
                ////////////////////////////
        policemenInterface = new JLabel();
        policemenInterface.setBounds((int)(ZombieTerror.SQUARE_SIZE*0.5),(int) (ZombieTerror.SQUARE_SIZE*6.0), (int)(ZombieTerror.SQUARE_SIZE*1.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints3full.png"));
        policemenInterface.setIcon(icon);
        policemenInterface.setVisible(true);
        add( policemenInterface);
        
        policemenIcon = new JButton();
        policemenIcon.setBounds(0,(int) (ZombieTerror.SQUARE_SIZE*6.0), (int)(ZombieTerror.SQUARE_SIZE*0.5), (int)(ZombieTerror.SQUARE_SIZE*0.5));
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/IconPoliceman4.png"));
        policemenIcon.setIcon(icon);
                policemenIcon.addActionListener((ActionEvent e) -> {
            try {
                
                queue.put(new PistolEvent());
                
            } catch (InterruptedException ex) {  
            JOptionPane.showMessageDialog(this,
            "Error:",
            "Plese exit "+ex,
            JOptionPane.ERROR_MESSAGE); }
        });
       
        add( policemenIcon);
        
        repaint();
       
        
    }
    /**
     * Draws board from 2d constant table
     * @param g 
     */
    @Override
    public void paintComponent (Graphics g)
    {
        ImageIcon icon;
        for (int i=0; i<ZombieTerror.BOARD_WIDTH; i++){   
            for (int j=0; j<ZombieTerror.BOARD_HEIGHT; j++){
                switch (ZombieTerror.BOARD_GENESIS[i][j]){
                case 0 :            
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/MapTiles/Empty.png"));           
                g.drawImage( icon.getImage(), ((i+2) * ZombieTerror.SQUARE_SIZE), (j * ZombieTerror.SQUARE_SIZE), this);   break;

                case 1 :
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/MapTiles/Obstacle.png"));           
                g.drawImage( icon.getImage(), ((i+2) * ZombieTerror.SQUARE_SIZE), (j * ZombieTerror.SQUARE_SIZE), this);   break;
                             
                case 2 : 
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/MapTiles/Spawn.png"));           
                g.drawImage( icon.getImage(), ((i+2) * ZombieTerror.SQUARE_SIZE), (j * ZombieTerror.SQUARE_SIZE), this);   break;             
                         
                case 3 : 
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/MapTiles/Exit.png"));           
                g.drawImage( icon.getImage(), ((i+2) * ZombieTerror.SQUARE_SIZE), (j * ZombieTerror.SQUARE_SIZE), this);   break;               
                        
                case 4 :  
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/MapTiles/Canal.png"));           
                g.drawImage( icon.getImage(), ((i+2) * ZombieTerror.SQUARE_SIZE), (j * ZombieTerror.SQUARE_SIZE), this);   break;            
                           
                }
            }    
        }   
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Border.png"));
        g.drawImage(icon.getImage(), (0), (0), this);
        g.drawImage(icon.getImage(), ((ZombieTerror.BOARD_WIDTH+2)*ZombieTerror.SQUARE_SIZE), (0), this);
        icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/BottomBorder.png"));
        g.drawImage(icon.getImage(), 2*ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE*ZombieTerror.BOARD_HEIGHT, this);

    }
    /**
     * 
     * @param p
     * @return true if p is on board false otherwise
     */
    public boolean hasCorrectCoordinates(Point p)    
    {
        return p.x>=2*ZombieTerror.SQUARE_SIZE && p.x<=(ZombieTerror.BOARD_WIDTH+1)*ZombieTerror.SQUARE_SIZE && p.y<ZombieTerror.BOARD_HEIGHT*ZombieTerror.SQUARE_SIZE && p.y>=0;   
    }
    /**
     * 
     * @param i - number of Action points 
     * @param pawnName - name of Pawn that action points has to be changed
     * @param isFull  - true if pawn has fullhp
     * Method draws interface label of pawn with pawnName. It needs info about 
     * action points and hp
     */
    public void reduceActionPoints(int i, String pawnName, boolean isFull)
    {           
                ImageIcon icon;    
                if (isFull){
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints"+i+"full.png"));
                }
                else{
                icon = new ImageIcon(getClass().getClassLoader().getResource("Images/Interface/Actionpoints"+i+"half.png"));
                }
                switch(pawnName)
                {
                    case "Banker":bankerInterface.setIcon(icon);break;
                    case "Girl":girlInterface.setIcon(icon);break;
                    case "Lumberjack":lumberjackInterface.setIcon(icon);break;
                    case "Policemen":policemenInterface.setIcon(icon);break;
                    case "Nurse":nurseInterface.setIcon(icon);break;
                    case "Mechanic":mechanicInterface.setIcon(icon);break;
                    
                }  
                                
                
    }
    /**
     * 
     * @param p
     * @return ZombiePawn with fixed position of p
     */
    public Zombie findZombiePawn (Point p)    {
        for (int i=0; i<ZombieTerror.MAX_ZOMBIES;i++)
        {
            if (zombie_list[i].getFixedPosition().equals(p)) return zombie_list[i];
        }
        return null;
    }
    /**
     * 
     * @param p
     * @return ZombiePawn with id == p
     */
        public Zombie findZombiePawn (int p)    {
            if (p>=0&&p<=ZombieTerror.MAX_ZOMBIES) return zombie_list[p];

        return null;
    }
        /**
         * 
         * Kills Zombie with id == p
         * Killing zombie is making it invisible and placing it in default 
         * coordinates
         */
        public void killZombie(int p)
        {
            Zombie zp;
            //if (p>=0&&p<=currentNumberOfZombies) 
                zp = zombie_list[p];
            
            
            //else {System.out.println("KRYTYCZNY!!!"); return;}
            
            currentNumberOfZombies--;
            
            zp.setLocation((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*1.5));
            zp.setFixedPosition(new Point((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*1.5)));
            zp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/PawnIcons/Zombie.png")));
            zp.setVisible(false);
        }
    /**
     * 
     * @param angle - how rotate pawn in case it is zombie
     * Sets focused pawn label to unfocused image
     */
    public void unFocusPawn(int angle )
    {
        if (focusPawn!=null)
        {
        switch (focusPawn.getClass().getSimpleName())
            {
            case "Girl": girlPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Girl.png"))); break; 
            case "Lumberjack": lumberjackPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Lumberjack.png"))); break;
            case "Banker": bankerPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons//Banker.png"))); break;
            case "Nurse": nursePawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Nurse.png"))); break;
            case "Mechanic": mechanicPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Mechanic.png"))); break;
            case "Policeman": policemanPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Policeman.png"))); break;
            case "Zombie":  focusPawn.setIcon(new ImageIcon (getClass().getClassLoader().getResource("Images/PawnIcons/Zombie.png")));
            
            Zombie z = (Zombie) focusPawn;
           
            if (angle==3) z.rotatePawn(-1);
            if (angle==2) {z.rotatePawn(1); z.rotatePawn(1);}
            if (angle==1) z.rotatePawn(1);
            
        }
        }   
    }
    /**
     * @param i - numbe of zombies to move in first round
     * First time interface have to be spawned
     */
    public void setFirstZombieInfo(int i){
        zombieInfo = new JLabel();
        zombieInfo.setBounds((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*0.5), ZombieTerror.SQUARE_SIZE, ZombieTerror.SQUARE_SIZE);
        zombieInfo.setIcon(new ImageIcon (getClass().getClassLoader().
        getResource("Images/Numbers/"+i+".png")));
        add(zombieInfo);
        repaint();
    }
    public void setZombieInfo(int i){
        zombieInfo.setIcon(new ImageIcon (getClass().getClassLoader().
        getResource("Images/Numbers/"+i+".png")));
        repaint();
    }
        /**
         * Show zombie terror interface warning 
         */
    public void beginZombieTerror(){
        terrorInfo = new JLabel();
        terrorInfo.setBounds((int)(ZombieTerror.SQUARE_SIZE*18.5),(int) (ZombieTerror.SQUARE_SIZE*10.5),  (int)( ZombieTerror.SQUARE_SIZE),(int) ZombieTerror.SQUARE_SIZE);
        terrorInfo.setIcon(new ImageIcon (getClass().getClassLoader().
        getResource("Images/Numbers/5.png")));
        add(terrorInfo);


        terrorWarning = new JLabel();
        terrorWarning.setBounds((int)(ZombieTerror.SQUARE_SIZE*18.25),(int) (ZombieTerror.SQUARE_SIZE*9),  (int)( ZombieTerror.SQUARE_SIZE*1.5),(int) ZombieTerror.SQUARE_SIZE*3);
        terrorWarning.setIcon(new ImageIcon (getClass().getClassLoader().
        getResource("Images/Interface/ZOMBIE TERROR.png")));
        add(terrorWarning);

        repaint();
    }
        /**
         * 
         * @param i - number to be displayed on Zombie terror interface
         * 
         */
        public void continueZombieTerror(int i){
            terrorInfo.setIcon(new ImageIcon (getClass().getClassLoader().
            getResource("Images/Numbers/"+i+".png")));
        }
        
        public void humansWon(){
            JLabel last = new JLabel();
            last.setBounds((int)(ZombieTerror.SQUARE_SIZE*8.5),(int) (ZombieTerror.SQUARE_SIZE*5),  (int)( ZombieTerror.SQUARE_SIZE*3),(int) ZombieTerror.SQUARE_SIZE*2);
            last.setIcon(new ImageIcon (getClass().getClassLoader().
            getResource("Images/Interface/Humanswon.png")));
            add(last);
            repaint();

        }
        
        public void zombiesWon(){
            endLabel = new JLabel();
            endLabel.setBounds((int)(ZombieTerror.SQUARE_SIZE*8.5),(int) (ZombieTerror.SQUARE_SIZE*5),  (int)( ZombieTerror.SQUARE_SIZE*3),(int) ZombieTerror.SQUARE_SIZE*2);
            endLabel.setIcon(new ImageIcon (getClass().getClassLoader().
            getResource("Images/Interface/Zombieswon.png")));
                    
            add(endLabel);
            repaint();
        }   
}




   