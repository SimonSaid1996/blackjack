// CS 0445 Spring 2018
// Assignment 1
// Card class for Assigment 1. You must use this class as given in the implementation
// of your Blackjack card game. 
public class Player
{	
	private  RandIndexQueue< Card >  hand;
	//private  RandIndexQueue< Card >  tempHand;		//used to calculate the scores
	
	public Player()
	{
		hand = new RandIndexQueue< Card > ( ); //but does the deck has to be the size of 52?
		//tempHand = new RandIndexQueue< Card > ( );
	}
	
	public RandIndexQueue< Card >  printCards(){
		return hand;
	}
	
	public Card discardCard(){
		Card c = hand.poll();
		return c;
	}
	
	public int calculateScores(){//calculate the score of different players
		int totalScore = 0;  //default setting 
		for (int i =hand.returnSize()-1 ; i >= 0 ; i--){
			if ( hand.peek( i )!=null ){
				Card c = hand.peek( i );
				
				
				if ( totalScore+c.value() <= 21 ){
					totalScore = totalScore +c.value();	//A is 11 in this case
					
				}
				else{
					totalScore =  totalScore +c.value2();   //A is 1 in this case   totalScore +
				}
			}			
		}
	
		return totalScore;
	}
	
	public boolean HandIsEmpty(){
		return hand.isEmpty();
	}
	
	//this is basically the hit
	public Card drawCard(Shoe myShoe){	//to poll a card from the shoe, can be used for both initialization and hit situations
		Card c = myShoe.get_one_card() ;
		hand.offer( c );
		return c;
	}
	
	
	
	public static void main(String [] args)
	{
		/*Player me = new Player();
		Card one = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
		Card two = new Card(Card.Suits.Diamonds, Card.Ranks.Two);
		Card three = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
		Card four = new Card(Card.Suits.Diamonds, Card.Ranks.Two);
		Card five = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
		
		Card six = new Card(Card.Suits.Diamonds, Card.Ranks.Two);
		Card seven = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
		Card eight = new Card(Card.Suits.Diamonds, Card.Ranks.Two);
		*/
		
		/*me.testAddingCard(one);
		me.testAddingCard(two);
		me.testAddingCard(three);
		me.testAddingCard(four);
		me.testAddingCard(five);
		/*me.testAddingCard(six);
		me.testAddingCard(seven);
		me.testAddingCard(eight);
		int score = me.calculateScores();
		System.out.println("the score is "+score);
		*/

		//Shoe myShoe = new Shoe( 4 );
		//me.drawCard( myShoe );

	}
}