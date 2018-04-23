
package gamecontrolerdemo;

import gamecontrolerdemo.view.pawns.Banker;
import gamecontrolerdemo.view.pawns.Policemen;
import gamecontrolerdemo.view.pawns.Nurse;
import gamecontrolerdemo.view.pawns.Lumberjack;
import gamecontrolerdemo.view.pawns.Zombie;
import gamecontrolerdemo.view.pawns.Mechanic;
import gamecontrolerdemo.view.pawns.Girl;
import gamecontrolerdemo.view.events.CustomEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.concurrent.BlockingQueue;
import javax.swing.*;


public class Board extends JPanel implements GlobalConstants  
{  
    private BlockingQueue <CustomEvent>  queue;

    private final int NUM_IMAGES=13;
   
    

    
    private final int MAX_ZOMBIES = 20;
    
    private final int EXIT = 0;
    private final int GIRL = 1;
    private final int LUMBERJACK = 2;
    private final int MECHANIC = 3;
    private final int NURSE = 4;
    private final int BANKER = 5;
    private final int POLICEMEN = 6;
    private final int ZOMBIE = 7;
    private final int OBSTACLE = 8;
    private final int SPAWN = 9;
    private final int EMPTY = 10;
    private final int WELL = 11;
    private final int SUBMENU = 12;
    
    private Image[] img; // przechowuje wszystkie obrazk
    private int[][] field;  // rysuje pola planszy ale NIE pionki
   
    public Girl girlPawn;
    public Lumberjack lumberjackPawn;
    public Mechanic mechanicPawn;
    public Nurse nursePawn;
    public Banker bankerPawn;
    public Policemen policemenPawn;
    
    private Zombie[] zombie_list;
    private JButton humanEndOfTurn;
    private JButton zombieEndOfTurn;
    private ExitButton exitButton;

    public Board (BlockingQueue<CustomEvent> q) 
    {
        this.queue=q;
        
        img = new Image [NUM_IMAGES];
        
        for (int i =0; i< NUM_IMAGES; i++)
        {
            img[i] = (new ImageIcon("Images//"+ i + ".png")).getImage();
        }
         setDoubleBuffered(true);
         this.setLayout(null);
         
        field = new int [GlobalConstants.ROWS][GlobalConstants.COLUMNS];
        
        for (int i=0; i<GlobalConstants.ROWS; i++)
        {   
            for (int j=0; j<GlobalConstants.COLUMNS; j++)
            {
              if ((i==0)||(j==0)||(i==11)||(j==15))
                 field[i][j]= SPAWN; 
                

              else field[i][j]= EMPTY;
              
              field [5][15]=EXIT;
              field [6][15]=EXIT;
              field [7][15]=EXIT;
            }
        }   

          girlPawn = new Girl("", queue);
          girlPawn.setBounds(SQUARE_SIZE/2, SQUARE_SIZE*2, SQUARE_SIZE, SQUARE_SIZE);
          girlPawn.setFixedPosition(new Point(SQUARE_SIZE/2, SQUARE_SIZE*2));
          ImageIcon iconLogo = new ImageIcon("Images//"+ GIRL+".png");
          girlPawn.setIcon(iconLogo);
          add(girlPawn);
          
          lumberjackPawn = new Lumberjack("", queue);
          lumberjackPawn.setBounds(SQUARE_SIZE/2, (int) (SQUARE_SIZE*3.5), SQUARE_SIZE, SQUARE_SIZE);
          lumberjackPawn.setFixedPosition(new Point(SQUARE_SIZE/2, (int) (SQUARE_SIZE*3.5)));
          iconLogo = new ImageIcon("Images//"+ LUMBERJACK+".png");
          lumberjackPawn.setIcon(iconLogo);
          add(lumberjackPawn);
          
          
          mechanicPawn = new Mechanic("", queue);
          mechanicPawn.setBounds(SQUARE_SIZE/2, SQUARE_SIZE*5, SQUARE_SIZE, SQUARE_SIZE);
          mechanicPawn.setFixedPosition(new Point(SQUARE_SIZE/2, (int)(SQUARE_SIZE*5)));
           iconLogo = new ImageIcon("Images//"+ MECHANIC+".png");
          mechanicPawn.setIcon(iconLogo);
          add(mechanicPawn);
          
          nursePawn = new Nurse("", queue);
          nursePawn.setBounds(SQUARE_SIZE/2, (int) (SQUARE_SIZE*6.5), SQUARE_SIZE, SQUARE_SIZE);
          nursePawn.setFixedPosition(new Point(SQUARE_SIZE/2, (int)(SQUARE_SIZE*6.5)));
           iconLogo = new ImageIcon("Images//"+ NURSE+".png");
          nursePawn.setIcon(iconLogo);
          add(nursePawn);
          
          bankerPawn = new Banker("", queue);
          bankerPawn.setBounds(SQUARE_SIZE/2, SQUARE_SIZE*8, SQUARE_SIZE, SQUARE_SIZE);
          bankerPawn.setFixedPosition(new Point(SQUARE_SIZE/2, (int)(SQUARE_SIZE*8)));
           iconLogo = new ImageIcon("Images//"+ BANKER+".png");
          bankerPawn.setIcon(iconLogo);
          add(bankerPawn);
          
          policemenPawn = new Policemen("", queue);
          policemenPawn.setBounds(SQUARE_SIZE/2, (int) (SQUARE_SIZE*9.5), SQUARE_SIZE, SQUARE_SIZE);
          policemenPawn.setFixedPosition(new Point(SQUARE_SIZE/2, (int)(SQUARE_SIZE*9.5)));
          iconLogo = new ImageIcon("Images//"+ POLICEMEN+".png");
          policemenPawn.setIcon(iconLogo);
          add(policemenPawn);
          
                                                  
          

        
        humanEndOfTurn = new TurnButton("END OF TURN", queue);
        humanEndOfTurn.setBounds(SQUARE_SIZE*9, 840, SQUARE_SIZE*2, SQUARE_SIZE/2);
        
                 iconLogo = new ImageIcon("Images//"+ GIRL+".png");
          humanEndOfTurn.setIcon(iconLogo);
        
        
        add(humanEndOfTurn);
        
       // zombieEndOfTurn = new JButton("END");
       // zombieEndOfTurn.setBounds(1400-SQUARE_SIZE*3/2, SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE/2);
       // zombieEndOfTurn.setEnabled(false);
       // add(zombieEndOfTurn);
        
        exitButton = new ExitButton("EXIT");
        exitButton.setBounds(300,840, SQUARE_SIZE, SQUARE_SIZE/2);
        add(exitButton);
        
    }
    
    
    
    public void spwanZombies ()
    {
        zombie_list = new Zombie [MAX_ZOMBIES];  
        for (int i=0; i<10; i++)
        {
            System.out.println("SpawnZ:"+i);
            zombie_list [i] = new Zombie ("", queue, i );
            zombie_list [i].setBounds((int)(SQUARE_SIZE*18.5),(int) (SQUARE_SIZE*1.5), SQUARE_SIZE, SQUARE_SIZE);
            zombie_list [i].setFixedPosition(new Point((int)(SQUARE_SIZE*18.5), (int) (SQUARE_SIZE*1.5)));
            ImageIcon iconLogo = new ImageIcon("Images//"+ ZOMBIE+".png");
            zombie_list [i].setIcon(iconLogo);
            add( zombie_list [i]);   
        } 
    }
    
    public void spawnHumanInterface()
    {
        
    }
    
    @Override
    public void paintComponent (Graphics g)
    {
        int cell = 0;
        for (int i = 0; i < GlobalConstants.ROWS; i++  )
        {
            for (int j =0; j < GlobalConstants.COLUMNS;  j++)
            {
                cell = field[i][j];
                
                
                        g.drawImage(img[cell], (j * SQUARE_SIZE+140),
                    (i * SQUARE_SIZE), this);
            }
        }
        
        g.drawImage(img[SUBMENU], (0), (0), this);
        g.drawImage(img[SUBMENU], (1260), (0), this);

    }

    public boolean hasCorrectCoordinates(Point p)    
    {
        return p.x>=2*SQUARE_SIZE && p.x<=(COLUMNS+1)*SQUARE_SIZE && p.y<ROWS*SQUARE_SIZE && p.y>=0;   
    }
    
    
    
    
   
}



        /*
         zombie_list = new Zombie [MAX_ZOMBIES];
        
        for (int i=0; i<MAX_ZOMBIES; i++)
        {
            zombie_list [i] = new Zombie ("");
            zombie_list [i].setBounds(210, 210, 70, 70);
            ImageIcon iconLogo = new ImageIcon("Images//"+ ZOMBIE+".png");
            zombie_list [i].setIcon(iconLogo);
            add( zombie_list [i]);
    }
        
        */