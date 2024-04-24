package transit.train;

import java.util.Random;

import transit.core.*;
import transit.people.Passenger;

public class MetroStation extends Stop 
{

	public MetroStation(String name, double x, double y) 
	{
		super(name, x, y);
	}
	
	public void gainPassengers()
	{
		Random rng = new Random();
		int numPassengers = rng.nextInt(10) + 1;
		
		for(int i = 0; i < numPassengers; i++)
		{
			passengersWaiting.add(new Passenger(this));
		}
		
	}

}
