package FlappyBirds;

//import com.sun.glass.ui.Window;


import java.awt.Color;

import java.awt.Graphics;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.util.ArrayList;

import java.util.Random;

import java.awt.font.*;

import javax.swing.*;

import javax.swing.plaf.FontUIResource;

import javax.swing.text.AttributeSet.FontAttribute;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;


public class FlappyBird implements ActionListener, MouseListener, KeyListener



{
	//package

    public static FlappyBird flappyBird;

    //size of the j.frame
    
    public final int WIDTH = 800, HEIGHT= 800;

    //A class for adding components to JPanels which will 
	//then be added to the JFrame to create the GUI 
    
    public Graphiclass gclass;

    
    //Using Rectangle shaped bird, default library
    
    
    public Rectangle bird;

    //variables for passing true/false values 
    
    public boolean gameOver, started;

    // integers for motion and score
    
  
    public int clicks, movement, score;
    
    
    //addition of obstacles 

    
    public ArrayList<Rectangle> obstacles;

    //generating a random value
    
    public Random ran;


    public FlappyBird()
    {
        
    	//NewExample obj=new NewExample();  
    	//It is used to create the object, allocates the memory at runtime.
    	//single argument to call constructor
    	
    	JFrame jframe = new JFrame();
        
    	//creating object for graphics
    	
    	gclass = new Graphiclass();
        
    	//this keyword is used to pass an argument, event handling.
    	
    	Timer timer = new Timer(20, this);
        
        
        ran= new Random ();

        //adding in J.frame
        
        
        jframe.add(gclass);
        
        
        //setting operation to close application after pressing exit button
        
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //setting up width, height/
        
        
        
        jframe.setSize(WIDTH, HEIGHT);
        
        
        
        //disabling re-sizable option
        
        
        jframe.setResizable(false);
        
        
        //enabling visibility on J.frame
        
        
        jframe.setVisible(true);
        
        
        //setting up the title
        
        
        
        jframe.setTitle("Flappy Bird");
        
        
        
        //enabling mouse-click by passing this as reference
        
        
        jframe.addMouseListener(this);
        
        
        
        //enabling space
        
        
        
        jframe.addKeyListener(this);

        
        
        //setting up location of bird at the center
        
        
        
        bird = new Rectangle(WIDTH/2 -10 , HEIGHT/2 -10, 20, 20);
        
        
        //creating obstacles using array list
        
        
        
        obstacles= new ArrayList<Rectangle>();

        
        
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        
       // Creates a Timer and initializes both the initial delay and 
        //between-event delay to delay milliseconds
        
        timer.start();

    }

    public void addColumn(boolean start)
    {
    	
    	// space gap between top and bottom obstacle
    	
        int space= 320;
        
        //width of the obstacle
        
        int width= 100;
        
        //height of the bottom obstacle 
        //generating random value for the height
        //so that there must be difference
        //between two obstacles
        //minimum height is 50
        //max height 300
        
        int height = 50 + ran.nextInt(300);



        if (start)
        {

        	//adjustment of the position of obstacles
        	//so that both upward and downward obstacle remains
        	//exactly perpendicular to each other
        	
        	obstacles.add(new Rectangle( WIDTH + width + (obstacles.size()) * 300, HEIGHT 
        			
        			//subtracting 120, because of the ground
        			
        			- height -120, width, height ));
            
        	
        	obstacles.add(new Rectangle( WIDTH + width + (obstacles.size()-1 ) * 300, 0, width, HEIGHT - height - space));
        }

        else
        {

        	//if there is one column in array list it will return 1
        	//1st column is at array index 0, and thats why we minus 1
        	
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1 ).x + 600, HEIGHT - height -120, width, height ));
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1 ).x, 0, width, HEIGHT - height - space ));
        }

    }


    public void jump()
    {
        if(gameOver)
        {

        	//for bird position after game over 
        	
            bird = new Rectangle(WIDTH/2 -10 , HEIGHT/2 -10, 20, 20);
   
            //obstacles removing after game is over
            
            obstacles.clear();
            //movement=0;


            //obstacles addition after game is restarted
        
            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        
        //if game is not started
        //then start the game by click
        
        
        if (!started)
        {
            started = true;
        }

        else if (!gameOver)
        {
        	
        	//motion of the ball
        	
            if (movement>0)
            {
                movement=0;
            }
            
            //ball will move in upward direction
            
            movement -=10;
        }


    }







    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        int speed =10;
       
        
        clicks++;

        if (started)
        {

        	
        	// moving forward
        	//1st four obstacles
        	
        	
            for(int i=0; i<obstacles.size(); i++)
            {
                Rectangle column = obstacles.get(i);
                column.x -= speed;
            }

        
            //after the click
            //handling jump strength
            
            if ((clicks % 2 ==0) && ( movement<15))
            {
                movement++;
            }

           
            //rest of obstacles
            
            

 

                 for (int i = 0; i<obstacles.size(); i++)
            {
                Rectangle column = obstacles.get(i);
                if(column.x + column.width<0)
                {
                    obstacles.remove(column);
                    
                	//when top column is 0, 
                	//add another column
                	
                	if(column.y == 0)
                    {
                       addColumn(false);
                    }
                }
            }


            
            
            
                 /*          
                 for (int i = 0; i<obstacles.size(); i++)
                 {
                     Rectangle column = obstacles.get(i);
                     if(column.x<0)
                     {
                        // obstacles.add(column);
                     	
                     	if(column.x== 0)
                         {
                            addColumn(false);
                         }
                     }
                 }


                 
                 
                 //            for (int i = 0; i<obstacles.size(); i++)
                 {
                     Rectangle column = obstacles.get(i);
                     if(column.x + column.height<0)
                     {
                        // obstacles.remove(column);
                         
                     	//when top column is 0, 
                     	//add another column
                     	
                     	if(column.x< 0)
                         {
                            addColumn(false);
                         }
                     }
                 }*/
            
            
            
            
            
            //adds motion to the bird

            bird.y+=movement;

         //adding score
            
            for (Rectangle column : obstacles)
            {

                if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
                {
                    score++;


                }


                
                //when game have to be over
                

                if ( column.intersects(bird))
                {
                    gameOver= true;


                    if (bird.x <= column.x)
                    {

                    	//bird will stay after column hits.
                    	
                        bird.x=column.x - bird.width;            
                    }
                    score=0;
                }

                
                //if bird collides with top
                
                if(bird.y > HEIGHT - 120 || bird.y < 0)
                {
                    gameOver= true;
                }

                if(gameOver)
                {
                    bird.y = HEIGHT -120 -bird.height;       // helps the bird to stay on ground after game over
                }

                gclass.repaint();


                
                
                
                


            }
        }
    }
   
    
    //painting the graphics
    
    
    public void paintColumn(Graphics g, Rectangle column)
    {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);

    }


    
    


    public void repaint(Graphics g)
    {
    	// inside frame color
    	
        g.setColor(Color.cyan);                                    
        
        g.fillRect(0,0, WIDTH, HEIGHT );
       
      //setting color of the bird
        
        g.setColor(Color.red);                                     
        
      //location of bird
        
        g.fillRect(bird.x, bird.y, bird.width, bird.height);       
        
      //ground color
        
        g.setColor(Color.orange);                                  
        
      //ground adjustments
        
        g.fillRect(0, HEIGHT - 120, WIDTH,120);                    

        g.setColor(Color.green);
        
        g.fillRect(0, HEIGHT-120, WIDTH, 20);


        
        
        //for-each loop for printing column

      
        if(!gameOver){
            for (Rectangle column: obstacles)
            {
                paintColumn(g, column);
            }
        }


        g.setColor(Color.white);
        //g.setFont(new Font( "Arial",  1 , 100));
        g.setFont(new FontUIResource("Arial", 4, 30));


        if (!started)
        {
            g.drawString("Click To Start. :D ", 150, HEIGHT/2 - 50 );

        }

        if (gameOver)
        {
            g.drawString("Game Over", 150, HEIGHT/2 - 50 );
            g.setColor(Color.cyan);
            g.fillRect(bird.x, bird.y, bird.width, bird.height);

        }


        if(!gameOver && started)
        {
            g.drawString(String.valueOf(score), WIDTH/2 -25, 100 );

        }

    }


    
    
    
    
    
    
    
    
    
    



    public static void main (String [] agrs)
    {
        flappyBird= new FlappyBird();


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        jump();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode()== KeyEvent.VK_SPACE)
			
		{
			jump();
		}
		
	}


}

