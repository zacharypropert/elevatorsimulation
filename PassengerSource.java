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
    // private Passenger newPassenger;
    /**
     * Constructor for objects of class PassengerSource
     */
    public PassengerSource(UpList upList, DownList downList, int maxFloor, Clock clock) //JEREMY!
    {
        //upList=new ArrayList<>();
        //downList=new ArrayList<>();
        //createPassenger();
        this.clock= clock; //JEREMY
        rgenFloor = rgen.nextInt(3);
        this.upList = upList;
        this.downList = downList;
    }

    /**
     *Creates and places passenger
     */
    public void createPassenger()
    {
        int halfMax = maxFloor / 2;
        int randomInt = halfMax + maxFloor;
        int start = rgen.nextInt(randomInt - (halfMax / 4) + 1);
        // rgenFloor = rgen.nextInt(randomInt);
        int end = rgen.nextInt(randomInt + 1);
        if(start > maxFloor)
            start = 1;
        if(end > maxFloor)
            end = 1;
        while(start==end)
        {
            end = rgenFloor;
            rgenFloor = rgen.nextInt(3);
        }
        Passenger newPassenger = new Passenger(start, end, clock.getTick());//JEREMY
        if(newPassenger.getStart() < newPassenger.getDestination())
        {
            upList.addPassenger(newPassenger);
        }else
        {
            downList.addPassenger(newPassenger);
        }
    }

 
    public void test()
    {

        System.out.println("Up List");

        upList.Display();

        System.out.println();
        System.out.println();
        System.out.println("Down List");
        downList.Display();

    }
    
    public void act(int tick)
    {
        int count = 0;
        while(count <= tick)
        {        
            createPassenger();
            count++;
        }

        test();

    }

}
