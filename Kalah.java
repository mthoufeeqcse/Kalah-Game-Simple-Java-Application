import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Kalah extends KalahBoard{

	/***
	 * @param playerList
	 * @Description : The method implements the core logic of the game
	 * 
	 */
	public void play(ArrayList<Player> playerList) throws Exception
	{
		Hashtable<Integer, Integer> hm = new Hashtable<Integer,Integer>();
		Pits pits = new Pits(hm);

		boolean isLastStone=false;
		int nextPit=0;
		int stoneCount=0;
		int currentPit=0;

		try{
			displayBoard(playerList,pits);
			do
			{
				selectPlayerTurn(playerList,currentPit,isLastStone);
				int selectedPit = getSelectedPit(playerList,hm);
				nextPit = selectedPit+1;
				int totalStones= hm.get(selectedPit);
				stoneCount = totalStones;
				for(int i=0;i<totalStones;)
				{
					if(nextPit>14)
					{
						nextPit=1;
					}
					if(stoneCount==1)
					{
						isLastStone=true;
					}

					hm.put(selectedPit, hm.get(selectedPit)-1);

					if(isOpponentKalah(playerList,nextPit))
					{
						nextPit = nextPit+1;
						hm.put(nextPit,hm.get(nextPit)+1);
					}
					else
					{
						hm.put(nextPit,hm.get(nextPit)+1);
					}
					currentPit=nextPit;
					nextPit++;
					i++;
					stoneCount--;

				}
				if(isLastStoneOnOwnEmptyPit(isLastStone,playerList,currentPit,hm))
				{
					captureStones(playerList,currentPit,hm);
				}
				pits.setHm(hm);
				displayBoard(playerList,pits);

			}while((!isGameOver(hm)));

			collectRemainingStones(hm,playerList);
			pits.setHm(hm);
			displayBoard(playerList,pits);
			determineWinner(playerList,pits);

		}
		catch(Exception e)
		{
			System.out.println("Oops!! Something went wrong.Please restart the game again");
		}


	}

	/***
	 * 
	 * @Description : The method read and validate the input from the user 
	 */
	private int getPitNumber() throws Exception {

		boolean isValidNumber=false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int pitNumber=0;
		do
		{
			try
			{
				pitNumber = Integer.parseInt(br.readLine());
				if(pitNumber>14)
				{
					System.out.println("Please select a valid pit number.Try again!!");
					pitNumber=getPitNumber();
				}
				isValidNumber=true;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Please select a valid number.Try again!!");
			}
		}while(!isValidNumber);

		return pitNumber;
	}

	/***
	 * @param playerList,hm
	 * @Description : The method identify and validate the selected pit.
	 * Instance - if the user selects opponent pits or selects empty pit
	 */
	private int getSelectedPit(ArrayList<Player> playerList, Hashtable<Integer,Integer> hm) throws Exception {

		boolean isValidSelection=false;
		int pitNumber=0;
		int selectedPit=0;
		do
		{
			pitNumber = getPitNumber();
			selectedPit=increasePitNumber(pitNumber, playerList.get(0).playerTurn, selectedPit);
			if(playerList.get(0).playerTurn && selectedPit >=player1Kalah  && selectedPit <15)
			{
				System.out.println("Not a valid pit.Please select a pit from your side!!");
				selectedPit=getSelectedPit(playerList,hm);
				isValidSelection=true;

			}
			else if(playerList.get(1).playerTurn
					&& selectedPit >=player2Kalah && selectedPit <= player1Kalah)
			{
				System.out.println("Not a valid pit.Please select a pit from your side!!");
				selectedPit=getSelectedPit(playerList,hm);
				isValidSelection=true;

			}
			else if(hm.get(selectedPit)==0)
			{
				System.out.println("The selected pit is empty. please select another pit!!");
				selectedPit=getSelectedPit(playerList,hm);
				isValidSelection=true;

			}
			else
			{
				isValidSelection=true;
			}
		}
		while(!isValidSelection);

		return selectedPit;

	}

	/***
	 * @param hm,playerList
	 * @Description : The method collects all the remaining stones 
	 *                when all the opponent pits are empty.
	 */
	private void collectRemainingStones(Hashtable<Integer, Integer> hm, ArrayList<Player> playerList) {

		if(hm.get(2) ==0 && hm.get(3)==0 && hm.get(4)==0 && hm.get(5)==0 && hm.get(6)==0 && hm.get(7)==0)
		{
			for (int i=9;i<=14;i++)
			{
				hm.put(player2Kalah, hm.get(player2Kalah)+ hm.get(i));
				hm.put(i, 0);
			}
		}
		else if(hm.get(9) ==0 && hm.get(10)==0 && hm.get(11)==0 && hm.get(12)==0 && hm.get(13)==0 && hm.get(14)==0)
		{
			for (int i=2;i<=7;i++)
			{
				hm.put(player1Kalah, hm.get(player1Kalah)+ hm.get(i));
				hm.put(i, 0);
			}

		}

	}

	/***
	 * @param hm
	 * @Description : Determines end of game when all pits on any one side is empty
	 */
	private boolean isGameOver(Hashtable<Integer, Integer> hm) {

		if(hm.get(2) ==0 && hm.get(3)==0 && hm.get(4)==0 && hm.get(5)==0 && hm.get(6)==0 && hm.get(7)==0 ||
				hm.get(9) ==0 && hm.get(10)==0 && hm.get(11)==0 && hm.get(12)==0 && hm.get(13)==0 && hm.get(14)==0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/***
	 * @param playerList,currentPit,hm
	 * @Description : The method captures stones if last stone lands on own empty pit 
	 * and put into players kalah.
	 */
	private void captureStones(ArrayList<Player> playerList, int currentPit, Hashtable<Integer,Integer> hm) {

		int oppositePit = findOppositePit(currentPit);

		if(playerList.get(0).playerTurn)
		{
			if(null!=hm.get(oppositePit))
			{
				hm.put(player1Kalah,hm.get(player1Kalah)+hm.get(oppositePit)+1);
			}
			else
			{
				hm.put(player1Kalah,hm.get(player1Kalah)+1);
			}

		}
		else if(playerList.get(1).playerTurn)
		{
			if(null!=hm.get(oppositePit))
			{
				hm.put(player2Kalah,hm.get(player2Kalah)+hm.get(oppositePit)+1);
			}
			else
			{
				hm.put(player2Kalah,hm.get(player2Kalah)+1);
			}
		}

		hm.put(currentPit, 0);
		hm.put(oppositePit,0);
	}

	/***
	 * @param currentPit
	 * @Description : The method identifies the opposite opponent pit for the current pit
	 */
	private int findOppositePit(int currentPit) {

		int oppositePit = 0;
		if(currentPit==2)
		{
			oppositePit=14;
		}
		else if(currentPit==3)
		{
			oppositePit=13;
		}
		else if(currentPit==4)
		{
			oppositePit=12;
		}
		else if(currentPit==5)
		{
			oppositePit=11;
		}
		else if(currentPit==6)
		{
			oppositePit=10;
		}
		else if(currentPit==7)
		{
			oppositePit=9;
		}
		else if(currentPit==9)
		{
			oppositePit=7;
		}
		else if(currentPit==10)
		{
			oppositePit=6;
		}
		else if(currentPit==11)
		{
			oppositePit=5;
		}
		else if(currentPit==12)
		{
			oppositePit=4;
		}
		else if(currentPit==13)
		{
			oppositePit=3;
		}
		else if(currentPit==14)
		{
			oppositePit=2;
		}
		return oppositePit;

	}

	/***
	 * @param isLastStone,playerList,currentPit,hm
	 * @Description : The method checks if the last stone lands on own empty pit.
	 */
	private boolean isLastStoneOnOwnEmptyPit(boolean isLastStone, ArrayList<Player> playerList, int currentPit, Hashtable<Integer,Integer> hm) {

		if(isLastStone)
		{
			if(playerList.get(0).playerTurn && currentPit>1 && currentPit<8 && hm.get(currentPit)==1)
			{
				return true;
			}
			else if(playerList.get(1).playerTurn && currentPit>8 && currentPit<15 && hm.get(currentPit)==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}

	}

	/***
	 * @param pitNumber,player1Turn,selectedPit
	 * @Description : The method increases the selected pit number by 1 to match the mapping with 
	 * Hashtable. In hash table mapping player1kalah and player2kalah are 8 and 1 respectively
	 */
	private int increasePitNumber(int pitNumber, boolean player1Turn, int selectedPit) {

		if(player1Turn)
		{
			selectedPit=pitNumber+1;
		}
		else
		{
			selectedPit=pitNumber+2;
		}
		return selectedPit;
	}

	/***
	 * @param playerList,currentPit,isLastStone
	 * @Description : The method selects the next player turn.
	 */
	private void selectPlayerTurn(ArrayList<Player> playerList, int currentPit,boolean isLastStone) {

		if(firstGame)
		{
			playerList.get(0).setPlayerTurn(true);
			firstGame=false;
		}
		else if(playerList.get(0).playerTurn)
		{
			if(isLastStone && currentPit==player1Kalah)
			{
				playerList.get(0).setPlayerTurn(true);
				playerList.get(1).setPlayerTurn(false);
			
			}
			else
			{
				playerList.get(0).setPlayerTurn(false);
				playerList.get(1).setPlayerTurn(true);
				
			}
		}
		else if(playerList.get(1).playerTurn)
		{
			if(isLastStone && currentPit==player2Kalah)
			{
				playerList.get(0).setPlayerTurn(false);
				playerList.get(1).setPlayerTurn(true);
				
			}
			else
			{
				playerList.get(0).setPlayerTurn(true);
				playerList.get(1).setPlayerTurn(false);
				
			}
		}

		if(playerList.get(0).playerTurn)
		{
			System.out.println(playerList.get(0).getPlayerName() + " "+"Please select your pit [1,2,3,4,5,6] :");
		}
		else if(playerList.get(1).playerTurn)
		{
			System.out.println(playerList.get(1).getPlayerName() + " "+"Please select your pit [7,8,9,10,11,12] :");
		}

	}

	/***
	 * @param playerList,currentPit
	 * @Description : The method determines the opposite Kalah to skip from putting stones in opponent kalah
	 */
	private boolean isOpponentKalah(ArrayList<Player> playerList, int currentPit)
	{
		if(playerList.get(0).playerTurn && currentPit==player2Kalah || playerList.get(1).playerTurn && currentPit==player1Kalah)
		{
			return true;
		}
		else
		{
			return false;
		}


	}


}

