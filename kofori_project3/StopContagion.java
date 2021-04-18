import java.util.*;

public class StopContagion {

    public int countDegree(Node n){
        return n.getAdj().size();
    }

    public Node maxDegree(ArrayList<Node> list){
        Node maxNode = new Node("");
        for (Node n : list) {
            if(countDegree(n) > countDegree(maxNode)){
                maxNode = n;
            }
        }
        return maxNode;
    }

    public int findNode(ArrayList<Node> list, Node n){
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getValue().equals(n.getValue())){
                return i;
            }
        }
        return -1;
    }

    public Node getNode(ArrayList<Node> list, Node n){
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getValue().equals(n.getValue())){
                return list.get(i);
            }
        }
        return new Node("");//or return null
    }

    public ArrayList<Node> removeNode(ArrayList<Node> list, Node n){
        int index = findNode(list, n);
        list.remove(index);
        return list;
    }

    public ArrayList<Node> removeNodeAll(ArrayList<Node> list, Node n){
        int index = findNode(list, n);
        list.remove(index);
        for (Node node : list) {
            ArrayList<Node> adj = node.getAdj();
            int k = findNode(adj, n);
            if(k>-1){
                adj.remove(k);
            }
        }
        return list;
    }
    
    public int sumDegree(ArrayList<Node> list){
        int sum = 0;
        for (Node node : list) {
            sum += (node.getAdj().size()-1);
        }
        return sum;
    }

    //Part one
    public ArrayList<Node> inoculate(ArrayList<Node> list, int num){
        ArrayList<Node> output = new ArrayList<>();
        int count = 0;
        while(count < num){
            Node maxNode = maxDegree(list);
            output.add(getNode(list, maxNode));
            removeNodeAll(list, maxNode);
            count++;
        }
        return output;
    }
    
    public void outputDegree(ArrayList<Node> list){
        for(int i = 0; i<list.size(); i++){
            Node n = list.get(i);
            System.out.print(n.getValue() + " " + countDegree(n));
            System.out.println();
        }
    }

    //Part two
    // Ball(i,r)
    public ArrayList<Node> ball(Node s, int r){
        LinkedList<Node> q = new LinkedList<>();
        HashMap<Node, Boolean> visted = new HashMap<>();
        ArrayList<Node> circle = new ArrayList<>();

        visted.put(s, true);

        q.add(s);

        while(!q.isEmpty() && r>0){
            s = q.poll();
            Iterator<Node> i = s.getAdj().listIterator();
            while(i.hasNext()){
                Node v = i.next();
                if(!visted.containsKey(v)){
                    visted.put(v, true);
                    q.add(v);
                    circle.add(v);
                }
            }
            r--;
        }
        return circle;
    }

    // δBall(i,r)
    public ArrayList<Node> sigmaBall(Node s, int r){
        LinkedList<Node> q = new LinkedList<>();
        HashMap<Node, Boolean> visted = new HashMap<>();
        ArrayList<Node> circle = new ArrayList<>();

        visted.put(s, true);

        q.add(s);

        while(!q.isEmpty() && r>0){
            s = q.poll();
            Iterator<Node> i = s.getAdj().listIterator();
            while(i.hasNext()){
                Node v = i.next();
                if(!visted.containsKey(v)){
                    visted.put(v, true);
                    q.add(v);
                    if(r==1) circle.add(v);
                }
            }
            r--;
        }
        return circle;
    }

    //CI(i,r) = (countDegree(i)-1)*sum(countDegree for all elements of sigmaBall(i,r))
    public int collInfl(Node n, int r){
        ArrayList<Node> circle = sigmaBall(n, r);
        int sum = sumDegree(circle);
        int currDegree = countDegree(n)-1;
        int ci = currDegree*sum;
        return ci;
    }

    public String mostIfl(HashMap<String, Integer> map){
        int max = -1;
        String node = "";
        for(String n : map.keySet()){
            int curr = map.get(n);
            if(curr > max){
                max = curr;
                node = n;
            }
        }
        return node;
    }

    public ArrayList<Node> dismantle(ArrayList<Node> list, int r, int num_nodes){
        HashMap<String, Integer> map = new HashMap<>();
        while(!list.isEmpty() && num_nodes > 0){
            num_nodes--;
            for (Node node : list) {
                int ci = collInfl(node,r);
                map.put(node.getValue(), ci);
            }
     
            String maxValue = mostIfl(map);
            Node maxNode = new Node(maxValue);
            System.out.println(maxValue + " " + map.get(maxValue));
            map.remove(maxValue);
            removeNodeAll(list, maxNode);

            ArrayList<Node> affectNodes = ball(maxNode, r+1);
            for (Node node : affectNodes) {
                int ci = collInfl(node,r);
                map.put(node.getValue(), ci);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        Read r = new Read();
        StopContagion  s = new StopContagion();

        if(args[0].equals("-r")){
            ArrayList<Node> graph = r.parse(args[3]);
            int radius = Integer.parseInt(args[1]);
            int num_nodes = Integer.parseInt(args[2]);
            s.dismantle(graph, radius, num_nodes);
        }
        else if(args[0].equals("-d")){
            ArrayList<Node> graph = r.parse(args[2]);
            int num_nodes = Integer.parseInt(args[1]);
            s.outputDegree(s.inoculate(graph, num_nodes));
        }
    }
}
