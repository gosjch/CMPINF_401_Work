package movie_diary;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaryEntry 
{
	private Film film;
	public Film getFilm() 
	{
		return this.film;
	}
	public void setFilm(Film film)
	{
		this.film = film;
	}
	
	private double rating;
	public double getRating()
	{
		return this.rating;
	}
	public void setRating(double rating)
	{
		this.rating = rating;
	}
	
	private LocalDate date;
	public LocalDate getDate()
	{
		return this.date;
	}
	public void setDate(LocalDate date)
	{
		if(date != null) 
		{
			this.date = date;
		}
		else
		{
			throw new IllegalArgumentException("Date can never be null.");
		}
	}
	
	public DiaryEntry(Film film, double rating, LocalDate date)
	{
		if(film == null) throw new IllegalArgumentException("Film cannot be null.");
		this.film = film;
		this.date = date;
		
		if(rating == 0 || rating == 0.5 || rating == 1 || rating == 1.5 || rating == 2 || rating == 2.5 || rating == 3 || rating == 3.5 || rating == 4 || rating == 4.5 || rating == 5 || rating == -1)
		{
			this.rating = rating;
		}
		else
		{
			throw new IllegalStateException("Rating isn't valid.");
		}
	}
	
	public DiaryEntry(Film film, double rating)
	{
		this(film, rating, LocalDate.now());
	}
	
	public DiaryEntry(Film film, LocalDate date)
	{
		this(film, -1, LocalDate.now());
	}
	
	public DiaryEntry(Film film)
	{
		this(film, -1, LocalDate.now());
	}
	
	public String toString()
	{
		String newString = this.film.getFilmName() + " was made in " + this.film.getYear();
		
		if(rating >= 0)
		{
			newString += " with a rating of " + this.rating;
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = date.format(formatter);
		newString += ", entered into the diary at " + dateString;
		
		return newString + ".";
	}
	
	public boolean loggedDuringYear(int year)
	{
		if(date.getYear() == year)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toCSV ()
	{
		String newString = film.getFilmName() + "," + film.getYear() + ",";
		
		if(this.rating >= 0)
		{
			newString += this.rating + ",";
		}
		else
		{
			newString += ",";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return newString += date.format(formatter) + "\n";
	}
}
