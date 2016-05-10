
/**
 * Creates the initial objects.
 * Accesses the "Master List(s)" of all the Passengers.
 * Runs the main simulator loop and a tickCount(i.e. time).
 * It invokes the act() methods of the main object(ECar and PassengerSource) at each iteration.
 * 
 * @author
 * @February 23, 2016.
 */
public class Controller
{
    private UpList upList;
    private DownList downList;
    private SinkList sinkList;
    private InCarList incarList;
    private int maxFloor;
    private PassengerSource source;
    private ECar car;
    private int tick;
    private Clock myClock;
    private Statistics myReport; 
    private GUI gui;
    private int numRun;
   // private Bank bank;
    private int numOfECars;

    /**
     * Constructor for objects of class Controller.
     */
    public Controller(int maxFloor, int numRun) throws invalidFloorException 
    {
        if(maxFloor < 2)
            throw new invalidFloorException();
        else
            this.maxFloor = maxFloor;

        this.numRun = numRun; 
        gui = new GUI(this);
        myClock = new Clock();
        upList = new UpList(myClock);    
        downList = new DownList(myClock);
        sinkList = new SinkList(myClock);
        incarList = new InCarList(myClock); 
        myReport = new Statistics(sinkList, incarList, gui); 

        source = new PassengerSource(upList, downList, maxFloor, myClock);
        car = new ECar(upList, downList, sinkList, incarList, maxFloor, myClock, gui);

        gui.setMax(maxFloor);
        run(numRun);
    }

    public int getMaxFloor()
    {
        return maxFloor;
    }

    public void guiUpdate()
    {
        gui.setProgress(car.getFloor(), maxFloor);
        gui.setRun(myClock.getTick(), numRun);
        gui.setPassengers(upList.size(), downList.size());
        gui.setTotalPass(upList.size(), downList.size(), sinkList.size());
        gui.setAverage(myReport.getAvg());
        gui.setInCar(incarList.size());
        gui.setTotalPower(car.getTotalPower());
        gui.drawCar(car.getFloor());
        gui.setDropOff(sinkList.size());
        gui.update();
    }

    /**
     * Contains the simulation loop to invoke the act method on the objects.
     */
    public void run(int numRun)
    {
        tick = 0;

        while (tick < numRun)
        {
            source.act();
            car.act(myClock.getTick());
            tick++;
            myClock.incrementTick();

            guiUpdate();
            try{
                Thread.sleep(500);
            }  catch (Exception e){Thread.currentThread().interrupt();};

        }
 
        myReport.fullReport();
        guiUpdate();
    }

    /**
     * Displays each wait time for passengers
     */
    public void showAllWaitTimes() 
    {
        myReport.listWaitTimes();
    }

    /**
     * Displays floors requested by passengers
     */
    public void showDesiredFloors() 
    {
        myReport.desirableFloors();
    }

    /**
     * Displays passengers that have reached destination after run
     */ 
    public void ControlltestID()       
    {
        myReport.testID();
    }

    /**
     * Displays passengers stuck in Elevator after run
     */
    public void leftInCar()          
    {
        myReport.inCarRemaining();
    }

}
