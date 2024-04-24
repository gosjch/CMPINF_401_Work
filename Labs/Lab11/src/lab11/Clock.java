package lab11;

import java.util.ArrayList;

public class Clock 
{
	public int hour;
	public int minute;
	ArrayList<Observer> observerCollection = new ArrayList<Observer>();

	public Clock() 
	{
		hour = 0;
		minute = 0;
	}
	
	public void registerObserver(Observer observer)
	{
		observerCollection.add(observer);
	}
	
	public void unregisterObserver(Observer observer)
	{
		observerCollection.remove(observer);
	}
	
	public void notifyObservers()
	{
		for(Observer element : observerCollection)
		{
			element.update(getTime());
		}
	}
	
	public String getTime()
	{
		if(minute > 9)
		{
			return hour + ":" + minute;
		}
		else
		{
			return hour + ":0" + minute;
		}
	}
	
	public void tick()
	{
		// Move time forward by 15 minutes, if it goes over 59 move hour forward and add remaining minutes
		minute += 15;
		if(minute > 59)
		{
			minute = 60 - minute;
			hour++;
			if(hour >23)
			{
				hour = 0;
			}
		}
		
		notifyObservers();
	}

}
