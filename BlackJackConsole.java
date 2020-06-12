import java.util.Scanner;

import javax.swing.JOptionPane;

public class BlackJackConsole {
	public BlackJackConsole() throws EmptyDeckException{
		System.out.println("Welcomto the BlackJack table. Let's play !");
		Deck deck = new Deck(2);
		Hand hand = new Hand();
		BlackJack BlackJack = new BlackJack();
		System.out.println("Here is the deck "+ deck+"\n");
		for(int i=0;i<3;i++) {
			try {
				Card c = deck.draw();
				System.out.println("Theis card is a "+c+" worth "+c.getPoints()+" points");
			}catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
		}
		System.out.println("Your hand is currently : ["+hand+"]");
		for(int i=0; i<3; i++) {
			try {
				hand.add(deck.draw());
			}catch (EmptyDeckException ex) {
				System.out.println(ex.getMessage());
				System.exit(-1);
			}
		}
		System.out.println("Your hand is currently : "+hand.toString()+hand.count());
		System.out.println("Your point is :"+hand.best());
		BlackJack.reset();
		System.out.println("Play with the Bank!");
		System.out.println("The bank draw : "+BlackJack.getBankString()+" : "+BlackJack.getBankBest());
		System.out.println("Your draw :"+BlackJack.getPlayerHandString()+" : "+BlackJack.getPlayerBest());
		
		
		Scanner scan = new Scanner(System.in);
		boolean whileInGame = true;
		while(whileInGame)
		{
			System.out.println("Do you want another card? [y/n]");
			String choice = scan.next();
			switch(choice) {
				case  "y" :
					try{
						BlackJack.playerDrawAnotherCard();
					}catch(EmptyDeckException ex) {
						System.err.println(ex.getMessage());
						System.exit(-1);
					}
					System.out.println("Your new hand : "+BlackJack.getPlayerHandString()+" : "+BlackJack.getPlayerBest());
					
				break;
				case "n" :
					try{
						BlackJack.bankLastTurn();
					}catch(EmptyDeckException ex) {
						System.err.println(ex.getMessage());
						System.exit(-1);
					}
					System.out.println("The bank draw cards. New hand :"+BlackJack.getBankString()+" : "+BlackJack.getBankBest());
					System.out.println("Player best : "+BlackJack.getPlayerBest());
					System.out.println("Bank best : "+BlackJack.getBankBest());
					if(BlackJack.isPlayerWinner() == true && BlackJack.isBankWinner() == false) {
						System.out.println("You won!");
					}
					else if(BlackJack.isBankWinner() == true && BlackJack.isPlayerWinner() ==false) { 
						System.out.println("Bank won!");
					}
					else if(BlackJack.isBankWinner() == true && BlackJack.isPlayerWinner() == true) {
						if(BlackJack.getBankBest() > BlackJack.getPlayerBest()) 
							System.out.print("Bank won!");
						else if(BlackJack.getBankBest() < BlackJack.getPlayerBest())
							System.out.println("You won!");
						else if (BlackJack.getBankBest() == BlackJack.getPlayerBest())
							System.out.println("Draw!");
					}
					else 
						System.out.println("Bank won!");
				whileInGame = false;	
				break;
			}
		}
	}
	
		public static void main(String args[]) throws EmptyDeckException{
			new BlackJackConsole();
		}
}
 

