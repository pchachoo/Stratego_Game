import java.io.FileNotFoundException;
import java.util.Scanner;


public class MoveFromAvailableMovesTest {
	
	public static String MoveFromAvailableMoves(int row, int column, int choice)
	{
		String availableMoves=Board.AvailableMoves(row,column);
		String makeMove; 
		char[] a= new char[3];
		System.out.println("int choice:"+choice);
		char choiceChar = (char) (choice+0x30);//converting to char
		System.out.println("char choice:"+choice);
		System.out.println(availableMoves.length());
		for(int i=17;i<availableMoves.length();i=i+7)
		{
			System.out.println(i);
		}
		for(int i=17;i<availableMoves.length();i=i+7)
			{if(choiceChar==availableMoves.charAt(i))
				{	a[0]= availableMoves.charAt(i+3); 
					System.out.println(a[0]);
					a[1]= availableMoves.charAt(i+4);
					System.out.println(a[1]);
					a[2]= availableMoves.charAt(i+5);
					System.out.println(a[2]);
				}
			}
		makeMove=""+a[0]+a[1]+a[2];
		System.out.println("makeMove: "+makeMove);
		return makeMove;	
	}
	
	public static void main(String args[]) throws FileNotFoundException
	{
		Board newBoard= new Board();
		Scanner input= new Scanner(System.in);
		char c1='X';
		char c2= 'O';
		newBoard.setSide(c1);
		System.out.println("Player X will be Computer");
		newBoard.SetBoard("Random", c1);
		
		newBoard.setSide(c2);//for Player O's turn:
		System.out.println(newBoard);
		System.out.println("Player O, enter file name to load board or 'random': ");
		String filein= input.next();
		newBoard.SetBoard(filein, c2);
		System.out.println(newBoard);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		MoveFromAvailableMoves(3,4,1);
		/*while(newBoard.getGameEnd()!=0)
		{	
			newBoard.setSide(c2);
			System.out.println(newBoard);
			//get row_no and column_no of tile where piece is placed
			System.out.println("Player " + newBoard.getSide()+ ", enter an integer row and integer column to move FROM:\n");
			int row=input.nextInt();
			int column=input.nextInt();
			String amoves=Board.AvailableMoves(row, column);
			System.out.println(amoves);
			//add code to enter choice and attack
			if((amoves.compareTo("Available moves:\nNo Moves\n")!=0)&&(amoves.compareTo("Error - Unmoveable Piece\nNo Moves\n")!=0)
					&&(amoves.compareTo("")!=0))
				{
					int choice=0;
					while(choice<1 || choice>newBoard.getMovesCount())
					{
						System.out.println("Enter choice number");
						choice=input.nextInt();
					}
						System.out.println(" "+Board.Attack(row, column, choice, 0, 0));
						int RowTo=Board.setRowto(row, column, choice);
				        int ColumnTo=Board.setColumnto(row, column, choice);
				        ComputerPlayer.refreshMemory(row, column, RowTo, ColumnTo);
						System.out.println(newBoard);
				}
		}	*/
		
	}

}
