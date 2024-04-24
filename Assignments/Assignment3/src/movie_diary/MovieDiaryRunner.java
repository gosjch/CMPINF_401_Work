package movie_diary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MovieDiaryRunner
{
	private static Library library;
	private static Diary diary;
	private static Scanner scan;

	public static void logFilm()
	{
		System.out.print("What's the name of the film? ");
		String filmName = scan.nextLine();
		System.out.print("What year did it release? ");
		int year = Integer.parseInt(scan.nextLine());
		Film toLog = library.getFilm(filmName, year);
		if(toLog == null) 
		{
			System.out.println(filmName + " (" + year + ") could not be found in the library.");
			return;
		}

		System.out.print("What rating do you give it? (0-5 stars, can be half stars; leave it blank if you'd prefer no rating): ");
		String ratingStr = scan.nextLine();
		System.out.print("When did you watch the film? (leave this blank if you want to log it for today): ");
		String dateStr = scan.nextLine();

		if(ratingStr == null || ratingStr.length() < 1)
		{
			if(dateStr == null || dateStr.length() < 1)
			{
				diary.logFilm(toLog);
			}
			else
			{
				diary.logFilm(toLog, LocalDate.parse(dateStr));
			}
		}
		else if(dateStr == null || dateStr.length() < 1)
		{
			diary.logFilm(toLog, Double.parseDouble(ratingStr));
		}
		else
		{
			diary.logFilm(toLog, Double.parseDouble(ratingStr), LocalDate.parse(dateStr));
		}
	}

	public static void viewLogs()
	{
		//Grab the 5 most recent logs and show them to the user
		ArrayList<DiaryEntry> recentLogs = diary.getRecentLogs();
		String logs = "";
		System.out.println("Recent Logs:");
		for(DiaryEntry log : recentLogs)
		{
			System.out.println("\t" + log.toString());
		}
		System.out.println();
	}
	
	public static void searchFilms()
	{
		System.out.print("What are you looking for? ");
		String filmname = scan.nextLine();
		
		System.out.println("Here's what I could find...");
		ArrayList<Film> films = library.search(filmname);
		for(Film film : films)
		{
			System.out.println("\t" + film.toString());
		}
		System.out.println();
		
		if(films.size() == 0)
			System.out.println("\tNothing!\n");
	}
	
	public static void actorFilmography()
	{
		System.out.print("Which actor are you interested in? ");
		String actorName = scan.nextLine();
		
		System.out.println("Here's what I could find in their filmography...");
		ArrayList<Film> films = library.getActorsFilmography(actorName);
		for(Film film : films)
		{
			System.out.println("\t" + film.toString());
		}
		System.out.println();
		
		if(films.size() == 0)
			System.out.println("\tNothing!\n");
	}
	
	public static void directorFilmography()
	{
		System.out.print("Which director are you interested in? ");
		String directorsName = scan.nextLine();
		
		System.out.println("Here's what I could find in their filmography...");
		ArrayList<Film> films = library.getDirectorsFilmography(directorsName);
		for(Film film : films)
		{
			System.out.println("\t" + film.toString());
		}
		System.out.println();
		
		if(films.size() == 0)
			System.out.println("\tNothing!\n");
	}
	
	public static void browseLibrary()
	{
		String[] options = {"Search by Title", "Look at Actor Filmography", "Look at Director Filmography"};
		System.out.println("What would you like to do? (Choose a number)");
		for(int i = 0; i < options.length; i++) System.out.println(i + ": " + options[i]);
		int n = Integer.parseInt(scan.nextLine());

		if(n == 0)
		{
			searchFilms();
		}
		else if(n == 1)
		{
			actorFilmography();
		}
		else if(n == 2)
		{
			directorFilmography();
		}
	}

	public static boolean mainPrompt() 
	{
		String[] options = {"Log a film", "View Logs", "Look through Library", "Quit"};
		System.out.println("What would you like to do? (Choose a number)");
		for(int i = 0; i < options.length; i++) System.out.println(i + ": " + options[i]);
		int n = Integer.parseInt(scan.nextLine());

		if(n == 0)
		{
			logFilm();
			return true;
		}
		else if(n == 1)
		{
			viewLogs();
			return true;
		}
		else if(n == 2)
		{
			browseLibrary();
			return true;
		}

		return n != 3;
	}

	public static void main(String[] args)
	{
		scan = new Scanner(System.in);

		System.out.print("What's the file name of your library: ");
		String library_fn = scan.nextLine();
		System.out.print("What's the file name of your diary: ");
		String diary_fn = scan.nextLine();

		//Instantiate our library by reading in the library file
		library = new Library(library_fn);
		//Instantiate our diary by reading in the diary file
		diary = new Diary(diary_fn, library);

		boolean continuing = true;
		while(continuing)
		{
			continuing = mainPrompt();
		}
	}

}
