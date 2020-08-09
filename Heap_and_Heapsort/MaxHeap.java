
package Lab5_Heap;

public class MaxHeap {
    
    private Integer[] heap;
    private int heapSize;
    private int numItems;
    private int nextEmpty;
    
    public MaxHeap(int n) {
        this.heapSize = n;
        this.numItems = 0;
        this.nextEmpty = 1;
        this.heap = new Integer[n+1]; //index 0 should not be used       
    }
    public MaxHeap(Integer[] array) {
        this(array.length);
        
        for (int i=0; i<array.length; i++) {
            this.insert(array[i]); 
        }
    }
    
    public void insert(int num) {
        if(this.numItems >= this.heapSize) {
            System.out.println("Not enough room, making larger heap...");
            Integer[] tempArray = new Integer[this.heapSize * 2];
            for(int i=0; i<this.heapSize; i++) {
                tempArray[i] = this.heap[i];
            }
            this.heap = tempArray;
            this.heapSize = tempArray.length;
        }        
        this.heap[this.nextEmpty] = num;
        this.numItems++;
        this.percolate_UP(this.nextEmpty++);        
    }
    private void percolate_UP(int index) {
        int currentIndex = index, temp;
        while(currentIndex != 1 && this.heap[currentIndex] > this.heap[currentIndex/2]) { //need to bubble up
            temp = this.heap[currentIndex/2];
            this.heap[currentIndex/2] = this.heap[currentIndex];
            this.heap[currentIndex] = temp;
            currentIndex /= 2;
        }
    }
    
    public int deleteMax() {
        if(this.heapSize == 0) 
            return -1;
        int max = this.heap[1];
        this.heap[1] = this.heap[this.nextEmpty - 1];
        this.heap[this.nextEmpty - 1] = null;
        this.numItems--;
        this.nextEmpty--;
        this.percolate_DOWN(1);
        return max;
    }
    private void percolate_DOWN(int index) {
    //percolation has to take into account whether left or right child is larger
        int currentIndex = index, temp, leftDiff = 0, rightDiff = 0, offset = 0;
        boolean leftExists = true, rightExists = true;
        while(this.heap[currentIndex] != null) {
            try{
                leftDiff = this.heap[currentIndex] - this.heap[currentIndex * 2];
            }catch(Exception e){leftExists = false;}
            try{
                rightDiff = this.heap[currentIndex] - this.heap[(currentIndex * 2) + 1];
            }catch(Exception e){rightExists = false;}
            if(rightDiff < 0 && leftDiff < 0 && rightExists && leftExists) { //both children are bigger
                if(Math.abs(rightDiff) > Math.abs(leftDiff)) { offset = 1; }
                else { offset = 0; }
            }
            else if(rightDiff < 0 && rightExists) { offset = 1; }
            else if(leftDiff < 0 && leftExists) { offset = 0; }
            else { break; }//neither are bigger, no need to percolate anymore
            
            //do swap, where offset decides left child or right child
            temp = this.heap[currentIndex];
            this.heap[currentIndex] = this.heap[(currentIndex * 2) + offset];
            this.heap[(currentIndex * 2) + offset] = temp; 
            currentIndex = currentIndex*2 + offset;
        }
    }
    
    public String toString() {
        String s = "";
        for(int i=1; i<=this.heapSize; i++) {
            if(this.heap[i] != null)
                s += this.heap[i] + " ";
            else { break; }
        }
        return s;
    }
    
    public static void heapSort(Integer[] arrayToSort) {
        MaxHeap h = new MaxHeap(arrayToSort);
        int totalItems = h.getNumItems();
        int index = 0;
        while(h.getNumItems() != 0) {
            arrayToSort[totalItems - 1 - index] = h.deleteMax();
            index++;
        }
    }
    
    public Integer[] getHeap() {
        return heap;        
    }
    public int getSize() {
        return heapSize;
    }
    public int getNumItems() {
        return numItems;
    }    
}
