/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSL;

/**
 *
 * @author mateu
 */
public class SLLSet {
    private int size;
    SLLNode head;
    
    public SLLSet(){
        head = new SLLNode(0,null);        
    }
    public SLLSet(int[] sortedArray){
        this();
        size = sortedArray.length;
        SLLNode p = head;
        for(int i=0;i<size;i++){
            p.next = new SLLNode(sortedArray[i],null);
            p = p.next;
        }
    }    
    public int getSize(){
        return size;
    }
    public SLLSet copy(){
        if(size==0){
            return new SLLSet();
        }
        int[] a = new int[size];
        int i=0;
        for(SLLNode p=head.next;p!=null;p=p.next){
            a[i++] = p.value;
        }
        return new SLLSet(a);
    }
    public boolean isIn(int v){
        for(SLLNode p=head.next;p!=null;p=p.next){
            if(p.value == v){return true;}
        }
        return false;
    }
    public void add(int v){
        if(head.next == null){
            head.next = new SLLNode(v,null);
        }
        else if(isIn(v)==false){
            for(SLLNode p=head;p.next!=null;p=p.next){
                if(p.next.value>v){
                    p.next = new SLLNode(v,p.next);
                    size++;
                    break;
                }
                if(p.next.next==null){
                    p.next.next = new SLLNode(v,null);
                    size++;
                    break;
                }
            }
        }        
    }
    public void remove(int v){
        if(isIn(v) == true)
        {            
            for(SLLNode p=head; p.next!=null;p=p.next){
                if(p.next.value == v){
                    
                    p.next = p.next.next;
                    size--;
                    break;
                }
            }
        }
        
    }
    public SLLSet intersection(SLLSet s){
        int i=0;
        for(SLLNode p=head.next;p!=null;p=p.next){
            if(s.isIn(p.value) == true){
                i++;
            }
        }
        if(i==0){
            return new SLLSet();
        }
        int[] a = new int[i];
        i = 0;
        for(SLLNode p=head.next;p!=null;p=p.next){
            if(s.isIn(p.value) == true){
                a[i++] = p.value;
            }
        }
        
        return new SLLSet(a);
    }
    public SLLSet union(SLLSet s){
       SLLSet u = s.copy();
       for(SLLNode p=head.next;p!=null;p=p.next){
           u.add(p.value);
       }
       return u;
    }
    public SLLSet difference(SLLSet s){
       SLLSet d = copy();
       for(SLLNode q=s.head.next;q!=null;q=q.next){
           d.remove(q.value);
       }
       return d;
    }
    public static SLLSet union(SLLSet[] sArray){
        SLLSet s = sArray[0].copy();
        if(sArray.length>1){
            for(int i=1;i<sArray.length;i++){
                s = s.union(sArray[i]);
            }                
        }
        return s;     
    }
    public String toString(){
        String s = "";
        for(SLLNode p=head.next;p!=null;p=p.next){
            s+=p.value+", ";
        }
        return s;
    }
    
}
