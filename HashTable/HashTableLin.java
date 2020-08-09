
package pkg2si4_lab4_hashtables;
public class HashTableLin {
    private int size;
    private int numKeys;
    private double loadfactor;
    private int[] table;
    
    public HashTableLin(int maxNum, double load){
        this.numKeys = 0;
        this.size = findClosestPrime(maxNum/load);
        this.loadfactor = load; //hash table should not have more than Totalsize*load elements stored
        this.table = new int[this.size];        
    }    
    
    private int findClosestPrime(double initial){
        int current;
        if(Math.abs(initial - (int)initial) < 1.0/10000)//essentially an int with floating point offset
            current = (int)initial;
        else
            current = (int)initial + 1;          
        if (current == 1 || current == 2)
            return current;
        if(current == 0)
            return 1;
        while(notPrime(current))
            current++;
        return current;
    }
    private boolean notPrime(int n){
        for(int i=2; i<=n/2; i++){
            if(n%i == 0)
                return true;
        }        
        return false;
    }
    
        //HashFunction
    private int h(int x){
        return x%this.size;
    }    
    
    public void insert(int n){
        int index = insertLocation(n);
        if(index == -1)//already in table
            return;
        if((this.numKeys + 1)/((double)this.size) > this.loadfactor){//does adding an element put the load factor above threshold?
            System.out.println("Rehashing...");
            this.rehash();
            index = insertLocation(n);
        }
        this.numKeys++;
        this.table[index] = n;
    }
    
    private int insertLocation(int n){//returns -1 if already in table
        int index = h(n);
        while(this.table[index] != 0){//while spots are full
            if(this.table[index] == n)
                return -1;
            index = (index + 1)%this.size;
        }
        return index;
    }
    
    public boolean isIn(int n){
        return insertLocation(n) == -1;
    }
    
    public void printKeys(){
        for(int i=0;i<this.size;i++){
            if(this.table[i] > 0)
                System.out.println(this.table[i]);
        }
    }
    
    public double getMaxLoadFactor(){
        return this.loadfactor;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public int getNumKeys(){
        return this.numKeys;
    }
    
    public int[] getTable(){
        return this.table;
    }
    
    public int insertCount(int n){
        this.insert(n);
        return countProbesToFind(n);//count how many probes to find or not find an element, and also insert
    }
    
    private int countProbesToFind(int n){
        int index = h(n);
        int probes = 0;
        while(this.table[index] != 0){//while spots are full
            probes++;
            if(this.table[index] == n)
                return probes;
            index = (index + 1)%this.size;
        }
        return -1;
    }
    
    public int countProbesforUnsuccessful(int n){
        int index = h(n);
        int probes = 1;
        while(this.table[index] != 0){
            probes++;
            if(this.table[index] == n)//item is in there, ie successful find
                return -1;
            index = (index+1)%this.size;
        }
        return probes;
    }
    
    private void rehash(){
        this.size = findClosestPrime(this.size * 2);
        this.numKeys = 0;
        int[] oldTable = this.table;
        this.table = new int[this.size];
        for(int i=0; i<oldTable.length; i++){
            if(oldTable[i] > 0)//positive integer
                this.insert(oldTable[i]);
        }
    }
}
