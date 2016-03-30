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

    public int checkRequest(int currentFloor, int direction)       //CKnote - Returns the 
    {
        int floor = currentFloor;
        int closest = 0;
        //loops through entire list
        for (int x =0;x<pList.size();x++) {

            while(direction==1)                                     //CKnote - While going up
            {                                                       //CKnote - Closest should change if destination is between eCar and previous mark
                if (pList.get(x).getDestination() >= floor && pList.get(x).getDestination() >= closest) {
                    closest = pList.get(x).getDestination();
                }
            }

            while(direction==-1)                                    //CKnote - While going down
            {                                                       //CKnote - Closest should change if destination is between eCar and previous mark
                if (pList.get(x).getDestination() <= floor && pList.get(x).getDestination() <= closest) {
                    closest = pList.get(x).getDestination();
                }
            }            
        }
        return closest;                                             //returns floor floor closest to ecar in terms of direction
    }
}
