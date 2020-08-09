package HugeIntegerPackage;

import java.math.BigInteger;
import java.util.Random;

public class HugeIntegerTest {

    public static void main(String[] args) {
        boolean test = true; 
        
        if(test){
            System.out.println("Small value test:\n");
            testSmall(1000,100,false);//(# of tests, max int size,show output)
            System.out.println("\nBig value test:\n");
            testLarge(1000,120,false);//(# of tests, maxValue --> 2^(param) -1, show output)
        }
    }
    
    public static void testSmall(long tests,long absMax,boolean display){
        long n, m;
        int add_count = 0, sub_count = 0, mult_count = 0;
        HugeInteger a,b;
        for(int i=0;i<tests;i++){
            n = (long)(Math.random()*absMax*2) - absMax;
            m = (long)(Math.random()*absMax*2) - absMax;
            a = new HugeInteger(Long.toString(n));
            b = new HugeInteger(Long.toString(m));

            if(display)
                System.out.println(a.toInt() + " + " + b.toInt() + " = " + a.add(b));            
            if(a.add(b).toInt() == n + m)                
                add_count ++;
            else
                System.out.println("Test "+ (i+1) + " INCORRECT");
            
            if(display)
                System.out.println(a.toInt() + " - " + b.toInt() + " = " + a.subtract(b));            
            if(a.subtract(b).toInt() == n - m)
                sub_count++;
            else             
                System.out.println("Test "+ (i+1) + " INCORRECT");
            
            if(display)
                System.out.println(a.toInt() + " x " + b.toInt() + " = " + a.multiply(b));            
            if(a.multiply(b).toInt() == n * m)
                mult_count++;
            else             
                System.out.println("Test "+ (i+1) + " INCORRECT");
        }
        System.out.println("addition: "+ add_count + " / " +tests);
        System.out.println("subtraction: "+ sub_count + " / " +tests);
        System.out.println("multiplication: "+ mult_count + " / " +tests);
    }    
    public static void testLarge(long tests, int digits,boolean display){
        BigInteger n , m;
        int add_count = 0, sub_count = 0, mult_count = 0;
        HugeInteger a , b;
        
        for(int i=0;i<tests;i++){
            n = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) n = n.negate();
            m = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) m = m.negate();
            a = new HugeInteger(n.toString());
            b = new HugeInteger(m.toString());
            
            if(display)
                System.out.println(a.toString() + " + " + b.toString() + " = " + a.add(b));            
            if(a.add(b).toString().equals(n.add(m).toString()))                
                add_count ++;
            else
                System.out.println("Test "+ (i+1) + " INCORRECT");
        }
        System.out.println("addition: "+ add_count + " / " +tests);
        
        for(int i=0;i<tests;i++){
            n = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) n = n.negate();
            m = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) m = m.negate();
            a = new HugeInteger(n.toString());
            b = new HugeInteger(m.toString());
            
            if (display)
                System.out.println(a.toString() + " - " + b.toString() + " = " + a.subtract(b));            
            if(a.subtract(b).toString().equals(n.subtract(m).toString()))   
                sub_count++;
            else             
                System.out.println("Test "+ (i+1) + " INCORRECT");
        }
        System.out.println("subtraction: "+ sub_count + " / " +tests); 
        
        
        for(int i=0;i<tests;i++){
            n = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) n = n.negate();
            m = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) m = m.negate();
            a = new HugeInteger(n.toString());
            b = new HugeInteger(m.toString());
            
            if (display)
                System.out.println(a.toString() + " x " + b.toString() + " = " + a.multiply(b));            
            if(a.multiply(b).toString().equals(n.multiply(m).toString()))   
                mult_count++;
            else             
                System.out.println("Test "+ (i+1) + " INCORRECT");
        }
        System.out.println("multiplication: "+ mult_count + " / " +tests);                 
    }
}
