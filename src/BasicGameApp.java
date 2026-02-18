//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

/*2.18.2026
step 1: implement Key listner


 */
public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
    public Image asteroidPic;
    public Image asteroid2Pic;
    public Image BackgroundPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	public Astronaut astro;
    //todo: make a new object called astro2

//    private Astronaut astro2;
    public Astronaut astro2;
    public asteroid asteroid1;
    public asteroid2 asteroid2;

    // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();

         //randomness
         //range 0-9
        //ALWAYS PUT PARENTHESIS NEXT TO INT: (int)(Math.random(0 * range) + offset;

      int randx = (int)(Math.random()*10);
      //generates a number between 0.00001 to 0.999999(lots of 0 and lots of 9)
        //0.001 - 9.99
        //range to 1-10
        randx = (int)(Math.random()*1000)+1;
        //0.001 = 00.999
        //0.01-9.99
        //0-9
        //1-10

        //range to be 1-1000
        //randx = (int)(Math.random()*1000)+1;

        //todo: make a variable called randy and make the random range 1-700

        int randy = (int)(Math.random()*10);
        randy = (int)(Math.random()*700)+1;

      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
        asteroidPic = Toolkit.getDefaultToolkit().getImage("asteroid1.jpg");
        asteroid2Pic = Toolkit.getDefaultToolkit().getImage("asteroid2.jpeg");
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("download.jpg");

		astro = new Astronaut(10,100);
        astro.dy = (int)(Math.random()*5)-3;
        astro.dx = (int)(Math.random()*5)-3;

        astro2 = new Astronaut(randx,randy);
        astro2.dx = (int)(Math.random()*5)-3;
        astro2.dy = (int)(Math.random()*5)-3;

        asteroid1 = new asteroid(100, 0);
        asteroid1.dx = (int)(Math.random()*5)-3;
        asteroid1.dy = (int)(Math.random()*5)-3;

        asteroid2 = new asteroid2(496, 498);
        asteroid2.dx = (int)(Math.random()*5)-3;
        asteroid2.dy = (int)(Math.random()*5)-3;


	}// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		astro.move();
        astro2.move();
        asteroid1.move();
        asteroid2.move();
        crashing();
        crashing_asteroids();
	}

    public void crashing(){

        //if the astros crashes into each other
        if(astro.hitbox.intersects(astro2.hitbox)){
            System.out.println("Crashing!");
            astro.dx = - astro.dx;
            astro2.dx = - astro2.dx;
            astro.dy = - astro.dy;
            astro2.dy = - astro2.dy;
            astro2.isAlive = false;
        }
    }
    public void crashing_asteroids(){
        if(asteroid1.hitbox.intersects(asteroid2.hitbox) && asteroid2.isCrashing == false){
            System.out.println("Crashing asteroids!");

            asteroid1.dx = - asteroid1.dx;

            asteroid1.dy = - asteroid1.dy;
            asteroid2.dx = - asteroid2.dx;
            asteroid2.dy = - asteroid2.dy;

            asteroid2.isCrashing = true;
            asteroid2.height = asteroid2.height+ 50;
        }
        if(asteroid1.hitbox.intersects(asteroid2.hitbox)){
            asteroid2.isCrashing = false;
        }
    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method


   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();

       /* 2.18.2026
       step 2: add Keylistner to canvas

        */
       canvas.addKeyListener(this);
       //we're controlling w/ our keys
       //the canvas is our?

      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();


		g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(BackgroundPic,0,0,WIDTH,HEIGHT,null);

      //draw the image of the astronaut
		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);

        if(astro2.isAlive == true){
            g.drawImage(astroPic, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);
            g.drawRect(astro2.hitbox.x, astro2.hitbox.y, astro2.hitbox.width, astro2.hitbox.height);
        }

        g.drawImage(asteroidPic, asteroid1.xpos, asteroid1.ypos, 45, 67, null);
        g.drawImage(asteroid2Pic, asteroid2.xpos, asteroid2.ypos, 26, 78, null);

		g.drawRect(astro.hitbox.x, astro.hitbox.y, astro.hitbox.width, astro.hitbox.height);

        g.drawRect(asteroid1.hitbox.x, asteroid1.hitbox.y, 45, 67);
        g.drawRect(asteroid2.hitbox.x, asteroid2.hitbox.y, 36, 88);
        g.dispose();

		bufferStrategy.show();
	}

    /* 2.18.2026
    Step 3: add methods
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        //gets the letters that were pressed, not e.g. backspace or space

        //a code for each key is different, unique so

        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 38){ //when up arrow is pressed

            //astro.dy = astro.dy - 1;

            //the below code makes it always go up every time pressed up button
             astro.isNorth = true;
        }

        if(e.getKeyCode() == 40){ //when up arrow is pressed

            //astro.dy = astro.dy - 1;

            //the below code makes it always go up every time pressed up button
            astro.isSouth = true;
        }

        if(e.getKeyCode() == 39){ //when up arrow is pressed

            //astro.dy = astro.dy - 1;

            //the below code makes it always go up every time pressed up button
            astro.isEast = true;
        }

        if(e.getKeyCode() == 37){ //when up arrow is pressed

            //astro.dy = astro.dy - 1;

            //the below code makes it always go up every time pressed up button
            astro.isWest = true;
        }



//        if(e.getKeyCode() == 39){ //when up arrow is pressed
//
//            astro.dx = astro.dx - 1;
//
//            //the below code makes it always go up every time pressed up button
//            // astro.dy = -Math.abs(astro.dy);
//        }

    }

    @Override
    public void keyReleased(KeyEvent e) {   //key released is triggered everytime we stop touching a key
        System.out.println("I stopped touching " + e.getKeyCode());
        //to see which key code we jsut released

        if(e.getKeyCode() == 38 ){  //38 is up arrow
            astro.isNorth = false;
            System.out.println("not going up");
        }
        if(e.getKeyCode() == 40 ){  //38 is up arrow
            System.out.println("not going up");

            astro.isSouth = false;
        }
        if(e.getKeyCode() == 39 ){  //38 is up arrow
            System.out.println("not going up");

            astro.isEast = false;
        }
        if(e.getKeyCode() == 37 ){  //38 is up arrow
            System.out.println("not going up");

            astro.isWest = false;
        }

    }
}