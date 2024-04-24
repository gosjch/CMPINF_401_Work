package transit.core;

import transit.people.*;

import java.util.ArrayList;

public abstract class Stop 
{
	protected String stopName;
	public String getStopName()
	{
		return this.stopName;
	}
	public void setStopName(String stopName)
	{
		this.stopName = stopName;
	}
	
	protected int stopNumber;
	public int getStopNumber()
	{
		return this.stopNumber;
	}
	public void setStopNumber(int stopNumber)
	{
		this.stopNumber = stopNumber;
	}
	
	protected double xCoordinate;
	public double getXCoordinate()
	{
		return this.xCoordinate;
	}
	public void setXCoordinate(double x)
	{
		this.xCoordinate = x;
	}
	
	protected double yCoordinate;
	public double getYCoordinate()
	{
		return this.yCoordinate;
	}
	public void setYCoordinate(double y)
	{
		this.yCoordinate = y;
	}
	
	protected ArrayList<Passenger> passengersWaiting = new ArrayList<Passenger>();
	public ArrayList<Passenger> getPassengersWaiting()
	{
		return this.passengersWaiting;
	}
	public void setPassengersWaiting(ArrayList<Passenger> passengersWaiting)
	{
		this.passengersWaiting = passengersWaiting;
	}
	
	protected ArrayList<Passenger> passengersArrived = new ArrayList<Passenger>();
	public ArrayList<Passenger> getPassengersArrived()
	{
		return this.passengersArrived;
	}
	public void setPassengersArrived(ArrayList<Passenger> passengersArrived)
	{
		this.passengersArrived = passengersArrived;
	}
	
	public Stop nextStop;
	
	public Stop(String stopName, double x, double y)
	{
		this.stopName = stopName;
		this.xCoordinate = x;
		this.yCoordinate = y;
		stopNumber = this.stopName.hashCode();
	}
	
	public ArrayList<Passenger> losePassengers(int numPassengers)
	{
		if(numPassengers > passengersWaiting.size())
		{
			// if removing numPassengers makes it go neg, return null
			return null;
		}
		
		ArrayList<Passenger> removedPassengers = new ArrayList<Passenger>();
		for(int i = 0; i < numPassengers; i++)
		{
			// index shifts as elements are removed, just removes first again ang again
			removedPassengers.add(passengersWaiting.remove(0));
		}
		
		return removedPassengers;
	}
	
	public String toString()
	{
		String outputString = "Stop Name: " + stopName +"\n"
				+ "Stop Number: " + stopNumber + "\n"
				+ "X Coordinate: " + xCoordinate +"\n"
				+ "Y Coordinate: " + yCoordinate + "\n"
				+ "# Passengers Waiting: " + passengersWaiting.size() + "\n"
				+ "# Passengers Arrived: " + passengersArrived.size() + "\n";
		
		if(nextStop != null) outputString += "Next Stop: " + nextStop.getStopName();
		
		return outputString;
	}
	
	public abstract void gainPassengers();
	

}
