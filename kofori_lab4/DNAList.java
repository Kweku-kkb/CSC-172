/**
 * Name: Kweku Ofori
 * NetID: kofori
 * Assignment: Lab4
 * Lab: MW 9:00AM-10:15AM
 *
 */

import java.io.*;

public class DNAList {

    public static final Sequence TYPE_EP = Sequence.NONE;
    public static final LinkedList<Character> PLACE_HOLDER_EP = new LinkedList<>();
    public static DNAList[] mainArray;

    public DNAList(Sequence seq, LinkedList<Character> l) {
        type = seq;
        sequence = l;
    }


    public Sequence type;
    public LinkedList<Character> sequence;
    
    //enumerator method
    public enum Sequence {
        DNA("DNA"), RNA("RNA"), NONE("NONE");
        public String type;
        Sequence(String sType) {
            this.type = sType;
        }
        public String getType() {
            return type;
        }
    }

    //DNA checker method
    public static boolean checkDNA(char[] array) {
        for (char c : array) {
            if (c != 'A' && c != 'G' && c != 'C' && c != 'T'){
                return false;
            }
        }
        return true;
    }
    
    //RNA checker method
    public static boolean checkRNA(char[] array) {
        for (char c : array) {
            if (c != 'A' && c != 'G' && c != 'C' && c != 'U'){
                return false;
            }
        }
        return true;
    }

    //insert method
    public static void insert(int position, String type, String sequence) {
        Sequence newType;
        switch (type) {
            case "DNA":
                newType = Sequence.DNA;
                break;
            case "RNA":
                newType = Sequence.RNA;
                break;
            default:
                newType = Sequence.NONE;
                break;
        }

        LinkedList<Character> l = new LinkedList<>();
        char[] array = sequence.toCharArray();

        //boolean check
        boolean check = false;
        if (type.equals("DNA")){
            check = checkDNA(array);
        }
        else if (type.equals("RNA")){
            check = checkRNA(array);
        }

        //when check returns true 
        if (check) {
            for (char item : array) {
                l.append(item);
            }
            mainArray[position] = new DNAList(newType, l);
        }
        //else
        else {
            System.out.println("Error occurred while inserting");
        }

    }

    //removing items
    public static void remove(int pos) {
        if (mainArray[pos].type.getType().equals("NONE")) {
            System.out.println("No sequence to remove at specified position");
        }
        else {
            mainArray[pos] = new DNAList(TYPE_EP, PLACE_HOLDER_EP);  //Else, Reset Position
        }
    }

    //printing method
    public static void print() {

        for (int i = 0; i < mainArray.length; i++) {
            if ( !(mainArray[i].type.getType().equals("NONE"))) {
                String sequenceString = "";
                mainArray[i].sequence.moveToStart();
                for (int j = 0; j < mainArray[i].sequence.length(); j++) {
                    sequenceString += mainArray[i].sequence.getValue();
                    mainArray[i].sequence.next();
                }
                System.out.println(i + "\t" + mainArray[i].type.getType() + "\t" + sequenceString);
            }
        }
    }

    //print method for NONE
    public static void print(int pos) {
        if (mainArray[pos].type.getType().equals("NONE")){
            System.out.println("No sequence to print at specified position");
        }
        else {
            String sequenceStr = "";
            mainArray[pos].sequence.moveToStart();
            for (int k = 0; k < mainArray[pos].sequence.length(); k++) {
                sequenceStr += mainArray[pos].sequence.getValue();
                mainArray[pos].sequence.next();
            }
            System.out.println(mainArray[pos].type.getType() + "\t" + sequenceStr);
        }
    }

    public static void clip(int pos, int start, int end) {

        if (mainArray[pos].type.getType().equals("NONE")){
            System.out.println("No sequence to clip at specified position");
        }
        else if (end < start){
            mainArray[pos] = new DNAList(mainArray[pos].type, PLACE_HOLDER_EP);
        }
        else if (start < 0){
            System.out.println("Invalid start index");
        }
        else if (start >= mainArray[pos].sequence.length()){
            System.out.println("Start index is out of bounds");
        }
        else if (end >= mainArray[pos].sequence.length()){
            System.out.println("End index is out of bounds");
        }

        else {
            String clipped = "";
            mainArray[pos].sequence.moveToPos(start);

            for (int i = 0; i < end-start+1; i++) {
                clipped += mainArray[pos].sequence.getValue();
                mainArray[pos].sequence.next();
            }

            LinkedList<Character> l = new LinkedList<>();
            char[] array = clipped.toCharArray();

            for (char item : array) {
                l.append(item); // Append it
            }
            mainArray[pos] = new DNAList(mainArray[pos].type, l);
        }
    }

    public static void copy(int pos1, int pos2) {

        if (mainArray[pos1].type.getType().equals("NONE")){
            System.out.println("No sequence to copy at specified position");
        }
        else {
            mainArray[pos2] = mainArray[pos1];   //copy pos1 onto pos2
        }
    }

    public static void transcribe(int pos) {

        if (mainArray[pos].type.getType().equals("NONE")){
            System.out.println("No sequence to transcribe at specified position");
        }

        else if (mainArray[pos].type.getType().equals("RNA")){
            System.out.println("Cannot transcribe RNA");
        }
        else if (mainArray[pos].type.getType().equals("DNA")) {
            String sequenceStr = "";
            mainArray[pos].sequence.moveToStart();
            for (int j = 0; j < mainArray[pos].sequence.length(); j++) {
                sequenceStr += mainArray[pos].sequence.getValue();
                mainArray[pos].sequence.next();
            }

            char[] array = sequenceStr.toCharArray();
            //replacing T's with U's
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 'T') {
                    array[i] = 'U';
                }
            }

            // Replace all the Letters in the Sequence
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 'U'){
                    array[i] = 'A';
                }
                else if (array[i] == 'A'){
                    array[i] = 'U';
                }
                else if (array[i] == 'C'){
                    array[i] = 'G';
                }
                else if (array[i] == 'G'){
                    array[i] = 'C';
                }
            }

            //Sequence Reversing Method
            char[] revArray = new char[array.length];
            int j = array.length;
            for (char anArray : array) {
                revArray[j - 1] = anArray;
                j--;
            }
            // Generate New Sequence
            LinkedList<Character> l = new LinkedList<>();
            for (char c : revArray) {
                l.append(c);
            }
            mainArray[pos] = new DNAList(Sequence.RNA, l);
        }
    }

    public static void main(String[] args) {

        int size = 0;
        String commandFile = "";

        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("First argument must be an integer.");
            }
            try {
                commandFile = args[1];
            } catch (Exception e) {
                System.err.print("Second argument must be the name of the input file.");
            }
        }

        mainArray = new DNAList[size];
        
        for (int i = 0; i < size; i++) {
            mainArray[i] = new DNAList(TYPE_EP, PLACE_HOLDER_EP);
        }

        //using .io and reading from file
        FileReader reader = null;
        BufferedReader bufferReader = null;
        try {
            reader = new FileReader(commandFile);
            bufferReader = new BufferedReader(reader);

            String currentLine;
            int pos;

            while ((currentLine = bufferReader.readLine()) != null) {

                String[] tokens = currentLine.split(" ");

                switch (tokens[0]) {
                    case "insert":
                        pos = Integer.parseInt(tokens[1]);
                        String type = tokens[2];
                        String sequence = tokens[3];
                        insert(pos, type, sequence);
                        break;
                    case "remove":
                        pos = Integer.parseInt(tokens[1]);
                        remove(pos);
                        break;
                    case "print":
                        if (tokens.length == 1)
                            print();
                        else if (tokens.length == 2) {
                            pos = Integer.parseInt(tokens[1]);
                            print(pos);
                        }
                        break;
                    case "clip":
                        pos = Integer.parseInt(tokens[1]);
                        int start = Integer.parseInt(tokens[2]);
                        int end = Integer.parseInt(tokens[3]);
                        clip(pos, start, end);
                        break;
                    case "copy":
                        int pos1 = Integer.parseInt(tokens[1]);
                        int pos2 = Integer.parseInt(tokens[2]);
                        copy(pos1, pos2);
                        break;
                    case "transcribe":
                        pos = Integer.parseInt(tokens[1]);
                        transcribe(pos);
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

}

