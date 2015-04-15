package coupledmotion;
/** InfoButton
 * Button that invokes the information screen
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import java.awt.event.*;

public class InfoButton implements MouseListener {

    private TheGUI gui; //the view

    /**
     * InfoButton
     * Constructor for the InfoButton class
     *
     * @param TheGUI gui gives access to the objects in the TheGUI class which can be controled
     */
    public InfoButton(TheGUI gui) {
        //stores the objects passed from this constructor
        this.gui = gui;
    }

    /**
     * mouseClicked
     *
     * @param MouseEvent arg0 listens for the mouse event
     */
    public void mouseClicked(MouseEvent arg0) {
    }

    /**
     * mouseEntered
     *
     * @param MouseEvent arg0 listens for the mouse event
     */
    public void mouseEntered(MouseEvent arg0) {
        this.gui.putOverlay(); //makes the information overlay show up
    }

    /**
     * mouseExited
     *
     * @param MouseEvent arg0 listens for the mouse event
     */
    public void mouseExited(MouseEvent arg0) {
        this.gui.removeOverlay(); //makes the information overlay disappear
    }

    /**
     * mousePressed
     *
     * @param MouseEvent arg0 listens for the mouse event
     */
    public void mousePressed(MouseEvent arg0) {
    }

    /**
     * mouseReleased
     *
     * @param MouseEvent arg0 listens for the mouse event
     */
    public void mouseReleased(MouseEvent arg0) {
    }
}