
/**
 *Models a single passenger, creates a passenger and defines their starting location and 
 *their destination. 50% of new passengers will start on the first floor..
 * 
 * @Zachary Propert
 * @2/16/2015
 */
public class Passenger
{
   private int start; //Starting floor
    private int destination; //ending floor
    private int time; //keep track of time, in ticks?

    /**
     * Creates a new passenger with a starting location and a destination, also sets the
     * time to 0.
     */
    public Passenger(int start, int destination)
    {
        this.start = start;
        this.destination = destination;
        time = 0; //may be changed depending on the way the tick is calculated
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
     * Returns the time tick of the passenger.
     */
    public int getTime()
    {
        return time;
    }
    
    public String toString()
    {
         return "A passenger is at floor "+ start + " and wishes to travel to floor "
       + destination;
    }
  
    
}
