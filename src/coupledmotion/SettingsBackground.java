package coupledmotion;
/** SettingsBackground
 * Background view of the settings screen
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.*;

public class SettingsBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private ImageIcon paper; //the image icon of paper
    private Image imgpaper; //the paper image

    /**
     * SettingsBackground
     * Constructor for the SettingsBackground class
     */
    public SettingsBackground() {
        super();
        this.paper = new ImageIcon(getClass().getResource("/icons/popup.png")); //get the image from file
        this.imgpaper = this.paper.getImage(); //store the image
    }

    /**
     * paintComponent
     * draws different objects
     *
     * @param Graphics g class needed to draw different objects
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paint the image
        g.drawImage(imgpaper, 0, 0, 150, 180, null);
    }
}