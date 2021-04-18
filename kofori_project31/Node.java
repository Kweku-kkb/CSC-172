import java.util.ArrayList;

public class Node{
    private String value;
    private ArrayList<Node> adj;
    //creating node
    public Node(String value){
        this.value = value;
        this.adj = new ArrayList<>();
    }

    public void addNode(Node node){
        this.adj.add(node); // adds neighboring nodes
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Node> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Node> adj) {
        this.adj = adj;
    }
}