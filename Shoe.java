// CS 0445 Spring 2018
// Assignment 1
// Card class for Assigment 1. You must use this class as given in the implementation
// of your Blackjack card game. 

//a shoe will have multiple decks, which means it is also a randindexqueue
public class Shoe
{
	private Deck myDeck;//ask about the multiple deck problems
	private  RandIndexQueue< Card >  myCards;		//you should copy all the cards in the decks into here
	private int originalCardN;
	private int curCardN;
	
	public Shoe(int deckNum)
	{
		originalCardN = 0;
		curCardN = 0;
		myCards = new RandIndexQueue< Card > ( );
		for (int i =0; i<deckNum; i++){		//to generate the same number of decks as the decknum specified
			myDeck = new Deck();
			for(int j =0; j<52; j ++){//to poll all 52 cards and put them int the mycards
				
				Card c = myDeck.poll();	
				myCards.offer( c );
				originalCardN = originalCardN +1;
			}
			
		}
		curCardN = originalCardN;	//the two numbers were the same originally
	}
	
	public Card get_one_card(){
		curCardN = curCardN -1;//with the cards being polled, curCardN--
		return myCards.poll();
	}
	
	public void shuffleCards(){
		myCards.shuffle();
	}
	
	public void reAddCard( Card addedC){
		myCards.offer( addedC );
		curCardN = curCardN +1; 	//add the curCardN back
		
	}
	
	public boolean TooFewCards(){
		//System.out.println("curcard is "+curCardN+"originalCardN is "+originalCardN);
		if(curCardN <= originalCardN/4){
			//System.out.println("too few cards");
			return true;
		}
		return false;
	}
	
	public static void main(String [] args)
	{
		//Shoe myShoe = new Shoe();
		//System.out.println("deck is "+myShoe.myCards.toString()+" originalCardN is "+myShoe.originalCardN);
		//DiscardPile pile = new DiscardPile();
		//pile.addCard( myShoe.get_one_card() );
		//Card c = pile.CardBackToShoe();
		//myShoe.reAddCard(c);

	}
}