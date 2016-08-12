/*********************************************************/
import java.io.FileNotFoundException;
import java.util.Scanner;
public class StrategoTest {
/**
 * @throws FileNotFoundException ****************************************************/

		public static void main(String[] args) throws FileNotFoundException
		{ char PlayAgain='Y';
			while((PlayAgain!='N')||(PlayAgain!='n'))
			{
				Scanner input= new Scanner(System.in);
				char c1='X';
				char c2= 'O';
				Board newBoard= new Board();
				ComputerPlayer compPlayer=new ComputerPlayer();
				
				//for Player X's turn:
				newBoard.setSide(c1);
				System.out.println("Player X will be Computer");
				newBoard.SetBoard("Random", c1);
				 for(int i = 6; i < 10; i++)
				    {
				     for(int j = 0; j < 10; j++)
				     {
				    	 compPlayer.BoardMemoryArray[i][j].setpieceRank(newBoard.tile[i][j].getRank());
				    	 compPlayer.BoardMemoryArray[i][j].setPlayer(newBoard.tile[i][j].getPlayer());
				     }
				   }
				newBoard.setSide(c2);//for Player O's turn:
				System.out.println(newBoard);
				System.out.println("Player O, enter file name to load board or 'random': ");
				String filein= input.next();
				newBoard.SetBoard(filein, c2);
				for(int i = 0; i < 4; i++)
			    {
			     for(int j = 0; j < 10; j++)
			     {
			      compPlayer.BoardMemoryArray[i][j].setPlayer(newBoard.tile[i][j].getPlayer()); //sets player to
			     }
			   }
				System.out.println(newBoard);
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				while(newBoard.getGameEnd()!=0)
				{	
					newBoard.setSide(c2);
					System.out.println(newBoard);
					//get row_no and column_no of tile where piece is placed
					System.out.println("Player " + newBoard.getSide()+ ", enter an integer row and integer column to move FROM:\n");
					int row=input.nextInt();
					int column=input.nextInt();
					String amoves=newBoard.AvailableMoves(row, column);
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
								System.out.println(" "+newBoard.Attack(row, column, choice, 0, 0));
								int RowTo=newBoard.setRowto(row, column, choice);
						        int ColumnTo=newBoard.setColumnto(row, column, choice);
						        compPlayer.refreshMemory(row, column, RowTo, ColumnTo);
								System.out.println(newBoard);
						}
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					newBoard.setSide(c1);
					compPlayer.selectBestMove();
				}	
				
				System.out.print("Play Again? Y/N");
				PlayAgain= input.next().charAt(0);
			}
					
		}

}
