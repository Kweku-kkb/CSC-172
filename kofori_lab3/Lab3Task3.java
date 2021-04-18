import java.util.*;

public class Lab3Task3 {
	
	// adding elements to arrayList
	
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(1);
        al.add(5);
        al.add(7);
        al.add(2);

        Lab3Task3 my = new Lab3Task3();

        my.printArrayListForLoopIterator(al);
        my.printArrayListEnhancedLoop(al);
        my.printArrayListForLoopIterator(al);
        my.printArrayListWhileLoopIterator(al);
    }

    public Lab3Task3() {

    }

    //using basic while
    public void printArrayListBasicLoop(ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
    }
 // Using enhanced for loop
    public void printArrayListEnhancedLoop(ArrayList<Integer> al) {
        for (int element : al) {
            System.out.println(element);
        }
    }
 // Using basic for loop with iterator
    public void printArrayListForLoopIterator(ArrayList<Integer> al) {
        Iterator iterator = al.listIterator();
        int i = 0;
        for (; iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
    }
 // Using basic while loop with iterator
    public void printArrayListWhileLoopIterator(ArrayList<Integer> al) {
        Iterator iterator = al.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
