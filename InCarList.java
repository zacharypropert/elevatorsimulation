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
    public InCarList(Clock c)                                       //CKnote - Takes in Clock from PList
    
    {
        //
        super(c);
    }

    public void addList(ArrayList<Passenger> other)                 //CKnote - Sets entry tick to passenger. Then adds passenger to pList?
    {
        //
        for(Passenger p : other) {
            p.setEntryTick(clock.getTick());
            pList.add(p);
            
        }
    }
    
    public boolean isEmpty(){                                       //CKnote - If pList is empty, return true
        if(pList.isEmpty() == true)
            return true;
        return false;
    }

}
