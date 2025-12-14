import java.awt.*;

public class asteroid {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public Rectangle hitbox;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public asteroid(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =-20;
        dy =-20;
        width = 160;
        height = 160;
        hitbox = new Rectangle(xpos, ypos, 55, 77);
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if(ypos > 700){
            ypos = 0;
        }
        if(ypos < 0){
            ypos = 700;
        }

        if(xpos > 850){
            xpos = 0;
        }
        if(xpos < 0){
            xpos = 850;
        }
        //todo: make the asteroid wrap when it hits the right and left walls
        //below: not how we move
//        if(xpos < 0 || xpos > 1250){
//            dx=-dx;
//
//        }
//
//        if(ypos < 0 || ypos > 850){
//
//            dy=-dy;
//        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}

//todo: for homework, add another asteroid
