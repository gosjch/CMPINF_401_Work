package transit.bus;

import transit.core.*;
import transit.people.*;
import java.util.Random;

public class BusStop extends Stop
{

	public BusStop(String name, double x, double y) 
	{
		super(name, x, y);
	}
	
	public void gainPassengers()
	{
		Random rng = new Random();
		int numPassengers = rng.nextInt(5) + 1;
		
		for(int i = 0; i < numPassengers; i++)
		{
			passengersWaiting.add(new Passenger(this));
		}
		
	}

}
