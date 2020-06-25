package FlappyBirds;



	import java.awt.Graphics;

	import javax.swing.JPanel;


	public class Graphiclass extends JPanel

	{

		// adding components to JPanels which will 
		//then be added to the JFrame to create the gui
		
		
	    private static final long serialVersionUID= 1L;


	    @Override
	    protected void paintComponent(Graphics g) {
	        // TODO Auto-generated method stub
	        super.paintComponent(g);

	        FlappyBird.flappyBird.repaint(g);
	    }

	}




