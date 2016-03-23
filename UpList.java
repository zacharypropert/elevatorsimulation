
/**
 * Write a description of class UpList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpList extends PList
{

    /**
     * Constructor for objects of class UpList
     */
    public UpList(Clock c)//sbw
    {
        super(c);  //sbw
    }

    /**
     * passengers going UP
     */
    public int checkRequest(int maxFloor)
    {
        int floor = 0;
        int closest = 1000;
        while(floor<maxFloor){
            for (int x =0;x<pList.size();x++) {
                if (pList.get(x).getStart() >= floor && pList.get(x).getStart() <= closest) {
                    closest = pList.get(x).getStart();
                }
            }
            floor++;
        }
        return closest;
    }

}
