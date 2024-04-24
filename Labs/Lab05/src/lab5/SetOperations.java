package lab5;

import java.util.Arrays;

public class SetOperations 
{
	
	public static int[] makeSet(int[] data) 
	{
		Arrays.sort(data); // Sort it so duplicates are grouped
		int [] tempList = new int[data.length]; // tempList with the size of input list
		tempList[0] = data[0]; // Add first value to keep index from going out of bounds in the loop
		int size = 1;
		
		for(int i = 1; i < data.length; i++)
		{
			if(data[i] != data[i - 1]) // If there isn't a duplicate, add unique char to tempList
			{
				tempList [size++] = data[i];  // Size of our new list increases for every unique character added
			}
		}
		
		int[] set = Arrays.copyOf(tempList, size);
		return set;
	}
	
	public static boolean isSet(int[] data)
	{
		if(data.length == 0) 
		{
			return true;
		}
		
		int[] newSet = makeSet(data);
		if(Arrays.equals(newSet, data)) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int[] union(int[] setA, int[] setB)
	{
		if(!isSet(setA) || !isSet(setB)) 
		{
			throw new IllegalArgumentException("Both arrays must be sets.");
		}
		
		int[] newSet = new int[setA.length + setB.length];
		
		for(int i = 0; i < setA.length; i++)
		{
			newSet[i] = setA[i];
		}
		
		for(int i = 0; i < setB.length; i++)
		{
			newSet[setA.length + i] = setB[i];
		}
		
		int[] union = makeSet(newSet);
		return union;
	}
	
	public static int[] intersection(int[] setA, int[] setB)
	{
		if(!isSet(setA) || !isSet(setB)) 
		{
			throw new IllegalArgumentException("Both arrays must be sets.");
		}
		
		int tempSetLength = 0;
		if(setA.length < setB.length)
		{
			tempSetLength = setA.length;
		}
		else
		{
			tempSetLength = setB.length;
		}
		int[] tempSet = new int[tempSetLength];
		int size = 0;
		int i = 0;
		int j = 0;
		
		while(i < setA.length && j < setB.length)
		{
			if(setA[i] < setB[j]) 
			{
				i++; // if current is smaller, move on
			}
			else if (setA[i] > setB[j])
			{
				j++; // if bigger, move on
			}
			else
			{
				tempSet[size++] = setA[i]; // if equal, add it and then move on
				i++;
				j++;
			}
		}
		
		int[] intersection = Arrays.copyOf(tempSet, size);
	    return intersection;
	}
	
	public static void main(String[] args) 
	{
		int[] valuesA = {};
		int [] valuesB = {2, 3};
		
		for(int element : union(valuesA, valuesB)) 
		{
			System.out.println(element);
		}
	}

}
