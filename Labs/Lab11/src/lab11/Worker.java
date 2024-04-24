package lab11;

public class Worker implements Observer 
{
	public String name;
	public int energy;
	public int producedToothbrushes;
	public String currentActivity;

	public Worker(String name) 
	{
		this.name = name;
		energy = 0;
	}
	
	public void work()
	{
		if(energy!=0)
		{
			producedToothbrushes+=100;
			energy--;
		}
		else
		{
			System.out.println("Worker is exhausted.");
		}
	}
	
	public void rest()
	{
		if(energy != 30) energy++;
	}
	
	public String toString()
	{
		String output = "";
		
		if(currentActivity.equals("rest")) 
		{
			output += name + " is resting and has " + energy + " energy.";
		}
		else if(currentActivity.equals("work"))
		{
			output += name + " is currently working and has made " + producedToothbrushes + " toothbrushes and has " + energy + " energy.";
		}
		else if(currentActivity.equals("transit"))
		{
			output += name + " is in transit.";
		}
		
		return output;
	}
	
	public void update(String event)
	{
		// event will be formatted like this (hh:mm) like (13:23) or (8:02)
		int colonSpot = event.indexOf(':');
		int hour = Integer.parseInt(event.substring(0,colonSpot)); // Start at beginning, ignore colon spot
		int minutes = Integer.parseInt(event.substring(colonSpot + 1)); // Start after colon spot
		
		// At home or at lunch
		if( (hour <= 7 || (hour == 8 && minutes < 30)) || (hour == 12) || ( hour > 17 || (hour == 17 && minutes >= 30) ) )
		{
			rest();
			currentActivity = "rest";
		}
		
		// going to or leaving work
		if( (hour == 8 && minutes >= 30) || (hour == 17 && minutes < 30) )
		{
			currentActivity = "transit";
		}
		
		// working before or after lunch
		if( (hour >= 9 && hour < 12) || (hour >= 13 && hour < 17) )
		{
			work();
			currentActivity = "work";
		}
		
		if(hour == 23)
		{
			producedToothbrushes = 0;
		}
		
	}
}
