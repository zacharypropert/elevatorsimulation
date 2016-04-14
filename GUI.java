import java.awt.*;
import java.awt.Paint;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * GUI for elevator simulation
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI
{
    private JFrame frame;
    private JTextArea text;
    private Controller control;
    private JScrollPane sp;
    private JLabel run;
    private JProgressBar progress;
    private JProgressBar runs;

    public GUI()
    {
        makeFrame();
    }

    public void makeFrame()
    {
        Font arial = new Font("Arial", Font.BOLD, 14);
        frame = new JFrame("Elevator Simulator");
        createMenuBar(frame);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        //JPanel stats = new JPanel();
        //stats.add(run);

        //   stats. ("Average Wait Time: ", statistics.getAvgWait());

        text = new JTextArea(10, 30);
        JScrollPane sp = new JScrollPane(text);
        text.setVisible(true);
        text.setLineWrap(true);
        text.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
        text.setFont(arial);
        text.setForeground(Color.RED);
        sp.setBounds(1, 1, 250, 400);
        contentPane.add(sp, BorderLayout.WEST);
//         try {                
//             BufferedImage myPicture = ImageIO.read(new File("grid.png"));
//             JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//             contentPane.add(picLabel);
//         } catch (IOException ex) {
//             // handle exception...
//         }
        JPanel elevator = new JPanel();
        //bar.setSize(20, 60);
        progress = new JProgressBar(1,15);
        progress.setStringPainted(true);
        progress.setValue(0);
        progress.setOrientation(SwingConstants.VERTICAL);
     //   progress.setBackground(Color.BLACK);
     //   progress.setForeground(Color.WHITE);
        progress.setFont(arial);
        elevator.add(progress);
        contentPane.add(elevator, BorderLayout.CENTER);
        
        //JPanel bottom = new JPanel();
        runs = new JProgressBar();
        runs.setStringPainted(true);
        runs.setFont(arial);
        runs.setValue(0);
        //bottom.add(runs, BorderLayout.WEST);
        
        
        contentPane.add(runs, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);

    }

    public void createMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu = new JMenu("Run");
        menubar.add(menu);

        JMenuItem runItem = new JMenuItem("Run");
        runItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        runItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { appendText("Run was called"); }
            });
        menu.add(runItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quit(); }
            });
        menu.add(quitItem);
    }

    public void setProgress(int num, int floor)
    {        
        progress.setMaximum(floor);
        progress.setValue(num); 
        progress.setString("Floor: " + num);
    }
    
    public void setRun(int num, int run)
    {        
        runs.setMaximum(run);
        runs.setValue(num); 
        runs.setString("Run: " + num);
    }

    /**
     * Adds text to the text area
     */
    public void appendText(String t)
    {
        text.append(t+"\n");
    }

    /**
     * Quit function: quit the simulator.
     */
    private void quit()
    {
        System.exit(0);
    }
}
