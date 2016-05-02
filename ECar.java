/**
 * It is a little clunky right now
 * 
 * Jeremy & Troy
 * February 20, 2016
 */
public class ECar
{
    private int Idle;
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
    final int COMPLETED_IN = 1000;
    final int COMPLETED_DOWN = 0;
    private int elevatorBank;

    /**
     * Constructor for objects of class ECar
     * Starts the car at floor 1 going up
     */
    public ECar(UpList u, DownList d, SinkList s, InCarList i, int m, Clock c, GUI g)                  //CK note-Takes objects in from Controller
    {
        Idle = 1;
        direction = 1; //1 = going up, -1 = going down
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
     * The main component for logic
     * 
     */
    public void act(int tick)                                                    
    {
        gui.appendText("The Elevator is at floor:" + floor);   //displays the current Floor of the e-car
        //gui.setFloorNum(floor);
        
        if(direction == 1){
            gui.appendText("                   The Elevator is going up");
            
            //given current floor, we'll locate next floor to either discharge or 
            //  pick up, going up
            
            int closeUL = u.checkRequest(floor);
            int closeIC = i.checkUpRequest(floor);
            int closest = (closeUL < closeIC)?closeUL:closeIC;
             
            if((closest <= maxFloor)&& (closest >=floor)){ //finds the next stop
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

            if((closest >= 1)&&(closest <=floor)){ //our next stop
                floor = closest;
               
                i.addList(d.pickUpAtFloor(floor));  //removes passenger from DownList, adds them to InCarList
  
                s.addList(i.removeAtFloor(floor));  //removes passenger from InCarList, adds them to SinkList
                
                gui.appendText("Tick#" + tick + ": The Elevator is at floor: " + floor);
            }
            else{    //change direction
                direction = 1;
                gui.appendText("                  The Elevator now going up");
            
                //sbw---if none to pick up above, restart at 1
                
                int closeUL = u.checkRequest(floor);
                closeIC = i.checkUpRequest(floor);
                closest = (closeUL < closeIC)?closeUL:closeIC;
                
                if((closest == COMPLETED_UP)|| (closest== COMPLETED_IN))
                   floor=1;
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
