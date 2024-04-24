package lab7;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RecordList 
{
	private class Node
	{
		public double time;
		public String name;
		public Node next;
	}
	
	public Node head;
	
	public int add(String name, double time)
	{
		Node newNode = new Node();
		newNode.name = name;
		newNode.time = time;
		newNode.next = null;
		
		// List is empty 
		if(head == null)
		{
			head = newNode;
			return 0;
		}
		
		// New node's time is less than the head's time
		if(newNode.time < head.time)
		{
			newNode.next = head; // Link new node to the current head
			head = newNode; // Update head to new node
			return 0;
		}
		
		Node currentNode = head;
		int index = 0;
		// While the next node isn't null and the next node's time is less than the new node's time
		// Traverses through the linked list to find where to add new Node
		while (currentNode.next != null && currentNode.next.time < newNode.time) 
		{
	        currentNode = currentNode.next;
	        index++;
	    }

	    // Links newNode to the list by replacing currentNode's spot with it
	    newNode.next = currentNode.next;
	    currentNode.next = newNode;
		return index + 1;
	}
	
	public void print() // Copied from class toString example
	{
		String rep = "[";
		Node currentNode = head;
		while(currentNode != null)
		{
			rep += currentNode.name + ":" + currentNode.time + ", ";
			currentNode = currentNode.next;
		}
		rep += "]";
		System.out.println(rep);
	}
	
	public void writeToFile(String fileName)
	{
		try 
		{
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			Node currentNode = head;
			// Keeps going until there isn't a node
			while(currentNode != null)
			{
				// Writes to the file the current Node's data
				bw.write(currentNode.name + "," + currentNode.time + "\n");
				// Moves along
				currentNode = currentNode.next;
			}
			
			bw.close();
			fw.close();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args) 
	{
		String input = "";
		RecordList list = new RecordList();
		Scanner scan = new Scanner(System.in);
		do
		{
		 // Get user input
		 System.out.println("Please enter new record (name, time) or 'exit' to quit.");
		 input = scan.nextLine();
		 if (!input.equals("exit"))
		 {
			double time;
			String name;
			// Process user input
			try
			{
			 String [] parts = input.split(",");
			 name = parts[0].trim();
			 String timeStr = parts[1].trim();
			 time = Double.parseDouble(timeStr);
			}
			catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
			{
			 System.out.println("Invalid input, please try again!");
			 continue; // if input is invalid, restart the loop
			}
			// Add new record
			int result = list.add(name, time);
			// Print out a message if this is new best time
			if (result == 0)
			{
			 System.out.println("Congratulations! " + name + " has the fastest time so far");
			}
			// You might want to write a method like this for debugging
			list.print();
		 }
		} while (!input.equals("exit"));
		list.writeToFile("data/recordList.txt");

	}

}
