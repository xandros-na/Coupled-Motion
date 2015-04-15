package coupledmotion; /** ObjectGUI
 * The subview of different images and components all placed together
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.*;

public class ObjectGUI extends JComponent implements Runnable {

    //instantiate all the needed objects and variables in this class
    private static final long serialVersionUID = 1L;
    private Box box = new Box(); //the model
    private Box box1 = new Box(); //the model
    private Force force = new Force(); //the force calculator
    private int x = 240; //initial x value
    private int y = 190; //initial y value
    private JSlider friction, gravity; //instantiate the sliders
    private ImageIcon boxesII, topTableII, stringHII, stringVII, pulleyII, fGvectorII, fGvectorIII, fFvectorII, backgroundII, legsI, legsII, sliderBGII; //icon images of images to be used
    private int acceleration; //storse acceleration
    private int velocity = 400; //initial velocity
    private int width, height, width2, height2, width3, height3; //stores dimensions for the string and table top
    private Image boxes, topTable, stringH, stringV, fGvector, fGvector1, fFvector, background, sliderBG, pulley, rightleg, leftleg; //the images
    private String fOfGravityBox1, fOfFriction, muValue, accel, gValue, massOfBox, massOfBox1, fofGravityY, fofGravityX; //stores values of forces, mass, acceleration, mu, gravity
    private JButton play = new JButton(); //make a play button
    private JButton wrenchButton = new JButton(new ImageIcon(getClass().getResource("/icons/settingsbttn.png"))); //make a wrenchbutton
    private PlayButton pb; //playbutton object to get counters
    private boolean rightOrLeft; //true if system is uphill or angle is zero

    /**
     * ObjectGUI
     * Constructor for the ObjectGUI class
     *
     * @param Box box the box object which this class is controling values for
     * @param Box box1 the box object which this class is controling values for
     */
    public ObjectGUI(Box box, Box box1) {
        super();
        this.box = box;
        this.box1 = box1;

        //sets the imageicons their respective files
        this.boxesII = new ImageIcon(getClass().getResource("/icons/box.png"));
        this.topTableII = new ImageIcon(getClass().getResource("/icons/tabletop.png"));
        this.stringHII = new ImageIcon(getClass().getResource("/icons/StringV.png"));
        this.pulleyII = new ImageIcon(getClass().getResource("/icons/pulley.png"));
        this.stringVII = new ImageIcon(getClass().getResource("/icons/StringH.png"));
        this.fGvectorII = new ImageIcon(getClass().getResource("/icons/vector.png"));
        this.fGvectorIII = new ImageIcon(getClass().getResource("/icons/vector1.png"));
        this.fFvectorII = new ImageIcon(getClass().getResource("/icons/vector2.png"));
        this.backgroundII = new ImageIcon(getClass().getResource("/icons/background.png"));
        this.legsII = new ImageIcon(getClass().getResource("/icons/rightleg.png"));
        this.legsI = new ImageIcon(getClass().getResource("/icons/leftleg.png"));
        this.sliderBGII = new ImageIcon(getClass().getResource("/icons/controlsbg.png"));

        this.setPreferredSize(new Dimension(800, 600));
        this.box.setGUI(this); //sets the box's view as this
        this.registerControls();

        //gets the images from the imageicons
        this.boxes = this.boxesII.getImage();
        this.topTable = this.topTableII.getImage();
        this.stringH = this.stringHII.getImage();
        this.stringV = this.stringVII.getImage();
        this.fGvector = this.fGvectorII.getImage();
        this.fGvector1 = this.fGvectorIII.getImage();
        this.fFvector = this.fFvectorII.getImage();
        this.background = this.backgroundII.getImage();
        this.sliderBG = this.sliderBGII.getImage();
        this.pulley = this.pulleyII.getImage();
        this.rightleg = this.legsII.getImage();
        this.leftleg = this.legsI.getImage();

        //initial values for the width and height of the strings and the tabletop
        this.width = 150;
        this.height = stringH.getHeight(null);
        this.width2 = stringV.getWidth(null);
        this.height2 = 100;
        this.width3 = topTable.getWidth(null);
        this.height3 = topTable.getHeight(null);

        this.update();
    }

    /**
     * paintComponent
     * draws different objects
     *
     * @param Graphics g class needed to draw different objects
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //made a graphics g2 object set the font
        Graphics2D g2 = (Graphics2D) g;
        Font font1 = new Font("Helvetica", Font.BOLD, 13);
        g.setFont(font1);

        //improve the rendering of when image is rotated(credit to google)
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //calculate the force of gravity in x and y components so that it will be rounded off in here at the start
        this.force.calcGravityY(box);
        this.force.calcGravityX(box);

        //store the different values that are calculated and parse them to a string
        this.fOfGravityBox1 = Double.toString(this.force.calcFofGravityRounded());
        this.fOfFriction = Double.toString(this.force.calcFofFrictionRounded());
        this.muValue = Double.toString(this.force.getMu());
        this.accel = Double.toString(this.force.calcAccelRounded());
        this.gValue = Double.toString(this.force.getGravity());
        this.massOfBox = Double.toString(this.box.getMass());
        this.massOfBox1 = Double.toString(this.box1.getMass());
        this.fofGravityY = Double.toString(this.force.calcGravityYRounded());
        this.fofGravityX = Double.toString(this.force.calcGravityXRounded());

        //draws all the needed images, and text on the screen
        g.drawImage(background, 0, 0, 508, 500, null);
        g.drawImage(stringV, 402, 231, this.width2, this.height2, null);
        g.drawImage(fGvector, 384, this.y + 96, null);
        g.drawImage(boxes, 355, this.y + 56, null);
        g.drawImage(pulley, 380, 191, null);
        g.drawString("Fg = " + this.fOfGravityBox1 + " N", 420, this.y + 160);
        g.drawString("a = " + this.accel + " m/s^2", 350, 191);
        g.drawString(this.massOfBox1 + " kg", 387, this.y + 96);
        g.drawImage(rightleg, 305, 261 + (int) (this.box.getAngle() / .564), null);
        g.drawImage(leftleg, 190, 261 + (int) (this.box.getAngle() / .259), null);
        g.drawString("Mu = " + this.muValue, 10, 111);
        g.drawString("Gravity = " + this.gValue + " m/s^2", 10, 131);
        g.drawImage(sliderBG, 0, 0, 508, 43, null);

        //adjusts the rotation of the images and string below this code based on the angle
        g2.rotate(Math.toRadians((this.box.getAngle() * (-1))), 380 + 25, 191 + 25);

        g.drawImage(boxes, this.x - 200, 180, null);
        g.drawImage(topTable, -150, 231, this.width3 - (int) (this.box.getAngle() / .625), this.height3, null);
        g.drawImage(stringH, this.x - 115, 211, this.width + 110, this.height, null);
        g.drawString(this.massOfBox + " kg", this.x - 175, 221);

        g.drawImage(fGvector, this.x - 170, 222, null);
        g.drawString("Fgy = " + fofGravityY + " N", this.x - 170, 292);

        //draws Fgx component and the vector connected to the box
        if (this.force.calcGravityX(box) > 0) {
            g.drawImage(fGvector1, this.x - 215, 175, null);
            g.drawString("Fgx = " + fofGravityX + " N", this.x - 280, 200);
        }

        //if the system is going up hill draw the friction vector and the friction value on the left part of the box
        if (!this.rightOrLeft) {
            if (this.force.calcGravityX(box) > (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box)) || this.force.calcGravityX(box) < (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box))) {
                g.drawImage(fFvector, this.x - 120, 180, null);
                g.drawString("Ff = " + this.fOfFriction + " N", this.x - 100, 200);
            }
        }
        //if the system is going down hill draw the friction vector and the friction value  on the right part of the box
        else {
            if (this.force.calcFofGravity(box1) > (this.force.calcFofFriction(box) + this.force.calcGravityX(box)) || this.force.calcFofGravity(box1) < (this.force.calcFofFriction(box) + this.force.calcGravityX(box))) {
                g.drawImage(fGvector1, this.x - 215, 175, null);
                g.drawString("Ff = " + this.fOfFriction + " N", this.x - 235, 255);
            }
        }
    }

    /**
     * run
     * the code which a thread will execute
     */
    public void run() {
        //runs the thread depending on the angle
        if (this.box.getAngle() == 0) {
            //runs the thread while the counter from the playbutton is divisible by 2 and while the y component of the box hanging is less than 300
            while (this.pb.getCounter() % 2 == 0 && this.y < 300) {
                this.rightOrLeft = true;
                this.update();
            }
        } else {
            ////runs the thread while the counter from the playbutton is divisible by 2
            while (this.pb.getCounter() % 2 == 0) {
                //if system is going uphill set the boolean to true so that the paint method will repaint only the components under this condition
                if (this.force.calcFofGravity(box1) > (this.force.calcFofFriction(box) + this.force.calcGravityX(box))) {
                    this.rightOrLeft = true;
                    this.update();
                    if (this.getYcomp() == 300) {
                        break; //stops the thread when the y component of the box hanging reaches 300
                    }
                }
                //if system is going down hill set the boolean to false so that the paint method will repaint only the components under this condition
                else if (this.force.calcGravityX(box) > (this.force.calcFofGravity(box1) + this.force.calcFofFriction(box))) {
                    this.rightOrLeft = false;
                    this.update();
                    if (this.getXcomp() == 300) {
                        break; //stops the thread when the x component of the box on the table reaches 300
                    }
                }
            }
        }
    }

    /**
     * update
     * updates the view for the changes that were made by the thread
     */
    public void update() {
        //adds or subtract 1 pixel to the x,y components and the width of horizontal string and height of the vertical string
        this.y = this.y + this.box.getAccel();
        this.x = this.x + this.box.getAccel();
        this.width = this.width - this.box.getAccel();
        this.height2 = this.height2 + this.box.getAccel();

        this.repaint();
        //sleep method
        try {
            //make sure velocity is always positive because negative gives error
            if (this.velocity > 0 && this.velocity > this.acceleration) {
                Thread.currentThread();
                Thread.sleep(this.velocity -= this.acceleration); //subtracts the acceleration to current velocity so system looks accelerating
            } else if (this.velocity < 0) {
                int a;
                a = this.velocity % this.acceleration; //find the remainder (the last positive value before velocity goes negative) to use as sleep value
                Thread.sleep(a);
            }
        } catch (InterruptedException ie) {
        }
    }

    /**
     * makeSlider
     * adds the mu slider which has been modified to this class
     */
    public void makeSlider() {
        //changes the look and feel of the slider
        Icon icon = new ImageIcon(getClass().getResource("/icons/slider.png"));
        UIDefaults defaults = UIManager.getDefaults();
        defaults.put("Slider.horizontalThumbIcon", icon);
        defaults.put("Slider.horizontalSize", (new Dimension(100, 100)));

        //makes a slider based on these default properties
        this.friction = new JSlider();
        this.friction.setMinimum(0);
        this.friction.setMaximum(300);
        this.friction.setValue(0);
    }

    /**
     * makeGSlider
     * adds the gravity slider which has been modified to this class
     */
    public void makeGSlider() {
        //changes the look and feel of the slider
        Icon icon = new ImageIcon(getClass().getResource("/icons/slider.png"));
        UIDefaults defaults = UIManager.getDefaults();
        defaults.put("Slider.horizontalThumbIcon", icon);

        //makes a slider based on these default properties
        this.gravity = new JSlider();
        this.gravity.setMinimum(0);
        this.gravity.setMaximum(1860);
        this.gravity.setValue(980);
    }

    public JSlider addSlider() {
        return this.friction;
    }

    public JSlider addGSlider() {
        return this.gravity;
    }

    public JButton addPlay() {
        return this.play;
    }

    public JButton addWrench() {
        return this.wrenchButton;
    }

    /**
     * registerControls
     * connects these controlers to this class
     */
    private void registerControls() {
        //add listeners to play button, mu and gravity sliders
        this.pb = new PlayButton(this.box, this.box1, this.play, this, this.force, this.wrenchButton);
        this.play.addActionListener(pb);

        //stores the new sliders with new look inside the JButton objects
        this.makeSlider();
        this.friction = this.addSlider();
        this.makeGSlider();
        this.gravity = this.addGSlider();

        MuSlider ms = new MuSlider(this.force, this.friction, this.box, this.box1, this);
        this.friction.addMouseListener(ms);

        GSlider gs = new GSlider(this.force, this.gravity, this.box, this.box1, this);
        this.gravity.addMouseListener(gs);
    }

    /**
     * reset
     * reset the modified values back to default
     */
    public void reset() {
        //resets the default values
        this.x = 240;
        this.y = 190;
        this.velocity = 400;
        this.acceleration = 0;
        this.force.setGravity(9.8);
        this.force.setMu(0);
        this.friction.setValue(0);
        this.gravity.setValue(980);
        this.width = stringH.getWidth(null) - 250;
        this.height = stringH.getHeight(null);
        this.width2 = stringV.getWidth(null);
        this.height2 = stringV.getHeight(null) - 200;
        this.box.setAngle(0);
    }

    /**
     * setAcceleration
     * set the acceleration variable to the passed integer
     *
     * @param int a the int value stored in this is used to set the value of acceleration
     */
    public void setAcceleration(int a) {
        this.acceleration = a;
    }

    /**
     * setVelocity
     * set the velocity variable to the passed integer
     *
     * @param int a the int value stored in this is used to set the value of velocity
     */
    public void setVelocity(int v) {
        this.velocity = v;
    }

    /**
     * getVelocity
     * gets the value stored in velocity
     *
     * @return returns the int value stored in the variable velocity
     */
    public int getVelocity() {
        return this.velocity;
    }

    /**
     * getYcomp
     * gets the value stored in the variable y
     *
     * @return returns the int value stored in the variable y
     */
    public int getYcomp() {
        return this.y;
    }

    /**
     * getVelocity
     * gets the value stored in the variable x
     *
     * @return returns the int value stored in the variable x
     */
    public int getXcomp() {
        return this.x;
    }

    /**
     * setXandYupHill
     * sets the different values required for when the system is moving up hill
     */
    public void setXandYupHill() {
        //method to reduce stuttering when going uphill or when system has 0 angle
        this.y = this.y - 1;
        this.x = this.x - 1;
        this.width = this.width + this.box.getAccel();
        this.height2 = this.height2 - this.box.getAccel();
    }

    /**
     * setXandYdownHill
     * sets the different values required for when the system is moving down hill
     */
    public void setXandYdownHill() {
        //method to reduce stuttering when going downhill
        this.y = this.y + 1;
        this.x = this.x + 1;
        this.width = this.width + this.box.getAccel();
        this.height2 = this.height2 - this.box.getAccel();
    }

    /**
     * setIfAngled
     * sets the different values required for when the system is at an angle
     *
     * @param int h the regulated height of certain objects in this class
     * @param int y the regulated y-position of certain objects in this class
     * @param int w the regulated width of certain objects in this class
     * @param int x the regulated x-position of certain objects in this class
     */
    public void setIfAngled(int h, int y, int w, int x) {
        //adjusts the box's x and y properties and the horizontal string's properties depending on the angle
        this.height2 = h;
        this.y = y;
        this.width = w;
        this.x = x;
    }
}