import java.util.ArrayList;
/**
 * Write a description of class InCarList here.
 * 
 * @author (Evan) 
 * @version (a version number or a date)
 */
public class InCarList extends PList
{
    private static int passCount = 1; //maintains passenger id count 

    /**
     * Constructor for objects of class InCarList
     */
    public InCarList(Clock c)      //Takes in Clock from PList

    {
        super(c);
    }

    public void addList(ArrayList<Passenger> other) //Sets entry tick to passenger. Then adds passenger to pList?
    {
        //        
        for(Passenger p : other) {
            p.setEntryTick(clock.getTick());
            p.setID(passCount);          //Assigns id to passenger
            pList.add(p);            
            passCount++;

        }
    }

    public boolean isEmpty(){    //If pList is empty, return true
        if(pList.isEmpty() == true)
            return true;
        return false;
    }

    
    public int checkUpRequest(int currentFloor)       //Returns the closest passenger
    {
        int floor = currentFloor;
        int closest = 1000;
        //loops through entire list
        for (int x =0;x<pList.size();x++) {
                //Closest should change if destination is between eCar and previous mark
                
                if (pList.get(x).getDestination() >= floor && pList.get(x).getDestination() <= closest) {
                    closest = pList.get(x).getDestination();
                }
            
 
        }
        return closest;      //returns floor floor closest to ecar in terms of direction
    }
    
    public int checkDownRequest(int currentFloor){
        int floor = currentFloor;
        int closest = 0;
        
        for (int x =0;x<pList.size();x++) {
                //Closest should change if destination is between eCar and previous mark
                
                if (pList.get(x).getDestination() <= floor && pList.get(x).getDestination() >= closest) {
                    closest = pList.get(x).getDestination();
                }
              
        }
        
        return closest;
    }
    
    public ArrayList<Passenger> getCloneList() //Statistics copy
    {
        return pList;
    }
}
