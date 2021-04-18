/**
 * Name: Kweku Ofori
 * Assignment: Lab5
 * Lab: MW 9:00AM-10:15AM
 */
public class Node {

	
	//global variables
	public String data;
    public Node left;
    public Node right;
    

    public Node(){}
    
    //Node constructor for a string parameter
    public Node(String st){
    	data = st;
    }
    
    public String data() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Node left() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node right() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    //checks for leaf
    public boolean isLeaf() {
        return (left == null) && (right == null) && (data != null);
    }
    
    //checks for branch
    public boolean isBranch() {
        return (data == null);
    }
}
