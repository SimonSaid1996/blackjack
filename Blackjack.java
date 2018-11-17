// CS 0445 Spring 2018
// Assignment 1
// Card class for Assigment 1. You must use this class as given in the implementation
// of your Blackjack card game. 
import java.util.*;

public class Blackjack 
{
	//can do betting credits 5
	
	private  Player dealer;
	private  Player p1;
	private  Shoe shoe;
	private DiscardPile myPile;
	private int playerPoints;
	private int dealerPoints;
	private boolean betBoo;
	private boolean playerBet;
	private boolean dealerBet;
	private boolean betModeOn;
	
	public Blackjack( int deckNum )
	{
		dealer = new Player();
		p1 = new Player();
		shoe = new Shoe( deckNum );
		myPile = new DiscardPile();
		playerPoints = 10;
		dealerPoints = 10;
		betBoo = false;
		playerBet = false;
		dealerBet = false;
		betModeOn = true;
	}

	public void hit(){//players choose to draw more cards
		System.out.println("hit");
	}
	
	public void winOrLose(){//to judge who gets a higher score
		System.out.println("the scores are out");
	}
		
	public Card DrawCard(Player p){
		return p.drawCard( shoe );
	}
	
	public int calculateScores( Player p ){
		return p.calculateScores();
	}
	
	public void DiscardCard(){		
		while( !p1.HandIsEmpty() ){	//if the cards are not empty, we draw cards and calculate their sum
			Card c = p1.discardCard();
			myPile.addCard( c );
		}
		while(!dealer.HandIsEmpty() ){
			Card c = dealer.discardCard();
			myPile.addCard( c );
		}
	}
		
	
	public int compareScores(int playerScore, int dealerScore, int mode){
		int result = -1;
		if( !isBusted( playerScore ) && !isBusted( dealerScore ) ){
			if ( playerScore > dealerScore ){
				if ( mode ==0 )
					System.out.println("result: player wins!\n");
				result = 0;
			}
			else if (playerScore < dealerScore){
				if (mode ==0)
					System.out.println("result: dealer wins!\n");
				result = 1;
			}
			else{
				if (mode == 0)
					System.out.println("result: draw!\n");
				result = 2;
			}		
		}
		else if ( isBusted( playerScore ) && !isBusted( dealerScore ) ){
			if (mode ==0)
				System.out.println("result: dealer wins!\n");
			result = 0;
		}
		else if ( !isBusted( playerScore ) && isBusted( dealerScore ) ){
			if(mode ==0)
				System.out.println("result: player wins! \n");
			result = 1;
		}
		else{
			if (mode ==0)
				System.out.println("result: draw! \n");			
			result = 2;
		}
		return result;	
	}
	
	public boolean isBusted(int s){//to check if the score is over 21
		if( s > 21 )
			return true;
		else
			return false;
	}
	
	public boolean checkAndAddDiscardC(){
		if( shoe.TooFewCards() ){
			while( !myPile.isEmpty() ){
				Card c = myPile.CardBackToShoe();
				shoe.reAddCard( c );
			}
			shoe.shuffleCards();		//reshuffle cards
			return true;
		}
		return false;
	}
	
	public boolean printBusted(Player p, int score, int mode){
		if ( isBusted( score ) && p.equals( p1 ) ){
			if ( mode ==0 ){
				System.out.println("player BUSTED: "+p1.printCards()+":"+score);
			}
			return false;	
		}
		else if (isBusted( score ) && p.equals( dealer )){
			if(mode ==0 ){
				System.out.println("Dealer BUSTED: "+dealer.printCards()+":"+score );		
			}
			return false;
		}
		return true;
		
	}
	
	public int hitOrStand(Player p, int score, int mode){
		boolean printStandLater = true;
		while( score < 17  ){
			if( score < 17 ){//hit
				if ( p.equals(p1) ){//player's case
					Card c = DrawCard( p1 );
					score =  calculateScores( p1 );  //score +
										
					if ( mode == 0 ){
						System.out.println("Player hits:"+c);	
					}
					printStandLater = printBusted( p1, score, mode );
				}							
				else{				//dealer's case
					Card c = DrawCard( dealer );
					score =  calculateScores( dealer ); //score +
					
					
					if (mode ==0){
						System.out.println("Dealer hits:"+c);	
					}
					
					printStandLater = printBusted( dealer, score, mode );
				}
			}	
			else{	//stand
				printStand( p, score, mode );
				printStandLater = false;		//to decide if we need to print stand later
			}	
		}
		if( printStandLater ){
			printStand( p, score, mode );	
		}
		return score;
			
	}
	
	public void printStand(Player p, int score, int mode){
		if (mode ==0 ){
			if ( p.equals(p1) )			//player's case
				System.out.println("Player STANDS:"+p1.printCards()+":"+score);
			else
				System.out.println("Dealer STANDS:"+dealer.printCards()+":"+score);
		}
		
	}
	
	
	public int[] DoResultArray(int[] resultA, int result ){
		if ( result == 0 ){
			resultA[ 0 ] = resultA[ 0 ] +1; 
		}
		else if ( result ==1 ){
			resultA[ 1 ] = resultA[ 1 ] +1; 
		}
		else{
			resultA[ 2 ] = resultA[ 2 ] +1; 			
		}
		return resultA;
	}
	
	public void roleWant2Bet(String b, String role){
		if( !b.equals( "Y" )&& !b.equals( "N" ) ){
			betBoo = false;
		}
		else{
			if (role .equals("p") && b.equals( "Y" ) ){
				playerBet = true;
			}
			else if ( role .equals("d") && b.equals( "Y" ) ){
				dealerBet = true;
				bothBet();
			}
		}	
	}
	
	public void bothBet(){
		if (dealerBet && playerBet){
			betBoo = true;
		}
	}
	
	public void resetBetboo(){
		betBoo = false;
	}
	
	public void betRoundResult(int result){
		if( betBoo ){
			if( result == 0){	//player wins
				playerPoints = playerPoints +1;
				dealerPoints = dealerPoints -1;
			}
			else if( result ==1 ){ //dealer wins
				playerPoints = playerPoints +1;
				dealerPoints = dealerPoints -1;
			}
		}
	}
	
	public void bettingMode(String role){
		if(betModeOn){	//if the betmode is on
			if(role.equals("p")){	//ask player
				System.out.println("\nplayer, want to bet? Y:for yes and N: for no");
				Scanner sc = new Scanner(System.in);
				String playerA = sc.next();
				roleWant2Bet( playerA,  "p");
			}
			else{
				System.out.println("\ndealer, want to bet? Y:for yes and N: for no");
				Scanner sc1 = new Scanner(System.in);
				String dealerA = sc1.next();
				roleWant2Bet( dealerA,  "d");
			}
		}
	}
	
	public static void main(String [] args)
	{
		if(args.length != 2){
			System.out.println("invalid input");
		}
		else{
			int roundNum = Integer.parseInt( args[ 0 ] ) ;
			int deckNum = Integer.parseInt( args[ 1 ] ) ;
			int resultArray[] = new int[ 3 ];
			
			int result = 0;
			Blackjack game = new Blackjack( deckNum );
			
			if (roundNum <= 10 ){
				System.out.println("Starting Blackjack with"+ roundNum +" rounds and "+ deckNum +"decks in the shoe");
				System.out.println("\ndo you want the betting mode to be on? N is to turn off, otherwise default betting mode on");
				Scanner sc = new Scanner(System.in);
				String decision = sc.next();
				if(decision.equals("N") ){
					game.betModeOn =false;
				}
				for(int i =0; i<roundNum; i++){
					
					System.out.println("\nRound "+ (i+1) +" begins:");
					game.resetBetboo();
					game.bettingMode("p");
					
					
					game.DrawCard( game.p1 );
					game.DrawCard( game.p1 );
					int p1Score = game.calculateScores( game.p1 );
					System.out.println("Player:"+game.p1.printCards()+" :"+p1Score );
					
					game.bettingMode("d");
					
					
					game.DrawCard( game.dealer );
					game.DrawCard( game.dealer );
					int dealerScore = game.calculateScores( game.dealer );
					System.out.println("Dealer:"+game.dealer.printCards()+" :"+dealerScore );
				
					p1Score = game.hitOrStand( game.p1,p1Score, 0 );
					dealerScore = game.hitOrStand( game.dealer,dealerScore,0 );
					
					result = game.compareScores( p1Score, dealerScore, 0 );	
					game.betRoundResult(result);
					resultArray = game.DoResultArray( resultArray,result );
					
					
					game.DiscardCard();
					game.checkAndAddDiscardC();				
				}
				System.out.println("after "+roundNum+" rounds, here are the results:\n\t Dealer Wins: "+resultArray[ 0 ]+"\n\t Player Wins: "+resultArray[ 1 ]+"\n\t push: "+resultArray[ 2 ] );
				if(game.betModeOn){
					System.out.println("after "+roundNum+" rounds, \n\t Dealer score: "+ game.dealerPoints +"\n\t Player score: "+ game.playerPoints );	
				}

			}
			else{
				System.out.println("Starting Blackjack with "+roundNum+" rounds and "+deckNum+" decks in the shoe");
				for(int i =0; i<roundNum; i++){
					game.DrawCard( game.p1 );
					game.DrawCard( game.p1 );
					int p1Score = game.calculateScores( game.p1 );
					
					game.DrawCard( game.dealer );
					game.DrawCard( game.dealer );
					int dealerScore = game.calculateScores( game.dealer );
					
					p1Score = game.hitOrStand( game.p1,p1Score, 1 );
					dealerScore = game.hitOrStand( game.dealer,dealerScore,1 );
				
					result = game.compareScores( p1Score, dealerScore, 1 );	
					resultArray = game.DoResultArray( resultArray,result );
					
					game.DiscardCard();
					if ( game.checkAndAddDiscardC() ){
						System.out.println("Reshuffling the shoe in round "+i+"\n");
					}
					
				}
				System.out.println("after "+roundNum+" rounds, here are the results:\n\t Dealer Wins: "+resultArray[ 0 ]+"\n\t Player Wins: "+resultArray[ 1 ]+"\n\t push: "+resultArray[ 2 ] );
			}
			
			
		}
		
	}
}