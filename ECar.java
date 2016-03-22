import java.util.ArrayList;
/**
 * Write a description of class ECar here.
 * 
 * Jeremy & Troy
 * February 20, 2016
 */
public class ECar
{
    private int Idle; //not sure about this one
    private int direction;
    private UpList u;
    private DownList d;
    private SinkList s;
    private InCarList i;
    private int maxFloor;
    private int floor;

    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1
     */
    public ECar(UpList u, DownList d, SinkList s, InCarList i, int m)
    {
        Idle = 1;
        direction = 1; //e-car starts going up initially
        this.u = u;
        this.d = d;
        this.s = s;
        this.i = i;
        maxFloor = m;
        floor = 1;

    }

    public void act()
    {
        if(direction == 1){
            System.out.println("UpList Here:");
            u.display();    //displays the upList at the beginning of the while loop
            while(floor < maxFloor){
                if(u.checkRequest(floor) != 0){
                    
                    i.addList(u.removeAtFloor(floor));
                    s.addList(i.removeAtFloor(floor));
                    System.out.println("");
                    
                }
                floor++;
            }
            System.out.println("InCarList Here:");
            i.display();    //displays the inCarList at the end of the while loop
            System.out.println("");
            System.out.println("SinkList Here:");
            s.display();    //displays the sinkList at the end of the while loop
            direction = -1;
        }

        if(direction == -1){
            System.out.println("DownList Here:");
            d.display();    //displays the downList at the beginning of the while loop
            while(floor > 0){
                if(d.checkRequest(floor) != 0){
                    
                    i.addList(d.removeAtFloor(floor));
                    s.addList(i.removeAtFloor(floor));
                    System.out.println("");
                    
                }
                floor = floor-1;
            }
            System.out.println("InCarList Here:");      
            i.display();            //displays the inCarList at the end of the while loop
            System.out.println("");
            System.out.println("SinkList Here:");
            s.display();        //displays the sinkList at the end of the while loop
            direction = 1;
        }

    }

}
