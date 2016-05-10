import java.util.*;
/**
 * Generates Passengers with randomized attributes, and inserts into UpList, DownList collections. Randomly generates the starting location and the destination..
 *
 * @Zach & Connor
 * @2/16/2016
 */
public class PassengerSource
{
    private UpList upList;
    private DownList downList;
    Random rgen = new Random();
    private int maxFloor;
    private int rgenFloor;
    private Clock clock; 
    private static int passCount = 0; //Keeps track of passengers with iD
    /**
     * Constructor for objects of class PassengerSource
     */
    public PassengerSource(UpList upList, DownList downList, int maxFloor, Clock clock)//JEREMY!
    {
        this.clock= clock; 
        this.maxFloor = maxFloor; 
        this.upList = upList;
        this.downList = downList;
    }

    /**
     *Creates and places passenger
     */
    public void createPassenger() throws invalidFloorException
    {

        int halfMax = maxFloor / 2;
        int randomInt = halfMax + maxFloor;
        int start = rgen.nextInt(randomInt - (halfMax / 4) + 1);
        int end = rgen.nextInt(randomInt + 1);
        
        if(start > maxFloor)
            start = 1;
        if(end > maxFloor)
            end = 1;

        if(start == 0 || end == 0 || start==end ) 
            throw new invalidFloorException(start); 
       
        Passenger newPassenger = new Passenger(start, end, clock.getTick());

        if(newPassenger.getStart() < newPassenger.getDestination())
        {
            upList.addPassenger(newPassenger);
        }else
        {
            downList.addPassenger(newPassenger);
        }        
        passCount++;
    }

    /**
     * Creates a new passenger and if the passenger has floor values that are invalid an
     * exception is thrown.
     */
    public void act()
    {
       try{
            createPassenger(); 
        }
        catch(invalidFloorException e) 
        { 
            act(); 
        }
    }
}
