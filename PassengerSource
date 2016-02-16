/*
*
* Class file for PassengerSource.  
* PassengerSource will generate passengers with randomized attributes,
* keeps track of these passengers in a collection. Randomly generates the attributes, 
* if the attributes do not fit in with the elevator/building schematics the passenger
* is not created or add to the queue.
*
*/
import java.util.*;

/**
 * Generates Passengers with randomized attributes, and inserts into UpList, DownList collections. Randomly generates the starting location and the destination..
 *
 * @Zach & Connor
 * @2/16/2016
 */
public class PassengerSource
{
    private ArrayList<Passenger>upList;  
    private ArrayList<Passenger> downList;  
    Random rgen = new Random();
    private int totalFloors = 3;

    /**
     * Constructor for objects of class PassengerSource
     */
    public PassengerSource()
    {
        upList=new ArrayList<>();
        downList=new ArrayList<>(); 
    }

    /**
     *  Sorts the newly created passenger in the up or down list.
     */
    public void sort()
    {
        if(Passenger.getStart() < Passenger.getDestination())
        {
            upList.add(p1);
        }else if(Passenger.getStart() > Passenger.getDestination())
        {
            downList.add(p1);
        }else
        {
            createPassenger();
        }
    }

    /**
     * 
     */

    public Passenger createPassenger()
    {
        int start = rgen;
        int end = rgen;

        Passenger newPassenger = new Passenger(start, end);
        sort();
    }

}
