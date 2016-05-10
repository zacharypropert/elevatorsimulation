
/**
 * Models a single passenger, creates a passenger and defines their starting location and 
 * their destination. 
 *
 * Also declares the tick at creation, tick when passenger enters elevator car, and tick when the
 * passenger exits the elevator car.
 * 
 * @Zach CS216
 * @2/16/2015
 */
public class Passenger
{
    private int start; //Starting floor
    private int destination; //ending floor
    private int startTick; //tick at passenger creation
    private int entryTick; //tick when passenger enters elevator car
    private int exitTick;  //tick when passenger exits elevator car
    //private Clock clock;  //sbw doesn't belong
    private int iD; //CKnote - iD for each passenger
    

    /**
     * Creates a new passenger with a starting location and a destination, also sets the
     * creation tick (startTick), sets the entryTick and exitTick to 0.
     */
    public Passenger(int start, int destination, int tick)
    {
        this.start = start;
        this.destination = destination;
        startTick = tick;
        entryTick = 0;
        exitTick = 0;
        iD = 0;; //CKnote - Should give every passenger a unique id
    }
    
    /**
     * Returns number unique identifying a passenger
     */
    public int getID() //Cknote
    {
        return iD;
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
     * Allows Passenger's ID to manipulated for later use and testing
     */
     public void setID(int newID) //Cknote
    {
        iD = newID;
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

    /**
     * toString() method for class Passenger to output the passenger's starting location and where 
     * the passenger's destination is at.
     */
    public String toString()
    {
        return "A passenger is at floor "+ start + " and wishes to travel to floor "
        + destination;
    }

}
