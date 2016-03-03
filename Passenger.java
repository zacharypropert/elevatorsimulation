
/**
 *Models a single passenger, creates a passenger and defines their starting location and 
 *their destination. 50% of new passengers will start on the first floor..
 * 
 * @Zach CS216
 * @2/16/2015
 */
public class Passenger
{
    private int start; //Starting floor
    private int destination; //ending floor
    private int startTick; //keep track of time, in ticks?
    private int entryTick;
    private int exitTick;
    private Clock clock;
       

    /**
     * Creates a new passenger with a starting location and a destination, also sets the
     * time to 0.
     */
    public Passenger(int start, int destination, int tick)
    {
        this.start = start;
        this.destination = destination;
        startTick = tick;// clock.getTick(); //may be changed depending on the way the tick is calculated
        entryTick = 0;
        exitTick = 0;
    }
    
    /**
     * Returns the starting location of a passenger.
     */
    public int getStart()
    {
        return start;
    }
    
    /**
     * Returns the destination of the passenger.
     */
    public int getDestination()
    {
        return destination;
    }
    
    /**
     * Returns the start tick of the passenger from when the passenger is created.
     */
    public int getStartTick()
    {
        return startTick;
    }
    
    /**
     * Returns the start tick of the passenger from when the passenger is created.
     */
    public void setStartTick(int sTick)
    {
        startTick = sTick;
    }
    
    /**
     * Returns the entry tick of the passenger from when the passenger enters the eCar.
     */
    public int getEntryTick()
    {
        return entryTick;
    }
    
    /**
     * Returns the entry tick of the passenger from when the passenger enters the eCar.
     */
    public void setEntryTick(int eTick)
    {
        entryTick = eTick;
    }
    
    /**
     * Returns the exit tick of the passenger from when the passenger exits the eCar.
     */
    public int getExitTick()
    {
        return exitTick;
    }
    
     /**
     * Returns the exit tick of the passenger from when the passenger exits the eCar.
     */
    public void setExitTick(int xTick)
    {
        exitTick = xTick;
    }
  
  
     public String toString()
    {
         return "A passenger is at floor "+ start + " and wishes to travel to floor "
       + destination;
    }
  
}
