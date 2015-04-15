package coupledmotion;
/** Main Class
 * location of the main method
 * Modified June 13, 2012
 * @author D.Miguel & C.Mannem
 */

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        //instantiate TheGUI as the main layer of the frame
        TheGUI main = new TheGUI();

        //create the frame and its properties and added TheGUI in the frame
        JFrame f = new JFrame();
        f.setContentPane(main);
        f.setTitle("Coupled Motion App");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(400, 150);
        f.setSize(508, 500);
        f.setResizable(false);
        f.setVisible(true);
    }
}