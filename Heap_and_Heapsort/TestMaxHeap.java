
package Lab5_Heap;
import java.util.Arrays;

public class TestMaxHeap {
    private static int MAXSIZE = 11;
    private static int MAXNUM = 10;
    public static void main(String[] args) {
        testMaxHeap(2500,true);
    }
    
    private static void testMaxHeap(int numTests, boolean showTests) {
        int size, numCorrect = 0;
        int[] randArray;
        Integer[] randArray_toHeap;
        for(int i=0;i<numTests; i++) {
            //choose random array size
            size = (int)(Math.random()*MAXSIZE + 1);
            randArray = new int[size];
            randArray_toHeap = new Integer[size];
            
            //fill array with random numbers
            for(int j=0; j<size; j++) {
                randArray[j] = (int)(Math.random()*(MAXNUM + 1));
                randArray_toHeap[j] = randArray[j];
            }
            Arrays.sort(randArray);
            MaxHeap.heapSort(randArray_toHeap);
            
            //print arrays to verify they are the same
            if(showTests) {
                System.out.println("Java sorted: " + Arrays.toString(randArray));
                System.out.println("Heap sorted: " + Arrays.toString(randArray_toHeap)); //using Java 'Arrays' to print out array sorted by HeapSort
                System.out.println();
            }
            
            //verify the sort
            if(Arrays.toString(randArray).equals(Arrays.toString(randArray_toHeap)))
                numCorrect++;   
        }        
        System.out.println(String.format("Result: %d / %d",numCorrect, numTests));
    }
    
}
