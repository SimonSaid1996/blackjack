// CS 0445 Spring 2018
// Assignment 1 MyQ<T> interface
// Carefully read the specifications for each of the operations and
// implement them correctly in your RandIndexQueue<T> class.

// The overall logic of the MyQ<T> is the following:
//		Data is logically "added" in the same order that it is "removed".
// The interface itself does not specify in any way how the data must be
// stored / maintained.  Thus it could be implemented in many different
// ways.

// However, for this assignment, you MUST implement the interface with the
// following requirements: 
// 1) The underlying data must be a simple Java array
// 2) No Java collection classes may be used in this implementation.  All of
//    your methods must act directly on the underlying array
// 3) Your offer() and poll() methods must require only a single data assignment
//    each.  In other words, there should be no shifting to create or to fill
//    a space in your array.  To see how to implement your queue in this way,
//    go to recitation and read Section 11.7-11.16 of your text. You are not
//    required to use this exact implementation but it should be similar.
// 4) The offer() method must always succeed (barring some extreme event).  This
//    means that your implementation must have a way to dynamically resize your
//    underlying array when necessary.  Be careful about resizing -- it should not
//    affect the logical ordering of the MyQ.
// 5) Since offer() should always succeed, isFull() should always return false.
//
//    Note: Do not count the moves necessary for resizing in your totals.  We
//    will discuss the overhead of resizing the array later on in lecture.
import java.util.Random;

public class RandIndexQueue<T> implements MyQ<T>, Indexable<T>,Shufflable
{
	private T[] t;
	public int curSize = 0;
	private int frontInd = 0;
	private int backInd = 0;
	private static final int DEFAULT_CAP = 5;
	private int moves = 0;
	
	public RandIndexQueue(){
		this(DEFAULT_CAP);
		//default constructor
	}
	//////// in theory can delete this
	
	
	////////////////
	public int returnSize(){
		return t.length;
	}
	
	////////////
	public RandIndexQueue( int size ){		//set up the physical size
		//@SuppressWarninigs("unchecked")
		t = (T[])new Object[ size+1 ]; // Unchecked cast
		backInd = size;
	}
	
	private void ensureCapacity(){
		if (frontInd == ((backInd + 2) % t.length)) // if array is full,
		{ // double size of array
			T[] oldQueue = t;
			int oldSize = oldQueue.length;
			// the cast is safe because the new array contains null entries
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[2 * oldSize];
			t = tempQueue;
			for (int index = 0; index < oldSize - 1; index++)
			{
				t[index] = oldQueue[frontInd];
				frontInd = (frontInd + 1) % oldSize;
			} // end for
			frontInd = 0;
			backInd = oldSize - 2;
		} // end if
	}
	
	public boolean offer(T item){
		if ( item !=null ){ 
			ensureCapacity();	//check to ensure the physical size is enough
			backInd = (backInd + 1)%t.length;
			t[ backInd ] = item;
			curSize = curSize +1 ;
			moves = moves +1;//curSize;//(curSize+ 1 )*(curSize /2 );
			return true;
		}
		else{
			return false;
		}
		
	}
	
	// Remove and return the logical front item in the MyQ.  If the MyQ
	// is empty, return null
	public T poll(){
		T front = null;
		if (!isEmpty())
		{
			front = t[frontInd];
			t[ frontInd ] = null;
			frontInd = (frontInd + 1) % t.length;
			curSize = curSize -1;	//decrease the cursize
		} // end if
		moves = moves +1; //curSize;//(curSize+ 1 )*(curSize /2 );
		return front;
	}
	
	
	//used to calculate scores of the cards
	public T peek(int indx){
		if ( t[ indx ]!=null ) {//if not null, return the first element
			return t[ indx ];
		}
		else{//else, return null
			return null;
		}
	}
	
	
	// Get and return the logical front item in the MyQ without removing it.
	// If the MyQ is empty, return null
	public T peek(){
		if ( t[ frontInd ]!=null ) {//if not null, return the first element
			return t[ frontInd ];
		}
		else{//else, return null
			return null;
		}
	}
	
	// Return true if the MyQ is full, and false otherwise
	public boolean isFull(){
		return false;
	}
	
	// Return true if the MyQ is empty, and false otherwise
	
	
	public boolean isEmpty(){
		return frontInd == ((backInd + 1) % t.length);
	}
	

	// Reset the MyQ to empty status by reinitializing the variables
	// appropriately
	public void clear(){
		t = (T[])new Object[ DEFAULT_CAP ]; // reset the entire array into
		backInd = DEFAULT_CAP;
		frontInd = 0;
		curSize = 0;
	}
	
	// Methods to get and set the value for the moves variable.  The idea for
	// this is the same as shown in Recitation Exercise 1 -- but now instead
	// of a separate interface these are incorporated into the MyQ<T>
	// interface.  The value of your moves variable should be updated during
	// an offer() or poll() method call.  However, any data movement required
	// to resize the underlying array should not be counted in the moves.
	
	////////////////////////////
	//still not quite sure when to add moves, what u mean by moves?
	//not super sure how the resize is going to affect the moves, ask tomorrow
	//how is move going to be updated by poll??
	////based on the description of adding and deleting should be the same way, i am assuming that we are polling and offering both starts from the frontInd, therefore the same distance
	///////////////////////////
	
	public int getMoves(){
		return moves;
	}
	
	public void setMoves(int moves1){	//set the moves
		moves = moves1;
	}
	
	
	// Get and return the value located at logical location i in the implementing
	// collection, where location 0 is the logical beginning of the collection.
	// If the collection has fewer than (i+1) items, throw an IndexOutOfBoundsException 
	public T get(int i){
		
		if ( i+1 > curSize ){		//not possible since logically i will always be less than the cursize-1
			System.out.println("the collection has fewer than i+1 situation");
			i = -1; //change i into -1 and always get the index out of bound in the later try catch statement
		}
		else{		//if passed the i+1 situation, will exit the function here
			
			int logicI = ( frontInd + i )%t.length;
			
			return t[ logicI ];
			
		}
		try{
			t[ i ] = null;
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Assign item to logical location i in the implementing collection, where location
	// 0 is the logical beginning of the collection.  If the collection has fewer than
	// (i+1) items, throw an IndexOutOfBoundsException
	public void set(int i, T item){
		
		if ( i+1 > curSize ){
			int logicI = -1;	//to make logicI outofbound
			System.out.println("the collection has fewer than i+1 situation");
			try{
				t[ logicI ] = null;
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		}
		else{
			int logicI = ( frontInd + i )%t.length;
			t[ logicI ] = item;
		}
		
	}
	
	// Return the number of items currently in the Indexable. Note that this is the
	// same method specified in the MyQ<T> interface.  It is fine for a single method
	// to be part of more than one interface
	public int size(){
		return curSize;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("contents:");

		for(T item : t)
		{	
			if( item !=null ){
				result.append(item+" ");	
			}
			
		}

		result.append(" ");
		return result.toString();
		
        //return "contents:" + for(int i =0; i<t.length;i++){ t[ i ] };
    }
	
	
	public void shuffle(){	//simply just swap the first element with any one of the elements
		for(int i =0; i<curSize; i++ ){
			Random rand = new Random();
			int  n = rand.nextInt(curSize) + 1;
			int logicN = ( frontInd + n )%t.length;	
			T temp = t[ frontInd ];
			t[ frontInd ] = t[ logicN ];
			t[ logicN ] = temp;
		}
		
		
	}
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API).  Note that this should not change the size of the
	// collection.
}