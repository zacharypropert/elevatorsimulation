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
     
    public int findClosest(int floor, int direction)
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

        if(u.checkRequest(floor) == 1000 && i.checkRequest(floor, direction) == 2000)
            close = 1000;

        return close;
    }
    */

    public void act(int tick)  //sbw - parameter                                                    //CK note- Mmm... Tracing
    {
        System.out.println("The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
        if(direction == 1){
            System.out.println("                   The elevator is going up");

            while(u.checkRequest(floor) != COMPLETED_UP){
                i.addList(u.pickUpAtFloor(floor));  //removes the passenger from the upList and adds them to the inCarList
                floor++;
                System.out.println("Tick#" + tick + ": The Elevator is at floor: " + floor);
                System.out.println(u.checkRequest(floor));   //these two lines are used for debugging
                u.display();
            }

            while(i.checkUpRequest(floor)!=2000){
                s.addList(i.removeAtFloor(floor));  //removes the passenger from the inCarList and adds them to the sinkList

                System.out.println("Tick #" + tick + ":  The Elevator is at floor: " + floor);    //displays the current Floor of the e-car
                
                System.out.println(u.checkRequest(floor));   //these two lines are used for debugging
                u.display();
                floor++;
            }

            if(d.checkRequest(maxFloor) > floor){
                while(floor<d.checkRequest(maxFloor)){
                    floor++;
                    i.addList(d.removeAtFloor(floor));
                }
            }
            direction = -1; //changes the direction to down

        }
        else if(direction == -1){
            System.out.println("                  The Elevator is going down");

            while(d.checkRequest(floor) != COMPLETED_DOWN){
                i.addList(d.pickUpAtFloor(floor));  //removes the passenger from the downList and adds them to the inCarList
                floor--;
                System.out.println("Tick#" + tick + ": The Elevator is at floor: " + floor);
                System.out.println(d.checkRequest(floor));   //these two lines are used for debugging
                d.display();
            }

            while(i.checkDownRequest(floor)!=0){
                s.addList(i.removeAtFloor(floor));  //removes the passenger from the inCarList and adds them to the sinkList

                System.out.println("Tick#" + tick + ": The Elevator is at floor: " + floor);    //displays the current Floor of the e-car

                floor--;
            }

            if(u.checkRequest(0) < floor){
                while(floor<u.checkRequest(0)){
                    floor--;
                    i.addList(u.removeAtFloor(floor));
                }
            }
            direction = 1; //changes the direction to up

        }

    }


}
