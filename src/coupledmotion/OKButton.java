package coupledmotion;
/** OkButton
 * Button which sets different values and redraws the screen appropriatly
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.event.*;

public class OKButton implements ActionListener {
    private Box box, box1; //the models
    private JTextField t1, t2, t3; //the textfields for masses and angle
    private JButton playbutton; //the play button
    private TheGUI gui;  // the layered pane the holds the view
    private ObjectGUI obj; //the view

    public OKButton(Box box, Box box1, JTextField t1, JTextField t2, JTextField t3, JButton ib, TheGUI gui, ObjectGUI obj) {
//stores the objects passed from this constructor 
        this.box = box;
        this.box1 = box1;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.gui = gui;
        this.playbutton = ib;
        this.obj = obj;
    }

    public void actionPerformed(ActionEvent e) {
        //stores the values from the textfields
        String mass = this.t1.getText();
        String mass1 = this.t2.getText();
        String angle = this.t3.getText();

        //error handling method
        try {
            //parse the strings to double
            double i = Double.parseDouble(mass);
            double j = Double.parseDouble(mass1);
            double k = Double.parseDouble(angle);

            //sets the mass and the angle to the model
            this.box.setMass(i);
            this.box1.setMass(j);
            this.box.setAngle(k);

            this.gui.makeInvisible(); //make the control panel disappear
            this.obj.repaint(); //repaints the view when angle and masses are changed
            this.playbutton.setEnabled(true); //enables the play button
        } catch (NumberFormatException ie) {
        }
    }
}