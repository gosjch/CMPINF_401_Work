package lab11;

public class Boss implements Observer 
{
	public boolean factoryOpen;

	public Boss() 
	{
		factoryOpen = false;
	}
	
	public void update(String event)
	{
		// event will be formatted like this (hh:mm) like (13:23) or (8:02)
		int colonSpot = event.indexOf(':');
		int hour = Integer.parseInt(event.substring(0,colonSpot)); // Start at beginning, ignore colon spot
		int minutes = Integer.parseInt(event.substring(colonSpot + 1)); // Start after colon spot
		
		if(hour > 8 || (hour == 8 && minutes >= 30))
		{
			factoryOpen = true;
		}
		
		if(hour >= 18)
		{
			factoryOpen = false;
		}
		
	}
	
	public String toString()
	{
		if(factoryOpen)
		{
			return "The factory is open!";
		}
		else
		{
			return "The factory is closed!";
		}
	}

}
