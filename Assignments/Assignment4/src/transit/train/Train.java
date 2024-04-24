package transit.train;

import transit.core.*;

public class Train extends Vehicle
{
	private int cars;
	public int getCars()
	{
		return this.cars;
	}
	public void setCars(int cars)
	{
		this.cars = cars;
	}

	public Train(String driver, double sp, int cars, MetroRoute rt, MetroStation stop) 
	{
		super(driver, sp, rt, stop);
		this.cars = cars;
	}
	
	public Train(String driver, double sp, int cars, MetroRoute rt)
	{
		super(driver, sp, rt);
		this.cars = cars;
	}
	
	public double move(int minutes)
	{
		double totalDistance = 0;
		if(this.isStopped)
		{
			letPassengersOn();
			this.isStopped = false;
			updateDestination(); // should set a new destination based on route
		}
		
		double potentialDistance = speed * (minutes/60.0); // convert minutes to hours
		
		// how far destination is from train in a straight line
		double deltaX = currentDestination.getXCoordinate() - this.xCoordinate;
		double deltaY = currentDestination.getYCoordinate() - this.yCoordinate;
		double destinationDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		
		// if it didn't reach the destination, calculate its position using the ratio 
		// of potential distance to destination distance, which is how we find out the new
		// x and y coordinates of the train
		if(potentialDistance < destinationDistance)
		{
			double travelRatio = potentialDistance / destinationDistance;
			this.xCoordinate += deltaX * travelRatio;
			this.yCoordinate += deltaY * travelRatio;
			totalDistance += potentialDistance;
		}
		else
		{
			//reached destination
			this.xCoordinate = currentDestination.getXCoordinate();
			this.yCoordinate = currentDestination.getYCoordinate();
			this.isStopped = true;
			letPassengersOff();
			totalDistance += destinationDistance;
		}
		
		return totalDistance;
	}
	
	public int getCapacity()
	{
		// 120 people per train car
		return 120*cars;
	}

}
