import java.util.*;
/**

 */
public class Statistics
{
    private UpList upList;
    private DownList downList;
    private SinkList sinkList;    
    private int ArrayList;    
    /**
     * Constructor for objects of class Statistics
     */
    public Statistics(UpList upList, DownList downList, SinkList sinkList)
    {
        upList=new UpList();
        downList=new DownList();
        sinkList=new SinkList<>();
        waitList=new ArrayList<>();

    }

    /**

     */
    public void avgWait()
    {
        int countPassenger=0;
        for(passenger p : sinkList)
        {            
            wait = p.getExit-p.getStart;
            WaitList.add(wait);    
            countPassenger++;
        }
        for(int i : WaitList)
        {
            temp=wait;
            tamp+wait=totalWaitTime;
            avgWait = totalWaitTime/countPassenger;
            //Need way to get total passengers in simulation
            //Longest Wait Time, Average Wait Time            
        }
        
        
        System.out.println("THe average wait time for all passengers was " +avgWait);
    }
}
