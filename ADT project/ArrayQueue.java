// James Cai
// ICS4U1-01
// December 9, 2019
// Mr. Radulovic
//ADT Assignment
/*  This Class is used to create queue object and implement the function queue have by using array.In this assignment this class is used for BFS. 
 * The maximum number of node this queue can hold will be 20000 because in this assignment the possible outcome of gene will not exceed 20000*/

public class ArrayQueue <T> {
	private Node<T>[] list;// array the used to implement the Queue
	int length;//Variable that used to track the length of the queue.
	public ArrayQueue()
	{
		list = new Node[20000];//Maximum size of the array.
		length = 0;
	}
	
	// adds a node to the end of the queue
	public void enqueue(Node<T> node) {
		list[length] = node;
		length++;//make the length +1
		
	}

	// removes the first node from the queue and return the node we removed.
	public Node<T> dequeue() {
		Node remove = list[0];
		list[0] = null;
		for(int m = 0; m< length ; m++) {//Make every node move 1 index forward to fill the gap.
			list[m] = list[m+1];
		}length--;//make the length -1
		return remove;
	}

	// returns the first node of the queue without remove it.
	public Node<T> peek() {
		return list[0];
	}

	// returns the size of the queue
	public int size() {
		return length;
	}

	// returns true if the queue is empty, false if not
	public boolean isEmpty() {
		if(length == 0) {
			return true;
		}else {
		return false;
		}
	}
	
	// Return a string that contain all the value of node in the queue.
	public String toString() {
		String str = "";
		for(int i = 0; i< length; i++) {
			str += list[i].getValue()+" ";
		}
		return str;
	}
}
