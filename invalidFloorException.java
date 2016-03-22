
/**
 * An exception class thrown in Controller.java and PassengerSource.java
 * 
 * @Zach
 * 
 */
public class invalidFloorException extends Exception
{
    public invalidFloorException()
    {
        super("Invalid amount of floors, there cannot be less than 2 floors!");
    }
    
    public invalidFloorException(int floor)
    {
        super(floor + " is an invalid floor!");
    }
}
