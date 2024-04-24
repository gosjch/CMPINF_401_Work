package movie_diary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Library 
{
	private ArrayList<Film> films = new ArrayList<Film>();
	public ArrayList<Film> getFilms()
	{
		return this.films;
	}
	public void setFilms(ArrayList<Film> films)
	{
		this.films = films;
	}
	
	public Library(String fileName)
	{
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			// Making an array list to store every line from csv
			ArrayList<String> csvLines = new ArrayList<String>();
			
			// Adding every line of file to csvLines
			String line = br.readLine(); // Read first line to skip headers
	        while ((line = br.readLine()) != null) 
	        {
	            csvLines.add(line);
	        }
			
			for(int i = 0; i < csvLines.size(); i++) // traversing through csvLines
			{
				// First getting the actors
				int quoteSpot = csvLines.get(i).indexOf("\"");
				
				String actorsTemp = csvLines.get(i).substring(quoteSpot+1);
				actorsTemp = actorsTemp.replaceAll("\"", ""); // Remove all quotes
				String[] cast = actorsTemp.split(",\\s*"); // Split by comma and optional whitespace

				// Splits each individual string in the array list
				// the string array should look like [title,year (int),director,runtime (int),cast]
				String[] currentLineParts = csvLines.get(i).split(",");
				
				// making values, parsing non-string parts as corresponding value types
				// Catching number format exceptions
				String title = null;
				int year= 0;
				String director = null;
				int runtime = -1;
				try 
				{
					title = currentLineParts[0];
					year = Integer.parseInt(currentLineParts[1]);
					director = currentLineParts[2];
					runtime = Integer.parseInt(currentLineParts[3]);
				}
				catch(NumberFormatException h)
				{
					System.out.println("Couldn't parse integers.");
					break;
				}
				
				
				// Make the film object, add it to films
				// Catch illegal states
				Film currentFilm = null;
				try 
				{
					currentFilm = new Film(title, year, runtime, director);
				}
				catch(IllegalStateException s)
				{
					System.out.println("False runtime or year detected. Films from before 1881 were not added.");
					continue;
				}
				
				for(int j = 0; j < cast.length; j++) 
				{
				    // Add each actor to the currentFilm
					currentFilm.addActor(cast[j].trim().replaceAll("[\\'\\[]", "")); // remove brackets and single quotes
				    // Debugger System.out.println("Actor name:" + cast[j].trim().replaceAll("[\\'\\[]", ""));
				}
				// Debugger System.out.println("Created Film: " + currentFilm.getFilmName() + " with cast: " + Arrays.toString(currentFilm.actors));
				this.films.add(currentFilm);
				
			}
			
			// Debug if needed System.out.println(films);
			
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Film getFilm(String name, int year)
	{
		Film outputFilm = null;
		
		for(int i = 0; i < this.films.size(); i++)
		{
			// most periods i've used yet 
			// hopefully ignores case and trailing/leading whitespace
			if(this.films.get(i).getFilmName().toLowerCase().equals(name.trim().toLowerCase()) && this.films.get(i).getYear() == year)
			{
				outputFilm = this.films.get(i);
			}
		}
		return outputFilm;
	}
	
	public ArrayList<Film> search(String searchTerm)
	{
		ArrayList<Film> outputFilms = new ArrayList<Film>();
		ArrayList<Film> exactMatches = new ArrayList<Film>();
		ArrayList<Film> partialMatches = new ArrayList<Film>();
		
		// ignores case for the searchTerm
		String inputTerm = searchTerm.toLowerCase();
		
		// Traverse through films
		for(int i = 0; i < this.films.size(); i++)
		{
			// Get current film, clean the film name, and compare it to input term after cleaning and ignoring case
			String currentCleanFilmName = this.films.get(i).getFilmName().toLowerCase();
			if(currentCleanFilmName.equals(inputTerm))
			{
				// If the cleaned names are the same, add to exact array
				exactMatches.add(this.films.get(i));
			}
			
			// If it it contains the cleaned name, add it to partial
			else if(currentCleanFilmName.contains(inputTerm))
			{
				// If the cleaned name contains the input, add to output array
				partialMatches.add(this.films.get(i));
			}
		}
		
		// add them in the right order
		outputFilms.addAll(exactMatches);
		outputFilms.addAll(partialMatches);
		
		return outputFilms;
	}
	
	public ArrayList<Film> getActorsFilmography(String actorsName)
	{
		ArrayList<Film> outputFilms = new ArrayList<Film>();
		// If the actors name of the current film in films is in the films' actors array, add to output
		
		for(int i = 0; i <films.size(); i++)
		{
			for(int j = 0; j < films.get(i).actors.length; j++)
			{
				if(films.get(i).actors[j].equalsIgnoreCase(actorsName.trim())) 
				{
					outputFilms.add(films.get(i));
				}
			}
		}
		return outputFilms;
	}
	
	public ArrayList<Film> getDirectorsFilmography(String directorsName)
	{
		ArrayList<Film> outputFilms = new ArrayList<Film>();
		// If the director name of the current film in films is equal to input name, add to output
		
		for(int i = 0; i <films.size(); i++)
		{
			if(films.get(i).getDirector().equals(directorsName))
			{
				outputFilms.add(films.get(i));
			}
		}
		return outputFilms;
	}

}
