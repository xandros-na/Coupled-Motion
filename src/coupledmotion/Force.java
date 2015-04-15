package coupledmotion;

/**
 * Force Class
 * Model for the view
 * Modified June 13, 2012
 *
 * @author D.Miguel & C.Mannem
 */
public class Force {

    private double gravity = 9.8; //set initial value of gravity
    private double mu, fGravityX, fGravityY, acceleration, fOfFriction, fOfGravity; //stores the different force values

    /**
     * Force
     * Constructor for the Force class
     */
    public Force() {
        super();
    }

    /**
     * calcFofGravity
     * equation for to calculate the force of gravity
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcFofGravity(Box box) {
        this.fOfGravity = box.getMass() * this.gravity;
        return this.fOfGravity;
    }

    /**
     * calcFofGravityRounded
     * equation for to calculate the force of gravity rounded
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double which is rounded to 2 decimal places
     */
    public double calcFofGravityRounded() {
        this.fOfGravity = (int) (this.fOfGravity * 100.0);
        this.fOfGravity = (double) (this.fOfGravity / 100.0);
        return this.fOfGravity;
    }

    /**
     * calcFofGravityX
     * equation for to calculate the x-component of the force of gravity
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcGravityX(Box box) {
        this.fGravityX = box.getMass() * this.gravity * (Math.sin(Math.toRadians(box.getAngle())));
        return this.fGravityX;
    }

    /**
     * calcFofGravityXRounded
     * equation for to calculate the X-component of the force of gravity rounded
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double rounded to 2 decimal places
     */
    public double calcGravityXRounded() {
        this.fGravityX = (int) (this.fGravityX * 100.0);
        this.fGravityX = (double) (this.fGravityX / 100.0);
        return this.fGravityX;
    }

    /**
     * calcFofGravityY
     * equation for to calculate the y-component of the force of gravity
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcGravityY(Box box) {
        this.fGravityY = box.getMass() * this.gravity * (Math.cos(Math.toRadians(box.getAngle())));
        return this.fGravityY;
    }

    /**
     * calcFofGravityY
     * equation for to calculate the y-component of the force of gravity rounded
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double rounded to 2 decimal places
     */
    public double calcGravityYRounded() {
        this.fGravityY = (int) (this.fGravityY * 100.0);
        this.fGravityY = (double) (this.fGravityY / 100.0);
        return this.fGravityY;
    }

    /**
     * calcFofFriction
     * equation for to calculate the force of friction
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcFofFriction(Box box) {
        this.fOfFriction = box.getMass() * this.gravity * this.mu * (Math.cos(Math.toRadians(box.getAngle())));
        return this.fOfFriction;
    }

    /**
     * calcFofFrictionRounded
     * equation for to calculate the force of friction rounded
     *
     * @param Box box takes in a box to get values from
     * @return double returns a the answer as a double rounded to 2 decimal places
     */
    public double calcFofFrictionRounded() {
        this.fOfFriction = (int) (this.fOfFriction * 100.0);
        this.fOfFriction = (double) (this.fOfFriction / 100.0);
        return this.fOfFriction;
    }

    /**
     * calcAcceleration
     * equation to calculate the acceleration of the system to display
     *
     * @param Box box takes in a box to get values from
     * @param Box box1 takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcAcceleration(Box box1, Box box) {
        //calculates the acceleration when the angle is 0
        this.acceleration = calcFofGravity(box) - calcFofFriction(box1);
        this.acceleration = this.acceleration / (box.getMass() + box1.getMass());
        if (this.acceleration > 1) {
            return this.acceleration;
        }
        //rounds acceleration to 1 because thread.sleep don't accept decimal values
        else if (this.acceleration < 1 && this.acceleration > 0) {
            return 1;
        }
        //returns zero if acceleration is negative to show that system is not moving
        else {
            return 0;
        }
    }

    /**
     * realAcceleration
     * equation to calculate the acceleration of the system
     *
     * @param Box box takes in a box to get values from
     * @param Box box1 takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double realAcceleration(Box box1, Box box) {
        this.acceleration = calcFofGravity(box) - calcFofFriction(box1);
        this.acceleration = this.acceleration / (box.getMass() + box1.getMass());
        return this.acceleration;
    }

    /**
     * calcAccelerationUpHill
     * equation to calculate the acceleration of the system when its going uphill
     *
     * @param Box box takes in a box to get values from
     * @param Box box1 takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcAccelUpHill(Box box1, Box box) {
        //calculates the acceleration if the system is going uphill
        this.acceleration = calcFofGravity(box) - calcGravityX(box1) - calcFofFriction(box1);
        this.acceleration = this.acceleration / (box.getMass() + box1.getMass());
        if (this.acceleration > 1) {
            return this.acceleration;
        }
        //rounds acceleration to 1 because thread.sleep don't accept decimal values
        else if (this.acceleration < 1 && this.acceleration > 0) {
            return 1;
        }
        //returns zero if acceleration is negative to show that system is not moving
        else {
            return 0;
        }
    }

    /**
     * calcAccelerationDownHill
     * equation to calculate the acceleration of the system when its going downhill
     *
     * @param Box box takes in a box to get values from
     * @param Box box1 takes in a box to get values from
     * @return double returns a the answer as a double
     */
    public double calcAccelDownHill(Box box1, Box box) {
        //calculates the acceleration if the system is going downhill
        this.acceleration = calcGravityX(box1) - calcFofGravity(box) - calcFofFriction(box1);
        this.acceleration = this.acceleration / (box.getMass() + box1.getMass());
        if (this.acceleration > 1) {
            return this.acceleration;
        }
        //rounds acceleration to 1 because thread.sleep don't accept decimal values
        else if (this.acceleration < 1 && this.acceleration > 0) {
            return 1;
        }
        //returns zero if acceleration is negative to show that system is not moving
        else {
            return 0;
        }
    }

    /**
     * calcAccelerationRounded
     * equation to calculate the acceleration of the system rounded
     *
     * @param Box box takes in a box to get values from
     * @param Box box1 takes in a box to get values from
     * @return double returns a the answer as a double rounded to 2 decimal places
     */
    public double calcAccelRounded() {
        this.acceleration = (int) (this.acceleration * (100.0));
        this.acceleration = (double) (this.acceleration / 100.0);
        return this.acceleration;
    }

    /**
     * setMu
     * sets the double value of the mu constant
     *
     * @param double mu sets the double value that was passed
     */
    public void setMu(double mu) {
        this.mu = mu;
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
     * setAccel
     * sets the acceleration value with the passed double
     *
     * @param double a sets the double value stored in this variable
     */
    public void setAccel(double a) {
        this.acceleration = a;
    }

    /**
     * reset
     * resets all of the different variables to zero
     */
    public void reset() {
        //resets all the values to zero
        this.fOfFriction = 0;
        this.fOfGravity = 0;
        this.fGravityX = 0;
        this.acceleration = 0;
    }
}