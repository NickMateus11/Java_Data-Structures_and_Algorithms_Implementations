package HugeIntegerPackage;

public class HugeIntTiming{
    static int MAXNUMINTS = 100;
    static int MAXRUN = 500;
    
    public static double runTiming_compare(int n, int MAXRUN, int MAXNUMINTS){
        HugeInteger huge1, huge2;
        int result;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0;numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                { result = huge1.compareTo(huge2); }
            endTime = System.currentTimeMillis();
            //System.out.println(endTime - startTime);
            runTime += (double)(endTime - startTime) / ((double) MAXRUN);
        }
        
        runTime = runTime / (double)(MAXNUMINTS);
        return runTime;
    }
    
    public static double runTiming_add(int n, int MAXRUN, int MAXNUMINTS){
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0;numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                { huge3 = huge1.add(huge2); }
            endTime = System.currentTimeMillis();
            //System.out.println(endTime - startTime);
            runTime += (double)(endTime - startTime) / ((double) MAXRUN);
        }
        
        runTime = runTime / (double)(MAXNUMINTS);
        return runTime;
    }
    
    public static double runTiming_sub(int n, int MAXRUN, int MAXNUMINTS){
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0;numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                {huge3 = huge1.subtract(huge2);}
            endTime = System.currentTimeMillis();
            //System.out.println(endTime - startTime);
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        
        runTime = runTime/(double)(MAXNUMINTS);
        return runTime;
    }
    
    public static double runTiming_multiply(int n, int MAXRUN, int MAXNUMINTS){
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0;numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                {huge3 = huge1.multiply(huge2);}
            endTime = System.currentTimeMillis();
            //System.out.println(endTime - startTime);
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        
        runTime = runTime/(double)(MAXNUMINTS);
        return runTime;
    }
    
    public static double runTiming_divide(int n, int MAXRUN, int MAXNUMINTS){
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0;numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
//            System.out.println(huge1);
//            System.out.println(huge2);
            for(int numRun=0; numRun < MAXRUN; numRun++)
                {huge3 = huge1.divide(huge2);}
            endTime = System.currentTimeMillis();
            //System.out.println(endTime - startTime);
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        
        runTime = runTime/(double)(MAXNUMINTS);
        return runTime;
    }
    
}

