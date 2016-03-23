
/**
 * Write a description of class DownList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DownList extends PList  //sbw
{

    /**
     * Constructor for objects of class DownList
     */
    public DownList(Clock c)  //sbw
    {
        super(c);  //sbw
    }

    /**
     * passengers going UP
     */
    public int checkRequest(int maxFloor)
    {
        int floor = maxFloor;
        int closest = 0;
        while(floor > 0){
            for (int x =0;x<pList.size();x++) {
                if (pList.get(x).getStart() <= floor && pList.get(x).getStart() >= closest) {
                    closest = pList.get(x).getStart();
                }
            }
            floor--;
        }
        return closest;
    }

}
