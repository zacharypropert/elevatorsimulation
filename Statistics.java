import java.util.*;
/**
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

    public void longestWait()
    {        
        int wait = 0;
        int temp = 0;
        int totalWaitTime = 0;        
        for(Passenger p : sinkList.getCloneList())
        {
            wait = p.getExitTick() - p.getStartTick();
            if(wait>temp)
            {
                temp = wait;                
            }
        }

        System.out.println("The longest wait was " + temp + " ticks");
    }

    public void numberOfPassengers()
    {
        int countPassenger = 0;
        for(Passenger p : sinkList.getCloneList())
        {
            countPassenger++;
        }

        System.out.println("There were " + countPassenger + " passengers");
    }

    public void fullReport()
    {
        avgWait();
        longestWait();
        numberOfPassengers();
    }
}
