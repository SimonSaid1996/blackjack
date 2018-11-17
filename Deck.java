//the deck class should have a pile of cards, this is the place to use the randcard.java class
import java.util.*;


public class Deck
{
	private  RandIndexQueue< Card >  myDeck;
	//private Card.suit suit;
	//private Ranks rank;
	//public static enum Suits {Spades, Clubs, Diamonds, Hearts}
	//public static enum Ranks {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace}
	
	public Deck()
	{
		myDeck = new RandIndexQueue< Card > ( ); //but does the deck has to be the size of 52?
		//try to generate a deck of 52 cards, send different types depends on the number
		
		int type = -1;//default is -1
		for(int i =0; i <4; i++ ){
			if( i == 0){
				type = 0;
				generateCards(type);
			}
			else if(  i == 1 ){
				type = 1;
				generateCards(type);
			}
			else if( i == 2 ){
				type = 2;
				generateCards(type);
			}
			else{
				type = 3;
				generateCards(type);
			}
		}
		myDeck.shuffle();//shuffle all the cards into a different order
	}
	
	//this method will show the value of the card at the same time
	public void generateCards(int type){
		
		for (Card.Ranks r: Card.Ranks.values()){
			Card c = null;
			int cardV = -1;
			if ( type ==0 ){
				c = new Card(Card.Suits.Diamonds, r); //(Card.Suits.Diamonds, Card.Ranks.Seven);	
				cardV = c.value();

			}
			else if ( type ==1 ){
				c = new Card(Card.Suits.Spades, r); //(Card.Suits.Diamonds, Card.Ranks.Seven);
				cardV = c.value();

			}
			else if ( type ==2 ){
				c = new Card(Card.Suits.Hearts, r); //(Card.Suits.Diamonds, Card.Ranks.Seven);
				cardV = c.value();

			}
			else{
				c = new Card(Card.Suits.Clubs, r); //(Card.Suits.Diamonds, Card.Ranks.Seven);
				cardV = c.value();

			}
			//System.out.println("myCards " + c.toString() );//+" "+cardV);
			myDeck.offer( c );
		}		
	}
	
	/*public RandIndexQueue< Card > copyDeck(){
		return myDeck;
	}*/
	
	public Card poll(){
		return myDeck.poll();
	}
	
	public void addingCards(Card cd){	//add the 1/4 of the cards back to the deck
		System.out.println("under construction");
	}
	
	public static void main(String [] args)
	{
		Deck test = new Deck();
		System.out.println("deck is "+test.myDeck.toString());
		//need to print the shit inside of the test
	}
}