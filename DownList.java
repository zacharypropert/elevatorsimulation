
/**
 * Write a description of class DownList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DownList extends PList
{

    /**
     * Constructor for objects of class DownList
     */
    public DownList()
    {
        //
    }
    
    
   /**
     * passengers going UP
     */
    public int checkRequest(int floor)
    {
        //
        int closest = pList.get(0).getStart();
        for (int x =0;x<pList.size();x++) {
            if (pList.get(x).getStart() <= floor && pList.get(x).getStart() >= closest) {
                closest = pList.get(x).getStart();
            }
        }
        return closest;
    }
}
