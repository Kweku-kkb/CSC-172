/**
 * Name: Kweku Ofori
 * Assignment: Lab5
 * Lab: MW 9:00AM-10:15AM
 */

import java.io.*; 
import java.util.*;

public class Lab5 {
	
	//variables for constructor
	private Node root; 
	int size;
	
	static int num; //iteration counter
	
	//Trie constructor
	public Lab5() { 
		root = null;
		size = 0;
	}
		
	private static Node build(Node root, String st) {
		char[] stChar = st.toCharArray();
		//returns a leaf when node is null
		if (root == null) { 
			return new Node(st);
		} 
		if (root.isLeaf()) {
			String temp = root.data();
			root = new Node();  
			Node curr = root;
			char[] tempChar = temp.toCharArray();
			//checks for conditions
			for (int j = num; j < stChar.length; j++) {
				if (stChar[j] == tempChar[j]) {
					//conditions for characters 
					if (stChar[j] == '0') {
						curr.setLeft(new Node());
						curr = curr.left();
					}
					else if (stChar[j] == '1'){ 
						curr.setRight(new Node());
						curr = curr.right();
					}
				}
				
				//checks for a different character
				//move left if different at 0 
				else if (stChar[j] > tempChar[j]) {
					curr.setRight(new Node(st));
					curr.setLeft(new Node(temp));
					break;
				}
				
				//moves right if different at 1
				else if (stChar[j] < tempChar[j]) {
					curr.setRight(new Node(temp));
					curr.setLeft(new Node(st));
					break;
				}
			}
		}
		
		//checks if root is a branch
		else if (root.isBranch()) { 
			if (stChar[num] == '0') {
				num++;
				root.setLeft(build(root.left(), st));
			}
			else if (stChar[num] == '1') {
				num++;
				root.setRight(build(root.right(), st));
			}
		}
		return root;
	}
	
	//inserts and checks for correct input
	public static boolean insert(Lab5 trie, String st) {
		try {
			num = 0;
			trie.root = build(trie.root, st);
			trie.size++;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;  
		}
	}
	
	//temporary arraylist for byTrieToList 
	static ArrayList<String> tempList = new ArrayList<String>();
	
	//traversing lexicographically and uses preorder
	public static void preorder(Node root) {
		if (root == null) return;
		if (root.data() != null) {
			tempList.add(root.data());
		}
		preorder(root.left());    
		preorder(root.right());	
	}
	
	//TrieToList returns a list after taking the trie parameter
	public static ArrayList<String> trieToList(Lab5 trie){
		tempList.clear();
		ArrayList<String> list = new ArrayList<String>();
		preorder(trie.root);
		//loops through templist
		for (String s : tempList) {
			list.add(s);
		}
		return list;
	}
	
	//returns size
	public static int size(Lab5 trie) {
		return trie.size;
	}
	
	//returns the largest
	//last index value of the list after sorting using trieToList
	public static String largest(Lab5 trie) {
		ArrayList<String> list = trieToList(trie);   
		return list.get(list.size()-1);
	}

	
	//returns the smallest
	public static String smallest(Lab5 trie) {
		ArrayList<String> list = trieToList(trie);   
		return list.get(0);
	}
	
	//search method
	public static String search(Lab5 trie, String st) {
		char[] stChar = st.toCharArray();
		Node curr = trie.root;
		Node prev = curr;   
		int i = 0;
		boolean here = false;
		//loop for longest prefix match
		while (!here) {
			if (curr == null) {
				break;
			}
			if (curr.isLeaf() && curr.data().equals(st)) {
				here = true;   //break with here as true
				break;
			}
			if (stChar[i] == '0') {
				prev = curr;  
				curr = curr.left();
			}
			else if (stChar[i] == '1') {
				prev = curr;
				curr = curr.right();
			}
			i++;
		}
		
		if (here) {
			return curr.data();
		}
		else{
			return prev.data();
		}
			 
	}

	
	//finds the max depth of the tree which recursively calls itself
	private static int depth(Node node) {
		
		if (node == null) {
            return 0; 
		}
        else{
            int leftDepth = depth(node.left()); 
            int rightDepth = depth(node.right()); 
   
            if (leftDepth > rightDepth) 
                return (leftDepth + 1); 
             else 
                return (rightDepth + 1); 
        }
	}
	
	//gets the height of the trie
	public static int height(Lab5 trie) {
		int height = depth(trie.root);
		return height;
	}
	
	public static void main(String[] args) {
		Lab5 trie = new Lab5();
		
		String command = "";
		
		if (args.length > 0) {
			try {
				command = args[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//trycatch
		FileReader fReader = null;
		BufferedReader bReader = null;
		try {
			
			fReader = new FileReader(command);
			bReader = new BufferedReader(fReader);
			
			String currLine;
			String st;
			
			while ((currLine = bReader.readLine()) != null) {
				
				String[] put = currLine.split(" ");
				
				//check conditions for commands
				if(put[0].equals("insert")){
					st = put[1];
					insert(trie, st);
				}
				else if(put[0].equals("search")){
					st = put[1];
					System.out.println(search(trie, st));
				}
				else if(put[0].equals("print")){
					ArrayList<String> list = trieToList(trie);
					for (String s : list) {
						System.out.print(s + " ");
					}
					System.out.println();
				}
				else if(put[0].equals("size")){
					System.out.println(size(trie));
				}
				
				else if(put[0].equals("height")){
					System.out.println(height(trie));
				}
				else if(put[0].equals("largest")){
					System.out.println(largest(trie));
				}
				else if(put[0].equals("smallest")){
					System.out.println(smallest(trie));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
