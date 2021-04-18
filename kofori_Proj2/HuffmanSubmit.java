/**
 * Name: Kweku Ofori
 * Assignment: Project2 -- Huffman Coding
 * Lab: MW 9:00AM-10:15PM
 *
 */
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.*;

public class HuffmanSubmit implements Huffman {


	//Node class for comparing frequency values in priority queue 
    class Node implements Comparable<Node>{
    	Node left;
        Node right;
        int freq;
        String data;
   
        public Node(){
            freq = 0;
            left = null;
            right = null;
            data = "";
        }

        //compareTo method for priority queue
        public int compareTo(Node n) {
            return this.freq - n.freq;
        }
        
        public Node(int freq){
            data = "";
            this.freq = freq;
        }

    }

    //implementing Huffman tree
    public Node build(PriorityQueue<Node> prQueue){
        Node root = null;
        // summing up characters with smaller frequencies then adding back into priority queue
        while(prQueue.size() > 1){
            Node leastVal1 = prQueue.poll();
            Node leastVal2 = prQueue.poll();
            Node sum = new Node(leastVal1.freq + leastVal2.freq);
            sum.left = leastVal1;
            sum.right = leastVal2;
            prQueue.add(sum);
            root = sum;
        }
        return root;
    }
    
    //method to write the final encoded file using booleans
    public void writeBoolean(HashMap<String, String> mainMap, String inputFile, String outputFile){
        BinaryIn input = new BinaryIn(inputFile);
        BinaryOut output = new BinaryOut(outputFile);
        while(!input.isEmpty()){
            char r = input.readChar();
            String eightBitR = String.format("%8s", Integer.toBinaryString(r)).replace(' ', '0');
            String coded = mainMap.get(eightBitR);
            for(char c : coded.toCharArray()){
                if(c == '1'){
                    output.write(true);
                }
                else {
                    output.write(false);
                }
            }
        }
        output.flush();
        output.close();
    }

    //traversing the tree and adding the character path to main map
    public void traverse(HashMap<String, String> mainMap, Node rt, String val){
        if(!rt.data.equals("")){
            mainMap.put(rt.data,val);
        }
        //if(!(rt.left==null && rt.right==null)){
        else{
            traverse(mainMap, rt.left, val +"0");
            traverse(mainMap, rt.right, val + "1");
        }
    }

    // encoding data
    public void encode(String inputFile, String outputFile, String freqFile){

	    BinaryIn input = new BinaryIn(inputFile);
        BinaryOut freqOutput = new BinaryOut(freqFile);
        PriorityQueue<Node> prQueue = new PriorityQueue<>();

        //frequency file map
        HashMap<Character, Integer> freqMap = new HashMap<>();
        
        //character key map and tree-path to the character as value
        HashMap<String, String> mainMap  = new HashMap<>();

        //loop to keep track of characters in the file and frequency
        while(!input.isEmpty()){
            try {
                char c = input.readChar();

                if(freqMap.containsKey(c)){
                    freqMap.replace(c, freqMap.get(c) + 1);
                }
                else{
                    freqMap.put(c,1);
                }

            } catch (NullPointerException E) {
                break;
            }
        }

        //writing character frequencies onto feqFile in binary
        for(Character ch : freqMap.keySet()){
            freqOutput.write(String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0') + ":" + freqMap.get(ch) + "\n");
        }
        freqOutput.flush();

        //obtaining freqFile info and put into the priorityQueue
        try{
            Scanner scan = new Scanner(new File(freqFile));
            while(scan.hasNextLine()){
                String[] scannedStrArray = scan.nextLine().split(":",2);
                    Node node = new Node();
                    node.freq = Integer.parseInt(scannedStrArray[1]);
                    node.data = scannedStrArray[0];
                    prQueue.add(node);
            }
            //build and traverse the tree then write the encoded file
            Node tree = build(prQueue);
            traverse(mainMap, tree,"");
            writeBoolean(mainMap, inputFile, outputFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

   // decoding data
   public void decode(String inputFile, String outputFile, String freqFile){
       BinaryIn input = new BinaryIn(inputFile);
       BinaryOut output = new BinaryOut(outputFile);
       HashMap<String, String> mainMap  = new HashMap<>();
       PriorityQueue<Node> prQueue = new PriorityQueue<>();
       Node tree = null;

       //obtaining freqFile info and put into the priorityQueue
       try{
           Scanner scan = new Scanner(new File(freqFile));
           while(scan.hasNextLine()){

               String[] scannedStrArray = scan.nextLine().split(":",2);

                   Node node = new Node();
                   node.freq = Integer.parseInt(scannedStrArray[1]);
                   node.data = scannedStrArray[0];
                   prQueue.add(node);
           }

           //build and traverse the tree
           tree = build(prQueue);
           traverse(mainMap,tree,"");

       }catch (FileNotFoundException e){
           e.printStackTrace();
       }

       Node tempRoot = tree;
       int toCharInt;
       char finalChar;

       //writing final decoded file using corresponding element of the tree
       try{
           while(!input.isEmpty()){
               if(!tempRoot.data.equals("")){
                   toCharInt = Integer.parseInt(tempRoot.data,2);
                   finalChar = (char) toCharInt;
                   output.write(finalChar);
                   tempRoot = tree;
               }
               else{

                   boolean bitValue = input.readBoolean();
                   if(bitValue){
                       tempRoot = tempRoot.right;
                   }
                   else{
                       tempRoot = tempRoot.left;
                   }
               }

           }
           output.close();
       }catch (NullPointerException e){
           e.printStackTrace();
       }
   }

   public static void main(String[] args) {
      Huffman huffman = new HuffmanSubmit();
       huffman.encode("ur.jpg", "ur.enc", "freq.txt");
       huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
   }

}
