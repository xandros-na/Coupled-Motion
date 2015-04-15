package coupledmotion;
/** MuSlider
 * Slider which controls the mu constant
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MuSlider implements MouseListener {

    private Force force; //the force calculator
    private JSlider slide; //the mu slider
    private Box box; //the model
    private Box box1; //the model
    private ObjectGUI obj; //the view
    private double mu, a; //a stores the original mu value and mu stores the mu value/100
    private int i; //stores acceleration


    /**
     * MuSlider
     * Constructor for the MuSlider class
     *
     * @param Force     force takes in the force class which conects to the model
     * @param JSlider   slide takes in a JSlider component
     * @param Box       box the box object that may need values changes
     * @param Box       box1 another box object that may need value changes
     * @param ObjectGUI obj connects this with the subview which handles all the repainting
     */
    public MuSlider(Force force, JSlider slide, Box box, Box box1, ObjectGUI obj) {
//stores the objects passed from this constructor
        this.force = force;
        this.slide = slide;
        this.box = box;
        this.box1 = box1;
        this.obj = obj;
    }

    /**
     * mouseClicked
     *
     * @param MouseEvent e listens for the mouse event
     */
    public void mouseClicked(MouseEvent arg0) {
    }

    /**
     * mouseEntered
     *
     * @param MouseEvent e listens for the mouse event
     */
    public void mouseEntered(MouseEvent arg0) {
    }

    /**
     * mouseExited
     *
     * @param MouseEvent e listens for the mouse event
     */
    public void mouseExited(MouseEvent arg0) {
    }

    /**
     * mousePressed
     *
     * @param MouseEvent e listens for the mouse event
     */
    public void mousePressed(MouseEvent arg0) {
    }

    /**
     * mouseReleased
     * executes the code after the click of a mouse is released
     *
     * @param MouseEvent e listens for the mouse event
     */
    public void mouseReleased(MouseEvent arg0) {
        //get the mu value from the slider and set the mu value to the force calculator
        this.a = (double) slide.getValue();
        this.mu = this.a / 100;
        this.force.setMu(this.mu);

        //calculate the force of gravity based on the new mu value
        this.force.calcGravityY(box);
        this.force.calcGravityX(box);
        this.force.calcGravityXRounded();
        this.force.calcGravityYRounded();

        Thread.currentThread().interrupt();

        //system has 0 angle and driving force is greater than friction force
        if (this.force.calcFofFriction(box) < this.force.calcFofGravity(box1) && this.box.getAngle() == 0) {
            //calculate the acceleration, make the animation move by 1 pixel, make the box reduce stuttering and sets the acceleration to be used in Thread.sleep method
            this.i = (int) this.force.calcAcceleration(box, box1);
            this.obj.setXandYupHill();
            this.box.setHowFast(1);
            this.obj.setAcceleration(this.i);
        }
        //system has 0 angle and friction force is greater than driving force
        else if (this.force.calcFofFriction(box) > this.force.calcFofGravity(box1) && this.box.getAngle() == 0) {
//stops the animation  
            this.force.setAccel(0);
            this.obj.setVelocity(400);
            this.box.setHowFast(0);
            this.obj.setAcceleration(0);
        }
        //system has angle>0 and the force of gravity of the hanging box is greater than the friction and force of gravity on the other box combined
        else if (this.force.calcFofGravity(box1) > (this.force.calcFofFriction(box) + this.force.calcGravityX(box)) && this.box.getAngle() != 0) {
            //calculate the acceleration, make the animation move by 1 pixel, make the box reduce stuttering and sets the acceleration to be used in Thread.sleep method
            this.i = (int) (this.force.calcAccelUpHill(box, box1));
            this.obj.setXandYupHill();
            this.box.setHowFast(1);
            this.obj.setAcceleration(this.i);
            System.out.println("uphill");
        }
        //system has angle>0 and the force of gravity of the box on table is greater than the friction and force of gravity on the hanging box combined
        else if (this.force.calcGravityX(box) > (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box)) && this.box.getAngle() != 0) {
            //calculate the acceleration, make the animation move by -1 pixel, make the box reduce stuttering and sets the acceleration to be used in Thread.sleep method
            this.i = (int) (this.force.calcAccelDownHill(box, box1));
            this.obj.setXandYdownHill();
            this.box.setHowFast(-1);
            this.obj.setAcceleration(this.i);
            System.out.println("downhill");
        }
        //if conditions 3 and 4 are not met
        else if (this.force.calcGravityX(box) < (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box)) && this.box.getAngle() != 0 || (this.force.calcFofGravity(box1) < (this.force.calcFofFriction(box) + this.force.calcGravityX(box)))) {
            //stop the animation
            this.force.setAccel(0);
            this.obj.setVelocity(400);
            this.box.setHowFast(0);
            System.out.println("stopped");
        }
    }
}