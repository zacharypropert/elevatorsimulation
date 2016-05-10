import java.util.ArrayList;
/**
 * Will create a bank of elevators
 */
public class Bank
{
    private int amount;     //amount of elevators
    private int maxFloor;   //number of floors
    private UpList u;
    private DownList d;
    private SinkList s;
    private Clock c;
    private ArrayList<Ecar2> ecar;       //used to pick an elevator from the list
-                                       //that is not the first car

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
        ecar= new ArrayList<Ecar2>();    // an array list to keep track of the elevators

        for(int x =0; x<amount; x++){
            ecar.add(new Ecar2(maxFloor,c));
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
       if(u.checkRequest(0)!= 1000){
           int a = getNearestUpCar(u.checkRequest(0));
           
           int closeUL = u.checkRequest(0);
           int closeIC = ecar.get(getNearestUpCar(a)).checkUp();
           int closest = (closeUL < closeIC)?closeUL:closeIC;
           if(closest <= maxFloor){
               //block used to change the floor of the car and pick up the passenger at said floor
               System.out.println("Tick#"+tick+":");
               System.out.println("Elevator#"+a+" is at floor: "+ ecar.get(a).getFloor()+ " and is currently going up");
               ecar.get(a)
                    .changeFloor(u.checkRequest(0))
                    .pickUp(u);
               s.addList(ecar.get(a).removePassenger());
                }
           else{
               //changes the direction of the elevator
               System.out.println("Elevator#"+a+ " is now going down");
               ecar.get(a).changeDirection();
            }
            
       }
       else if(d.checkRequest(maxFloor) != 0){
           int b = getNearestDownCar(d.checkRequest(maxFloor));
           
           
           int closeDL = d.checkRequest(maxFloor);
           int closeIC = ecar.get(b).checkDown();
           int closest = (closeDL > closeIC)?closeDL:closeIC;
           if(closest >= 1){
               //block used to change the floor of the car and pick up the passenger at said floor
               System.out.println("Tick#:"+tick+":");
               System.out.println("Elevator#"+b+" is at floor: "+ ecar.get(b).getFloor() + " and is currently going down");
               ecar.get(b)
                    .changeFloor(d.checkRequest(maxFloor))
                    .pickUp(d);
               s.addList(ecar.get(b).removePassenger());
            }
           else{
               System.out.println("Elevator#" +b+ " is now going up");
               ecar.get(b).changeDirection();
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
        Ecar2 car = new Ecar2(maxFloor, c);
        int e = 0;
        ArrayList<Integer> e2 = new ArrayList<>(); //used if multiple cars are closest

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
                ecar.get(x).getFloor() == car.getFloor()){
                    e2.add(x);
                    e2.add(e);
                }
            }
        }

        if(e2.size()>0){
            return e2.get(0);
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
        Ecar2 car = new Ecar2(maxFloor, c);
        int e = 0;
        ArrayList<Integer> e2  = new ArrayList<>();

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
                ecar.get(x).getFloor() == car.getFloor()){
                    e2.add(x);
                    e2.add(e);
                }
            }
        }        

        if(e2.size()>0){
            return e2.get(0);
        }

        return e;

    }
    

}
