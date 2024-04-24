package movie_diary;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Diary 
{
	private ArrayList<DiaryEntry> logs = new ArrayList<DiaryEntry>();
	public ArrayList<DiaryEntry> getLogs()
	{
		return this.logs;
	}
	public void setLogs(ArrayList<DiaryEntry> logs)
	{
		this.logs = logs;
	}
	
	private String diaryFileName;
	public String getDiaryFileName()
	{
		return this.diaryFileName;
	}
	public void setDiaryFileName(String fileName)
	{
		this.diaryFileName = fileName;
	}
	
	public Diary(String fileName, Library library)
	{
		try
		{
			this.diaryFileName = fileName;
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
			
			for(int i = 0; i < csvLines.size(); i++) 
					// traversing through csvLines
			{	
				// Splits each individual string in the array list
				// the string array should look like [Name,Year(int),Rating(double),Date]
				String[] currentLineParts = csvLines.get(i).split(",");
				
				// making values, parsing non-string parts as corresponding value types
				String name = currentLineParts[0];
				double rating;
				int year = Integer.parseInt(currentLineParts[1].trim());
				if(currentLineParts[2].isEmpty() || currentLineParts[2].isBlank())
				{
					rating = -1;
				}
				else
				{
					rating = Double.parseDouble(currentLineParts[2].trim());
				}
				// making film
				Film currentFilm = library.getFilm(name, year);
				if(currentFilm == null) continue;
				
				// splitting the YYYY-MM-DD part, should be [year, month, day]
				String[] currentDateParts = currentLineParts[3].split("-");
				
				int localYear = Integer.parseInt(currentDateParts[0]);
				int localMonth = Integer.parseInt(currentDateParts[1]);
				int localDay = Integer.parseInt(currentDateParts[2]);
				
				// Make a local date object out of the initialized parts
				LocalDate date = LocalDate.of(localYear, localMonth, localDay);
				
				// Make the DiaryEntry object, add it to logs
				DiaryEntry log = new DiaryEntry(currentFilm, rating, date);
				this.logs.add(log);
			}
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void logFilm(DiaryEntry log) 
	{
		this.logs.add(log);
		
		try
		{
			//Open up a file writing stream
			//By passing true as a second parameter, we open in APPEND mode
			FileWriter fw = new FileWriter(this.diaryFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(log.toCSV());
			
			bw.close();
			fw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void logFilm(Film film, double rating, LocalDate date) 
	{
		DiaryEntry log = new DiaryEntry(film, rating, date);
		this.logs.add(log);
		
		try
		{
			//Open up a file writing stream
			//By passing true as a second parameter, we open in APPEND mode
			FileWriter fw = new FileWriter(this.diaryFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(log.toCSV());
			
			bw.close();
			fw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void logFilm(Film film, LocalDate date) 
	{
		logFilm(film, -1, date);
	}
	
	public void logFilm(Film film, double rating) 
	{
		logFilm(film, rating, LocalDate.now());
	}
	
	public void logFilm(Film film) 
	{
		logFilm(film, -1, LocalDate.now());
	}
	
	public ArrayList<DiaryEntry> getRecentLogs()
	{
		ArrayList<DiaryEntry> outputLogs = new ArrayList<DiaryEntry>();
		for(int i = 0; i < 5; i++)
		{
			int index = this.logs.size() -1 - i;
			outputLogs.add(this.logs.get(index));
		}
		return outputLogs;
	}
	
	
	public ArrayList<DiaryEntry> getFilmsLoggedByYear(int year)
	{
		ArrayList<DiaryEntry> outputLogs = new ArrayList<DiaryEntry>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(int i = 0; i < logs.size(); i++)
		{
			LocalDate currentLocalDate = logs.get(i).getDate();
			String currentDate = currentLocalDate.format(formatter);
			String[] tempArr = currentDate.split("-");
			int currentYear = Integer.parseInt(tempArr[0]);
			
			if(currentYear == year)
			{
				outputLogs.add(logs.get(i));
			}
		}
		return outputLogs;
	}

}
