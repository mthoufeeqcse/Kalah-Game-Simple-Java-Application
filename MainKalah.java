import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainKalah {

	public static void main(String[] args) throws Exception {

		System.out.println("Welcome to Kalah");
		
		ArrayList<Player> playerList = new ArrayList<>();
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1;i<3;i++)
		{
			System.out.println("Enter the name of Player"+i);
			Player player = new Player();
			player.setPlayerName(br.readLine());
			playerList.add(player);
		}
		
		System.out.println("Here we go !!"+ "\n"+ playerList.get(0).getPlayerName() +" "+ "Vs" +" "+ playerList.get(1).getPlayerName() + "\n");
		
		Kalah kalah = new Kalah();
		kalah.play(playerList);
		
	}

}
