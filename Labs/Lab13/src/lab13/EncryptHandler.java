package lab13;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EncryptHandler implements HttpHandler 
{
	private int key;
	
	public String encrypt(String stringToEncrypt) 
	{
		
		char[] alphabetUp = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] alphabetLow = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		char[] inputChars = stringToEncrypt.toCharArray();
		String encryptedString = "";
		
		for(int i = 0; i < inputChars.length; i++)
		{
			char thisChar = inputChars[i];
			
			if(Character.isUpperCase(thisChar))
			{
				int placeInAlphabet = thisChar - 'A'; // Minuses the smallest capital ASCII value by the upper case character to get it's place in the alphabet
				int newPlace = (placeInAlphabet + key) % 26; // Since there are 26 letters in the alphabet, it wraps around if the index goes beyond 25 (remembering we start count at 0)
				if (newPlace < 0) // Checking for negative keys, which should move it in the opposite direction
				{
				    newPlace += 26;
				}
				encryptedString += alphabetLow[newPlace];
				
			} 
			else if(Character.isLowerCase(thisChar))
			{
				
				int placeInAlphabet = thisChar - 'a'; // Minuses the smallest capital ASCII value by the lower case character to get it's place in the alphabet
				int newPlace = (placeInAlphabet + key) % 26;
				if (newPlace < 0) 
				{
				    newPlace += 26;
				}
				encryptedString += alphabetUp[newPlace];
				
			} 
			else
			{
				encryptedString += thisChar;
			}
		}
		
		return encryptedString;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException 
	{
		String method = exchange.getRequestMethod();
		OutputStream outputStream = exchange.getResponseBody();
		
		if(method.equals("POST"))
		{
			System.out.println(LocalDate.now().toString() + "_" + LocalTime.now().toString() + ": POST /");
		
			//grab inputstream tied to the user's posted data
			InputStream requestStream = exchange.getRequestBody();
			// scan to capture input
			Scanner scan = new Scanner(requestStream);
			
			// loop through input to make it one string, should be in "key;plaintext" format
			String requestString = "";
			while(scan.hasNextLine())
			{
				requestString += scan.nextLine() + "\n";
			}
			
			// get colon index and parse the key
			int colonIndex = requestString.indexOf(";");
			this.key = Integer.parseInt(requestString.substring(0, colonIndex));
			
			// get string part and encrypt that
			String responseString = encrypt(requestString.substring(colonIndex+1)); 
			
			// send back a response and response code 200
			exchange.sendResponseHeaders(200, responseString.length());
			// use output stream to so the user gets the output
			outputStream.write(responseString.getBytes());

			outputStream.flush();
			outputStream.close();
			System.out.println("\tResponse: " + responseString + " (200)");
			

		}
	}

}
