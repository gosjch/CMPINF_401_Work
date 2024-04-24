package wordle;
import java.util.Scanner;

public class Wordle 
{

	public static boolean containsChar(String inputString, char inputChar)
	{
		String validInputString = removeWhitespace(inputString.toUpperCase());
		for(int i = 0; i<validInputString.length(); i++)
		{
			if(validInputString.charAt(i)==inputChar)
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isValidInput (String inputString)
	{
		boolean isValidInput = true;
		if(inputString.length() != 5)
		{
			isValidInput = false;
		}

		for(int i = 0; i < inputString.length(); i++)
		{
			if(!(Character.isLetter(inputString.charAt(i))))
			{
				isValidInput = false;
			}
		}

		return isValidInput;
	}

	public static boolean isCharCorrect (String inputString, char inputChar, int inputInt)
	{
		String newString = removeWhitespace((inputString.toUpperCase()));
		char newChar = Character.toUpperCase(inputChar);

		if(newString.charAt(inputInt) == newChar)
		{
			return true;
		}
		return false;
	}

	public static String removeWhitespace (String inputString)
	{
		
		String newString = inputString.trim();
		newString = newString.replace(" ", "");
		newString = newString.replace("\t", "");
		newString = newString.replace("\n", "");
		return newString;
	}

	public static String generateRoundFeedback(String trueWord, String inputString) 
	{
	    String validInputString = inputString.toUpperCase();
	    String feedback = "";
	    if (!isValidInput(validInputString)) 
	    {
	        return null;
	    } 
	    
	    for (int i = 0; i < validInputString.length(); i++) 
	    {
	    	char guessChar = validInputString.charAt(i);
	    	
	    	if(guessChar == trueWord.charAt(i)) 
	    	{
	    		feedback += guessChar;
	    	} 
	    	else if(trueWord.indexOf(guessChar) >= 0) 
	    	{
	    		feedback += "*";
	    	} 
	    	else 
	    	{
	    		feedback += "_";
	    	}
	    }
	    	return feedback;
	}
	
	public static String playRound (String trueWord, Scanner playerInput)
	{
		String results = "false";
		System.out.println("Your guess: ");
		String playerGuess = (removeWhitespace(playerInput.nextLine())).toUpperCase();
		if(playerGuess.equals(trueWord))
		{
			results = "true";
		} else if(!isValidInput(playerGuess)) {
			results = null;
		}
		if(!isValidInput(playerGuess))
				{
			System.out.println("Invalid input. Try again.");		} 
		else 
		{
			System.out.println(generateRoundFeedback(trueWord, playerGuess));
		}
		
		return results;
	}

	public static void playGame(String trueWord, Scanner playerInput)
	{
		boolean rightGuess = false;
		int guesses = 6;
		System.out.println("Welcome to Wordle! You have 6 guesses.");
		while(rightGuess == false && guesses > 0) 
		{
			if(playRound(trueWord, playerInput) == "true")
			{
				rightGuess = true;
				System.out.println("YOU WIN");
				break;
			}
			else if(playRound(trueWord, playerInput) == null)
			{
				System.out.println("You have " + guesses + " guesses left.");
			} 
			else 
			{
				guesses--;
				System.out.println("You have " + guesses + " guesses left.");
			}
		}
		if(guesses == 0)
		{
			System.out.println("YOU LOSE");
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		playGame("APPLE", sc);
	}

}
