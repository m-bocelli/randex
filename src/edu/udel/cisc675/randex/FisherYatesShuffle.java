package edu.udel.cisc675.randex;
import java.util.random.RandomGenerator;

public class FisherYatesShuffle {
    private RandomGenerator rand;

    public FisherYatesShuffle(RandomGenerator rand) {
        this.rand = rand;
    }

    public void shuffleArray(int[] arr) {
        int size = arr.length;
        initArr(arr, size);
        for (int i=size-1; i>=0; i--) {
            int j = rand.nextInt(i+1);
            if (i!=j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
    }

    private void initArr(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            arr[i] = i;
    }
}