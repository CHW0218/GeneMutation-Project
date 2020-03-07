// James Cai
// ICS4U1-01
// December 9, 2019
// Mr. Radulovic
//ADT Assignment
/*  This program simulate how genes can mutate into other genes. It will reads input from the file,
 *the input is consist of valid gene, diseased genes and test cases. In the file, some number 
 *will be provided as limitation. For example the program will calculate whether a given gene
 *P can mutate into a target gene Q within M steps, and the length of product can not exceed L length.
 *For the mutation, 3 rule that have different chance of occurring will be apply. If there is a chance 
 *for P to mutate into Q within M step, the program will print "YES" can output the chance for that to occur.
 *Otherwise, it will output "NO".*/

//Import the necessary pakage that will be used in the program.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Class that contain the whole program.
public class Gene {
	
	String[] valid_gene = null;
	//String array that holds all the valid gene that can be mutate to during the process.
	
	boolean[] visited_gene = null;
	//Boolean array that record the valid_gene is being visited or not during mutation. Prevent the program visited same node multiple times.
	
	String[][] test_case = null;
	//2D array that hold the test cases, the index 0 on the sub array represent the the test gene, the index 1 on the sub array represent the the target gene.
	
	
	//This rule class is used to apply one of the three rule of mutation to the given gene depend on the input. After applying the rule, 
	//it will compare the possible outcome with the vaild gene and return a arraylist that contain all the vaild result.
	//The input of this class will be gene that will mutate, number of rule that apply, list of valid gene, list that mark the vsited node and the maximum length of gene L.
	public ArrayList<String> rule(Node<String> gene, int n,String[] valid_gene,boolean[] visited_gene,int L){
		
		String G = (String) gene.getValue();
		//Get the string value in the gene node.
		
		//Create a arraylist that use to hold the vaild result.
		ArrayList<String> Gene = new ArrayList<String>();
		
		//base that can be use in the gene.
		String[] base = {"A","G","C","T"};
		
		//First mutation rule that switch the first and last base chemical of the gene.Have 0.02% chance to happen.
		if(n == 1) {
		//switch the basse and assign it to variable "result". 
		String result = G.charAt(G.length()-1) + G.substring(1, G.length()-1) + G.charAt(0);
		for (int j = 0; j < valid_gene.length;j++) {//Go through all the valid gene to check if the result is valid.
			if (valid_gene[j].equals(result)&&visited_gene[j]==false) {/*if the result is valid and we didn't get same result before,
																	   add it to the return array and mark that gene visited*/
				Gene.addNode(new Node<String>(result,gene.getChance()*0.02,gene.getlevel()+1));//add to the array, also calculate the chance of that result.
				visited_gene[j]=true;
			}
		}
		
		return Gene;
		}
		
		//Second mutation rule that replace two identical, adjacent base chemicals in the gene to a single base chemical. Have 0.06% chance to happen.
		else if(n == 2) {
			for(int m = 0; m<G.length()-1;m++) {//Go through and base chemicals in the gene and check if there is any adjacent identical chemicals.
				if(G.charAt(m) == G.charAt(m+1)) {
						for(int i = 0; i<base.length;i++) {
							String result = G.substring(0, m)+base[i]+G.substring(m+2, G.length());
							for (int j = 0; j < valid_gene.length;j++) {//Once found one, create results of all the possible out come and check if any of them are valid.
								if (valid_gene[j].equals(result)&&visited_gene[j]==false) {/*if the result is valid and we didn't get same result before,
									   														add it to the return array and mark that gene visited*/
									Gene.addNode(new Node<String>(result,gene.getChance()*0.06,gene.getlevel()+1));//add to the array, also calculate the chance of that result.
									visited_gene[j]=true;
								}
							}
						}
				}
			}
		
		return Gene;
		}
		
		//Third mutation rule that If the two base pairs G and T occur side by side in any order, another base chemical can be inserted in
		//between them as long as the overall length of the gene does not exceed L. Have 0.08% chance to occur.
		else if(n == 3) {
			for(int m = 0; m<G.length()-1;m++) {//Go through and base chemicals in the gene and check if there is any adjacent identical chemicals.
				if((G.charAt(m) == 'G')&& (G.charAt(m+1) == 'T')||(G.charAt(m) == 'T')&& (G.charAt(m+1) == 'G')) {
						for(int i = 0; i<base.length;i++) {
							//Once found one, create results of all the possible out come and check if any of them are valid.
							String result = G.substring(0, m+1)+base[i]+G.substring(m+1, G.length());
							for (int j = 0; j < valid_gene.length;j++) {
								if (valid_gene[j].equals(result)&&visited_gene[j]==false&&result.length()<=L) {/*if the result is valid and we didn't get same result before,
																												add it to the return array and mark that gene visited*/
									Gene.addNode(new Node<String>(result,gene.getChance()*0.08,gene.getlevel()+1));//add to the array, also calculate the chance of that result.
									visited_gene[j]=true;
								}
							}
						}
				}
			}
		return Gene;
		}
		
		//return a empty array if none of the rules were applied.
		return Gene;
	}
	
	//Main method of the program.
	public static void main(String[] args) {
		
		//initialize variable that need to be used
		int L = 0,V = 0,D = 0,M = 0,G = 0,num_gene = 0;
		Gene main = new Gene();
		
		//Path of the file.
		File file = new File("GENES.txt");
		
		//scan the file and read input from it.
		try {
			Scanner scan = new Scanner(file);
			//Read L from the file.
			L =  Integer.parseInt(scan.nextLine());
			//Read V from the file.
			V = Integer.parseInt(scan.nextLine());
			//Read D from the file.
			D = Integer.parseInt(scan.nextLine());
			//Total number of valid and disease gene.
			num_gene = V + D;
			main.valid_gene = new String[num_gene];
			//read next V+D line for the valid gene.
			for(int i = 0; i < num_gene; i++) {
				main.valid_gene[i] = scan.nextLine();
				}
			M = Integer.parseInt(scan.nextLine());
			G = Integer.parseInt(scan.nextLine());
			//Create 2D array that hold the test gene and target gene.
			main.test_case = new String[G][2];
			//scan for the test gene and target gene.
			for(int i = 0 ; i< G;i++) {
				main.test_case[i][0] = scan.next();
				main.test_case[i][1] = scan.next();
			}
		} catch (FileNotFoundException e) {
			//Error if the file cannot be found.
			e.printStackTrace();
			}
		//Initialize visited boolean array
		main.visited_gene = new boolean[main.valid_gene.length];
		
		// Call the mutation class to doing BFS.
		main.Mutation(M,main.test_case,main.valid_gene,main.visited_gene,L);

	}
	
	//The main class that doing Breadth First Search to search for the target gene.
	public void Mutation(int M,String[][] test_case,String[] valid_gene,boolean[] visited_gene,int L){
		
		//for loop that used to go through every single test case from the input file.
		for(int j = 0; j< test_case.length;j++) {
		visited_gene = new boolean[valid_gene.length];//Reset all the valid gene to unvisited when we start a new test case.
		int search_level = M;//Setting the max step the gene can mutate.
		Node<String> start_gene = new Node<String>(test_case[j][0],1,0);
		Node<String> target_gene = new Node<String>(test_case[j][1],1,0);
		ArrayQueue <String> queue = new ArrayQueue <String>();//Initialize queue that used for BFS.
		ArrayList<String> succeed = new ArrayList();//Array that holding all the succeed result, which gene P can mutate into a gene Q within the given steps,
		queue.enqueue(start_gene);//Add the first node we have to the queue.
		while(!queue.isEmpty()){
		Node<String> rootNode = queue.peek();//Set the first node on the queue as rootNode and try to add all it's child to the queue.
		for(int i = 1; i<=3;i++) {//Try all three rules of mutation.
			ArrayList<String> childrenList = rule(rootNode,i,valid_gene,visited_gene,L);//Hold the result that produce by each rule in the childrenList.
			if(childrenList.size()!=0) {//When there is result
				if(childrenList.getNode(0).getlevel()<= search_level) {//If the step it take is not exceed the max step we setting.
					for(int m = 0; m < childrenList.size(); m++) {//For every single node on the children list
						Node<String> current_node = childrenList.getNode(m);
						if(current_node.getValue().equals(target_gene.getValue())) {//check if ant of them equal to the target gene.
						succeed.addNode(current_node);//Add it to succeed.
						search_level = succeed.getNode(0).getlevel();/*Setting the max step to the step of first result we found,
																		because this program is used to find result that have the largest chance to happen, so once we found one, 
																		any result that have step than the one we found will have the smaller chance, so only search on the 
																		current level to see if we can found larger chance result*/
					}
					queue.enqueue(current_node);//add child to the queue.
				}
			}
		}
		}queue.dequeue();//dequeue the rootNode because we already visited it.
		}
		if(succeed.size()!=0) {//If we found any feasible solution, print"YES".
		System.out.println("Yes");
		double Largest_probability = succeed.getFirstNode().getChance();
		for(int m = 0; m < succeed.size(); m++) {//Compare the chance of all the solution to find the largest chance.
			if(Largest_probability < succeed.getNode(m).getChance()) {
				Largest_probability = succeed.getNode(m).getChance();
			}
		}
		System.out.println(Largest_probability);
		}else {//If no feasible solution found, print"No"
			System.out.println("No");
		}		
		}		
		}
}

