// imports array and scanner class
import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {
	
	public static boolean isAnagram(String a, String b) {
		//converts input to char array
		char first [] = a.toCharArray();
		char second [] = b.toCharArray();
		// sorts array
		Arrays.sort(first);
		Arrays.sort(second);
		
		Boolean results = Arrays.equals(first, second); // checks if fist and second strings match
				
		if(results == true) {
			System.out.println("The compared strings are anagrams");
			return true;
		}
		System.out.println("The compared strings are not anagrams");
		return false;
		
	}
	
	public static boolean isRotation(String realStr, String otherStr) {
		String newStr = realStr.concat(realStr); // concatenates the first string entered plus itself
		if(newStr.contains(otherStr)) { // checks if new string is in concatenated string 
			System.out.println("It is a rotational string");
			return true;
		}
		System.out.println("It is not a rotational string");
		return false;	
	}

	
	public static void main(String[] args) {
		//gets user data
		Scanner user = new Scanner(System.in);
		System.out.println("Enter first string");
		String firstStr = user.next();
		System.out.println("Enter second string");
		String secondStr = user.next();
		
		isAnagram(firstStr,secondStr); // calls isAnagram method
		isRotation(firstStr,secondStr); // calls isRotation method
	}

}
