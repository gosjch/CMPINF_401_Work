package lab6;

import java.util.Arrays;

public class Film 
{
	private String filmName;
	public String getFilmName()
	{
		return this.filmName;
	}
	
	private int year;
	public int getYear()
	{
		return this.year;
	}
	
	private int runtime;
	public int getRuntime()
	{
		return this.runtime;
	}
	
	private String director;
	public String getDirector()
	{
		return this.director;
	}
	public String[] actors = new String[0];
	
	public Film(String filmName, int year, int runtime)
	{
		this.director = "Alan Smithee";
		this.filmName = filmName;
		
		if(runtime < 0)
		{
			throw new IllegalStateException("Runtime cannot be negative");
		}
		else
		{
			this.runtime = runtime;
		}
		
		if(year <= 1888)
		{
			throw new IllegalStateException("Year must be after 1888");
		}
		else
		{
			this.year = year;
		}
	}
	
	public Film(String filmName, int year, int runtime, String director)
	{
		this(filmName, year, runtime);
		this.director = director;
	}
	
	public String getFormattedRuntime()
	{
		int hours = this.runtime/60;
		int mins = this.runtime % 60;
		return hours + " hours and " + mins + " minutes.";
	}
	
	public String toString()
	{
		if(actors.length > 0)
		{
			return filmName + " has a runtime of " + runtime + " minutes and was made in " + year + ", directed by " + director + ". Actors: " + actors[0];
		}
		return filmName + " was made in " + year + " and has a runtime of " + runtime + ", directed by " + director + ".";
	}
	
	public String[] getOverlappingActors(Film other)
	{
		int tempLength = 0;
		if(actors.length < other.actors.length)
		{
			tempLength = actors.length;
		}
		else
		{
			tempLength = other.actors.length;
		}
		String[] tempActors = new String[tempLength];
		int size = 0;
		
		for(String thisActor : actors)
		{
			for(String otherActor : other.actors)
			{
				if(thisActor.equals(otherActor))
				{
					tempActors[size++] = thisActor;
				}
			}
		}
		
		String[] overlappedActors = Arrays.copyOf(tempActors, size);
	    return overlappedActors;
	}
	
	public static void main(String[] args) {
		

	}

}
