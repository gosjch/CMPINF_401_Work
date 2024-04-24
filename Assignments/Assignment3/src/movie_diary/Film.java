package movie_diary;

public class Film 
{
	private String filmName;
	public String getFilmName()
	{
		return this.filmName;
	}
	public void setFilmName(String filmName)
	{
		this.filmName = filmName;
	}
	
	private int year;
	public int getYear()
	{
		return this.year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	
	private int runtime;
	public int getRuntime()
	{
		return this.runtime;
	}
	public void setRuntime(int runtime)
	{
		this.runtime = runtime;
	}
	
	private String director;
	public String getDirector()
	{
		return this.director;
	}
	public void setDirector(String director)
	{
		this.director = director;
	}
	
	public String[] actors = new String[0];
	
	public Film(String filmName, int year, int runtime)
	{
		this.director = "Alan Smithee"; // Default director
		this.filmName = filmName;
		
		if(runtime < 0)
		{
			throw new IllegalStateException("Runtime cannot be negative");
		}
		else
		{
			this.runtime = runtime;
		}
		
		if(year <= 1880)
		{
			throw new IllegalStateException("Year must be after 1880");
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
	
	public void addActor(String name)
	{
		if(name == null || name.isEmpty() || name.isBlank())
		{
			throw new IllegalArgumentException("String cannot be null, solely whitespace, or nothing.");
		}
		
		// tempArray made to increase size
		String[] tempArray = new String[this.actors.length + 1];
		int i = 0;
		while(i < this.actors.length)
		{
			// Copy contents of actors array
			tempArray[i] = this.actors[i];
			i++;
		}
		
		// Add new actor to last position of the tempArray
		tempArray[tempArray.length -1] = name;
		
		// Actors now equals tempArray
		this.actors = tempArray;
	}
	
	public boolean castContainsActor(String name)
	{
		boolean containsActor = false;
		for(int i = 0; i < this.actors.length; i++)
		{
			if(this.actors[i].equals(name))
			{
				containsActor = true;
			}
		}
		return containsActor;
	}
	
	public String toString()
	{
		String outputString = filmName + " was made in " + year + " and has a runtime of " + runtime + ", directed by " + director;
		if(actors.length > 0)
		{
			outputString += ", starring actors are as follows";
			for(int i = 0; i < actors.length-1; i++)
			{
				outputString += ", "+ actors[i]; 
			}
		}
		return  outputString + ".";
	}

}
