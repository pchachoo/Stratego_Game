
public class CompPlayerTest {
public static void main(String args[])
{
	ComputerPlayer compPlayer=new ComputerPlayer();
	for(int i = 0; i < 10; i++)
	{
		for(int j = 0; j < 10; j++)
		{	
			ComputerPlayer.BoardMemoryArray[i][j]= new ComputerMemory();
		}
	}
	ComputerPlayer.BoardMemoryArray[0][1].pieceRank= '9';
	ComputerPlayer.BoardMemoryArray[0][1].Player='X';
	System.out.println(compPlayer.toString());
}
}
