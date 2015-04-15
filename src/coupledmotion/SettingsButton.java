package coupledmotion;
/** PlayButton
 * Button which opens the settings screen for different inputs
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.event.*;

public class SettingsButton implements ActionListener {

    private TheGUI gui; //the main gui

    /**
     * SettingsButton
     * Constructor for the SettingsButton class
     *
     * @param TheGUI  gui gives access to the objects in the TheGUI class which can be controled
     * @param JButton sb takes in a JButton
     */
    public SettingsButton(TheGUI gui, JButton sb) {
        //stores the objects passed from this constructor
        this.gui = gui;
    }

    /**
     * actionPerformed
     * executes code after the event has occured
     *
     * @param ActionEvent e listents for the event
     */
    public void actionPerformed(ActionEvent e) {
        this.gui.makeInvisible(); //makes the popup control panel appear or disappear
    }
}