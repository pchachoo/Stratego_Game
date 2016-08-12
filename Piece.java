/******************************************************/
public class Piece {
//	static Piece pieces[]= new Piece[40];
//	boolean Color;
	private char Rank;
	private char Player;
	

	
	public void setPlayer(char player) {Player = player;}

	public Piece()
	{
		setRank('0');
		setPlayer(' ');
	}
	
	public Piece(char rank, char player)
	{
		setRank(rank);
		setPlayer(player);
	}
	
	public void setRank(char rank)
	{
		Rank=rank;		
	}
	
	public char getRank()
	{
//		setRank(pieceId);
		return Rank;
	} 

	public char getPlayer()
	{
		return Player;
	} 
	
	public String toString()
	{
		return "Player "+Player+"'s piece with rank "+Rank+" ";
	}
	
	public void Die()
	{
		//frees tile if if piece is dead
		this.setRank('0');
		this.setPlayer(' ');
	}
	

	public Piece clone()
	{
		return new Piece(this.getRank(), this.getPlayer());
	}
}
