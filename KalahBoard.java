import java.util.ArrayList;

public class KalahBoard {

	boolean firstGame = true;

	static final int player1Kalah=8;
	static final int player2Kalah=1;
	
	
	/***
	 * @param playerList,pits
	 * @Description : The method displays the kalah board with remaining stones 
	 *                in each pit on every players turn
	 */
	public void displayBoard(ArrayList<Player> playerList,Pits pits)
	{
		if(null!=pits && null!= pits.getHm())
		{
			System.out.println("-----------------------------------------------");
			System.out.printf("%7s %7s %7s %7s %7s %7s", "pit6", "pit5", "pit4", "pit3", "pit2","pit1");
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.printf("%7s %7s %7s %7s %7s %7s",pits.getHm().get(7),pits.getHm().get(6), pits.getHm().get(5), pits.getHm().get(4),
					pits.getHm().get(3),pits.getHm().get(2));
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.printf("%7s %7s %7s %7s %7s %7s", 
					pits.getHm().get(9),pits.getHm().get(10), pits.getHm().get(11), pits.getHm().get(12),
					pits.getHm().get(13),pits.getHm().get(14) + "\n");
			System.out.println("-----------------------------------------------");
			System.out.printf("%7s %7s %7s %7s %7s %7s", "pit7", "pit8", "pit9", "pit10", "pit11","pit12" + "\n");
			System.out.println("-----------------------------------------------");
			if(null!=playerList && null!=playerList.get(0) && null!=playerList.get(1))
			{
				System.out.println("\t |" + playerList.get(0).getPlayerName() +"'s" + "Kalah :"+ pits.getHm().get(8) +
						"| \t |" + playerList.get(1).getPlayerName() + "'s" + "Kalah :" + pits.getHm().get(1)+ "|");
			}
						
			System.out.println("-----------------------------------------------");
		}
		

	}

	
	/***
	 * @param playerList,pits
	 * @Description : The method determines and display the winner 
	 * based on the no.of stones in players Kalah
	 */
	public void determineWinner(ArrayList<Player> playerList,Pits pits)
	{
		if(null!=pits && null!=pits.getHm())
		{
			System.out.println("-----------------------------------------------");
			if(pits.getHm().get(player1Kalah)>pits.getHm().get(player2Kalah))
			{
				if(null!=playerList && null!=playerList.get(0).getPlayerName())
				{
					System.out.println("Congratulations" + " " + playerList.get(0).getPlayerName() + "!! " + "You won the game");
				}
			}
			else if(pits.getHm().get(player1Kalah)<pits.getHm().get(player2Kalah))
			{
				if(null!=playerList && null!=playerList.get(1).getPlayerName())
				{
					System.out.println("Congratulations" + " " + playerList.get(1).getPlayerName() +  "!! " +"You won the game");
				}
				
			}
			System.out.println("-----------------------------------------------");
			System.out.println("GAME END!!");
		}
		

	}


}

