
/**
 * Write a description of class invalidFloorException here.
 * 
 * @Zach
 * @version (a version number or a date)
 */
public class invalidFloorException extends Exception
{
    public invalidFloorException()
    {
        super("Invalid amount of floors, there cannot be less than 2 floors!");
    }
    
    public invalidFloorException(int floor)
    {
        super(floor + " is an invalid amount of floors, there cannot be less than 2 floors!");
    }
}
