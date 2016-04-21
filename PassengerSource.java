import java.util.*;
/**
 * Generates Passengers with randomized attributes, and inserts into UpList, DownList collections. Randomly generates the starting location and the destination..
 *
 * @Zach & Connor
 * @2/16/2016
 */
public class PassengerSource
{
    private UpList upList;
    private DownList downList;
    Random rgen = new Random();
    private int maxFloor;
    private int rgenFloor;
    private Clock clock; //JEREMY ADDED!
    private static int passCount = 0; //CKnote - Keeps track of passengers with iD
    /**
     * Constructor for objects of class PassengerSource
     */
    public PassengerSource(UpList upList, DownList downList, int maxFloor, Clock clock) //JEREMY!
    {
        //upList=new ArrayList<>();
        //downList=new ArrayList<>();
        //createPassenger();
        this.clock= clock; //JEREMY
        this.maxFloor = maxFloor; //Zach
        this.upList = upList;
        this.downList = downList;
    }

    /**
     *Creates and places passenger
     */
    public void createPassenger() throws invalidFloorException
    {

        int halfMax = maxFloor / 2;
        int randomInt = halfMax + maxFloor;
        int start = rgen.nextInt(randomInt - (halfMax / 4) + 1);
        int end = rgen.nextInt(randomInt + 1);
        
        if(start > maxFloor)
            start = 1;
        if(end > maxFloor)
            end = 1;

        if(start == 0 || end == 0 || start==end ) //zach
            throw new invalidFloorException(start); //zach
       
        Passenger newPassenger = new Passenger(start, end, clock.getTick());//JEREMY

        if(newPassenger.getStart() < newPassenger.getDestination())
        {
            upList.addPassenger(newPassenger);
        }else
        {
            downList.addPassenger(newPassenger);
        }        
        passCount++;
    }

    public void test()
    {
        System.out.println("--------------------------------------------------------------");
        System.out.println("                          Up List");
        System.out.println("--------------------------------------------------------------");
        upList.display();   //sbw display()

        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("                         Down List");
        System.out.println("--------------------------------------------------------------");
        downList.display();  //sbw
               
        System.out.println();
        System.out.println("******************************************************************");
        System.out.println();

    }

    public void act()//Connor no int tick
    {
       try{
            createPassenger(); //zach
        }
        catch(invalidFloorException e) //zach
        { 
            act(); //zach 
        }
    }
}
