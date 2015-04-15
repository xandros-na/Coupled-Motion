package coupledmotion;
/** TheGUI
 * the view of the program which contstructs all the different pieces together into one
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;

public class TheGUI extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private JButton setItButton = new JButton(new ImageIcon(getClass().getResource("/icons/loginbttn.png"))); //makes the "set" button in the control panel
    private JButton infoButton = new JButton(new ImageIcon(getClass().getResource("/icons/infobttn.png"))); //makes the little "i" button
    private Box box = new Box(); //the model
    private Box box1 = new Box(); //the model
    private ObjectGUI obj = new ObjectGUI(box, box1); //instantiate the view for the boxes
    private SettingsBackground popup; //the control panel background
    private InfoBackground overlay; //the information background(instructions)
    private boolean isVisible = true; //variable if the components are visible or not
    private JTextField t1 = new JTextField("mass 1", 5); //textfield for mass 1
    private JTextField t2 = new JTextField("mass 2", 5); //textfield for mass 2
    private JTextField t3 = new JTextField("angle", 5); //textfield for angle

    /**
     * TheGUI
     * Constructor for the TheGUI class
     */
    public TheGUI() {
        super();
        //create the popup background for the settings and overlay background for the instructions
        this.popup = new SettingsBackground();
        this.overlay = new InfoBackground();

        this.layoutView();
        this.registerControls();
    }

    /**
     * layoutView
     * draws all the different components into place
     */
    public void layoutView() {
        //set the properties of the different objects e.g. where it is on the screen, is there a border, is the background painted etc.
        this.obj.addWrench().setBounds(430, 410, 72, 60);
        this.obj.addWrench().setBorderPainted(false);
        this.obj.addWrench().setContentAreaFilled(false);

        this.overlay.setBounds(0, 0, 520, 480);

        this.infoButton.setBounds(480, 50, 20, 20);
        this.infoButton.setBorderPainted(false);
        this.infoButton.setContentAreaFilled(false);

        this.setItButton.setBounds(213, 145, 80, 46);
        this.setItButton.setBorderPainted(false);
        this.setItButton.setContentAreaFilled(false);

        this.obj.addPlay().setBounds(0, 240, 345, 240);
        this.obj.addPlay().setOpaque(false);
        this.obj.addPlay().setEnabled(false);

        this.obj.addSlider().setBounds(30, 10, 200, 20);
        this.obj.addGSlider().setBounds(290, 10, 200, 20);
        this.obj.addGSlider().setOpaque(false);
        this.obj.addSlider().setOpaque(false);

        this.t1.setBounds(228, 65, 50, 20);
        this.t2.setBounds(228, 95, 50, 20);
        this.t3.setBounds(228, 125, 50, 20);

        this.obj.setBounds(0, 0, 508, 500);
        this.popup.setBounds(180, 41, 150, 180);

        //added all the needec objects such as: wrenchbutton, overlay, infoButton etc. to TheGUI class which is a layered pane
        this.add(obj.addWrench());
        this.add(infoButton);
        this.add(setItButton);
        this.add(this.popup);
        this.add(this.obj.addSlider());
        this.add(this.obj.addGSlider());
        this.add(this.obj);
        this.add(this.obj.addPlay());
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(overlay);
    }

    /**
     * registerControls
     * connects these controlers to this class
     */
    public void registerControls() {
        //adds listeners to settingsbutton, okbutton and infoButton
        SettingsButton sb = new SettingsButton(this, this.obj.addWrench());
        this.obj.addWrench().addActionListener(sb);

        OKButton okb = new OKButton(this.box, this.box1, this.t1, this.t2, this.t3, this.obj.addPlay(), this, this.obj);
        this.setItButton.addActionListener(okb);

        InfoButton infob = new InfoButton(this);
        this.infoButton.addMouseListener(infob);
    }

    /**
     * makeInvisible
     * hides or reveals the settings popup
     */
    public void makeInvisible() {
        if (this.isVisible) {
            //moves the popup background, setItButton, and the textfields at the back of the layer
            this.moveToBack(popup);
            this.moveToBack(t1);
            this.moveToBack(t2);
            this.moveToBack(t3);
            this.moveToBack(setItButton);
            this.isVisible = false;
        } else {
            //moves the popup background, setItButton, and the textfields at the front of the layer
            this.moveToFront(popup);
            this.moveToFront(t1);
            this.moveToFront(t2);
            this.moveToFront(t3);
            this.moveToFront(setItButton);
            this.isVisible = true;
        }
    }

    /**
     * putOverlay
     * reveals the overlay image
     */
    public void putOverlay() {
        if (this.isVisible) {
            //moves the textfields to the front then move the overlay(instructions) to the front
            this.moveToFront(t1);
            this.moveToFront(t2);
            this.moveToFront(t3);
        }
        this.moveToFront(overlay);
    }

    /**
     * removeOverlay
     * hides the overlay image
     */
    public void removeOverlay() {
        if (this.isVisible) {
            //moves the textfields to the front then move the overlay(instructions) to the back
            this.moveToFront(t1);
            this.moveToFront(t2);
            this.moveToFront(t3);
        }
        this.moveToBack(overlay);
    }
}
