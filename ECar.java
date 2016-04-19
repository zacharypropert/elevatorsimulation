/**
 * It is a little clunky right now
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
    private Clock c;
    final int COMPLETED_UP = 1000;
    final int COMPLETED_DOWN = 0;

    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1
     */
    public ECar(UpList u, DownList d, SinkList s, InCarList i, int m, Clock c)                  //CK note-Takes objects in from Controller
    {
        Idle = 1;
        direction = 1; //e-car starts going up initially
        this.u = u;
        this.d = d;
        this.s = s;
        this.i = i;
        maxFloor = m;
        floor = 1;
        this.c = c;

    }
    
    /**
     * 
     * Method Objective: call inCarList and upList to check for next passenger and returns the passenger closest to the eCar
     * at its current floor. If there are no passengers in both lists return 1000 so the elevator will change direction. 
     * 
     * NOTE: Method DOES NOT work as of yet because there is currently no checkRequest in inCarList, so unable to test but
     * I believe with my current understanding of the code this should work. -Zach
     * 
     * InCarList has been updated for checkRequest. Still an issue with some of findClosest()...
     * I believe there is a simple fix for the third if. -Connor
     
    public int findClosest(int floor)
    {
        int ul = 0;
        int il = 0;
        int close = 0;

        ul = u.checkRequest(floor);
        il = i.checkUpRequest(floor);

        if(floor<ul && ul<il) //Changed boolean statement from if(floor<ul && ul<il) -Connor
            close = ul;

        if(floor<il && il<ul) //Changed boolean statement from if(floor<il && il<ul) -Connor
            close = il;

        if(u.checkRequest(floor) == 1000 && i.checkUpRequest(floor) == 2000)
            close = 1000;

        return close;
    }
    */
   
    public void act(int tick)  //sbw - parameter                                                    //CK note- Mmm... Tracing
    {
        System.out.println("The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
        if(direction == 1){
            System.out.println("                   The Elevator is going up");
            
            //given current floor, we'll locate next floor to either discharge or 
            //  pick up, going up
            
            int closeUL = u.checkRequest(floor);
            int closeIC = i.checkUpRequest(floor);
            int closest = (closeUL < closeIC)?closeUL:closeIC;
            if(closest <= maxFloor){ //our next stop
                floor = closest;
                
                i.addList(u.pickUpAtFloor(floor));  //removes passenger from UpList, adds them to InCarList

                s.addList(i.removeAtFloor(floor));  //removes passenger from InCarList, adds them to SinkList
                
                System.out.println("Tick#" + tick + ": The Elevator is at floor: " + floor);
            }
            else{    //change direction
                if(d.checkRequest(maxFloor) > floor)
                    floor = maxFloor;   //restart from top floor
                direction = -1;
            }
        }
        
        else if(direction == -1){
            System.out.println("                  The Elevator is going down");
            
            //given current floor, we'll locate next floor to either discharge or 
            //  pick up, going down
            
            int closeDL = d.checkRequest(floor);
            int closeIC = i.checkDownRequest(floor);
            int closest = (closeDL > closeIC)?closeDL:closeIC;
            if(closest >= 1){ //our next stop
                floor = closest;
               
                i.addList(d.pickUpAtFloor(floor));  //removes passenger from DownList, adds them to InCarList
  
                s.addList(i.removeAtFloor(floor));  //removes passenger from InCarList, adds them to SinkList
                
                System.out.println("Tick#" + tick + ": The Elevator is at floor: " + floor);
            }
            else{    //change direction
                if(u.checkRequest(0) < floor)
                    floor = 1;   //restart from bottom floor
                direction = 1;
            }
        }

    }
    
    public int getFloor()
    {
        return floor;
    }


}
