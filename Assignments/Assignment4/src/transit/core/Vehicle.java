package transit.core;

import java.util.ArrayList;

import transit.people.Passenger;

public abstract class Vehicle
{
	protected String identifier;
	protected String driverName;
	protected double speed;
	protected double xCoordinate;
	public double getX()
	{
		return this.xCoordinate;
	}
	protected double yCoordinate;
	public double getY()
	{
		return this.yCoordinate;
	}
	protected Route route;
	public Stop currentDestination;
	protected ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	protected boolean isStopped;
	
	public String toString()
	{
		return "Identifier: " + identifier + "\n" +
				"Driver Name: " + driverName + "\n" +
				"Speed: " + speed + " \n" + 
				"Coordinates: (" + xCoordinate + "," + yCoordinate +") \n" +
				"Route #: " + route.getRouteNumber() + "\n" +
				"Current Destination: " + "\t" + currentDestination + "\n" +
				"# of Passengers: " + passengers.size() + "\n" +
				"State: Is stopped = " + isStopped;
	}
	
	public Vehicle(String driverName, double speed, Route route, Stop stop)
	{
		// identifier set as the hashcode of the driver's name as a string (following UML)
		this.driverName = driverName;
		this.identifier = Integer.toString(driverName.hashCode());
		
		this.speed = speed;
		this.route = route;
		
		// if no stop is given, default to the first stop of the route
		if(stop == null)
		{
			currentDestination = this.route.firstStop;
		}
		else
		{
			// otherwise destination set to the given stop
			this.currentDestination = stop;
		}
		
		this.xCoordinate = currentDestination.xCoordinate;
		this.yCoordinate = currentDestination.yCoordinate;
		this.isStopped = true;
	}
	
	public Vehicle(String driverName, double speed, Route route)
	{
		this(driverName, speed, route, null);
	}
	
	
	public void thankTheDriver() 
	{
		System.out.println("Thanks " + driverName + "!"); 
	}
	
	public int letPassengersOff()
	{
		int counter = 0;
		for(Passenger occupant : passengers)
		{
			if(occupant.getDestination() == currentDestination)
			{
				currentDestination.passengersArrived.add(occupant);
				counter++;
			}
		}
		return counter;
	}
	
	public int letPassengersOn()
	{
		int counter = 0;
		
		// makes sure passengers let on don't go over capacity
		int potentialPassengers = currentDestination.passengersWaiting.size();
		if(potentialPassengers > (getCapacity() - passengers.size()))
		{
			potentialPassengers = getCapacity() - passengers.size();
		}
		
		// adds potential passengers to passengers, removes from destination object
		for(Passenger element : currentDestination.losePassengers(potentialPassengers))
			{
			passengers.add(element);
			counter++;
			}
		
		// return amount of passengers added
		return counter;
	}
	
	// not in UML, assuming i'll be doing this a lot
	public void updateDestination()
	{
		this.currentDestination = this.currentDestination.nextStop;
	}
	
	public abstract double move(int minutes);
	
	public abstract int getCapacity();
}
