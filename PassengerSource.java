import java.util.*;

/**
 * Generates Passengers with randomized attributes, and inserts into UpList, DownList collections. Randomly generates the starting location and the destination..
 *
 * @Zach & Connor
 * @2/16/2016
 */
public class PassengerSource
{

    private ArrayList<Passenger>upList;  
    private ArrayList<Passenger> downList;  
    Random rgen = new Random();
    private int totalFloors = 3;
    private int rgenFloor;
    // private Passenger newPassenger;

    /**
     * Constructor for objects of class PassengerSource
     */
    public PassengerSource()
    {
        upList=new ArrayList<>();
        downList=new ArrayList<>();
        //createPassenger();
        rgenFloor = rgen.nextInt(3);    
    }   

    /**
     *Creates and places passenger
     */

    public void createPassenger()
    {
        int maxFloor = 100;
        int halfMax = maxFloor / 2;
        int randomInt = halfMax + maxFloor;
        int start = rgen.nextInt(randomInt - (halfMax / 4));
       // rgenFloor = rgen.nextInt(randomInt);  
        int end = rgen.nextInt(randomInt);
        
        if(start > maxFloor)
            start = 0;
            
        if(end > maxFloor)
            end = 0;
            
        while(start==end)
        {
            end = rgenFloor;
            rgenFloor = rgen.nextInt(3);  
        }

        Passenger newPassenger = new Passenger(start, end);

        if(newPassenger.getStart() < newPassenger.getDestination())
        {
            upList.add(newPassenger);
        }else  
        {
            downList.add(newPassenger);
        }
    }

    public void test()
    {
        
        System.out.println("Up List");
        for(Passenger u: upList)
            System.out.println(u.toString());

        System.out.println();
        System.out.println();
        System.out.println("Down List");

        for(Passenger d: downList)
            System.out.println(d.toString());

    }

}
