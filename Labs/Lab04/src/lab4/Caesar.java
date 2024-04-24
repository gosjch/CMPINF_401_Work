package lab4;

public class Caesar 
{
	private int key;
	
	public void setKey (int newValue)
	{
		this.key = newValue;
	}
	
	public int getKey()
	{
		return this.key;
	}
	
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
	
	public String decrypt(String stringToDecrypt) 
	{
		char[] alphabetUp = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] alphabetLow = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		char[] inputChars = stringToDecrypt.toCharArray();
		String deEncryptedString = "";
		
		for(int i = 0; i < inputChars.length; i++)
		{
			char thisChar = inputChars[i];
					
			if(Character.isUpperCase(thisChar))
			{
				int placeInAlphabet = thisChar - 'A';
	            int newPlace = (placeInAlphabet - key) % 26; // Now gets original place in alphabet w/o the key
	            if (newPlace < 0)
	            {
	                newPlace += 26;
	            }
				deEncryptedString += alphabetLow[newPlace];
				
			} 
			else if(Character.isLowerCase(thisChar))
			{
				
				int placeInAlphabet = thisChar - 'a';
	            int newPlace = (placeInAlphabet - key) % 26;
	            if (newPlace < 0)
	            {
	                newPlace += 26;
	            }
				deEncryptedString += alphabetUp[newPlace];
			} 
			else
			{
				deEncryptedString += thisChar;
			}
		}
		
		return deEncryptedString;
	}

	public static void main(String[] args) 
	{
		Caesar cipher = new Caesar();
		cipher.setKey(3);
		System.out.println(cipher.encrypt("hello WORLD!")); // prints “KHOOR zruog!”
		System.out.println(cipher.decrypt("KHOOR zruog!")); //prints “hello WORLD!”
		cipher.setKey(6);
		System.out.println(cipher.encrypt("zzz")); //prints “FFF”
		System.out.println(cipher.decrypt("FFF")); //prints “zzz”
		cipher.setKey(-6); // Negative keys should be supported!
		System.out.println(cipher.encrypt("FFF")); //prints “zzz”

	}

}
