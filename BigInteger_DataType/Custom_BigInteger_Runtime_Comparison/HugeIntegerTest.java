package HugeIntegerPackage;

import java.math.BigInteger;
import java.util.Random;

public class HugeIntegerTest {

    public static void main(String[] args) {
        boolean my_test = false; 
        boolean test_time = false;
        boolean test_time_bigInt = true;
//        HugeInteger n1 = new HugeInteger("12456789123456789");
//        HugeInteger n2 = new HugeInteger("123456789123456789");
//        System.out.println(HugeInteger.kmultiply(n1,n2));
        BigInteger n = new BigInteger(35000,new Random());
        System.out.println(n.toString().length());

        //run for ~48 sec each
        
        if(test_time)
            test_HugeInt_time();            
        if(test_time_bigInt)
            test_BigInt_time();        
        
        if(my_test){
            System.out.println("Small value test:\n");
            testSmall(1000,100,false);//(# of tests, max int size,show output)
            System.out.println("\nBig value test:\n");
            testLarge(1000,120,false);//(# of tests, maxValue --> 2^(param) -1, show output)
        }
    }
    
    public static void testSmall(long tests,long absMax,boolean display){
        long n, m;
        int add_count = 0, sub_count = 0, mult_count = 0, div_count = 0, zero_count = 0;
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
             
            if (m == 0)
                zero_count++;
            else if(m!=0 && a.divide(b).toInt() == (int)(n / m)){
                if(display)
                    System.out.println(a.toInt() + " / " + b.toInt() + " = " + a.divide(b));
                div_count++;  
            }
            else            
                System.out.println("Test "+ (i+1) + " INCORRECT");
            
        }
        System.out.println("addition: "+ add_count + " / " +tests);
        System.out.println("subtraction: "+ sub_count + " / " +tests);
        System.out.println("multiplication: "+ mult_count + " / " +tests);
        System.out.println("division: "+ div_count + " / " +(tests - zero_count));
    }    
    public static void testLarge(long tests, int digits,boolean display){
        BigInteger n , m;
        int add_count = 0, sub_count = 0, mult_count = 0, div_count = 0;
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
        
        
        for(int i=0;i<tests;i++){
            n = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) n = n.negate();
            m = new BigInteger(digits,new Random());
            if(Math.random()*2 < 1) m = m.negate();
            a = new HugeInteger(n.toString());
            b = new HugeInteger(m.toString());
            
            if (display)
                System.out.println(a.toString() + " / " + b.toString() + " = " + a.divide(b));            
            if(a.divide(b).toString().equals(n.divide(m).toString()))   
                div_count++;
            else             
                System.out.println("Test "+ (i+1) + " INCORRECT");
        }
        System.out.println("division: "+ div_count + " / " +tests); 
    }
    
    public static void test_HugeInt_time(){        
        System.out.println("Compare");
        System.out.println(HugeIntTiming.runTiming_compare(10, 5000, 100));
        System.out.println(HugeIntTiming.runTiming_compare(100, 5000, 100));
        System.out.println(HugeIntTiming.runTiming_compare(500, 5000, 100));
        System.out.println(HugeIntTiming.runTiming_compare(1000, 5000, 100));
        System.out.println(HugeIntTiming.runTiming_compare(5000, 5000, 100));
        System.out.println(HugeIntTiming.runTiming_compare(10000, 5000, 100));
        
        System.out.println("Addition");
        System.out.println(HugeIntTiming.runTiming_add(10, 100, 100));
        System.out.println(HugeIntTiming.runTiming_add(100, 100, 100));
        System.out.println(HugeIntTiming.runTiming_add(500, 100, 100));
        System.out.println(HugeIntTiming.runTiming_add(1000, 100, 100));
        System.out.println(HugeIntTiming.runTiming_add(5000, 100, 100));
        System.out.println(HugeIntTiming.runTiming_add(10000, 100, 100));

        System.out.println("Subtraction");
        System.out.println(HugeIntTiming.runTiming_sub(10, 100, 100));
        System.out.println(HugeIntTiming.runTiming_sub(100, 100, 100));
        System.out.println(HugeIntTiming.runTiming_sub(500, 100, 100));
        System.out.println(HugeIntTiming.runTiming_sub(1000, 100, 100));
        System.out.println(HugeIntTiming.runTiming_sub(5000, 100, 100));
        System.out.println(HugeIntTiming.runTiming_sub(10000, 100, 100));

        System.out.println("Multiplication");
        System.out.println(HugeIntTiming.runTiming_multiply(10, 5, 100));
        System.out.println(HugeIntTiming.runTiming_multiply(100, 5, 100));
        System.out.println(HugeIntTiming.runTiming_multiply(500, 5, 100));
        System.out.println(HugeIntTiming.runTiming_multiply(1000, 5, 100));
        System.out.println(HugeIntTiming.runTiming_multiply(5000, 5, 10));
        System.out.println(HugeIntTiming.runTiming_multiply(10000, 5, 1));

        System.out.println("Division");
        System.out.println(HugeIntTiming.runTiming_divide(10, 100, 10));
        System.out.println(HugeIntTiming.runTiming_divide(100, 100, 10));
        System.out.println(HugeIntTiming.runTiming_divide(500, 100, 10));
        System.out.println(HugeIntTiming.runTiming_divide(1000, 100, 10));
        System.out.println(HugeIntTiming.runTiming_divide(5000, 100, 10));
        System.out.println(HugeIntTiming.runTiming_divide(10000, 100, 10));
    }
    
    public static void test_BigInt_time(){
        System.out.println("Compare");
        System.out.println(HugeIntTiming2.runTiming2_compare(30, 10000, 100));//10
        System.out.println(HugeIntTiming2.runTiming2_compare(350, 10000, 100));//100
        System.out.println(HugeIntTiming2.runTiming2_compare(1500, 10000, 100));//500
        System.out.println(HugeIntTiming2.runTiming2_compare(3500, 10000, 100));//1000
        System.out.println(HugeIntTiming2.runTiming2_compare(17000, 10000, 100));//5000
        System.out.println(HugeIntTiming2.runTiming2_compare(35000, 10000, 100));//10000
        
        System.out.println("Addition");
        System.out.println(HugeIntTiming2.runTiming2_add(30, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_add(350, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_add(1500, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_add(3500, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_add(17000, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_add(35000, 10000, 100));

        System.out.println("Subtraction");
        System.out.println(HugeIntTiming2.runTiming2_sub(30, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_sub(350, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_sub(1500, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_sub(3500, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_sub(17000, 10000, 100));
        System.out.println(HugeIntTiming2.runTiming2_sub(35000, 10000, 100));

        System.out.println("Multiplication");
        System.out.println(HugeIntTiming2.runTiming2_multiply(30, 5000, 100));
        System.out.println(HugeIntTiming2.runTiming2_multiply(350, 5000, 100));
        System.out.println(HugeIntTiming2.runTiming2_multiply(1500, 5000, 100));
        System.out.println(HugeIntTiming2.runTiming2_multiply(3500, 5000, 100));
        System.out.println(HugeIntTiming2.runTiming2_multiply(17000, 5000, 10));
        System.out.println(HugeIntTiming2.runTiming2_multiply(35000, 5000, 10));

        System.out.println("Division");
        System.out.println(HugeIntTiming2.runTiming2_divide(30, 10000, 10));
        System.out.println(HugeIntTiming2.runTiming2_divide(350, 10000, 10));
        System.out.println(HugeIntTiming2.runTiming2_divide(1500, 10000, 10));
        System.out.println(HugeIntTiming2.runTiming2_divide(3500, 10000, 10));
        System.out.println(HugeIntTiming2.runTiming2_divide(17000, 10000, 10));
        System.out.println(HugeIntTiming2.runTiming2_divide(35000, 10000, 10));
    }
}
