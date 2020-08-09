
package pkg2si4_lab3_binarysearchtreeset;
 
public class TestBSTSet {
	public static void main(String[] args) {
            BSTSet t1 = new BSTSet(new int[]{1,2,3,6,1,2,4,22,11,44,33,55222,32});
            BSTSet t2 = new BSTSet(new int[]{22,11,44,66,99,88,77});
            t1.printNonRec();
            t2.printBSTSet();
            System.out.println(t2.height());
            BSTSet t3 = t1.union(t2);
            t3.printBSTSet();
            BSTSet t4 = t1.union(t2);
            t3.add(100);
            BSTSet t5 = t3.difference(t4);
            t5.printBSTSet();
            
            
	}
}
