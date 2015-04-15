package coupledmotion;
/** PlayButton
 * Button which sets different values and redraws the screen appropriatly
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.event.*;

public class PlayButton implements ActionListener {

    private Box box; //the model
    private Box box1; //the model
    private JButton wrenchbutton; //the wrenchbutton
    private ObjectGUI obj; //the view for the box
    private Force force; //the force calculator
    private Thread t; //instantiate the thread
    private int counter = 1; //counter to be used to play or pause
    private int i; //stores the acceleration

    /**
     * PlayButton
     * Constructor for the PlayButton class
     *
     * @param Box       box allows control of different values in the box class
     * @param Box       box1 allows control of different values in the box class
     * @param JButton   play takes in a JButton
     * @param ObjectGUI objconnects the ObjectGUI class with the playbutton to give this class the ablility to redraw
     * @param Force     force connects the force class to invoke the intial calculations
     * @param JButton   wrenchbutton takes in a JButton
     */
    public PlayButton(Box box, Box box1, JButton play, ObjectGUI obj, Force force, JButton wrenchbutton) {
        //stores the objects passed from this constructor
        this.box = box;
        this.box1 = box1;
        this.obj = obj;
        this.wrenchbutton = wrenchbutton;
        this.force = force;
    }

    /**
     * actionPerformed
     * executes code after the event has occured
     *
     * @param ActionEvent e listents for the event
     */
    public void actionPerformed(ActionEvent e) {
        this.wrenchbutton.setEnabled(false); //disable wrenchbutton

        //starts the thread
        this.t = new Thread(obj);

        //system has 0 angle and the driving force is greater than friction force and the y component of hanging box hasnt reached 300
        if (this.box.getMass() != 0 && this.force.calcFofFriction(box) < this.force.calcFofGravity(box1) && this.box.getAngle() == 0 && this.obj.getYcomp() < 300) {
            //calculates acceleration and passes it to the ObjectGUI class to be used in the Thread.sleep method, sets the animation to move 1 pixel
            this.i = (int) this.force.calcAcceleration(box, box1);
            this.obj.setAcceleration(this.i);
            this.box.setHowFast(1);
            this.t.start();
            this.counter++;
            System.out.println("a" + this.counter);
        }
        //system has angle>0 and the force of gravity of the hanging box is greater than the friction and force of gravity on the other box combined and the y component of hanging box hasnt reached 30
        else if (this.box.getMass() != 0 && this.force.calcFofGravity(box1) > (this.force.calcFofFriction(box) + this.force.calcGravityX(box)) && this.box.getAngle() != 0 && this.obj.getYcomp() < 300) {
            //calculates acceleration and passes it to the ObjectGUI class to be used in the Thread.sleep method, sets the animation to move 1 pixel
            this.i = (int) (this.force.calcAccelUpHill(box, box1));
            this.obj.setAcceleration(this.i);
            this.box.setHowFast(1);
            this.t.start();
            this.counter++;
            System.out.println("b" + this.counter);
        }
        //system has angle>0 and the force of gravity of the box on table is greater than the friction and force of gravity on the hanging box combined and the x component of hanging box hasnt reached 30
        else if (this.box.getMass() != 0 && this.force.calcGravityX(box) > (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box)) && this.box.getAngle() != 0 && this.obj.getXcomp() > 300) {
            this.i = (int) (this.force.calcAccelDownHill(box, box1));
            this.obj.setAcceleration(this.i);
            this.box.setHowFast(-1);
            this.t.start();
            this.counter++;
            System.out.println("c" + this.counter);
        }
        //if the x component or y component reached 300
        else if (this.obj.getYcomp() == 300 || this.obj.getXcomp() == 300) {
            System.out.println("reset");
            //makes the play button act as a reset button by calling the reset methods from force and obj and also stops the animation by moving 0 pixels
            this.box.setHowFast(0);
            this.obj.reset();
            this.force.reset();
            this.wrenchbutton.setEnabled(true); //re-enables the wrenchbutton
            this.counter++;
            System.out.println("counter" + this.counter);
        }
        //if no mass is entered set the movement by 0 pixels
        else {
            this.box.setHowFast(0);
        }
    }

    /**
     * getCounter
     * gets the int value of a counter
     *
     * @return int gets the integer value of the counter in this class
     */
    public int getCounter() {
        return this.counter;
    }
}