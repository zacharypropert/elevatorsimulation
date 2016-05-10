import java.util.ArrayList;
/**
 * Write a description of class SinkList here.
 * 
 * @David Hoadley 
 * @3/3/2016
 */
public class SinkList extends PList
{

    /**
     * Constructor for objects of class SinkList
     */
    public SinkList(Clock c)
    {
        super(c);
    }

    //override
    public void addList(ArrayList<Passenger> other)
    {
        //
        for(Passenger p : other) {
            p.setExitTick(clock.getTick());
            pList.add(p);

        }
    }

    public ArrayList<Passenger> getCloneList()
    {
        return pList; //attempted to clone but wouldn't work
    }

   
}
