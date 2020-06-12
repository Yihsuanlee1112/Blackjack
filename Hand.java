import java.util.LinkedList;
import java.util.List;

public class Hand {
	LinkedList<Card> cardList = new LinkedList<Card>();
	public Hand() {
		
	}
	public String toString() {
		String currentCards = "";
		for(int i=0;i<cardList.size();i++) {
			currentCards += cardList.get(i);
		}
		return currentCards;
	}
	public void add(Card card) {
		cardList.add(card);
	}
	public void clear() {
		cardList.clear();
	}
	public LinkedList<Integer> count(){
		LinkedList<Integer> counting = new LinkedList<Integer>();
		int total=0;
		for(int i=0;i<cardList.size();i++) {
			total+=cardList.get(i).getPoints();
		}counting.add(total);
		for(int j=0;j<cardList.size();j++) {
			if(cardList.get(j).getPoints()==1) {
				total+=10;
				counting.add(total);
			}
		}
		return counting;
	}
	public int best() {
		LinkedList<Integer> counting = new LinkedList<Integer>();
		counting = count();
		int max = counting.get(0);
		for(int i=0;i<counting.size();i++) {
			if(counting.get(i)>max && counting.get(i)<=21) {
				max = counting.get(i);
			}	
		}
		return max ; 
	}
	public List<Card>getCardList(){
		LinkedList<Card> handRepro = new LinkedList<Card>(cardList);
		return handRepro;
	}
	
}
