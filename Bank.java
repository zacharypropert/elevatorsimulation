import java.util.ArrayList;
import java.util.Random;
/**
 * Will create a bank of elevators
 */
public class Bank
{
    private int amount;     //amount of elevators
    private int maxFloor;   //number of floors
    private UpList u;
    private DownList d;
    public SinkList s;
    private Clock c;
    private ArrayList<ECar> ecar;
    private Random rand;

    /**
     * recieves the number of elevators and
     * the number of maximum floors
     */
    public Bank(int amount, int maxFloor, UpList u, DownList d, SinkList s, Clock c)
    {
        this.maxFloor = maxFloor;
        this.amount = amount;
        this.u = u;
        this.d = d;
        this.s = s;
        this.c = c;
        ecar= new ArrayList<ECar>();    // an array list to keep track of the elevators
        rand = new Random();

        for(int x =0; x<amount; x++){
            ecar.add(new ECar(maxFloor,c));
        }
    }

    /**
     * runs through the program
     * checks for the closest car to a request
     * then the car goes to the request and continues until 
     * it has no more requests and it is empty
     * then it waits for another request
     */
    public void act(int tick){
        for(int i =0; i<ecar.size();i++){
            int a =i;
            int b=i;
            if(u.checkRequest(0)!= 1000 || ecar.get(a).checkUp()!=2000){
                //int a = getNearestUpCar(u.checkRequest(0));

                int closeUL = u.checkRequest(0);
                int closeIC = ecar.get(a).checkUp();
                int closest = (closeUL < closeIC)?closeUL:closeIC;
                if(closest <= maxFloor){
                    //block used to change the floor of the car and pick up the passenger at said floor
                    System.out.println("Tick#"+tick+":");
                    System.out.println("Elevator#"+a+" is at floor: "+ ecar.get(a).getFloor()+ " and is currently going up");
                    ecar.get(a)
                    .changeFloor(closest)
                    .pickUp(u);
                    s.addList(ecar.get(a).removePassenger());
                }
                else{
                    System.out.println("Elevator#"+a+ " is now going down");
                    ecar.get(a).changeDirection();
                }

            }
            else if(d.checkRequest(maxFloor) != 0||ecar.get(b).checkDown()!=0){
                //int b = getNearestDownCar(d.checkRequest(maxFloor));
                
                int closeDL = d.checkRequest(maxFloor);
                int closeIC = ecar.get(b).checkDown();
                int closest = (closeDL > closeIC)?closeDL:closeIC;
                if(closest >= 1){
                    //block used to change the floor of the car and pick up the passenger at said floor
                    System.out.println("Tick#:"+tick+":");
                    System.out.println("Elevator#"+b+" is at floor: "+ ecar.get(b).getFloor() + " and is currently going down");
                    ecar.get(b)
                    .changeFloor(closest)
                    .pickUp(d);
                    s.addList(ecar.get(b).removePassenger());
                }
                else{
                    System.out.println("Elevator#" +b+ " is now going up");
                    ecar.get(b).changeDirection();
                }
            }
        }
    }

    /**
     * Checks for the closest ecar going UP 
     * returns the position in the ECar list
     * of the closest ECar
     */
    public int getNearestUpCar(int floor)
    {
        ECar car = new ECar(maxFloor, c);
        int e = 0;
        ArrayList<Integer> e2 = new ArrayList<>(); //used if multiple cars are closest
        int r;

        for(int x = 0; x<ecar.size(); x++){
            if(ecar.get(x).getDirection() == 1){
                if(ecar.get(x).getFloor() <= floor && 
                ecar.get(x).getFloor() > car.getFloor() && ecar.get(x).isFull()==false){
                    car= ecar.get(x);
                    e = x;
                    for(int a = 0; a<e2.size();a++)
                        e2.remove(a);
                }
                else if(ecar.get(x).getFloor() <= floor && 
                ecar.get(x).getFloor() == car.getFloor() && ecar.get(x).isFull() == false){
                    e2.add(x);
                    e2.add(e);
                }
            }
        }

        if(e2.size()>0){
            r=rand.nextInt(e2.size());
            return e2.get(r);
        }

        return e;
    }

    /**
     * Checks for the closest ecar going DOWN
     * returns the position in the ECar list
     * of the closest ECar
     */
    public int getNearestDownCar(int floor)
    {
        ECar car = new ECar(maxFloor, c);
        int e = 0;
        ArrayList<Integer> e2  = new ArrayList<>();
        int r;

        for(int x = 0; x<ecar.size(); x++){
            if(ecar.get(x).getDirection() == -1){
                if(ecar.get(x).getFloor() >= floor && 
                ecar.get(x).getFloor() < car.getFloor()&& ecar.get(x).isFull()==false){
                    car= ecar.get(x);
                    e= x;
                    for(int a = 0; a<e2.size(); a++)
                        e2.remove(a);
                }
                else if(ecar.get(x).getFloor() <= floor && 
                ecar.get(x).getFloor() == car.getFloor()&& ecar.get(x).isFull()==false){
                    e2.add(x);
                    e2.add(e);
                }
            }
        }        

        if(e2.size()>0){
            r=rand.nextInt(e2.size());
            return e2.get(0);
        }

        return e;

    }

}
