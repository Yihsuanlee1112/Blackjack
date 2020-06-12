
public class Card {
	private Value value;
	private Suit suit;
	public Card(Value value, Suit suit) {
		this.value=value;
		this.suit=suit;
	}
	public String toString() {
		return value.getSymbol()+suit.getSymbol();
	}
	public String getSuitSymbol() {
		return suit.getSymbol();
	}

	public String getSuitName() {
		return suit.name();
	}
	public String getValueSymbol() {
		return value.getSymbol();
	}
	public int getPoints() {
		return value.getPoints();
	}
}
