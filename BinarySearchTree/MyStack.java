
package pkg2si4_lab3_binarysearchtreeset;

public class MyStack<T> {
    SNode<T> head;
    int height;
    
    public MyStack(){
        head = new SNode<T>(null,null);
    }
    
    public T pop(){
        SNode<T> temp;
        if(isEmpty())
            return null;
        else{
            temp = head.next;
            head.next = head.next.next;
            height--;
            return temp.element;
        }
            
    }
    
    public void push(T node){
        head.next = new SNode(node,head.next);
        height++;
    }
    
    public boolean isEmpty(){
        return (head.next == null);
    }
    
    public T top(){
        SNode<T> temp = head.next;
        return temp.element;
    }
    
    public int getHeight(){
        return height;
    }
    
}
