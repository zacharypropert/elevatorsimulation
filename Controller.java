

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
    private Statistics myReport; //Connor
    
    /**
     * Constructor for objects of class Controller.
     */
    public Controller(int maxFloor, int numRun) throws invalidFloorException //CK- Changed maxCount to numRunand made parameter
    {
        if(maxFloor < 2)
        throw new invalidFloorException();
        else
        this.maxFloor = maxFloor;
        
        myClock = new Clock();
        upList = new UpList(myClock);    //sbw added parameters
        downList = new DownList(myClock);
        sinkList = new SinkList(myClock);
        incarList = new InCarList(myClock);  //...sbw
        myReport = new Statistics(sinkList); //Connor - no upList and downList
    
        source = new PassengerSource(upList, downList, maxFloor, myClock);
        car = new ECar(upList, downList, sinkList, incarList, maxFloor, myClock);
        
        run(numRun);
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
            
            tick++;
            
            myClock.incrementTick();
            
        }
        myReport.fullReport(); //Connor
    }
    
    public void showAllWaitTimes()
    {
         myReport.listWaitTimes();
    }

}
