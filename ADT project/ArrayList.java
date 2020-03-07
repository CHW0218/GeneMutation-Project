// James Cai
// ICS4U1-01
// December 9, 2019
// Mr. Radulovic
//ADT Assignment
/*  This class is used to create ArrayList objects of Nodes and implement the function arraylist have by using array. 
 * It is used to store mutations of genes during applying 3 rule of mutation. The default size is set to 20000 because
 * at most 20000 objects can only be stored in the arraylist at once, as the maximum number of valid and diseased genes add together is 20000.*/

public class ArrayList<T> {

	private Node<T>[] list;// Array used to implement the ArrayList
	int length;//Variable that used to track the length of the arraylist.
	public ArrayList()
	{
		list = new Node[20000];
		length = 0;
	}

	// Adds a node to the end of the arraylist.
	public void addNode(Node<T> n) {
		list[length] = n;
		length++;//make the length +1
		
	}

	// insert a node to the given index.
	public void insertNode(Node<T> n, int i) {
		for(int m = length-1; m>= i ; m--) {
			list[m+1] = list[m];//Make every after given index node move 1 index more to let a new node insert to the arraylist.
		}list[i] = n;
		length++;//make the length +1
	}

	// Remove node on the arraylist, if target node is given.
	public void removeNode(Node<T> n) {
		int target = (Integer) null;/*Variable that used to mark the index of the node we wants to remove. 
									  initialize it by null to prevent it remove any node of the given node is not found.*/
		for(int m = 0; m < length ; m++) {//go through the arraylist to find the given node.
			if(list[m].getValue()==n.getValue()) {//if node found, remove it.
				target = m;
				list[m]=null;
				break;
			}
		}
		//Make every node after removed node move 1 index forward to fill the gap.
		for(int m = target; m< length ; m++) {
			list[m] = list[m+1];
		}length--;//make the length -1
	}
	
	// Remove node of given index on the arraylist then return it, if index of target node is given.
	public Node<T> removeNode(int i) {	
		Node<T> target = list[i];//store the removed node.
		list[i]=null;
		for(int m = i; m< length ; m++) {//Make every node after removed node move 1 index forward to fill the gap.
			list[m] = list[m+1];
		}length--;
		return target;
	}

	//Return the node on the given index
	public Node<T> getNode(int i) {
		return list[i];
	}

	//Return the first node on the arraylist
	public Node<T> getFirstNode() {
		return list[0];
	}

	//Return the last node on the arraylist
	public Node<T> getLastNode() {
		return list[length-1];
	}

	//Return the size of the arraylist
	public int size() {
		return length;
	}
	
	// Return a string that contain all the value of node in the ArrayList.
	public String toString() {
		String str = "";
		for(int i = 0; i< length; i++) {
			str += list[i].getValue()+" ";
		}
		return str;
	}
}
