import java.util.*;
/**
 * It is a little clunky right now
 * 
 * Troy
 * April 20, 2016
 */
public class ECar
{
    private int direction;
    private InCarList i;
    private int maxFloor;
    private int floor;
    private Clock c;
    private boolean idle;
    private final int LIMIT=10;  //This limit makes it so that in the bank more cars have a chance to pick up passengers

    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1
     */
    public ECar(int m, Clock c)                  //CK note-Takes objects in from Controller
    {
        direction = 1; //e-car starts going up initially
        i = new InCarList(c);
        maxFloor = m;
        idle=true;
        floor = 1;
        this.c = c;

    }
    
    public ECar changeFloor(int NewFloor){
        floor = NewFloor;
        return this;
    }
    
    public int getFloor(){
        return floor;
    }

    public ECar changeDirection(){
        direction*=-1;
        return this;
    }

    public ArrayList<Passenger> removePassenger(){
        return i.removeAtFloor(floor); 
    }

    public ECar pickUp(PList p){
        i.addList(p.pickUpAtFloor(floor));
        return this;
    }

    public int checkUp(){
        return i.checkUpRequest(floor);
    }

    public int checkDown(){
        return i.checkDownRequest(floor);
    }

    public int getDirection(){
        return direction;
    }

    public String toString(){
        return "The car is at floor: " + floor;
    }
    
    public boolean isIdle(){
        return idle;
    }
    
    public boolean isFull(){
        if(i.size()==LIMIT)
            return true;
        return false;
    }
}
