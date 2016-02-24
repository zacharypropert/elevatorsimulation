
/**
 * Write a description of class ECar here.
 * 
 * Jeremy & Troy
 * February 20, 2016
 */
public class ECar
{
    private int Idle; //not sure about this one
    private UpList upList;
    private DownList downList;
    private SinkList sinkList;
    private InCarList incarList;
    private int floor;
    private int maxfloor;
    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1
     */
    public ECar(UpList u, DownList d, SinkList s, InCarList i, int floors)
    {
        upList = u;
        downList = d;
        sinkList = s;
        incarList = i;
        floor = 1;
        maxFloor = floors;
        
    }

}
