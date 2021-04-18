/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.text.SimpleDateFormat; 
import java.util.Calendar;
import java.util.Random;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class Sorting {
	

 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
	
	public static void bubbleSort(int list[]) { 
		//loops through unsorted array
        for (int i = 0; i < list.length-1; i++) { 
            for (int j = 0; j < list.length-i-1; j++) {
            	// swaps list[j+1] and list[j] values
                if (list[j] > list[j+1]) { 
                    int temp = list[j]; 
                    list[j] = list[j+1]; 
                    list[j+1] = temp; 
                }
            }
        }
    } 
	
	public static void selectionSort(int[] list) {
		int minValue,
		minIndex, 
		temp = 0;
		//loops through unsorted array
		for(int i = 0; i < list.length; i++) {
			minValue = list[i];
			minIndex = i;
			//checks for minimum value
			for(int j = i; j < list.length; j++) {
				if(list[j] < minValue) {
					minValue = list[j];
					minIndex = j;
				}
			}
			
			//swaps minimum value and first element
			if(minValue < list[i]) {
				temp = list[i];
				list[i] = list[minIndex];
				list[minIndex] = temp;
			}
		}
	}
	
	public static void insertionSort(int[] list) {
		//loops through unsorted array
		for(int i = 1; i < list.length; i++) {
			int key = list[i];
			int j = i - 1;
			//moves element element greater than key to a position ahead of their current position
			while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j];
                j = j - 1;
            }
            list[j + 1] = key;
		}
	}
	
    //merge and mergeSort methods taken from https://www.baeldung.com/java-merge-sort
   public static void merge(
           int[] a, int[] l, int[] r, int left, int right) {

       int i = 0, j = 0, k = 0;
       while (i < left && j < right) {
           if (l[i] <= r[j]) {
               a[k++] = l[i++];
           }
           else {
               a[k++] = r[j++];
           }
       }
       while (i < left) {
           a[k++] = l[i++];
       }
       while (j < right) {
           a[k++] = r[j++];
       }
   }

    public static void mergeSort(int[] arr, int n){
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(arr, l, r, mid, n - mid);
    }
    
    //partition method and Quick sort from https://www.baeldung.com/java-quicksort
   public static int partition(int arr[], int begin, int end) {
       int pivot = arr[end];
       int i = (begin-1);
       for (int j = begin; j < end; j++) {
           if (arr[j] <= pivot) {
               i++;
               int swapTemp = arr[i];
               arr[i] = arr[j];
               arr[j] = swapTemp;
           }
       }
       int swapTemp = arr[i+1];
       arr[i+1] = arr[end];
       arr[end] = swapTemp;
       return i+1;
   }

    public static void quickSort(int[] arr, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    public static void main(String[] args)  { 
        In in = new In(args[0]);
        
        //Random instance to be used when we randomly swap elements for array c later on
        Random rand = new Random();

        //String declaration for printing
        String netID = "kofori";
        String appliedAlgorithm = "";
        String appliedArray ="";
        String fileName = "";
        
        //String array containing names of the sorting algorithms
        String[] alg = {"Arrays.sort() (Java Default)", "Bubble Sort",  "Selection Sort" , "Insertion Sort", "Merge Sort", "Quick Sort"};
        //String array for the array names used
        String[] arrayName = {"a", "b", "c", "d"};
        //ArrayList for int arrays used
        ArrayList<int[]> arrays =  new ArrayList<>();
        
        // Storing file input in an array
        int[] a = in.readAllInts();
        
        // contains sorted integers from Arrays.sort()
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        
        // contains stored integers in reverse order
        int[] c = new int[b.length];
        for(int i = 0; i < c.length; i++ ){
           c [i] = b[b.length - 1 - i];
        }
        
        // d contains almost sorted array
        int[] d = Arrays.copyOf(b, b.length);
         
        //generates random ints
        for(int i =0; i < d.length * 0.1; i++) {
            int rand1 = rand.nextInt(d.length);
            int rand2 = rand.nextInt(d.length);
            int temp = d[rand1];
            d[rand1] = d[rand2];
            d[rand2] = temp;
        }
        
        //adds made int arrays to the ArrayList
        arrays.add(a);
        arrays.add(b);
        arrays.add(c);
        arrays.add(d);
        
        int arg2 = Integer.parseInt(args[1]);
        if(arg2 == 0){
            //loop to sort for each array from a to d
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                //get which array to use(a to d)
                Arrays.sort(arrays.get(i));
                double time = watch.elapsedTimeMillis();
                //choose the algorithm to be used based on the input argument
                appliedAlgorithm = alg[arg2];
                //choose the array to be used
                appliedArray = arrayName[i];

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                //write to file for array
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        //process similar to argument2 == 0 for the other arguments with small variation
        else if(arg2 == 1){
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                bubbleSort(arrays.get(i));
                double time = watch.elapsedTimeMillis();
                appliedAlgorithm = alg[arg2];
                appliedArray = arrayName[i];
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(arg2 == 2){
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                selectionSort(arrays.get(i));
                double time = watch.elapsedTimeMillis();
                appliedAlgorithm = alg[arg2];
                appliedArray = arrayName[i];
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(arg2 == 3){
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                insertionSort(arrays.get(i));
                double time = watch.elapsedTimeMillis();
                appliedAlgorithm = alg[arg2];
                appliedArray = arrayName[i];
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(arg2 == 4){
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                mergeSort(arrays.get(i),arrays.get(i).length);
                double time = watch.elapsedTimeMillis();
                appliedAlgorithm = alg[arg2];
                appliedArray = arrayName[i];
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(arg2 == 5){
            for(int i = 0; i < arrayName.length; i++){
                Stopwatch watch = new Stopwatch();
                quickSort(arrays.get(i),0, arrays.get(i).length-1);
                double time = watch.elapsedTimeMillis();
                appliedAlgorithm = alg[arg2];
                appliedArray = arrayName[i];
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                StdOut.printf("%s %s %8.1f   %s  %s  %s\n", appliedAlgorithm, appliedArray, time, timeStamp, netID, args[0]);
                fileName = arrayName[i] + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(Arrays.toString(arrays.get(i)));
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("The second argument must be between 0 and 5 (inclusive).");
        }
  } 
} 


