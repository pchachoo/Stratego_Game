public static String Attack(Board b, int rowFrom, int columnFrom, int choice)
    {//compares piece's rank with opponent's and attacks accordingly; incorporate special cases
        int RowTo=b.MoveFromAvailableMoves(rowFrom,columnFrom,choice).charAt(0);
        int ColumnTo=b.MoveFromAvailableMoves(rowFrom,columnFrom,choice).charAt(2);
        String temp="";
        if(Board.tile[RowTo][ColumnTo].getRank()=='F')
        {    temp= endGame(Board.tile[rowFrom][columnFrom].getPlayer(), Board.tile[RowTo][ColumnTo].getPlayer());
            Board.tile[RowTo][ColumnTo].Die();//flag   
            return temp;}
       
        else if((Board.tile[rowFrom][columnFrom].getRank()=='1')&&(Board.tile[RowTo][ColumnTo].getRank()!='S'))
        {    temp=  attackDisplays(Board.tile[rowFrom][columnFrom], Board.tile[RowTo][ColumnTo]);
            Board.tile[RowTo][ColumnTo].Die();//marshal- non-spy
            Board.tile[RowTo][ColumnTo]=Board.tile[rowFrom][columnFrom];
            return temp;}
       
        else if((Board.tile[rowFrom][columnFrom].getRank()=='1')&&(Board.tile[RowTo][ColumnTo].getRank()=='S'))
        {    temp=  attackDisplays(Board.tile[rowFrom][columnFrom], Board.tile[rowFrom][columnFrom]);
            Board.tile[rowFrom][columnFrom].Die();//marshal-spy
            Board.tile[rowFrom][columnFrom]=Board.tile[RowTo][ColumnTo];
            return temp;}
       
        else if((Board.tile[RowTo][ColumnTo].getRank()=='B'))
        {    temp=  attackDisplays( Board.tile[RowTo][ColumnTo], Board.tile[rowFrom][columnFrom]);
            Board.tile[rowFrom][columnFrom].Die();   
            return temp;}
       
        else if(Board.tile[rowFrom][columnFrom].getRank()< Board.tile[RowTo][ColumnTo].getRank())
        {    temp=  attackDisplays(Board.tile[rowFrom][columnFrom], Board.tile[RowTo][ColumnTo]);
            Board.tile[RowTo][ColumnTo].Die();
            Board.tile[RowTo][ColumnTo]=Board.tile[rowFrom][columnFrom];   
            return temp;}
       
        else if(Board.tile[rowFrom][columnFrom].getRank()== Board.tile[RowTo][ColumnTo].getRank())
        {    temp=  Tie(Board.tile[rowFrom][columnFrom], Board.tile[RowTo][ColumnTo]);
            Board.tile[RowTo][ColumnTo].Die();   
            Board.tile[rowFrom][columnFrom].Die();
            return temp;}
       
        else if (Board.tile[rowFrom][columnFrom].getRank()> Board.tile[RowTo][ColumnTo].getRank())
        {    temp= attackDisplays(Board.tile[RowTo][ColumnTo], Board.tile[rowFrom][columnFrom] );
            Board.tile[rowFrom][columnFrom].Die();
            Board.tile[rowFrom][columnFrom]=Board.tile[RowTo][ColumnTo];   
            return temp;}
       
        else //moving to blank tile
        {   
            Board.tile[RowTo][ColumnTo]=Board.tile[rowFrom][columnFrom];   
            return "was moved";}//test
           
    }
    