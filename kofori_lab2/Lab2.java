/* Name: Kweku Ofori
 * Partner: None
*/
import java.util.Arrays;

public class Lab2<T> {
	
	// Non-generic implementation
	public static void printArrayNonGen(Object[] anArray) {
		System.out.println(Arrays.toString(anArray));
	}
	
	//method overloading for data types
	public static void printArray(Integer[] intArray) {
		System.out.println(Arrays.toString(intArray));
	}
	
	public static void printArray(Double[] doubArray) {
		System.out.println(Arrays.toString(doubArray));
	}
	
	public static void printArray(Character[] charArray) {
		System.out.println(Arrays.toString(charArray));
	}
	
	public static void printArray(String[] strArray) {
		System.out.println(Arrays.toString(strArray));
	}

	
	//Generic implementation without the need for method overloading 
	public static <T> void printArrayGen(T[] Object) { // instantiation with type parameter T
		System.out.println(Arrays.toString(Object));	
	}
	
	public static void main(String[] args) {
		Integer [] intArray = {1, 2, 3, 4, 5 };
		Double [] doubArray = {1.1, 2.2, 3.3, 4.4};
		Character [] charArray = {'H','E','L', 'L', 'O' };
		String [] strArray = {"once", "upon", "a", "time" };
		
		// call functions for method overloading functions
		printArray(intArray);
		printArray(doubArray);
		printArray(charArray);
		printArray(strArray);
		
		// call functions for non-generic methods
		printArrayNonGen(intArray);
		printArrayNonGen(doubArray);
		printArrayNonGen(charArray);
		printArrayNonGen(strArray);
		
		// call functions for generic methods
		printArrayGen(intArray);
		printArrayGen(doubArray);
		printArrayGen(charArray);
		printArrayGen(strArray);
		
		System.out.println(getMax(intArray));
		System.out.println(getMax(doubArray));
		System.out.println(getMax(charArray));
		System.out.println(getMax(strArray));
		
		System.out.println(getMaxGen(intArray));
		System.out.println(getMaxGen(doubArray));
		System.out.println(getMaxGen(charArray));
		System.out.println(getMaxGen(strArray));

	}
	
	// Non-generic getMax implementation with comparable
	public static Comparable getMax(Comparable[] anArray) {
		Comparable max = anArray[0];
		for(int i = 0; i < anArray.length; i++) {
			if(anArray[i].compareTo(max) > 0) {
				max = anArray[i];
			}
		}
		return max; // returns max type
		
	}
	
	// Generic getMax implementation with comparable
	public static <T extends Comparable<T>> T getMaxGen(T [] anArray) {
		T max =  anArray[0]; // T defines max data type
		for(int i = 0; i < anArray.length; i++) {
			if(anArray[i].compareTo(max) > 0) {
				max = anArray[i];
			}
		}
		return max; // returns max type
	}
	
}



