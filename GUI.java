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
    private Container contentPane;
    private JLabel run;
    private JLabel max;
    private JLabel waiting;
    private JLabel inCar;
    private JLabel total;
    private JLabel tick;
    private JLabel average;
    private JLabel elevator;
    private JFrame frame;
    private JTextArea text;
    private JPanel grid;
    private Controller control;
    private JScrollPane sp;
    private JProgressBar progress;
    private JProgressBar runs;
    private JOptionPane option;
    private int numRun;
    private Graphics g;
    private ECar ecar;
    private int maxFloor;
    private int elevators;
    private int currentPassengers;
    private int currentTick;
    private int car;
    private double avg;
    //     private GridCanvas gridCanvas;

    public GUI(Controller c)
    {
        control = c;
        makeFrame();
        elevators = 1;
        currentTick = 0;
    }

    public void makeFrame()
    {
        Font arial = new Font("Arial", Font.PLAIN, 13);
        frame = new JFrame("Elevator Simulator");
        frame.setResizable(false);
        createMenuBar(frame);

        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(2,1));

        /* Constant Panel */
        JPanel constant = new JPanel();
        max = new JLabel("  Number of Floors: " + getMax());
        constant.add(max);
        elevator = new JLabel("  Number of elevators active: " + elevators);
        constant.add(elevator);
        contentPane.add(constant, BorderLayout.NORTH);

        /* Stats Panel */
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(10,1));
        stats.setBorder(BorderFactory.createTitledBorder("Current Statistics"));
        stats.setFont(arial);
        
        inCar = new JLabel("  Passengers in the elevator: ");
        inCar.setForeground(Color.blue);
        stats.add(inCar);

        total = new JLabel("  Current number of passengers: " + getPassengers());
        total.setForeground(Color.blue);
        stats.add(total);
        
        tick = new JLabel("  Tick: " + currentTick);
        tick.setForeground(Color.blue);
        stats.add(tick);

        average = new JLabel("  Average waiting time: ");
        average.setForeground(Color.blue);
        stats.add(average);

        west.add(stats);
        /* Text Box */
        text = new JTextArea(10, 30);
        JScrollPane sp = new JScrollPane(text);
        text.setVisible(true);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue));
        text.setFont(arial);
        text.setForeground(Color.RED);
        
        sp.setBounds(1, 1, 200, 450);
        west.add(sp);

        contentPane.add(west, BorderLayout.WEST);

        JPanel elevator = new JPanel();
        progress = new JProgressBar(1,15);
        progress.setStringPainted(true);
        progress.setValue(0);
        progress.setOrientation(SwingConstants.VERTICAL);
        progress.setFont(arial);
        progress.setSize(300, 300);
        elevator.add(progress);
        contentPane.add(elevator, BorderLayout.EAST);

        /* Grid */
        JPanel grid = new JPanel();
        grid.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        Canvas gridCanvas = new Canvas();
        gridCanvas = new GridCanvas(90, 500, control.getMaxFloor(), elevators); // (width, height, floors, elevators)
        grid.add(gridCanvas);
        contentPane.add(grid, BorderLayout.CENTER);

        /* Run Progress Bar */
        runs = new JProgressBar();
        runs.setStringPainted(true);
        runs.setFont(arial);
        runs.setValue(0);
        contentPane.add(runs, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Method called in controller to update the GUI
     */
    public void update()
    {
        max.setText("  Number of Floors: " + getMax()); //mf == maxfloor
        elevator.setText("  Number of elevators active: " + findEcar());
        total.setText("  Current number of passengers: " + getPassengers());
        tick.setText("  Tick: " + currentTick);
        average.setText("  Average waiting time: " + getAverage());
        inCar.setText("  Passengers in the elevator: " + getInCar());
    }

    public void setMax(int max)
    {
        maxFloor = max;
    }

    public int getMax()
    {
        return maxFloor;
    }

    public void setEcar(int e)
    {
        elevators = e;
    }

    public int findEcar()
    {
        return elevators;
    }

    public void setPassengers(int u, int d)
    {
        currentPassengers = u + d;
    }

    public int getPassengers()
    {
        return currentPassengers;
    }

     public void setAverage(double a)
    {
        avg = a;
    }
    
    public double getAverage()
    {
        return avg;
    }
    
     public void setInCar(int i)
    {
        car = i;
    }
    
    public int getInCar()
    {
        return car;
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
        runItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, SHORTCUT_MASK));
        runItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { runButton();  }
            });   
        menu.add(runItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quit(); }
            });
        menu.add(quitItem);

        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);

        JMenuItem about = new JMenuItem("About Elevator Simulator...");
        about.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { about(); }
            });   
        helpMenu.add(about);
    }    

    public void runButton()
    {
        //appendText("Run was called");
        String r = JOptionPane.showInputDialog(frame, "Input number of runs", "Run", JOptionPane.QUESTION_MESSAGE);
        numRun = Integer.parseInt(r);
        appendText(r + " " + numRun);
        control.run(numRun);
    }

    /**
     * Displays about popup
     */
    private void about()
    {
        JOptionPane.showMessageDialog(frame, 
            "Elevator Simulation Project for CS216",
            "About Elevator Simulator", 
            JOptionPane.INFORMATION_MESSAGE );
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
        currentTick = num;
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

    class GridCanvas extends Canvas
    {
        int width, height;
        int rows, columns;
        int rowHt, rowWid;

        GridCanvas(int w, int h, int r, int c)
        {
            width = w;
            height = h;
            rows = r;
            columns = c;
            setSize(width, height);
        }

        public void paint(Graphics g)
        {
            int i;
            width = getSize().width;
            height = getSize().height;

            rowHt = height / rows;
            for (i=0; i<=rows; i++)
                g.drawLine(0, i*rowHt, width, i*rowHt);

            rowWid = width/ columns;
            for(i=0; i < columns; i++)
                g.drawLine(i*rowWid, 0, i*rowWid, height);
        }

        // //         public void drawCar(int x)
        // //         {
        // //             g.setColor(Color.blue);
        // //             g.fillRect(4, 0, x*rowWid, height);
        // //         }

    }
}
