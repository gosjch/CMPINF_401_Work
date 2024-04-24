package movie_diary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiaryAnalysis 
{
	public Diary diary;
	
	public DiaryAnalysis(Diary diary)
	{
		this.diary = diary;
	}
	
	public ArrayList<String> getMostLoggedDirectors()
	{
	    HashMap<String, Integer> directorCount = new HashMap<>();
	    for (DiaryEntry entry : this.diary.getLogs())
	    {
	        String director = entry.getFilm().getDirector();
	        directorCount.put(director, directorCount.getOrDefault(director, 0) + 1);
	    }

	    List<Map.Entry<String, Integer>> sortedDirectors = new ArrayList<>(directorCount.entrySet());
	    sortedDirectors.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

	    ArrayList<String> topDirectors = new ArrayList<>();
	    for (int i = 0; i < Math.min(10, sortedDirectors.size()); i++)
	    {
	        topDirectors.add(sortedDirectors.get(i).getKey());
	    }

	    return topDirectors;
	}
	
	public ArrayList<String> getMostLoggedActors()
	{
		Map<String, Integer> frequencyMap = new HashMap<>();
		
		for(DiaryEntry entry : this.diary.getLogs()) 
		{
			String[] actors = entry.getFilm().actors;
			for(String actor : actors)
			{
				frequencyMap.put(actor, frequencyMap.getOrDefault(actor, 0) + 1);
			}
		}
		
		// proof i know what this does above
		List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
		list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
		
		ArrayList<String> sortedActors = new ArrayList<String>();
		for(int i =0; i < Math.min(list.size(), 10); i++)
		{
			sortedActors.add(list.get(i).getKey());
		}
		
		return sortedActors;
	}
	
	public double getAverageRatingForActor(String actorName)
	{
		double totalRating = 0;
		int count = 0;
		
		for(DiaryEntry entry : this.diary.getLogs())
		{
			if(entry.getFilm().castContainsActor(actorName) && entry.getRating() != -1)
			{
				totalRating += entry.getRating();
				count++;
			}
		}
		if(count == 0)
		{
			return -1;
		}
		return (totalRating/count);
	}
	
	public double getAverageRatingForDirector(String directorName)
	{
		double totalRating = 0;
		int count = 0;
		
		for(DiaryEntry entry : this.diary.getLogs())
		{
			if( (entry.getFilm().getDirector().equals((directorName))) && entry.getRating() != -1)
			{
				totalRating += entry.getRating();
				count++;
			}
		}
		if(count == 0)
		{
			return -1;
		}
		return (totalRating/count);
	}
	
	public String[] getYearEndFavorites(int year) 
	{
	    Map<String, Integer> actorFrequency = new HashMap<>();
	    Map<String, Integer> directorFrequency = new HashMap<>();

	    // Filter logs by year and count frequencies
	    for (DiaryEntry log : this.diary.getLogs()) 
	    {
	        if (log.getDate().getYear() == year) // Use LocalDate's getYear() to check the year of logging
	        {
	            // Here, we handle multiple actors for each film
	            for (String actor : log.getFilm().actors) // Assuming getActors() is a method in Film class
	            {
	                actorFrequency.put(actor, actorFrequency.getOrDefault(actor, 0) + 1);
	            }
	            // Count frequency of director
	            String director = log.getFilm().getDirector();
	            directorFrequency.put(director, directorFrequency.getOrDefault(director, 0) + 1);
	        }
	    }

	    // Find the actor and director with the highest frequency
	    String favoriteActor = null;
	    int maxActorFrequency = 0;
	    for (Map.Entry<String, Integer> entry : actorFrequency.entrySet()) 
	    {
	        if (entry.getValue() > maxActorFrequency) 
	        {
	            favoriteActor = entry.getKey();
	            maxActorFrequency = entry.getValue();
	        }
	    }

	    String favoriteDirector = null;
	    int maxDirectorFrequency = 0;
	    for (Map.Entry<String, Integer> entry : directorFrequency.entrySet()) 
	    {
	        if (entry.getValue() > maxDirectorFrequency) 
	        {
	            favoriteDirector = entry.getKey();
	            maxDirectorFrequency = entry.getValue();
	        }
	    }

	    return new String[]{favoriteActor, favoriteDirector};
	}
	
	public ArrayList<String> getHighestRatedDirectors() 
	{
        HashMap<String, Double> sumRatings = new HashMap<>();
        HashMap<String, Integer> countRatings = new HashMap<>();
        
        for (DiaryEntry entry : this.diary.getLogs()) 
        {
            if (entry.getRating() >= 0) 
            { // Assuming -1 indicates an unrated film
                String director = entry.getFilm().getDirector();
                sumRatings.put(director, sumRatings.getOrDefault(director, 0.0) + entry.getRating());
                countRatings.put(director, countRatings.getOrDefault(director, 0) + 1);
            }
        }
        
        HashMap<String, Double> averageRatings = new HashMap<>();
        for (String director : sumRatings.keySet()) 
        {
            double average = sumRatings.get(director) / countRatings.get(director);
            averageRatings.put(director, average);
        }
        
        List<String> sortedDirectors = averageRatings.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(10)
                .collect(Collectors.toList());
                
        return new ArrayList<>(sortedDirectors);
    }
	
	public ArrayList<String> getHighestRatedByReleaseYear(int year) 
	{
        // Filter diary entries by film's release year and check if they have a valid rating
        List<DiaryEntry> validEntries = diary.getLogs().stream()
            .filter(entry -> entry.getFilm().getYear() == year && entry.getRating() != -1)
            .collect(Collectors.toList());

        // Group entries by film and compute the average rating for each film
        Map<Film, Double> averageRatings = validEntries.stream()
            .collect(Collectors.groupingBy(
                DiaryEntry::getFilm,
                Collectors.averagingDouble(DiaryEntry::getRating)
            ));

        // Sort films by average rating in descending order
        List<Map.Entry<Film, Double>> sortedEntries = new ArrayList<>(averageRatings.entrySet());
        Collections.sort(sortedEntries, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Collect the top 10 or fewer films' titles
        ArrayList<String> topRatedFilms = sortedEntries.stream()
            .limit(10)
            .map(entry -> entry.getKey().getFilmName())
            .collect(Collectors.toCollection(ArrayList::new));

        return topRatedFilms;
    }
	
	public double predictRating(Film film) 
	{
	    List<Double> averages = new ArrayList<>();

	    // For each actor in the film, calculate average ratings and add to the list
	    for (String actor : film.actors) // Assuming getActors() method correctly retrieves actor list
	    {
	        double actorAverage = getAverageRatingForActor(actor);
	        if (actorAverage != -1) 
	        { // Only add if there is an average rating (actor was watched before)
	            averages.add(actorAverage);
	        }
	    }

	    // Calculate average rating for the director and add it five times to the list
	    double directorAverage = getAverageRatingForDirector(film.getDirector());
	    if (directorAverage != -1) 
	    { // Only add if there is an average rating (director was watched before)
	        for (int i = 0; i < 5; i++) 
	        {
	            averages.add(directorAverage);
	        }
	    }

	    // Calculate the weighted average of averages
	    if (!averages.isEmpty()) 
	    {
	        double sum = 0;
	        for (double average : averages) 
	        {
	            sum += average;
	        }
	        return sum / averages.size();
	    } 
	    else 
	    {
	        return -1; // Return -1 if the list is empty
	    }
	}
	
	 public ArrayList<Film> generateRecommendations(ArrayList<Film> choices) 
	 {
	        // Filter out films already watched
	        List<Film> unwatchedChoices = choices.stream()
	                .filter(film -> diary.getLogs().stream()
	                        .noneMatch(entry -> entry.getFilm().getFilmName().equals(film.getFilmName())))
	                .collect(Collectors.toList());

	        // Predict ratings for each film and collect into a list
	        List<FilmWithRating> ratedFilms = new ArrayList<>();
	        for (Film film : unwatchedChoices) 
	        {
	            double predictedRating = predictRating(film);
	            ratedFilms.add(new FilmWithRating(film, predictedRating));
	        }

	        // Sort films by predicted rating in descending order
	        ratedFilms.sort(Comparator.comparingDouble(FilmWithRating::getRating).reversed());

	        // Extract the top 10 films, or fewer if not enough films are available
	        return ratedFilms.stream()
	                .limit(10)
	                .map(FilmWithRating::getFilm)
	                .collect(Collectors.toCollection(ArrayList::new));
	    }

	    private class FilmWithRating 
	    {
	        private Film film;
	        private double rating;

	        public FilmWithRating(Film film, double rating) 
	        {
	        	
	            this.film = film;
	            this.rating = rating;
	        }

	        public Film getFilm() 
	        {
	            return film;
	        }

	        public double getRating() 
	        {
	            return rating;
	        }
	    }
	
	public static void main(String[] args)
	{
		Library l = new Library("library.csv");
		Diary d = new Diary("diary.csv", l);
		DiaryAnalysis analytics = new DiaryAnalysis(d);

		System.out.println("Most logged directors:");
		for(String nm : analytics.getMostLoggedDirectors())
		{
			System.out.println("\t" + nm);
		}
		
		System.out.println("Most logged actors:");
		for(String nm : analytics.getMostLoggedActors())
		{
			System.out.println("\t" + nm);
		}
	}

	
}
