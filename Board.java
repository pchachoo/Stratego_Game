import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
	static char Side='X';
    //static Piece Player1Pieces[]= new Piece[40]; //static Piece Player2Pieces[]= new Piece[40];
	private static final int MAX_TILES=40;
	private static int gameEnd=1;
	static int MovesCount=0; //used to remove error when player inputs invalid move choice
	
	int getMovesCount() { return MovesCount;}

	public void setSide(char side){Side=side;}
	
	public char getSide(){return Side;}

	public int getGameEnd()	{ return gameEnd;	} 
	
	public static Piece[][] tile= new Piece [10][10];
	
	
	public Board()
	{	for(int i=0;i<10;i++)
		{	for(int j=0;j<10;j++)
			{	if(((i==4)&&(j==2))||((i==4)&&(j==3))||((i==5)&&(j==2))||((i==5)&&(j==3))||((i==4)&&(j==6))||((i==4)&&(j==7))||((i==5)&&(j==6))||((i==5)&&(j==7)))
					tile[i][j]=new Piece('*','*');
				else
					tile[i][j]=new Piece('0',' ');
			}
		}
	}
	
	public void SetBoard(String inputfile, char side) throws FileNotFoundException
	{		int x=0, y=0;
			if((inputfile.toUpperCase().equals("RANDOM")))
			{	int currentTiles=MAX_TILES;
				while(currentTiles!=0)
				{	if(side=='X')	x=(int) (6+Math.random()*4);
					else if(side=='O')	x=(int) (Math.random()*4);	
					y=(int) (Math.random()*10); 
					char rank = ' ';
					if(availableTile(x,y))
					{	switch(currentTiles)
						{
							case 0: { rank='*'; break;}
							case 1: { rank='1'; break;}
							case 2: { rank='2'; break;}
							case 3: case 4: { rank='3';break;}
							case 5: case 6: case 7: { rank='4'; break;}
							case 8: case 9: case 10: case 11: { rank='5';  break;}
							case 12: case 13: case 14: case 15: { rank='6'; break;}
							case 16: case 17: case 18: case 19: { rank='7';  break;}
							case 20: case 21: case 22: case 23: case 24: { rank='8'; break;}
							case 25: case 26: case 27: case 28: case 29: case 30: case 31: case 32: { rank='9'; break;}
							case 33: { rank='S';  break;}
							case 34: case 35: case 36: case 37: case 38: case 39: { rank='B'; break;}
							case 40: { rank='F';  break;}
							default: {rank= '0'; break;}
						}
						tile[x][y]=new Piece(rank, Side);
						currentTiles--;
					}				
				}
			}
			
			else //set board from file
				{	int rowMin, rowMax;
					File inFile= new File (inputfile);
					Scanner fileinput= new Scanner(inFile);
					if(Side=='X') 
					{	rowMin=6; rowMax=9;
						for(int i=rowMin; i<=rowMax; i++)
							for(int j=0;j<10;j++)
								tile[i][j]= new Piece(fileinput.next().charAt(0), Side);
					} 
					else
					{	rowMin=0; rowMax=3;
						for(int i=rowMax; i>=rowMin; i--)
							for(int j=9;j>=0;j--)
								tile[i][j]= new Piece(fileinput.next().charAt(0), Side);
					}
				}
	}
					
	public String toString()
	{	String disBoard="";
		disBoard=disBoard+"\t O-Side";
		disBoard=disBoard+"\n   0 1 2 3 4 5 6 7 8 9";
		disBoard=disBoard+"\n_________________________\n";
		for(int i=0; i<10; i++)
			{	disBoard=disBoard+i+"|";
					for(int j=0; j<10; j++)	
					{
						if(tile[i][j].getPlayer()!=Side)
								disBoard=disBoard+" "+ tile[i][j].getPlayer();
						else
							disBoard=disBoard+" "+ tile[i][j].getRank();
					}
					disBoard=disBoard+" |"+i+"\n";
			}
		disBoard=disBoard+"_________________________";
		disBoard=disBoard+"\n   0 1 2 3 4 5 6 7 8 9";
		disBoard=disBoard+"\n\t X-Side\n";
		return disBoard;
	}

	public static boolean availableTile(int r, int c)
	{ /*checks if tile is available; 
		i.e. tile is not in lake or occupied by another of player's pieces*/
		boolean available_tile=true;
		if((tile[r][c].getPlayer()=='*')||(tile[r][c].getPlayer()==Side))
			available_tile=false;
		return available_tile;
	}

	public static String AvailableMoves(int row,int column)
	{	String availableMoves="";
		MovesCount=0;
		if(availableTile(row, column)!=true) //checks if tile is occupied; 
		{			
			if((tile[row][column].getRank()=='B')||(tile[row][column].getRank()=='F')
					||(tile[row][column].getPlayer()!=Side))
			{
				availableMoves= availableMoves+"Error - Unmoveable Piece\n";
			}
			
			else if(tile[row][column].getRank()=='9') //needs to check if tile is occupied by piece belonging to opponent
			{	int j=column;	availableMoves= availableMoves+"Available moves:\n";	
				for(int i=row+1;i<=10;i++)//for all rows below row till bottom row (column is constant)
				{       if(availableTile(i, j)==true)					
			        	{	MovesCount++;
			        		availableMoves= availableMoves+MovesCount+". "+i+","+j+"\n";	
			        //		if(tile[i][j].getRank()!=' ') { break;	}	
			        		if(((Side=='X')&&(tile[i][j].getPlayer()=='O'))||((Side=='O')&&(tile[i][j].getPlayer()=='X')))
								break;
			        	}	else break; // because one cannot jump pieces and we need to check till some other piece is found
			    }				 
			    for(int i=row-1;i>0;i--) //  for all rows above row till top (column is const)
				{       if(availableTile(i, j)==true)//&&(tile[i][j].getPlayer()!=Side))
			        	{	MovesCount++;
			        		availableMoves= availableMoves+MovesCount+". "+i+","+j+"\n";	
			        	//	if(tile[i][j].getRank()!=' ') { break;	}
			        		if(((Side=='X')&&(tile[i][j].getPlayer()=='O'))||((Side=='O')&&(tile[i][j].getPlayer()=='X')))
								break;
			        	}else break; // because one cannot jump pieces and we need to check till some other piece is found
			    }	
			    int i=row;
				{	for(int j1=column+1; j1<=10; j1++)//for all columns to right of column till rightmost column(row is constant)
					{	if(availableTile(i, j)==true)//&&(tile[i][j].getPlayer()!=Side))
			        	{	MovesCount++;
			        		availableMoves= availableMoves+MovesCount+". "+i+","+j1+"\n";	
			        	//	if(tile[i][j].getRank()!=' ') { break;	}
			        		if(((Side=='X')&&(tile[i][j].getPlayer()=='O'))||((Side=='O')&&(tile[i][j].getPlayer()=='X')))
								break;
			        	}else break; 
				}// because scout cannot jump pieces and we need to check till some other piece is found
				}
			    // for all columns to left of column till leftmost column(row is constant)
				{	for(int j1=column-1; j1>0; j1--)
			        {if(availableTile(i, j)==true)
			        	{	MovesCount++;
			        		availableMoves= availableMoves+MovesCount+". "+i+","+j1+"\n";
			        	//	if(tile[i][j].getRank()!=' ') { break;	}
			        		if(((Side=='X')&&(tile[i][j].getPlayer()=='O'))||((Side=='O')&&(tile[i][j].getPlayer()=='X')))
								break;
			        	}else break;
			        }// because one cannot jump pieces and we need to check till some other piece is found
			    }			    
			}
			else
			{		//displays available moves; 
					availableMoves= availableMoves+"Available moves:\n";
					if((row==0)||(row==9))
					{	int i=row;
						for(int j=column-1;j<=column+1;j++)
						{	if((availableTile(i,j)==true)&&
									(i>0)&&(j>0)&&(i<10)&&(j<10)&&
									((i==row)||(j==column)))
							{	MovesCount++;
								availableMoves= availableMoves+MovesCount+". "+i+","+j+"\n";							
							}					
						}						
					}
					
					if((column==0)||(column==9))
					{	int j=column;
						for(int i=row-1;i<=row+1;i++)
						{		if((availableTile(i,j)==true)&&
											(i>0)&&(j>0)&&(i<10)&&(j<10)&&
											((i==row)||(j==column)))
									{	MovesCount++;
										availableMoves= availableMoves+MovesCount+". "+i+","+j+"\n";							
									}
								
								
						}						
					}
					
					else 
					{
							for(int i=row-1;i<row+2;i++)
						{	for(int j=column-1;j<column+2;j++)
								{	if((availableTile(i,j)==true)&&
											(i>0)&&(j>0)&&(i<10)&&(j<10)&&
											((i==row)||(j==column)))
									{	MovesCount++;
										availableMoves= availableMoves+MovesCount+". "+i+","+j+"\n";							
									}
								
								}
						}
					}
					
			}	if(MovesCount==0) availableMoves= availableMoves+"No Moves\n";	
		}
		return availableMoves;
	} //end of function movePiece()	
	
	public static String MoveFromAvailableMoves(int row, int column, int choice)
	{
		String availableMoves=Board.AvailableMoves(row,column);
		String makeMove; 
		System.out.println(row+"   "+column);
		char[] a= new char[3];
		System.out.println("int choice:"+choice);
		char choiceChar = (char) (choice+0x30);//converting to char
		System.out.println("char choice:"+choice);
		System.out.println(availableMoves.length());
	/*	for(int i=17;i<availableMoves.length();i=i+7)
		{
			System.out.println(i);
		} */
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
	public static int setRowto(int rowFrom, int columnFrom, int choice)
	{
	//	System.out.println(MoveFromAvailableMoves(rowFrom,columnFrom,choice));
		char Row=MoveFromAvailableMoves(rowFrom,columnFrom,choice).charAt(0);
		return Row-0x30;
	}
	
	public static int setColumnto(int rowFrom, int columnFrom, int choice)
	{
	//	System.out.println(MoveFromAvailableMoves(rowFrom,columnFrom,choice));
		char Column=MoveFromAvailableMoves(rowFrom,columnFrom,choice).charAt(2);
		return Column-0x30;
	}
	
	public static String Attack(int rowFrom, int columnFrom, int choice, int rowto, int columnto)
	{//compares piece's rank with opponent's and attacks accordingly; incorporate special cases
		//different cases for whether it is human move or computer move
		int RowTo=rowto; //computer move
		int ColumnTo=columnto; //computer move
		
		if(choice!=0) //human move
		{
			RowTo=setRowto(rowFrom, columnFrom, choice); 
			ColumnTo=setColumnto(rowFrom, columnFrom, choice);
		}
		 
		System.out.println("RowTo"+RowTo+" ColumnTo:"+ColumnTo);
		String temp="";
		if(tile[RowTo][ColumnTo].getRank()=='F')
		{	temp= endGame(tile[rowFrom][columnFrom].getPlayer(), tile[RowTo][ColumnTo].getPlayer());
			tile[RowTo][ColumnTo].Die();//flag
			return temp;}
		
		else if((tile[rowFrom][columnFrom].getRank()=='1')&&(tile[RowTo][ColumnTo].getRank()!='S'))
		{	temp=  attackDisplays(tile[rowFrom][columnFrom], tile[RowTo][ColumnTo]);
			tile[RowTo][ColumnTo].Die();//marshal- non-spy
			tile[RowTo][ColumnTo]=tile[rowFrom][columnFrom];
			tile[rowFrom][columnFrom].Die();
			return temp;}
		
		else if((tile[rowFrom][columnFrom].getRank()=='1')&&(tile[RowTo][ColumnTo].getRank()=='S'))
		{	temp=  attackDisplays(tile[rowFrom][columnFrom], tile[rowFrom][columnFrom]);
			ComputerPlayer.pieceRevealedRank =tile[rowFrom][columnFrom].getRank();
			tile[rowFrom][columnFrom].Die();//marshal-spy
			tile[rowFrom][columnFrom]=tile[RowTo][ColumnTo];
			tile[RowTo][ColumnTo].Die();
			return temp;}
		
		else if((tile[RowTo][ColumnTo].getRank()=='B'))
		{	temp=  attackDisplays( tile[RowTo][ColumnTo], tile[rowFrom][columnFrom]);
			ComputerPlayer.pieceRevealedRank =tile[rowFrom][columnFrom].getRank();
			tile[rowFrom][columnFrom].Die();
			return temp;}
		
		else if((tile[rowFrom][columnFrom].getRank()< tile[RowTo][ColumnTo].getRank())&&(tile[RowTo][ColumnTo].getRank()!=' '))
		{	temp=  attackDisplays(tile[rowFrom][columnFrom], tile[RowTo][ColumnTo]);
			tile[RowTo][ColumnTo].Die();
			tile[RowTo][ColumnTo]=tile[rowFrom][columnFrom];	
			tile[rowFrom][columnFrom].Die();
			return temp;}
		
		else if(tile[rowFrom][columnFrom].getRank()== tile[RowTo][ColumnTo].getRank())
		{	temp=  Tie(tile[rowFrom][columnFrom], tile[RowTo][ColumnTo]);
			tile[RowTo][ColumnTo].Die();	
			tile[rowFrom][columnFrom].Die();
			return temp;}
		
		else if ((tile[rowFrom][columnFrom].getRank()> tile[RowTo][ColumnTo].getRank())&&(tile[RowTo][ColumnTo].getRank()!='0'))
		{	temp= attackDisplays(tile[RowTo][ColumnTo], tile[rowFrom][columnFrom] );
			ComputerPlayer.pieceRevealedRank=tile[rowFrom][columnFrom].getRank();
			tile[rowFrom][columnFrom].Die();
			tile[rowFrom][columnFrom]=tile[RowTo][ColumnTo];	
			tile[RowTo][ColumnTo].Die();
			return temp;}
		
		else //moving to blank tile
		{//	if (tile[RowTo][ColumnTo].getRank()=='0')
			tile[RowTo][ColumnTo]=tile[rowFrom][columnFrom].clone();	
			tile[rowFrom][columnFrom].Die();
			return "";}
			
	}
		
	private static String endGame(char p1, char p2) {
		// TODO Auto-generated method stub
		gameEnd=0;
		return ""+p1+"defeated "+p2+"\nGAME OVER";
	}
	
	private static String Tie(Piece tile, Piece tile2) {
		// TODO Auto-generated method stub
		gameEnd=0;
		return ""+tile+"had a draw with "+tile2+"\nBoth pieces died.";
	}

	private static String attackDisplays(Piece p1, Piece p2)
	{
		return ""+p1+"defeated "+p2;
	}
	
	public Board clone()
	{
		return new Board();
	}
		
}//end of class