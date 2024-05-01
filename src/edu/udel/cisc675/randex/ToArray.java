public class ToArray {
    private ToArray() {}
    
    /* Converts an array list of Integer to an array of int. */
    private static int[] toArray(ArrayList<Integer> list) {
		int n = list.size();
		int[] result = new int[n];
		for (int i=0; i<n; i++)
			result[i] = list.get(i);
		return result;
    }
}