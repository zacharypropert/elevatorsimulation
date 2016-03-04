import java.util.ArrayList;
/**
 * Write a description of class InCarList here.
 * 
 * @author (Evan) 
 * @version (a version number or a date)
 */
public class InCarList extends PList
{

    /**
     * Constructor for objects of class InCarList
     */
    public InCarList(Clock c)
    {
        //
        super(c);
    }

    public void addList(ArrayList<Passenger> other)
    {
        //
        for(Passenger p : other) {
            p.setEntryTick(clock.getTick());
            pList.add(p);
            
        }
    }

}
