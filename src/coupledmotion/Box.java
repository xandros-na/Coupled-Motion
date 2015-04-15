package coupledmotion;

/**
 * Box Class
 * Model for the Box UI
 * Modified June 13, 2012
 *
 * @author D.Miguel & C.Mannem
 */
public class Box extends Object {

    private double mass, angle, mu, gravity; //stores the mass, angle, mu and gravity
    private ObjectGUI view; //the view for this class
    private int acceleration; //stores acceleration

    /**
     * Box
     * Constructor for the Box class
     */
    public Box() {
        super();
        this.acceleration = 0;
    }

    /**
     * setGUI
     * sets the view that is being controled
     *
     * @param ObjectGUI gui the gui that needs to be accessed
     */
    public void setGUI(ObjectGUI gui) {
        this.view = gui;
    }

    /**
     * getMass
     * gets the double value of the stored mass
     *
     * @return double gets the double value stored in this variable
     */
    public double getMass() {
        return this.mass;
    }

    /**
     * getAccel
     * gets the double value of the acceleration
     *
     * @return int gets the int value stored in this variable
     */
    public int getAccel() {
        return this.acceleration;
    }

    /**
     * setMass
     * sets the double value of the mass
     *
     * @param double mass sets the double value that was passed
     */
    public double setMass(double mass) {
        this.mass = mass;
        return this.mass;
    }

    /**
     * setHowFast
     * sets how many pixels the object moves
     *
     * @param int a sets the int value that was passed
     */
    public void setHowFast(int a) {
        this.acceleration = a;
        this.updateView();
    }

    /**
     * updateView
     * calls the update method from the ObjectGUI class
     */
    public void updateView() {
        this.view.update();
    }

    /**
     * getAngle
     * gets the double value of the angle
     *
     * @return double gets the double value stored in this variable
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * setMu
     * sets the double value of the mu constant
     *
     * @param double mu sets the double value that was passed
     */
    public void setMu(double mu) {
        this.mu = mu;
        this.updateView();
    }

    /**
     * getMu
     * gets the double value of the mu constant
     *
     * @return double gets the double value stored in this variable
     */
    public double getMu() {
        return this.mu;
    }

    /**
     * setGravity
     * sets the double value of the gravitational constant
     *
     * @param double g sets the double value that was passed
     */
    public void setGravity(double g) {
        this.gravity = g;
        this.updateView();
    }

    /**
     * getGravity
     * gets the double value of the gravitational constant
     *
     * @return doule gets the double value stored in this variable
     */
    public double getGravity() {
        return this.gravity;
    }

    /**
     * setAngle
     * sets the double value of the angle
     *
     * @param double a sets the double value that was passed
     */
    public void setAngle(double a) {
        this.angle = a;

        //positions the box and the string on the table to the set dimensions
        if (this.angle == 0) {
            this.view.setIfAngled(100, 190, 150, 240);
        } else {
            this.view.setIfAngled(70, 190 + 56 - 20, 50, 340);
        }
    }
}