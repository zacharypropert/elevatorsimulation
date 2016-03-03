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
    
    //need a way to test for next floor ↓↓↓↓↓↓
    
    public void act()
    {
        for(int x = floor - direction; x <= maxFloor && x > 0; x=x+direction){
            //this should work for both up and down ↑↑↑↑
        }
        
    }
    
    

}
