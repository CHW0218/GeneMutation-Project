// James Cai
// ICS4U1-01
// December 9, 2019
// Mr. Radulovic
//ADT Assignment
/*In this assignment, each Node object represents a gene. Each gene have 3 parameters that needs to be initialized: 
 * the string represent the gene sequence, the chance of get that gene from P gene through mutations, and the 
 * amount of steps it took to mutate from P gene to that gene.*/
public class Node<T>  {
	private T value; //Store different data type value, in this assignment it contains the string of the gene sequence
	private double chance = 1;//The chance of mutating to this specific gene
	private int level = 0;//The step it took to mutate from P gene to this gene.
	
	//initialize the node.
	public Node(T n, double chance,int level)
	{
		value = n;
		this.chance = chance;
		this.level = level;
	}

	//Set the chance of gene to given value.
	public void setChance(double n) {
		chance = n;
	}
	
	//Return the chance of this gene.
	public double getChance() {
		return chance;
	}
	
	//Set the level of this gene to given value.
	public void setlevel(int n) {
		level = n;
	}
	
	//Return the level of this gene.
	public int getlevel() {
		return level;
	}
	
	//Return the value of this gene contain, the gene sequence.
	public T getValue() {
		return value;
	}

}
