import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class BlackJack {
	Deck deck;
	Hand playerHand;
	Hand bankHand;
	public boolean gameFinished;
	public BlackJack() {
		deck = new Deck(2);
		playerHand = new Hand();
		bankHand = new Hand();
		gameFinished = false;
		
		reset();
		
	}
	public void reset() {
		playerHand.clear();
		bankHand.clear();
		gameFinished = false;
		for (int i=0; i<2; i++) {//add two cards
			try {
				if(i==0) {
					bankHand.add(deck.draw());
				}playerHand.add(deck.draw());
			}
			catch(EmptyDeckException ex) {//check if the deck is empty
				JOptionPane.showMessageDialog(null,"This Deck is Empty","Error", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);}
		}
		
	}
	public String getPlayerHandString() {//display the playerHand
		return playerHand.toString();
	}
	public String getBankString() {//display the bankHand
		return bankHand.toString();
	}
	public int getPlayerBest() {//display the playerBest
		return playerHand.best();
	}
	public int getBankBest() {//display the bankBest
		return bankHand.best();
	}
	public boolean isPlayerWinner() {
		
			if(getPlayerBest() <= 21 && (getPlayerBest()>getBankBest()) || getBankBest() >21) {
				
				return true;
			}
			else
				return false;
		
	}
	public boolean isBankWinner() {
		if(getBankBest() <= 21 && (getPlayerBest()<getBankBest()) || getPlayerBest() > 21)
		{
			return true;
		}else {
			return false;
		}	 
	}
	public boolean isGameFinished() {
		if (gameFinished == true )
			return true;
		else 
			return false;
	}
	public void playerDrawAnotherCard() throws EmptyDeckException{ 
		
		if(gameFinished == false) {//if the game is not finished, add a card to the playerHand
			try {
				playerHand.add(deck.draw());
				if(getPlayerBest() > 21) {
					gameFinished = true;
				}
			}
			catch(EmptyDeckException ex) {//check if the deck is empty
				JOptionPane.showMessageDialog(null,"This Deck is Empty","Error", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
		}
		
	}
	public void bankLastTurn() throws EmptyDeckException {
		if(gameFinished == false && bankHand.best()<=21 && playerHand.best()<22) {
			while (bankHand.best() < playerHand.best()) {//continue to add card to the bank when the game isn't finished, until any side reaches 21
				try {
					bankHand.add(deck.draw());
					}
				catch(EmptyDeckException ex) {//check if the deck is empty
					JOptionPane.showMessageDialog(null,"This Deck is Empty","Error", JOptionPane.ERROR_MESSAGE);
					System.exit(-1);
				}
			}gameFinished = true;
		}
	}
	public List<Card> getPlayerCardList(){
		List<Card> originalList = playerHand.getCardList();
		LinkedList<Card> copyList = new LinkedList<Card>(originalList);//copy
		return copyList;
	}
	public List<Card> getBankCardList(){
		List<Card> originalList = bankHand.getCardList();
		LinkedList<Card> copyList = new LinkedList<Card>(originalList);//copy
		return copyList;
	}

}
