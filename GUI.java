
/**
 * test GUI showing progress bar 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
public class GUI extends JFrame
{
    // instance variables - replace the example below with your own
    private JProgressBar progressBar;
    /**
     * Constructor for objects of class GUI
     */
    public GUI(int maxFloor) 
    {
        super("Steve  Car");

        JPanel contentPane = (JPanel)this.getContentPane();
        //Where the GUI is constructed:
        progressBar = new JProgressBar(0, maxFloor  /*max size */);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        
        contentPane.add(progressBar);
        
        this.pack();
        this.setVisible(true);
    }
    
    /**
     *    update the progress bar for current floor
     */
    public void setFloorNum(int num)
    {
        progressBar.setValue(num);
        progressBar.setString(""+num);
        
    }

}
