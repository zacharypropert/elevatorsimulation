
/**
 * Creates a master Clock that maintains the time while Elevator is running.
 * 
 * David Hoadley
 * 3/1/16
**/
public class Clock
{
    private int tick;

    /**
     * Constructor for objects of class Clock
     */
    public Clock()
    {
        tick = 0; 
    }

    /**
     * getTick will return the tick when it is called.
     **/
    public int getTick()
    {
        // Returns the current tick
        return tick;
    }
    
    /**
     * This method will add one to tick.
     **/
    public void incrementTick()
    {
        //Adds one tick to global tick
        tick++;
    }
}
