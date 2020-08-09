package HugeIntegerPackage;

public class HugeInteger_string {
    String stringDecimal;
    int sign, length;
    
    public HugeInteger_string(String s){        
        for(int i=0;i<s.length();i++){
            if (!(Character.isDigit(s.charAt(i)) || s.charAt(i) == ('-'))){
                //throw exception
            }
        }
        this.sign = (s.startsWith("-"))?-1:1;
        if(this.sign == -1){
            this.stringDecimal = new String(s.substring(1));
        }else{
            this.stringDecimal = new String(s);
        }
        this.length = this.stringDecimal.length();
        //System.out.println(this.stringDecimal);
    }//huge integer exactly as inputed
    public HugeInteger_string(int n){
        if(n<=0){
            //throw exception
        }
        this.stringDecimal = Integer.toString((int)(Math.random()*9+1));
        for(int i=0; i<n-1; i++){
            this.stringDecimal += Integer.toString((int)(Math.random()*10));
        }
        this.length = n;
        this.sign = 1;
        //System.out.println(this.stringDecimal);
    }//random huge integer of size n and sign +
    public HugeInteger_string(int n,int sign){
        this(n);
        this.sign = sign;
        
    }//random hug integer of size n and inputed sign
    
    public HugeInteger_string add(HugeInteger_string h){
        int bigger = this.compareTo(h);                
        if(this.sign == 1 && h.sign == 1){//both positive continue with addition
            return HugeInteger_string.add(this,h);
        }
        if(this.sign == 1 && h.sign == -1){//h negative proceed with caution
            if(bigger == 1){
                return HugeInteger_string.subtract(this, h);
            }
            HugeInteger_string e = HugeInteger_string.subtract(h, this);
            e.sign = -1;
            return e;
        }
        if(this.sign == -1 && h.sign == 1){//this negative proceed with caution
            if(bigger == 1){
                HugeInteger_string e  = HugeInteger_string.subtract(this, h);
                e.sign = -1;
                return e;
            }
            return HugeInteger_string.subtract(h, this);
        }
        if(this.sign == -1 && h.sign == -1){//both negative continue with addition take note of sign
            HugeInteger_string e  = HugeInteger_string.add(this,h);
            e.sign = -1;
            return e;
        }
        return null;
    }    
    private static HugeInteger_string add(HugeInteger_string h1, HugeInteger_string h2){
        int digit1, digit2, sum, carry = 0;
        String n = "";
        for(int i=0;i<(h1.length > h2.length ? h1.length : h2.length); i++){//loops as long as the longest number
            if(i>=h2.length){//h has run out of digits
                digit2 = 0;
            }else{
                digit2 = Character.getNumericValue(h2.stringDecimal.charAt(h2.length - i - 1));
            }
            if(i>=h1.length){//h has run out of digits
                digit1 = 0;
            }else{
                digit1 = Character.getNumericValue(h1.stringDecimal.charAt(h1.length - i - 1));
            }
            sum = digit1 + digit2 + carry;
            if(sum>9){//checks for sum > 9 ie: carry is needed
                sum-=10;
                carry = 1;
            }else{
                carry = 0;
            }
            n = Integer.toString(sum) + n;
        }
        if(carry == 1){//extra end caryy if needed
            n = Integer.toString(carry) + n;
        }
        return new HugeInteger_string(n);        
    }
    
    public HugeInteger_string subtract(HugeInteger_string h){
        int bigger = this.compareTo(h);
        if(this.sign == 1 && h.sign == 1){//both positive
            if(bigger == 1 || bigger == 0){//this is bigger
               return HugeInteger_string.subtract(this,h);
            }
            HugeInteger_string e = HugeInteger_string.subtract(h,this);//swap order
            e.sign = -1;
            return e;
        }
        if(this.sign == 1 && h.sign == -1){//h negative
            return HugeInteger_string.add(this,h);
        }
        if(this.sign == -1 && h.sign == 1){//this negative
            HugeInteger_string e = HugeInteger_string.add(this, h);
            e.sign = -1;
            return e;
        }
        if(this.sign == -1 && h.sign == -1){//both negative
            if(bigger == 1){
                HugeInteger_string e = HugeInteger_string.subtract(this, h);
                e.sign = -1;
                return e;
            }
            return HugeInteger_string.subtract(this, h);
        }
        return null;
    }
    private static HugeInteger_string subtract(HugeInteger_string h1, HugeInteger_string h2){
        //assumption is big - small, this assumption is handled in public subtract() method
        int digit1,digit2,borrow = 0,temp;
        String n = "";
        for(int i=0; i<h1.length; i++){//loops as long as the largest integer
            digit1 = Character.getNumericValue(h1.stringDecimal.charAt(h1.length - i - 1));
            if(h2.length <= i){//h2 has run out of letters
                digit2 = 0;
            }else{
                digit2 = Character.getNumericValue(h2.stringDecimal.charAt(h2.length - i - 1));
            }
            temp = digit1 - digit2 - borrow;
            if(temp<0){//fix result if gone negative
                temp = 10 + temp;
            }
            if(digit1 < digit2 + borrow){//need to borrow
                borrow = 1;
                digit1 += 10;
            }else{
                borrow = 0;
            }
            n = Integer.toString(temp) + n;
        }
        HugeInteger_string e = new HugeInteger_string(n);
        e.removeLeadingZeroes();
        return e;
    }
    
    public void removeLeadingZeroes(){
        int i = 0;
        while(this.stringDecimal.charAt(i++) == '0');//while still leading zeroes   
        this.stringDecimal = this.stringDecimal.substring(i-1);
    }
    
    public int compareTo(HugeInteger_string h){
        if(this.length > h.length || (this.sign == 1 && h.sign == -1)){
            return 1;
        }
        if(this.length < h.length || (this.sign == -1 && h.sign == 1)){
            return -1;
        }
        //equal in length check each number individually
        for(int i=0; i<this.length; i++){
            if(Character.getNumericValue(this.stringDecimal.charAt(i)) > Character.getNumericValue(h.stringDecimal.charAt(i))){//this is greater than h
                if(this.sign == 1){
                    return 1;
                }
                return -1;
            }
            if(Character.getNumericValue(this.stringDecimal.charAt(i)) < Character.getNumericValue(h.stringDecimal.charAt(i))){//this is smaller than h
                if(this.sign == 1){
                    return -1;
                }
                return 1;
            }
        }
        return 0;//they are the same
    }
    
//    public int multiply(HugeInteger_string h){
//        int carry;
//        int offset;
//        int partialProd;
//        for(int i=0;i<h.length;i++){
//            carry = 0;
//            offset = i;
//            for(int j=0;j<this.length;j++){
//                partialProd = Character.getNumericValue(h.stringDecimal.charAt(h.length - i - 1)) * Character.getNumericValue(this.stringDecimal.charAt(this.length - j - 1));
//                carry = partialProd/10;//integer division
//                partialProd = partialProd % 10;
//            }
//                
//        }
//    }
    
    public String toString(){
        if(sign == -1){
            return new String("-" + this.stringDecimal);
        }
        return new String(stringDecimal);
    }
}