import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * GUI for elevator simulation, displays current statistics, and output screen, 
 * displays the elevator car as it moves up and down.
 * 
 */
public class GUI
{
    private JFrame frame;
    private Container contentPane;
    private JLabel run;
    private JLabel max;
    private JLabel waiting;
    private JLabel inCar;
    private JLabel total;
    private JLabel currentPass;
    private JLabel tick;
    private JLabel average;
    private JLabel elevator;
    private JLabel dropoff;
    private JLabel power;
    private JLabel powercost;
    private JTextArea text;
    private JPanel grid;
    private Controller control;
    private JScrollPane sp;
    private JProgressBar progress;
    private JProgressBar runs;
    private JOptionPane option;
    private GridCanvas gc;
    private Graphics g;
    private ECar ecar;
    private int numRun;
    private int maxFloor;
    private int elevators;
    private int currentPassengers;
    private int currentTick;
    private int inCarNum;
    private int drop;
    private int totalPass;
    private int temp;
    private double totalPower;
    private double avg;
    private double cost;

    public GUI(Controller c)
    {
        control = c;
        elevators = 1;
        currentTick = 0;
        temp = 0;
        makeFrame();
    }

    /**
     * Makes the window for the GUI adding in the panels, scrollpane,
     * elevator grid, stats, progress bars, menubar
     */
    public void makeFrame()
    {
        Font arial = new Font("Arial", Font.BOLD, 13);
        frame = new JFrame("Elevator Simulator");
        frame.setResizable(false);
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("elevator icon.png"));
        } catch (IOException e) {
        }

        frame.setIconImage(img);
        createMenuBar(frame);

        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(2,1));

        /* Constant Panel */
        JPanel constant = new JPanel();
        max = new JLabel("  Number of Floors: " + maxFloor);
        constant.add(max);
        elevator = new JLabel("  Number of elevators active: " + elevators);
        constant.add(elevator);

        contentPane.add(constant, BorderLayout.NORTH);

        /* Stats Panel */
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(12,1));
        stats.setBorder(BorderFactory.createTitledBorder("Current Statistics"));
        stats.setFont(arial);

        tick = new JLabel("  Tick: " + currentTick);
        tick.setForeground(Color.blue);
        stats.add(tick);

        inCar = new JLabel("  Passengers in the elevator car: " + inCarNum);
        inCar.setForeground(Color.blue);
        stats.add(inCar);

        currentPass = new JLabel("  Current number of passengers: " + currentPass);
        currentPass.setForeground(Color.blue);
        stats.add(currentPass);

        dropoff = new JLabel("  Passengers dropped off: " + drop);
        dropoff.setForeground(Color.blue);
        stats.add(dropoff);

        total = new JLabel("  Total Passengers: " + totalPass);
        total.setForeground(Color.blue);
        stats.add(total);

        power = new JLabel("  Total Power Used: ");
        power.setForeground(Color.blue);
        stats.add(power);

        powercost = new JLabel("  Cost of Power Used: $");
        powercost.setForeground(Color.blue);
        stats.add(powercost);

        average = new JLabel("  Average waiting time: ");
        average.setForeground(Color.blue);
        stats.add(average);
        west.add(stats);

        /* Text Box */
        text = new JTextArea(10, 30);
        JScrollPane sp = new JScrollPane(text);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret = (DefaultCaret)text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue));
        text.setFont(arial);
        text.setForeground(Color.blue);

        

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
        grid.setBounds(0, 0, 100, 500);
        gc = new GridCanvas(100, 500, control.getMaxFloor(), elevators); // (width, height, floors, elevators)
        gc.setBackground(Color.WHITE);
        grid.add(gc);
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
     * Method called in controller to update the statistics panel every tick 
     */
    public void update()
    {
        cost = totalPower * .0949; 
        max.setText("  Number of Floors: " + maxFloor); 
        elevator.setText("  Number of elevators active: " + elevators);
        currentPass.setText("  Current number of passengers: " + currentPassengers);
        tick.setText("  Tick: " + currentTick);
        average.setText("  Average waiting time: " + getAverage());
        inCar.setText("  Passengers in the elevator car: " + inCarNum);
        dropoff.setText("  Passengers dropped off: " + drop); 
        total.setText("  Total Passengers: " + totalPass);
        power.setText("  Total Power Used: " + totalPower + "kWh");
        powercost.setText("  Cost of Power Used: $" + Double.parseDouble(String.valueOf(cost).substring(0,3)));
    }

    /**
     * Removes the previously drawn elevator and then draws the elvator car at 
     * its current position/floor on the grid 
     */
    public void drawCar(int x)
    {
        g = gc.getGraphics();
        g.setColor(Color.black);
        g.clearRect(1, temp*gc.getScalingFactor(), 99, gc.getScalingFactor());
        g.drawRect(1, temp*gc.getScalingFactor(), 99, gc.getScalingFactor());
        temp = x-maxFloor;
        if(temp < 0)
            temp = temp * -1;
        g.setColor(Color.black);
        g.fillRect(1, temp*gc.getScalingFactor(), 99, gc.getScalingFactor());
        g.setColor(Color.blue);
        g.fillOval(1, temp*gc.getScalingFactor(), 99, gc.getScalingFactor());

    }
    /**
     * Creates the menubar for the program and declares what occurs when a button is pressed.
     */
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

    /**
     * Called through the menubar, displays a popup menu asking for the number of runs you would like to simulate,
     * then calls Controller's run() method
     */
    public void runButton()
    {
        //appendText("Run was called");
        String r = JOptionPane.showInputDialog(frame, "Input number of runs", "Run", JOptionPane.QUESTION_MESSAGE);
        numRun = Integer.parseInt(r);
        appendText("\n\n Running simulator " + r + " more times...");     
        control.run(numRun);
    }

    /**
     * Displays "About" window, showing class project, and 
     */
    private void about()
    {
        JOptionPane.showMessageDialog(frame, 
            "Elevator Simulation Project for CS216. \nCreated by: Zach, Connor, Evan, Jeremy, Troy \nProf. Weissman, Spring 2016",
            "About Elevator Simulator", 
            JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Updates the progress bar on the east panel with the current floor of the elevator every tick
     */
    public void setProgress(int num, int floor)
    {        
        progress.setMaximum(floor);
        progress.setValue(num); 
        progress.setString("Floor: " + num);
    }

    /**
     * Updates the "Runs" progress bar in the south panel 
     */
    public void setRun(int num, int run)
    {        
        runs.setMaximum(run);
        runs.setValue(num); 
        runs.setString("Run: " + num);
        currentTick = num;
    }

    /**
     * Sets the total passengers in the simulator to be displayed in the statistics panel.
     * Total passengers include passengers waiting and passengers that have been dropped off or in the car.
     */
    public void setTotalPass(int u, int d, int s)
    {
        totalPass = u + d + s;
    }

    /**
     * Called in Ecar to set the total power used by the elevator. 
     */
    public void setTotalPower(double tp)
    {
        totalPower = tp;
    }

    /**
     * Used to keep track of the passengers that have been dropped off by the elevator.
     */
    public void setDropOff(int d)
    {
        drop = d;
    }

    /**
     * Declares the max floor of the simulation.
     */
    public void setMax(int max)
    {
        maxFloor = max;
    }

    /**
     * Declares the current passengers in the simulation to be used in the statistics panel.
     */
    public void setPassengers(int u, int d)
    {
        currentPassengers = u + d;
    }

    /**
     * Declares the average wait time to be used in the statistics panel.
     */
    public void setAverage(double a)
    {
        avg = a;
    }

    /**
     * Formats the average to 2 decimal places. (ie. 4.32 instead of 4.325654)
     */
    public String getAverage()
    {
        return String.format("%.2f",avg);
    }

    /**
     * Declares the passengers currently in the elevator car.
     */
    public void setInCar(int i)
    {
        inCarNum = i;
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

    /**
     * Class used to draw the grid
     */
    class GridCanvas extends Canvas
    {
        int width, height;
        int rows, columns;
        int rowHt, rowWid;

        /**
         * GridCanvas constructor
         */
        GridCanvas(int w, int h, int r, int c)
        {
            width = w;
            height = h;
            rows = r;
            columns = c;
            setSize(width, height);
        }

        /**
         * Draws the lines of the grid based on the number rows (floors) and the columns (elevators)
         */
        public void paint(Graphics g)
        {
            int i;
            width = getSize().width;
            height = getSize().height;

            rowHt = height / rows;
            for (i=0; i<=rows; i++)
                g.drawRect(0, i*rowHt, width, i*rowHt);
            rowWid = width / columns; 
            for(i=0; i < columns; i++)
                g.drawRect(i*rowWid, 0, i*rowWid, height);
        }

        /**
         * Returns the rowHt to  be used in drawCar so the elevator is drawn correctly.
         */
        public int getScalingFactor()
        {
            return rowHt;
        }
    }
}
