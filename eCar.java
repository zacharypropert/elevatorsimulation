
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
    public void act()
    {
        //for the first floor the elevator can only go up
        if(floor == 1  && u.size() > 0 && i.size() == 0){
            direction = 1;
            while(floor <= maxFloor && u.size() > 0){
                for(int x = 0; x<u.size(); x++){
                    if(u.get(x).getStart==floor)
                        i.add(u.remove(x));
                    if(i.get(y).getDestination() == floor)
                        i.removeAtFloor;
                }
                floor++;
            }
            direction = 0;
        }
        
        //when the elevator finishes its first round up it will begin to go down
        //it will pick up passengers on its way down and drop them off as well
        if(direction = 0 && floor != 1){
            direction = -1;
            while(floor >= Idle){
                if(d.size()>0){
                    for(int y = 0;y<d.size(); y++){
                        if(d.get(y).getStart() == floor)
                            i.add(d.remove(y));
                        if(i.get(y).getDestination() == floor)
                            i.removeAtFloor;
                    }
                }
                floor = floor - 1
            }
        }

}
