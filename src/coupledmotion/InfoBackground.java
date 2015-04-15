package coupledmotion;
/** InfoBackground
 * Background view of the information screen
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.*;
import java.awt.*;

public class InfoBackground extends JComponent {

    private static final long serialVersionUID = 1L;
    private ImageIcon infobg; //the icon image of the overlay
    private Image imginfo; //the overlay image

    /**
     * InfoBackground
     * Constructor for the InfoBackground class
     */
    public InfoBackground() {
        super();
        this.infobg = new ImageIcon(getClass().getResource("/icons/overlay.png")); //get the image from file
        this.imginfo = this.infobg.getImage(); //store the image
    }

    /**
     * paintComponent
     * draws different objects
     *
     * @param Graphics g class needed to draw different objects
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paint the image on the whole screen
        g.drawImage(imginfo, 0, 0, 520, 480, null);
    }
}
