import java.util.Arrays;
import java.util.ArrayList;

public class Lab3Task1 {
	// iterating through an ArrayList and printing elements in 4x4 formatted
	public static void print2Darray(int[] [] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				System.out.print("\t"); //// adds a tab to row elements
			}
			System.out.println();
		}
	}
	
		
	// iterating through an ArrayList and printing elements in 4x4 formatted
	public static void print2DList(ArrayList<ArrayList<Integer>> list) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j));
				System.out.print("\t"); // adds a tab to row elements
			}
			System.out.println(); // prints new line
		}		
	}
	

	public static void main(String[] args) {
		
		// array containing set of integers
		int[] [] array = {{10,15,30,40},{15,5,8,2},{20,2,4,2},{1,4,5,0}};
		
		// method call
		print2Darray(array);
		
		//implementing an ArrayList which contains another ArrayList object
		ArrayList< ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> set1 = new ArrayList<Integer>(
				Arrays.asList(10,15,30,40));
		ArrayList<Integer> set2 = new ArrayList<Integer>(
				Arrays.asList(15,5,8,2));
		ArrayList<Integer> set3 = new ArrayList<Integer>(
				Arrays.asList(20,2,4,2));
		ArrayList<Integer> set4 = new ArrayList<Integer>(
				Arrays.asList(1,4,5,0));
		
		// adding an ArrayList to an ArrayList
		arrayList.add(set1);
		arrayList.add(set2);
		arrayList.add(set3);
		arrayList.add(set4);
		
		// method call
		print2DList(arrayList);
	}

}


