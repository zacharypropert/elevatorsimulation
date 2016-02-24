
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
    
    /**
     * Constructor for objects of class Controller.
     */
    public Controller(int maxFloor)
    {
        this.maxFloor = maxFloor;
        upList = new UpList();
        downList = new DownList();
        sinkList = new SinkList();
        incarList = new InCarList();  
    
        source = new PassengerSource(upList, downList, maxFloor);
        car = new ECar(upList, downList, sinkList, incarList, maxFloor);
        
        run(10);
    }
    
    /**
     * Contains the simulation loop to invoke the act method on the objects.
     */
    public void run(int maxCount)
    {
        tick = 0;
        
        while (tick < maxCount)
        {
            source.act(tick);
            
            car.act(tick);
            
            tick++;
        }
        
    }

}
