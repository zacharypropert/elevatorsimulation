import java.util.*;
/**
 * @Connor Keenan
 */
public class Statistics
{
    private SinkList sinkList;
    private InCarList inCarList;
    ArrayList<Integer> waitList = new ArrayList<Integer>();
    private float avgWait;
    private GUI gui;
    /**
     * Constructor for objects of class Statistics
     */
    public Statistics(SinkList sinkList, InCarList inCarList, GUI g)
    {
        this.sinkList = sinkList;
        this.inCarList = inCarList; 
        waitList=new ArrayList<>();
        gui = g;
    }    

    /**
     * Calculates the average wait time
     */
    public void avgWait()
    {        
        int wait = 0;
        int temp = 0;
        float totalWaitTime = 0;
        avgWait = 0;
        int countPassenger=0;
        for(Passenger p : sinkList.getCloneList())
        {
            wait = p.getExitTick() - p.getStartTick();
            waitList.add(wait);
            countPassenger++;
        }
        for(int i : waitList)
        {            
            totalWaitTime += i;              
        }
        avgWait = totalWaitTime/countPassenger;    
        String num = String.format("%.2f",avgWait);
        gui.appendText("\n The average wait time for all passengers was " + num + " ticks");
    }
    
    /**
     * Returns the calculated average wait of all unloaded passengers 
     */
    public float getAvg()
    {
        return avgWait;
    }

    /**
     * Calculates the longest wait time out of the unloaded passengers
     */
     public void longestWait()
    {        
        int wait = 0;               //Loops through wait times in sinkList 
        int temp = 0;               //keeps track of highest wait
        int totalWaitTime = 0;      //How many ticks everyone waited collectively 
        int angryPassNum = 0;       //reference number to sinkList to find longest wait
        int angryPassStart = 0;     //When that Passenger was created     
        Passenger angryPass = null;
        for(Passenger p : sinkList.getCloneList())
        {
            wait = p.getExitTick() - p.getStartTick();
            if(wait>temp)
            {
                temp = wait;    
                angryPassNum = sinkList.getCloneList().indexOf(p); // record number
                angryPassStart = p.getStartTick();
                angryPass = p;
            }
        }
        gui.appendText("\n Passenger #" + angryPass.getID() + " was #" + angryPassNum +" leaving the elevator on tick " + angryPassStart + " with the longest wait at " + temp + " ticks");
    }

    /**
     * Calculates the total number of passengers that have left the elevator
     */
    public void numberOfPassengers()
    {
        int countPassenger = 0;
        for(Passenger p : sinkList.getCloneList())
        {
            countPassenger++;
        }

        gui.appendText(" There were " + countPassenger + " passengers");
    }

    /**
     * Lists all wait times in Controller simulation
     */
    public void listWaitTimes()
    {        
        int countPassenger = 0;
        int i = 0;        
        for(Passenger p : sinkList.getCloneList())
        {
            countPassenger++;
        }
        System.out.print(" Wait times for all Passengers: ");
        for(Passenger p : sinkList.getCloneList())
        {            
            int wait = p.getExitTick() - p.getStartTick();
            
            if(i + 1 == countPassenger)
            {
                System.out.print(wait);
            }
            else
            {
                System.out.print(wait + ", ");
            }
            i++;
        }
        gui.appendText(" ");
    }
    
    
    /**
     * Creates a list of all the visited destinations
     */
    public void desirableFloors()
    {        
        int countPassenger = 0;
        int i = 0;
        for(Passenger p : sinkList.getCloneList())
        {
            countPassenger++;
        }
        System.out.print(" Destinations for all Passengers: ");
        for(Passenger p : sinkList.getCloneList())
        {            
            int floor = p.getDestination();
            
            if(i + 1 == countPassenger)
            {
                System.out.print(floor);
            }
            else
            {
                System.out.print(floor + ", ");
            }
            i++;
        }
        gui.appendText(" ");
    }

    /**
     * Called at the end of Controller's run to shoot back ending stats
     */
    public void fullReport()
    {
        avgWait();
        longestWait();
        numberOfPassengers();
    }
    /**
     * Ensures ID works for every Passenger
     */
    public void testID() 
    {
        for(Passenger p : sinkList.getCloneList())
        {   
            System.out.println(p.getID());
        }
    }
    
    /**
     * Finds passengers left in inCarList
     */
    public void inCarRemaining() 
    {
        for(Passenger p : inCarList.getCloneList())
        {   
            System.out.println(p.getID());
        }
    }
}
