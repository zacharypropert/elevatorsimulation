import java.util.*;
/**
 * @Connor Keenan
 */
public class Statistics
{
    private SinkList sinkList;
    ArrayList<Integer> waitList = new ArrayList<Integer>();
    /**
     * Constructor for objects of class Statistics
     */
    public Statistics(UpList upList, DownList downList, SinkList sinkList)
    {
        this.sinkList= sinkList;
        waitList=new ArrayList<>();

    }

    /**
     * Calculates the average wait time
     */
    public void avgWait()
    {        
        int wait = 0;
        int temp = 0;
        double totalWaitTime = 0;
        double avgWait = 0;
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

        System.out.println("The average wait time for all passengers was " + avgWait + " ticks");
    }

    /**
     * Calculates the longest wait time
     */
    public void longestWait()
    {        
        int wait = 0;               //Loops through wait times in sinkList 
        int temp = 0;               //keeps track of highest wait
        int totalWaitTime = 0;      //How many ticks everyone waited collectively 
        int angryPassNum = 0;       //reference number to sinkList to find longest wait
        int angryPassStart = 0;     //When that Passenger was created
        for(Passenger p : sinkList.getCloneList())
        {
            wait = p.getExitTick() - p.getStartTick();
            if(wait>temp)
            {
                temp = wait;    
                angryPassNum = sinkList.getCloneList().indexOf(p);
                angryPassStart = p.getStartTick();
            }
        }
        System.out.println("Passenger #" + angryPassNum +" arrived at tick " + angryPassStart + " with the longest wait at " + temp + " ticks");
    }

    /**
     * Calculates the total number of passengers
     */
    public void numberOfPassengers()
    {
        int countPassenger = 0;
        for(Passenger p : sinkList.getCloneList())
        {
            countPassenger++;
        }

        System.out.println("There were " + countPassenger + " passengers");
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
}
