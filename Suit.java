
public enum Suit {
	HEARTS("♥"), SPADES("♠"), CLUBS("♣"), DIAMONDS("♦");
	private String symbol;
	private Suit(String symbol) {
		this.symbol=symbol;
	}
	public String getSymbol() {
		return symbol;
	}
}
