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

    public void act(int tick)  //sbw - parameter                                                    //CK note- Mmm... Tracing
    {
        if(direction == 1){
            System.out.println("                   The elevator is going up");
            System.out.println("--------------------------------------------------------------");
            System.out.println("                          Up List");
            System.out.println("--------------------------------------------------------------");
            u.display();  //displays the curent passengers waiting to go up

            while(u.checkRequest(maxFloor) != 1000){

                i.addList(u.pickUpAtFloor(floor));  //removes the passenger from the upList and adds them to the inCarList
                System.out.println();
                System.out.println("--------------------------------------------------------------");
                System.out.println("                         InCar List");
                System.out.println("--------------------------------------------------------------");

                i.display();    //displays who is currently in the e-car

                System.out.println();
                System.out.println("******************************************************************");
                System.out.println("                The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
                System.out.println("******************************************************************");
                System.out.println();

                if(u.checkRequest(maxFloor)<floor && i.isEmpty() == true && u.checkRequest(maxFloor)!=1000){
                    floor--;
                    s.addList(i.removeAtFloor(floor));
                }
                else{
                    floor++;
                    s.addList(i.removeAtFloor(floor));
                }

                System.out.println(u.checkRequest(maxFloor));   //these two lines are used for debugging
                u.display();
            }

            while(i.isEmpty() == false){
                s.addList(i.removeAtFloor(floor));  //removes the passenger from the inCarList and adds them to the sinkList

                System.out.println();
                System.out.println("--------------------------------------------------------------");
                System.out.println("                         InCar List");
                System.out.println("--------------------------------------------------------------");

                i.display();    //displays who is currently in the e-car

                System.out.println();
                System.out.println("******************************************************************");
                System.out.println("                The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
                System.out.println("******************************************************************");
                System.out.println();

                System.out.println("--------------------------------------------------------------");
                System.out.println("                         Sink List");
                System.out.println("--------------------------------------------------------------");

                s.display();    //displays the passengers in the sink list
                floor++;
            }

            System.out.println();
            System.out.println("******************************************************************");
            System.out.println("                    This is run number: " + tick);      //displays the current run of the act method
            System.out.println("******************************************************************");
            System.out.println();

            direction = -1; //changes the direction to down
        }

        if(direction == -1){
            System.out.println("                  The Elevator is going down");
            System.out.println("--------------------------------------------------------------");
            System.out.println("                         Down List");
            System.out.println("--------------------------------------------------------------");
            d.display();  //displays the curent passengers waiting to go down

            while(d.checkRequest(maxFloor) != 0){

                i.addList(d.pickUpAtFloor(floor));  //removes the passenger from the downList and adds them to the inCarList
                System.out.println();
                System.out.println("--------------------------------------------------------------");
                System.out.println("                         InCar List");
                System.out.println("--------------------------------------------------------------");
                i.display();    //displays who is currently in the e-car

                System.out.println();
                System.out.println("******************************************************************");
                System.out.println("                The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
                System.out.println("******************************************************************");
                System.out.println();

                if(d.checkRequest(maxFloor) > floor && i.isEmpty() == true && d.checkRequest(maxFloor)!=0){
                    floor++;
                    s.addList(i.removeAtFloor(floor));
                }
                else{
                    floor--;
                    s.addList(i.removeAtFloor(floor));
                }
                
                System.out.println(d.checkRequest(maxFloor));   //these two lines are used for debugging
                d.display();
            }

            while(i.isEmpty() == false){
                s.addList(i.removeAtFloor(floor));  //removes the passenger from the inCarList and adds them to the sinkList

                System.out.println();
                System.out.println("--------------------------------------------------------------");
                System.out.println("                         InCar List");
                System.out.println("--------------------------------------------------------------");

                i.display();    //displays who is currently in the e-car

                System.out.println();
                System.out.println("******************************************************************");
                System.out.println("                The Elevator is at floor:" + floor);        //displays the current Floor of the e-car
                System.out.println("******************************************************************");
                System.out.println();

                System.out.println("--------------------------------------------------------------");
                System.out.println("                         Sink List");
                System.out.println("--------------------------------------------------------------");

                s.display();    //displays the passengers in the sink list
                floor--;
            }
            System.out.println();
            System.out.println("******************************************************************");
            System.out.println("                    This is run number: " + tick);      //displays the current run of the act method
            System.out.println("******************************************************************");
            System.out.println();

            direction = 1; //changes the direction to up
        }

    }

}
