import java.util.LinkedList;
import java.util.Collections;

public class Deck {
	LinkedList<Card> cardList = new LinkedList<Card>();
	public Deck(int nbBox) {
		for(int k=0;k<nbBox;k++) {
			for(int i=0;i<4; i++) {//four symbols
				for(int j=0;j<13;j++) {//13 cards each
				cardList.add(new Card(Value.values()[j],Suit.values()[i]));
				}
			}
		}
		Collections.shuffle(cardList);
	}
	public String toString() {
		return cardList.toString();
	}
	public Card draw() throws EmptyDeckException {
		return cardList.pollFirst();
	}
	

}
