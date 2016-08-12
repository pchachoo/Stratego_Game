/*********************************************************/
import java.util.LinkedList;
public class ComputerMemory {
	char pieceRank=' ';
	char Player=' ';
		//linked list to store all available moves for that piece
		LinkedList<int[]> MovesForPiece= new LinkedList<int[]>();
		
		public ComputerMemory()
		{	setpieceRank('0');
			setPlayer('X');	
		}
		
		public ComputerMemory(char c, char d)
		{
			setpieceRank(c);
			setPlayer(d);	
		}
		
		public void setpieceRank(char rank)
		{
			if(rank== 'S' || rank=='B' || rank=='F' || ((rank>=1)&&(rank<=9)))
				pieceRank=rank;
			else pieceRank='0';
		}
		
		public void setPlayer(char p)
		{
			if(p== 'X' || p=='O')
				Player=p;
			else Player=' ';
		}
		
		public char getpieceRank()
		{return pieceRank;}
		
		public char getPlayer()
		{return Player;	}
		
		public ComputerMemory clone()
		{return new ComputerMemory(this.getpieceRank(), this.getPlayer());}
	
		public void Die()//frees tile if if piece is dead
		{	setpieceRank('0');
			setPlayer(' ');
		}
}
