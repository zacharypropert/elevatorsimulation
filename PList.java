import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class PList
 * 
 * @author Evan Pierce 
 * @version 2/16/16
 */
public class PList
{
    private ArrayList<Passenger>pList;
    private Passenger entryTick;
    private Clock tick;
    
    /**
     * Constructor for objects of class PList
     */
    public PList()
    {
        pList = new ArrayList<>();
       
    }
    
    /**
     * adds a passenger
     */
    public void addPassenger(Passenger p)
    {
        //
        pList.add(p);
    }

    /**
     * adds a list of passenger
     */
    public void addList(ArrayList<Passenger> other)
    {
        //
        for(Passenger p : other) {
            pList.add(p);
            Passenger.setEntryTick(Clock.getTick()); //I did not have clock to check
        }
    }
    
    /**
     * removes all with current floor as destination
     */
    public ArrayList<Passenger> removeAtFloor(int floor)
    {
        //
        ArrayList<Passenger> myList = new ArrayList<>();
        Iterator<Passenger> it = pList.iterator();
        
        while(it.hasNext()) {
            Passenger p = it.next();
            if (p.getDestination() == floor) {
                myList.add(p);
                it.remove();  //sbw fix - took out parameter p
            }
        }
            
        return myList;
    }
    
    /**
     * tests display
     */
    public void Display()
    {
        //
        Passenger.toString();
    }

}
