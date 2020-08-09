package HugeIntegerPackage;


public class HugeInteger {
    
    public int length;
    public boolean positive;
    public int[] bigNum;
    
    
    public HugeInteger(String s)throws IllegalArgumentException { 
        s = removeLeadingZeroes(s);
        this.length = s.startsWith("-")? s.length()-1 : s.length();
        this.positive = !s.startsWith("-");
        this.bigNum = new int[this.length];
        int offset = (this.positive? 0 : 1);
        if(this.length == 0){
            throw new IllegalArgumentException("Invalid Input - Nothing Inputted");
        }
        for(int i = 0 ; i < this.length ; i++){
            if(!Character.isDigit(s.charAt(i+offset))){
                throw new IllegalArgumentException("Invalid Input - NaN");
            }            
            this.bigNum[i] = Character.getNumericValue(s.charAt(i+offset));
        }
    }
    
    public HugeInteger(String s, boolean b)throws IllegalArgumentException{
        this.length = s.startsWith("-")? s.length()-1 : s.length();
        this.positive = !s.startsWith("-");
        this.bigNum = new int[this.length];
        int offset = (this.positive? 0 : 1);
        if(this.length == 0){
            throw new IllegalArgumentException("Invalid Input - Nothing Inputted");
        }
        for(int i = 0 ; i < this.length ; i++){
            if(!Character.isDigit(s.charAt(i+offset))){
                throw new IllegalArgumentException("Invalid Input - NaN");
            }            
            this.bigNum[i] = Character.getNumericValue(s.charAt(i+offset));
        }
    }
    
    public HugeInteger(int n) throws IllegalArgumentException {
        this.length = n;
        this.positive = true;
        if(n<=0){
            throw new IllegalArgumentException("Invalid Input - n <= 0");
        }     
        this.bigNum = new int[n];
        this.bigNum[0] = (int)(Math.random()*9 + 1);
        for(int i=1;i<n;i++){
            this.bigNum[i] = (int)(Math.random()*10);
        }
    }
    
    public HugeInteger(int n,boolean b){
        this(n);
        this.positive = (Math.random()*2)>=1;
    }
    
    public HugeInteger add(HugeInteger h){        
        if(this.isPos() && h.isPos()){//both positive continue with addition
            return HugeInteger.add2(this,h);
        }
        if(this.isNeg() && h.isNeg()){//both negative continue with addition take note of positive
            HugeInteger n  = HugeInteger.add2(this,h);
            n.positive = false;
            return n;
        }        
        int bigger = this.compareMag(h);
        if(this.isPos() && h.isNeg()){//h negative proceed with caution
            if(bigger == 1 || bigger == 0){
                return HugeInteger.subtract2(this, h);
            }
            HugeInteger n = HugeInteger.subtract2(h, this);
            n.positive = false;
            return n;
        }
        if(this.isNeg() && h.isPos()){//this negative proceed with caution
            if(bigger == 1){
                HugeInteger n  = HugeInteger.subtract2(this, h);
                n.positive = false;
                return n;
            }
            return HugeInteger.subtract2(h, this);
        }
        return null;
    }    
    private static HugeInteger add2(HugeInteger a, HugeInteger b){
        String sum = "";
        int d1,d2,partialSum,carry = 0;
        for(int i=0;i<((a.length > b.length)? a.length : b.length);i++){//loop most amount needed
            d1 = ((a.length > i)?a.bigNum[a.length - i - 1]:0);//indexes right to left, if no digit nxists result is 0
            d2 = ((b.length > i)?b.bigNum[b.length - i - 1]:0);            
            partialSum = d1 + d2 + carry;
            sum = partialSum % 10 + sum;
            carry = partialSum/10; //integer division            
        }
        if(carry>0)
            sum = 1 + sum;//remember sum is a string so 1 is added to front of string
        return new HugeInteger(sum);        
    }
    
    public HugeInteger subtract(HugeInteger h){
        if(this.isPos() && h.isNeg()){//h negative
            return HugeInteger.add2(this,h);
        }
        if(this.isNeg() && h.isPos()){//this negative
            HugeInteger n = HugeInteger.add2(this, h);
            n.positive = false;
            return n;
        }     
        int bigger = this.compareMag(h);        
        if(this.isNeg() && h.isNeg()){//both negative
            if(bigger == 1){
                HugeInteger n = HugeInteger.subtract2(this, h);
                n.positive = false;
                return n;
            }
            return HugeInteger.subtract2(h, this);
        }
        if(this.isPos() && h.isPos()){//both positive
            if(bigger == 1 || bigger == 0){//this is bigger
               return HugeInteger.subtract2(this,h);
            }
            HugeInteger n = HugeInteger.subtract2(h,this);//swap order
            n.positive = false;
            return n;
        }   
        return null;
    }
    private static HugeInteger subtract2(HugeInteger a, HugeInteger b){
        int d1,d2,borrow = 0, partialDiff,temp;
        String diff = "";
        for(int i=0;i<a.length;i++){//a is longer by default
            d1 = a.bigNum[a.length - i -1];            
            d2 = (b.length>i)? b.bigNum[b.length - i - 1]:0;//may run out of digits            
            partialDiff = d1 - d2 - borrow;
            borrow = (partialDiff < 0)? 1 : 0;//borrow if current diff less than 0  
            diff = ((partialDiff < 0)? 10 + partialDiff : partialDiff) + diff;
        }
        return new HugeInteger(HugeInteger.removeLeadingZeroes(diff));
    } 
    
    public HugeInteger multiply(HugeInteger h){
        int rawProduct, carry;
        String partialProduct = "";
        HugeInteger product = new HugeInteger("0");        
        for(int i=0;i<this.length;i++){
            partialProduct = "";
            carry = 0;
            for(int z=0; z<i; z++){partialProduct += "0";}
            for(int j=0;j<h.length;j++){
                rawProduct = this.bigNum[this.length - i - 1] * h.bigNum[h.length - j - 1];                
                partialProduct = (rawProduct + carry) % 10 + partialProduct;
                carry = (rawProduct + carry)/10;
            }
            partialProduct = carry + partialProduct;
            product = HugeInteger.add2(new HugeInteger(HugeInteger.removeLeadingZeroes(partialProduct)), product);            

        }
        product.positive = (this.positive != h.positive) && (this.isNeg() || h.isNeg())? false : true;
        
        return product;
    }    
    public HugeInteger divide(HugeInteger h)throws IllegalArgumentException{    
        HugeInteger zero = new HugeInteger("0");
        HugeInteger one = new HugeInteger("1");
        HugeInteger count = new HugeInteger("0");
        if (h.length == 1 && h.bigNum[0] == 0){
            throw new IllegalArgumentException("Invalid Input - Division by zero");
        }
        if (this.compareMag(h) < 0)
            return new HugeInteger("0");
        HugeInteger partialDiv = new HugeInteger(this.toString());
        HugeInteger k = new HugeInteger(h.toString());
        k.positive = true;
        partialDiv.positive = true;
        while(partialDiv.compareTo(zero) >= 0){
            count = count.add(one);
            partialDiv = partialDiv.subtract(k);
        } 
        count = count.subtract(one);
        count.positive = (this.positive != h.positive) && (this.isNeg() || h.isNeg())? false : true;
        return count;
    }
    
    private static String removeLeadingZeroes(String s){
        int i = 0;
        while(s.charAt(i) == '0'){
            i++;
            if(s.length() == i){
                i--;
                break;
            }
        }
        return s.substring(i);
    }    
    public int compareMag(HugeInteger h){
        if(this.length > h.length)
            return 1;
        if(this.length < h.length)
            return -1;
        for(int i=0;i<this.length;i++){//same length
            if(this.bigNum[i] > h.bigNum[i])
                return 1;
            if(this.bigNum[i] < h.bigNum[i])
                return -1;
        }
        return 0;//they are the same
    } 
    public int compareTo(HugeInteger h){
        if(this.isPos() && h.isNeg()){
            return 1;
        }else if(this.isNeg() && h.isPos()){
            return -1;
        }else{
            return compareMag(h) * (this.positive?1:-1);
        }       
    }
    
    public boolean isNeg(){
        return !this.positive;
    }
    public boolean isPos(){
         return this.positive;
     }
    
    public int toInt(){//for small HugeIntegers only -- testing purposes
        return Integer.parseInt(this.toString());
    }    
    public String toString(){
        String n = "";
        if(!this.positive)
            n += "-";      
        for(int i=0;i<this.length;i++)
            n += this.bigNum[i];
        return n;
    }      
    
}
