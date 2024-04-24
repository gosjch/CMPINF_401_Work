package lab8;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe 
{
	public char[][] board;
	
	public TicTacToe()
	{
		board = new char[3][3];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				board[i][j] = ' ';
			}
		}
	}
	
	public void printBoard()
	{
		for(int row = 0; row < 3; row++)
		{
	System.out.println("-------");
	for(int col = 0; col < 3; col++)
	{
	System.out.print("|" + this.board[row][col]);
	}
	System.out.println("|");
	}
	System.out.println("-------");
	}
	
	public void makeComputerMove()
	{
		Random rand = new Random(System.currentTimeMillis());
		
		// Checks if computer has made its move
		boolean madeMove = false;
		while(!madeMove)
		{
			int row = rand.nextInt(3); // [0,2]
			int column = rand.nextInt(3); // [0,2]
			
			if(board[row][column] == ' ')
			{
				board[row][column] = 'O';
				madeMove = true;
			}
		}
	}
	
	public void makePlayerMove(Scanner scan)
	{
		System.out.println("Make a move. Enter a row and column like so: 'row,column' keeping in mind both start at 0.");
		
		String input;
		String[] inputParts;
		int row;
		int column;
		
		while(true)
		{
			try
			{
				input = scan.nextLine();
				inputParts = input.split(",");
				
				row = Integer.parseInt(inputParts[0].trim());
				column = Integer.parseInt(inputParts[1].trim());
				
				if(board[row][column] != ' ') throw new IllegalArgumentException();
				break;
				
			}
			catch(NumberFormatException h)
			{
				System.out.println("Invalid input. Try again.");
				continue;
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Space is not empty. Try again.");
				continue;
			}
		}
		
		board[row][column] = 'X';
	}
	
	public String checkWinner()
	{
		// Check if the player won any row
		for(int row = 0; row < board.length; row++)
		{
			if(board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X')
			{
				return "X";
			}
		}
		
		// Check if player won any column
		for(int column = 0; column < board.length /* legal since its 3x3 */ ; column++)
		{
			if(board[0][column] == 'X' && board[1][column] == 'X' && board[2][column] == 'X')
			{
				return "X";
			}
		}
		
		// Check if player won any diagonal
		
		if((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') || (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X'))
		{
			return "X";
		}
		
		// Check if the computer won any row
		for(int row = 0; row < board.length; row++)
		{
			if(board[row][0] == 'O' && board[row][1] == 'O' && board[row][2] == 'O')
			{
				return "O";
			}
		}
				
		// Check if computer won any column
		for(int column = 0; column < board.length /* legal since its 3x3 */ ; column++)
		{
			if(board[0][column] == 'O' && board[1][column] == 'O' && board[2][column] == 'O')
			{
				return "O";
			}
		}
				
		// Check if computer won any diagonal
				
		if((board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') || (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O'))
		{
			return "O";
		}
		
		// Check if tie. No winners at this point, so if the board is full, it's automatically a tie.
		int count = 0;
		
		for(int row = 0; row < board.length; row++)
		{
			for(int column = 0; column < board[row].length; column++)
			{
				// If it spots a single open space, break, if not, continue.
				if(board[row][column] != ' ')
				{
					count++; // Counting number of non-space characters
					continue;
				}
				else
				{
					break;
				}
			}
		}
		
		// Should be 9 elements in a 3x3 2D array. If sum got to 9, it's a tie.
		if(count == 9) return "TIE";
				
		return null;
	}
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner playerInput = new Scanner(System.in);
		
		String winner = null;
		while(winner == null)
		{
			game.printBoard();
			System.out.println("Player's turn.");
			game.makePlayerMove(playerInput);
			game.printBoard();
			System.out.println("Computer's turn.");
			game.makeComputerMove();
			winner = game.checkWinner();
		}
		game.printBoard();
		if(winner.equals("TIE"))
		{
			System.out.println("It's a TIE!");
		}
		else
		{
			System.out.println(winner + " is the winner!");
		}
	}

}
