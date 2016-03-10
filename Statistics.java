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
        int totalWaitTime = 0;
        int avgWait = 0;
        int countPassenger=0;
        for(Passenger p : sinkList.getCloneList())
        {
            wait = p.getExitTick() - p.getEntryTick();
            waitList.add(wait);
            countPassenger++;
        }
        for(int i : waitList)
        {
            temp = wait;
            totalWaitTime = temp + wait;
            avgWait = totalWaitTime/countPassenger;
        }
        System.out.println("The average wait time for all passengers was " +avgWait);
    }
}
