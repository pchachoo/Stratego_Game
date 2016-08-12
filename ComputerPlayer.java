/*********************************************************/
public class ComputerPlayer {
	//Computer Plays as X
//	static Board b= new Board();
	static ComputerMemory BoardMemoryArray[][] = new ComputerMemory[10][10];
	static int originalRow=0;
	static int originalColumn=0;
	static int finalRow=0;
	static int finalColumn=0;
	static char pieceRevealedRank;
	
	public ComputerPlayer()
	{//b=new Board();
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{	
				ComputerPlayer.BoardMemoryArray[i][j]= new ComputerMemory();
			}
		}		
	}

	public static void refreshMemory(int rowFrom, int columnFrom, int rowTo, int columnTo) 
	{
		// TODO Auto-generated method stub
		BoardMemoryArray[rowFrom][columnFrom].setpieceRank(Board.tile[rowFrom][columnFrom].getRank());
		BoardMemoryArray[rowTo][columnTo].setpieceRank(Board.tile[rowTo][columnTo].getRank());
		BoardMemoryArray[rowFrom][columnFrom].setPlayer(Board.tile[rowFrom][columnFrom].getPlayer());
		BoardMemoryArray[rowTo][columnTo].setPlayer(Board.tile[rowTo][columnTo].getPlayer());
	}
	
	public void getAllAvailableMoves()
	{
		String availableMovesPerPiece;
			for(int i=0;i<10; i++)
			{	for(int j=0;j<10; j++)
				{	if(BoardMemoryArray[i][j].getPlayer()=='X')
					{	availableMovesPerPiece=Board.AvailableMoves(i, j);
						for(int x=19; x< availableMovesPerPiece.length(); x=x+6)
						{	char DestinationRow=availableMovesPerPiece.charAt(x-2);
							char DestinationColumn=availableMovesPerPiece.charAt(x);
							int tempArray[]= new int[2];
							tempArray[0]=DestinationRow-0x30;
							tempArray[1]=DestinationColumn-0x30;
							BoardMemoryArray[i][j].MovesForPiece.add(tempArray);	
						}
					}
				}
			}
	}
	
	public void selectBestMove()
	{	getAllAvailableMoves();
		int priorityPreviousAvaiableMove=0;//variable for move priority. if in consecutive searches, 
		//move of higher priority is not found, then the current saved move should be executed
		for(int i=0;i<10; i++)
		{	for(int j=0;j<10; j++)
			{	if(BoardMemoryArray[i][j].getPlayer()!='X') continue; //checks if piece belongs to computer
				originalRow=i;
				originalColumn=j;
				int moveIndex=0;
				while(BoardMemoryArray[i][j].MovesForPiece.get(moveIndex)!=null) //will not enter loop for F, B since they have no available moves
				{   int[] tempArray=BoardMemoryArray[i][j].MovesForPiece.get(moveIndex);
					if(BoardMemoryArray[tempArray[0]][tempArray[1]].getPlayer()=='O' ) //tile being moved to is occupied by opponent
					{/*first case is if piece belongs to opponent and is of lower rank,
							second is if piece just belongs to opponent so attack is possible
								third is if piece can be moved to empty space*/
						if((BoardMemoryArray[tempArray[0]][tempArray[1]].getpieceRank()== 'S' && BoardMemoryArray[i][j].getpieceRank()=='1')
								||(BoardMemoryArray[tempArray[0]][tempArray[1]].getpieceRank()!= 'S' 
									&& BoardMemoryArray[tempArray[0]][tempArray[1]].getpieceRank()>BoardMemoryArray[i][j].getpieceRank())) 
							//greater rank value loses
						{	
							if(priorityPreviousAvaiableMove<3)
							{
								finalRow=tempArray[0];
								finalColumn=tempArray[1];
								priorityPreviousAvaiableMove=3;
							}
						} 
						else 
						{
							if(priorityPreviousAvaiableMove<2)
							{
								finalRow=tempArray[0];
								finalColumn=tempArray[1];
								priorityPreviousAvaiableMove=2;
							}
						} 
					}//end of outermost if
					
					else if(BoardMemoryArray[tempArray[0]][tempArray[1]].getPlayer()==' ')
					{
						if(priorityPreviousAvaiableMove<1)
						{
							finalRow=tempArray[0];
							finalColumn=tempArray[1];
							priorityPreviousAvaiableMove=1;
						}
					}
					moveIndex++;
				}//end of while
			}//end of second for loop [j]
		}//end of first for loop [i]
		Board.Attack(originalRow, originalColumn,0, finalRow, finalColumn);
		refreshMemory(originalRow, originalColumn, finalRow, finalColumn);
	}
	//function to find best move
}
