//the deck class should have a pile of cards, this is the place to use the randcard.java class
import java.util.*;


public class DiscardPile
{
	private  RandIndexQueue< Card >  discardCards;
	//private Card.suit suit;
	//private Ranks rank;
	//public static enum Suits {Spades, Clubs, Diamonds, Hearts}
	//public static enum Ranks {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace}
	
	public DiscardPile()
	{
		discardCards = new RandIndexQueue< Card > ( ); //but does the deck has to be the size of 52?
		//try to generate a deck of 52 cards, send different types depends on the number
		
	}
	
	
	public void addCard(Card C){
		discardCards.offer( C );
		//System.out.println("discard, add card "+ C );
	}
	
	public Card CardBackToShoe(){	//to send some cards back to the shoe
		return discardCards.poll();
	}
	
	public boolean isEmpty(){
		if ( discardCards.isEmpty() )
			return true;
		else
			return false;
	}
	
	public static void main(String [] args)
	{
		DiscardPile pile = new DiscardPile();
		Card c = new Card(Card.Suits.Diamonds, Card.Ranks.Six);
		pile.addCard( c );
		
		Card d = pile.CardBackToShoe();
		System.out.println(" the same card is "+d);
	}
}