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
    private GUI gui;
    final int COMPLETED_UP = 1000;
    final int COMPLETED_IN = 1000;  //sbw not sure why it's diff than 1000
    final int COMPLETED_DOWN = 0;
    private int elevatorBank;

    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1
     */
    public ECar(UpList u, DownList d, SinkList s, InCarList i, int m, Clock c, GUI g)                  //CK note-Takes objects in from Controller
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
        gui = g;
        elevatorBank = 1;
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
   
    public void act(int tick)                                                    //CK note- Mmm... Tracing
    {
        gui.appendText("The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
        //gui.setFloorNum(floor);
        
        if(direction == 1){
            gui.appendText("                   The Elevator is going up");
            
            //given current floor, we'll locate next floor to either discharge or 
            //  pick up, going up
            
            int closeUL = u.checkRequest(floor);
            int closeIC = i.checkUpRequest(floor);
            int closest = (closeUL < closeIC)?closeUL:closeIC;
            //.........debug
            //gui.appendText("...going up, closest is "+closest);
             
            if((closest <= maxFloor)&& (closest >=floor)){ //our next stop
                floor = closest;
                
                i.addList(u.pickUpAtFloor(floor));  //removes passenger from UpList, adds them to InCarList

                s.addList(i.removeAtFloor(floor));  //removes passenger from InCarList, adds them to SinkList
                
                gui.appendText("Tick#" + tick + ": The Elevator is at floor: " + floor);
            }
            else{    //change direction
                 direction = -1;  //sbw moved
                 gui.appendText("                  The Elevator now going down");
            
                
               //if any passengers are above us waiting to go down
               //   we set to top floor,. going down
               if((d.checkRequest(maxFloor) > floor) 
                     || (i.checkDownRequest(maxFloor) > floor))
                {    floor = maxFloor;   //restart from top floor
                      gui.appendText("Tick#" + tick + ": The Elevator is at floor: " + floor);
                    } //direction = -1;
            }
        }
        
        else if(direction == -1){
            gui.appendText("                  The Elevator is going down");
            
            //given current floor, we'll locate next floor to either discharge or 
            //  pick up, going down
            
            int closeDL = d.checkRequest(floor);
            int closeIC = i.checkDownRequest(floor);
            int closest = (closeDL > closeIC)?closeDL:closeIC;
            //.........debug
            //System.out.println("...going down, closest is "+closest);
            if((closest >= 1)&&(closest <=floor)){ //our next stop
                floor = closest;
               
                i.addList(d.pickUpAtFloor(floor));  //removes passenger from DownList, adds them to InCarList
  
                s.addList(i.removeAtFloor(floor));  //removes passenger from InCarList, adds them to SinkList
                
                gui.appendText("Tick#" + tick + ": The Elevator is at floor: " + floor);
            }
            else{    //change direction
                direction = 1;
                gui.appendText("                  The Elevator now going up");
            
                //sbw: 
                //-----------if none to pick up above, restart at 1
                  int closeUL = u.checkRequest(floor);
                  closeIC = i.checkUpRequest(floor);
                  closest = (closeUL < closeIC)?closeUL:closeIC;
                  if((closest == COMPLETED_UP)|| (closest== COMPLETED_IN))
                     floor=1;
               //------------------
               //sbw ???why? if(u.checkRequest(0) < floor)
               //             floor = 1;   //restart from bottom floor
                //direction = 1;
            }
        }

    }

    public int getElevators()
    {
        return elevatorBank;
    }
    
    public int getFloor()
    {
        return floor;
    }
}
