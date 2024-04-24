package transit.bus;

import transit.core.*;

public class Bus extends Vehicle
{
	private int capacity;
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	public int getCapacity()
	{
		return this.capacity;
	}

	public Bus(String driver, double sp, BusRoute rt, BusStop stop) 
	{
		super(driver, sp, rt, stop);
		this.capacity = 35;
	}
	
	public Bus(String driver, double sp, BusRoute rt)
	{
		super(driver, sp, rt);
		this.capacity = 35;
	}
	
	public double move(int minutes)
	{
		double totalDistance = 0;
		if(isStopped)
		{
			letPassengersOn();
			isStopped = false;
			updateDestination(); // should set a new destination based on route
		}
		
		double potentialDistance = this.speed * (minutes/60.0);  // speed = d/t, speed * t = d
		
		// Movement in X-axis
		double deltaX = currentDestination.getXCoordinate() - this.xCoordinate;
		// this might be negative so check if abs val is > 0 to see if it can move
		if(Math.abs(deltaX) > 0)
		{
			// It's movement in X is either the distance from the destination or the
			// distance it can travel in total, whichever is smaller 
			// if all its distance is moved up in the x this is touched on later for the Y
			double moveX = Math.min(Math.abs(deltaX), potentialDistance) * Math.signum(deltaX); // signum here makes sure we're using the right direction
			
			this.xCoordinate += moveX; // update bus X location
			totalDistance += Math.abs(moveX); // add to distance traveled
			potentialDistance -= Math.abs(moveX); // remove distance traveled from distance we can keep going
		}
		
		// first check if we can move and if we reached destination after X-axis movement
		if(potentialDistance > 0 && this.xCoordinate == currentDestination.getXCoordinate())
		{
			// if both true, we now move in Y-direction
			double deltaY = currentDestination.getYCoordinate() - this.yCoordinate;
			double moveY = Math.min(Math.abs(deltaY), potentialDistance) * Math.signum(deltaY);
			this.yCoordinate += moveY;
			totalDistance += Math.abs(moveY);
		}
		
		// check if we've reached true destination
		if(this.xCoordinate == currentDestination.getXCoordinate() && this.yCoordinate == currentDestination.getYCoordinate())
		{
			isStopped = true;
			letPassengersOff();
		}
		
		return totalDistance;
	}

}
