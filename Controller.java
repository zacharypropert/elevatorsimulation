
/**
 * Creates the initial objects.
 * Accesses the "Master List(s)" of all the Passengers.
 * Runs the main simulator loop and a tickCount(i.e. time).
 * It invokes the act() methods of the main object(ECar and PassengerSource) at each iteration.
 * 
 * First author: Jeremy 
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
    private Bank bank;

    /**
     * Constructor for objects of class Controller.
     */
    public Controller(int maxFloor, int numRun) throws invalidFloorException //CK- Changed maxCount to numRunand made parameter
    {
        if(maxFloor < 2)
            throw new invalidFloorException();
        else
            this.maxFloor = maxFloor;

        this.numRun = numRun; 
        gui = new GUI(this);
        myClock = new Clock();
        upList = new UpList(myClock);    //sbw added parameters
        downList = new DownList(myClock);
        sinkList = new SinkList(myClock);
        incarList = new InCarList(myClock);  //sbw
        myReport = new Statistics(sinkList, incarList, gui);

        source = new PassengerSource(upList, downList, maxFloor, myClock);
        
        car = new ECar(upList, downList, sinkList, incarList, 
            maxFloor, myClock, gui);  //testing progress bar
        bank = new Bank(numOfECars, maxFloor, upList, downList, sinkList, myClock); // used to make a bank

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
        gui.setEcar(car.getElevators());
        gui.setPassengers(upList.size(), downList.size());
        gui.setAverage(myReport.getAvg());
        gui.setInCar(incarList.size());
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
            car.act(tick);
            bank.act(tick);

            tick++;
            myClock.incrementTick();

            guiUpdate();
            try{
                Thread.sleep(500);
            }  catch (Exception e){Thread.currentThread().interrupt();};

        }
        myReport.fullReport(); 
    }

    /**
     * Displays each wait time for passengers
     */
    public void showAllWaitTimes() //Optional view of wait times after Controller runs
    {
        myReport.listWaitTimes();
    }

    /**
     * Displays floors requested by passengers
     */
    public void showDesiredFloors() //Floors traveled requested as
    {
        myReport.desirableFloors();
    }

    /**
     * Displays passengers that have reached destination
     */ 
    public void ControlltestID()        //Checks passengers in sinklist after run
    {
        myReport.testID();
    }

    /**
     * Displays passengers stuck in Elevator
     */
    public void leftInCar()             //Checks passengers in inCarList after run
    {
        myReport.inCarRemaining();
    }

}
