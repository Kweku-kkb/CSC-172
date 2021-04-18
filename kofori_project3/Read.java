import java.io.*;
import java.util.*;

// import jdk.nashorn.internal.parser.Scanner;

public class Read {

    public boolean containsNode(ArrayList<Node> list, Node n) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(n.getValue())) {
                return true;
            }
        }

        return false;
    }

    public Node getNode(ArrayList<Node> list, Node n) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(n.getValue())) {
                return n;
            }
        }
        return null;
    }

    public void print(ArrayList<Node> allNodes) {
        for (int i = 0; i < allNodes.size(); i++) {
            Node n = allNodes.get(i);
            System.out.print(n.getValue() + ": ");
            for (int j = 0; j < n.getAdj().size(); j++) {
                System.out.print(n.getAdj().get(j).getValue() + " ");
            }
            System.out.println();
            // System.out.println(n.getAdj().size());

        }
    }

    public void addNeighbor(Node n1, Node n2) {
        ArrayList<Node> list = n1.getAdj();
        list.add(n2);
        n1.setAdj(list);
    }

    // reads input file
    public ArrayList<Node> parse(String FileName) {
        ArrayList<Node> allNodes = new ArrayList<>();
        ArrayList<String> strNodes = new ArrayList<>();
        StopContagion s = new StopContagion();
        try {
            File in = new File(FileName);
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitArr = line.split("\\s+");
                // System.out.println(Arrays.toString(splitArr));
                Node n1 = new Node(splitArr[0]);
                Node n2 = new Node(splitArr[1]);

                if (strNodes.contains(n1.getValue())) {
                    n1 = allNodes.get(s.findNode(allNodes, n1));
                    s.removeNode(allNodes, n1);
                    addNeighbor(n1, n2);
                    allNodes.add(n1);
                } else {
                    strNodes.add(splitArr[0]);
                    addNeighbor(n1, n2);
                    allNodes.add(n1);
                }

                if (strNodes.contains(n2.getValue())) {
                    n2 = allNodes.get(s.findNode(allNodes, n2));
                    s.removeNode(allNodes, n2);
                    addNeighbor(n2, n1);
                    allNodes.add(n2);
                } else {
                    strNodes.add(splitArr[1]);
                    addNeighbor(n2, n1);
                    allNodes.add(n2);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        // System.out.println(strNodes);
        return allNodes;
    }
}
