
package pkg2si4_lab3_binarysearchtreeset;

public class BSTSet {
    TNode root;
    
    public BSTSet(){
        root = null;
    }
    public BSTSet(int[] input){
        this();
        if(input.length > 0){
            int[] inputCopy = new int[input.length];
            for(int i=0;i<input.length;i++)
                inputCopy[i] = input[i];
            sort(inputCopy, 0, inputCopy.length-1);
            BSTSet_addArray(inputCopy,0,inputCopy.length-1);            
        }
    }
    
    private void BSTSet_addArray(int[] sorted, int start, int end){
        add(sorted[(end+start)/2]);
        if(end-start == 1)
            add(sorted[end]);
        else if((end-start) >= 2){
            BSTSet_addArray(sorted,start,(end+start)/2-1);
            BSTSet_addArray(sorted,(end+start)/2+1,end);
        }
        return;
    }
      
    public boolean isIn(int v){
        return BSTSet.isIn(root, v);
    }
    private static boolean isIn(TNode node, int v){
        if(node == null)
            return false;
        else if(v < node.element)
            return BSTSet.isIn(node.left, v);
        else if(v > node.element)
            return BSTSet.isIn(node.right, v);
        else
            return true;
    }
    
    public void add(int num){
        root = BSTSet.add(root, num);
    }
    private static TNode add(TNode node, int num){
        if(node==null)
            node = new TNode(num, null, null);
        else if (num < node.element)
            node.left = BSTSet.add(node.left, num);
        else if (num > node.element)
            node.right = BSTSet.add(node.right, num);
        return node; //nothing changes if num == node.element
    }
    
    public boolean remove(int v){
        TNode node = root, parent, onlyChild;
        MyStack<TNode> stack = new MyStack<TNode>();
        if(root == null)
            return false;
        while(node != null){
            if(v < node.element){
                stack.push(node);
                node = node.left;
            }else if(v > node.element){
                stack.push(node);
                node = node.right;
                
            }else{//node found
                parent = stack.pop();                                  
                if(node.left != null && node.right != null){//2 children
                    int temp = BSTSet.findMax(node.left);//removeMax removes the max and returns it's value                                        
                    remove(temp);
                    node.element = temp;
                }else if(node.left == null && node.right == null){//no children
                    if(node == root)
                        root = null;
                    else if(node == parent.left){
                        parent.left = null;
                    }else
                        parent.right = null;                    
                }else{//1 child                    
                    onlyChild = (node.left == null)? node.right: node.left;
                    if(node == root){
                        if(root.left != null)
                            root = root.left;
                        else
                            root = root.right;
                    }
                    else if(node == parent.left){
                        parent.left = onlyChild;
                    }else
                        parent.right = onlyChild;                    
                }
                return true;
            }                            
        }
        return false;
    }       
    private static int findMax(TNode node){
        while(node.right != null)
            node = node.right;
        return node.element;
    }
    
    public BSTSet union(BSTSet s){
       MyStack<TNode> s_stack = s.convertToStack();
       MyStack<TNode> this_stack = convertToStack();
       MyStack<Integer> newValues = new MyStack<Integer>();
       int value;
       for(int i=0;i<s.size();i++){
           value = s_stack.pop().element;
           if(!isIn(value))
               newValues.push(value);
       }
       int[] array = new int[newValues.height + size()];
       int h1 = newValues.height, h2 = this_stack.height;
       for(int i=0;i<h1;i++)
           array[i] = newValues.pop();
       for(int i=0;i<h2;i++)
           array[i+h1] = this_stack.pop().element;
       return new BSTSet(array);
    }    
    public BSTSet intersection(BSTSet s){
        int value;
        MyStack<TNode> s_stack = s.convertToStack();
        MyStack<Integer> newValues = new MyStack<Integer>();
        for(int i=0;i<s.size();i++){
            value = s_stack.pop().element;
            if(isIn(value))
                newValues.push(value);
        }
        int length = newValues.height;
        int[] array = new int[length];
        for(int i=0;i<length;i++)
            array[i] = newValues.pop();
        return new BSTSet(array);
    }
    public BSTSet difference(BSTSet s){
        MyStack<TNode> this_stack = convertToStack();
        MyStack<TNode> s_stack = s.convertToStack();
        int[] array = new int[size()];
        for(int i=0;i<size();i++)
            array[i] = this_stack.pop().element;
        BSTSet this_copy = new BSTSet(array);
        int value;
        for(int i=0;i<s.size();i++){
            value = s_stack.pop().element;
            if(this_copy.isIn(value))
                this_copy.remove(value);
        }
        return this_copy;
    }
    
    public MyStack<TNode> convertToStack(){
        MyStack<TNode> stack = new MyStack<TNode>();
        BSTSet.convertToStack(root,stack);
        return stack;
    }
    private static void convertToStack(TNode node, MyStack<TNode> stack){
        if(node == null)
            return;
        BSTSet.convertToStack(node.right, stack);
        stack.push(node);
        BSTSet.convertToStack(node.left, stack);        
    }
        
    public int size(){
        return BSTSet.size(root);    
    }
    private static int size(TNode node){
        if(node == null)
            return 0;
        return 1 + BSTSet.size(node.right) + BSTSet.size(node.left);
    }
    
    public int height(){
        return BSTSet.height(root) - 1;
    }
    private static int height(TNode node){
        //do left and right traversal, only returning the deepest length
        int leftHeight = 0, rightHeight = 0;
        if(node == null)
            return 0;
        leftHeight = BSTSet.height(node.left);
        rightHeight = BSTSet.height(node.right);
        if(leftHeight> rightHeight)
            return leftHeight + 1;
        else
            return rightHeight + 1;
    }    

    public void printBSTSet(){
        if(root==null)
            System.out.println("The set is empty");
        else{
            System.out.println("The set elements are: ");
            printBSTSet(root);
            System.out.println("\n");
        }        
    }
    private void printBSTSet(TNode t){
        if (t == null)
            return;
        printBSTSet(t.left);
        System.out.print(" " + t.element + ", ");
        printBSTSet(t.right);               
    }
    
    public void printNonRec(){
        if(root==null)
            System.out.println("The set is empty");
        else{
            System.out.println("The set elements are: ");
            MyStack<TNode> stack = new MyStack<TNode>();
            TNode current = root;
            while(current != null || !stack.isEmpty()){                
                while(current != null){
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                System.out.print(" " + current.element + ", ");
                current = current.right;
            }
            System.out.println("\n");
        }        
    }
    
    //https://www.geeksforgeeks.org/merge-sort/
    private static void merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1; 
        int n2 = r - m;   
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
        
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
        
        int i = 0, j = 0; 
        int k = l; 
        while (i < n1 && j < n2){
            if (L[i] <= R[j]){
                arr[k] = L[i]; 
                i++; 
            } 
            else{
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while (i < n1) {
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2){
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
    public static void sort(int arr[], int l, int r){
        if (l < r){
            int m = (l+r)/2; 
            sort(arr, l, m); 
            sort(arr , m+1, r);
            merge(arr, l, m, r); 
        } 
    }   
}
