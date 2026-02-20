import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Astronaut {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public boolean isNorth;
    public boolean isSouth;
    public boolean isWest;
    public boolean isEast;



    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Astronaut(int pXpos, int pYpos) {
        isNorth = false;
        xpos = pXpos;
        ypos = pYpos;
        dx =10;
        dy =20;
        width = 60;
        height = 100;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        //anytime we update the hitbox
 
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

//below setup dx and dy to 0 makes the astronauts movement to only br controlled by the arrow keys or else stationary
//        dx = 0;
//        dy = 0;

        if(isNorth == true){
            dy = -2;
        }
        if(isSouth == true){
            dy = 2;
        }
        if(isEast == true){
            dx = 2;
        }
        if(isWest == true){
            dx = -2;
        }

        if(xpos < 0 || xpos > 950){
            dx=-dx;

        }

        if(ypos < 0 || ypos > 650){

            dy=-dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
        //hitbox update last to ensure most accurate changes
    }
}






