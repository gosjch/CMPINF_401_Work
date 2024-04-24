package transit.core;

import java.util.ArrayList;

public abstract class Route 
{
	protected int routeNumber;
	public int getRouteNumber()
	{
		return this.routeNumber;
	}
	public void setRouteNumber(int routeNum)
	{
		this.routeNumber = routeNum;
	}
	
	protected String routeDescription;
	public String getRouteDescription()
	{
		return this.routeDescription;
	}
	public void setRouteDescription(String routeDescription)
	{
		this.routeDescription = routeDescription;
	}
	
	public Stop firstStop;
	
	protected ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	public ArrayList<Vehicle> getVehicles()
	{
		return this.vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles)
	{
		this.vehicles = vehicles;
	}
	
	public String toString()
	{
		String returnString= "Route#:" + routeNumber + "\n"
				+ "Description: " + routeDescription + "\n"
				+ "First Stop: " + "\t" + firstStop + "\n" 
				+ "Vehicles: " + "\t";
		
		for(int i = 0; i < vehicles.size(); i++)
		{
			returnString += vehicles.get(i).toString();
		}
		
		returnString += "\nStops: " + "\t";
		Stop currentStop = firstStop;
		
		do 
		{
			returnString += currentStop;
			currentStop = currentStop.nextStop;
			
		}
		while(!currentStop.equals(firstStop));
		
		return returnString;
	}

	public Route(int routeNumber, String routeDesc, Stop firstStop) 
	{
		this.routeNumber = routeNumber;
		this.routeDescription = routeDesc;
		this.firstStop = firstStop;
		this.firstStop.nextStop = firstStop; // set up circular list
	}

	public void gainPassengersAll()
	{
		// check if route has any stops
		if(firstStop == null) return;
		
		Stop currentStop = firstStop;
		// call gainPassengers for the first stop then move onto the next
		do 
		{
			currentStop.gainPassengers();
			currentStop = currentStop.nextStop;
			
		}
		while(!currentStop.equals(firstStop));
	}
	
	public void moveAll(int minutes)
	{
		// Call move with given minutes on every vehicle in the route
		for(Vehicle vehicle : vehicles)
		{
			vehicle.move(minutes);
		}
	}
	
	public abstract void addDriver(String name, double speed);
	
	public abstract void addStop(String stopName, double x, double y);

}
