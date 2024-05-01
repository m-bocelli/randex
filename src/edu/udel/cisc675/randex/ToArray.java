package edu.udel.cisc675.randex;
import java.util.ArrayList;

public class ToArray {
    private ToArray() {}
    
    /* Converts an array list of Integer to an array of int. */
    public static int[] toArray(ArrayList<Integer> list) {
		int n = list.size();
		int[] result = new int[n];
		for (int i=0; i<n; i++)
			result[i] = list.get(i);
		return result;
    }
}