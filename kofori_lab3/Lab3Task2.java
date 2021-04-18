import java.util.ArrayList;
import java.util.Arrays;

public class Lab3Task2 {
	public static void runningSum2DArray(int [] [] array, int dir) {
		// adding elements from right to left
		if (dir == 2) {
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					if(j!=0) {
						array[i][j]= array[i][j] + array[i][j-1];
						System.out.print(array[i][j]);
					}else {
						System.out.print(array[i][j]);
					}
					System.out.print("\t");
				}
				System.out.println();
			}
		}
		
		// adding elements from left to right
		else if (dir == 1) {
			for(int i = 0; i < array.length; i++) {
				for(int j = array[i].length-1; j >= 0; j--) {
					if(j!=array.length-1) {
						array[i][j]= array[i][j] + array[i][j+1];
					}
				}
			}
			
			// printing elements
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					System.out.print(array[i][j]);
					System.out.print("\t"); //// adds a tab to row elements
				}
				System.out.println();
			}
		}
		
		// adding elements ascending order
		else if (dir == 3) {
			for(int i = array.length-1; i >= 0; i--) {
				for(int j = 0; j < array[i].length; j++) {
					if(i!=array.length-1) {
						array[i][j]= array[i][j] + array[i+1][j];
						}
				}
			}
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					System.out.print(array[i][j]);
					System.out.print("\t"); //// adds a tab to row elements
				}
				System.out.println();
			}
		}
		
		//adding elements in descending order
		else if (dir == 4) {
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					if(i!=0) {
						array[i][j]= array[i][j] +array[i-1][j];
					}
				}
			}
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					System.out.print(array[i][j]);
					System.out.print("\t"); //// adds a tab to row elements
				}
				System.out.println();
			}
		}	
	}
	
	
	public static void runningSum2DArrayList(ArrayList<ArrayList<Integer>> list, int dir) {
		if (dir == 2) {
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < list.get(i).size(); j++) {
					if(j!=0) {
						list.get(i).set(j,list.get(i).get(j) + list.get(i).get(j-1));
						System.out.print(list.get(i).get(j));
					}else {
						System.out.print(list.get(i).get(j));
					}
					System.out.print("\t");
				}
				System.out.println();
			}
		}
		
		else if (dir == 1) {
			for(int i = 0; i < list.size(); i++) {
				for(int j = list.get(i).size()-1; j >= 0; j--) {
					if(j != list.size()-1) {
						list.get(i).set(j,list.get(i).get(j) + list.get(i).get(j+1));
					}
				}
			}
			
			// printing elements
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < list.get(i).size(); j++) {
					System.out.print(list.get(i).get(j));
					System.out.print("\t"); //// adds a tab to row elements
				}
				System.out.println();
			}
		}
		
		// adding elements descending order
				else if (dir == 3) {
					for(int i = list.size()-1; i >= 0; i--) {
						for(int j = 0; j < list.get(i).size(); j++) {
							if(i != list.size()-1) {
								list.get(i).set(j,list.get(i).get(j) + list.get(i+1).get(j));
							}
						}
					}
					
					for(int i = 0; i < list.size(); i++) {
						for(int j = 0; j < list.get(i).size(); j++) {
							System.out.print(list.get(i).get(j));
							System.out.print("\t"); //// adds a tab to row elements
						}
						System.out.println();
					}
				}
		
		
		//adding elements in descending order
		else if (dir == 4) {			
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < list.get(i).size(); j++) {
					if(i != 0) {
						list.get(i).set(j,list.get(i).get(j) + list.get(i-1).get(j));
					}
				}
			}
			// printing element
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < list.get(i).size(); j++) {
					System.out.print(list.get(i).get(j));
					System.out.print("\t"); //// adds a tab to row elements
				}
				System.out.println();
			}
		}
		
		
	}

	public static void main(String[] args) {
		
		// array containing set of integers
		int[] [] array = {{10,15,30,40},{15,5,8,2},{20,2,4,2},{1,4,5,0}};
		
		//method call
		runningSum2DArray(array,2);
		runningSum2DArray(array,1);
		runningSum2DArray(array,3);
		runningSum2DArray(array,4);
		
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
		
		//method call
		runningSum2DArrayList(arrayList,2);
		runningSum2DArrayList(arrayList,1);
		runningSum2DArrayList(arrayList,3);
		runningSum2DArrayList(arrayList,4);
	}
	
					
}
