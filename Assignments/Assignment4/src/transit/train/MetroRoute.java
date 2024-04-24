package transit.train;

import transit.bus.Bus;
import transit.bus.BusStop;
import transit.core.*;

public class MetroRoute extends Route
{

	public MetroRoute(int routeNum, String routeDesc, MetroStation first) 
	{
		super(routeNum, routeDesc, first);
	}
	
	@Override
	public String toString()
	{
		String returnString= "BusRoute#:" + routeNumber + "\n"
				+ "Description: " + routeDescription + "\n"
				+ "First Stop: " + "\t" + firstStop + "\n" 
				+ "Trains: " + "\t";
		
		for(int i = 0; i < vehicles.size(); i++)
		{
			returnString += vehicles.get(i).toString();
		}
		
		returnString += "\nStops: " + "\t";
		Stop currentStop = (MetroStation) firstStop;
		
		do 
		{
			returnString += currentStop;
			currentStop = currentStop.nextStop;
			
		}
		while(!currentStop.equals(firstStop));
		
		return returnString;
	}
	
	public void addDriver(String name, double speed)
	{
		// add new bus to route
		vehicles.add(new Train(name, speed, 3, this));
	}
	
	public void addStop(String stopName, double x, double y)
	{
		// create new stop
		Stop newStop = new MetroStation(stopName, x, y);
		
		// if no stops in the route, initialize the route with this stop.
	    if (firstStop == null) 
	    {
	        firstStop = newStop;
	        newStop.nextStop = firstStop; // point to itself
	        return;
	    }
		
		Stop currentStop = firstStop;
		
		// traverse to the end of the route
		while(currentStop.nextStop != firstStop)
		{
			currentStop = currentStop.nextStop;
		}
		
		// replace stop
		currentStop.nextStop = newStop;
		newStop.nextStop = firstStop; // complete circle
	}

}
