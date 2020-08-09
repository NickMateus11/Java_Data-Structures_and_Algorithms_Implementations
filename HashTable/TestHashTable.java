
package pkg2si4_lab4_hashtables;

public class TestHashTable {
    private static int NUM = 100000;
    private static int numTests = 100;
    private static double maxMultiplier = 5000;
    
    public static void main(String[] args) {

        HashTableLin h1 = new HashTableLin(10,0.5);
        System.out.println("Total Size: " + h1.getSize());
        System.out.println("Total keys: " + h1.getNumKeys());
        h1.insert(2);
        h1.insert(1);
        h1.insert(23);
        h1.insert(1);
        h1.printKeys();
        System.out.println(h1.isIn(3));
        System.out.println(h1.isIn(23));
        h1.insert(435);
        h1.insert(312);
        h1.insert(654);
        h1.insert(31231);
        h1.insert(43);
        h1.insert(432);
        h1.insert(867);
        h1.insert(8672);
        h1.insert(9084);
        
        //System.out.println(h1.insertCount(43));
        //System.out.println(h1.insertCount(653));
        h1.printKeys();
        System.out.println("Total Size: " + h1.getSize());
        System.out.println("Total keys: " + h1.getNumKeys());

        
        runExperimentalHashing();    
    }
    
    private static void runExperimentalHashing(){
        double load = 0;
        System.out.println("\tLinear Probing\n-----------------------------");
        for(int i=0; i<9; i++){
            load += 0.1;
            runProbingAverager(load);
        }
        System.out.println("\n\tQuadratic Probing\n-----------------------------");
        load = 0;
        for(int i=0; i<9; i++){
            load += 0.1;
            runProbingAverager_Quad(load);
        }
        System.out.println("\n\tBONUS\n-----------------------------");
        load = 0;
        for(int i=0; i<9; i++){
            load += 0.1;
            runUnsuccessfulAverager(load);           
        }
    }
    
    private static void runProbingAverager(double load){        
        HashTableLin h;
        int numElements, probes;
        double avg_probes = 0;
        for(int i=0;i<numTests;i++){
            h = new HashTableLin(NUM, load);
            numElements = 0;
            probes = 0;
            for(int k=0; k<NUM; k++){//fill hash table
                h.insert((int)(Math.random()*(NUM*maxMultiplier)+1));         
            }

            for(int j=0;j<h.getSize(); j++){//go through table looking for non zero elements
                if(h.getTable()[j]!=0){//currently at an element
                    numElements++;
                    probes += h.insertCount(h.getTable()[j]);//count number of probes to find
                }
            }
            avg_probes += ((double)probes)/numElements;            
        }
        avg_probes /= numTests;
        double actual = 1.0/2 * (1+1.0/(1-load));
        System.out.println("Average probes required for lambda="+(double)((int)(load*10+0.5))/10+": "+avg_probes+"\t Theoretical: "+actual);
    }
    
    private static void runProbingAverager_Quad(double load){
        HashTableQuad h;        
        int numElements, probes;
        double avg_probes = 0;
        for(int i=0;i<numTests;i++){
            h = new HashTableQuad(NUM, load);        
            numElements = 0;
            probes = 0;
            for(int k=0; k<NUM; k++){//fill hash table
                h.insert((int)(Math.random()*(NUM*maxMultiplier)+1));         
            }
            
            for(int j=0;j<h.getSize(); j++){//go through table looking for non zero elements
                if(h.getTable()[j]!=0){//currently at an element
                    numElements++;
                    probes += h.insertCount(h.getTable()[j]);//count number of probes to find
                }
            }
            avg_probes += ((double)probes)/numElements;            
        }
        avg_probes /= numTests;
        double actual = 1/load * Math.log(1/(1-load));
        System.out.println("Average (quadratic) probes required for lambda="+(double)((int)(load*10+0.5))/10+": "+avg_probes+"\t Theoretical: "+actual);        
    }
    
    private static void runUnsuccessfulAverager(double load){
        HashTableLin h;
        int probes;
        double avg_probes = 0;
        int randomNum, probestoUnsuccess, unsuccessfulCount;
        
        for(int i=0;i<numTests;i++){
            h = new HashTableLin(NUM, load);
            probes = 0;
            unsuccessfulCount = 0;
            for(int k=0; k<NUM; k++){//fill hash table
                h.insert((int)(Math.random()*(NUM*maxMultiplier)+1));         
            }                 
            
            for(int j=0;j<NUM; j++){//run same number of times as total num of keys
                randomNum = (int)(Math.random()*(NUM*maxMultiplier)+1);
                probestoUnsuccess = h.countProbesforUnsuccessful(randomNum);
                if(probestoUnsuccess!= -1){
                    unsuccessfulCount++;
                    probes+=probestoUnsuccess;
                }
            }
            avg_probes += ((double)probes)/unsuccessfulCount;            
        }
        avg_probes /= numTests;
        double actual = 1.0/2 * (1+1.0/((1-load)*(1-load)));
        System.out.println("Average probes until UNSUCCESSFUL for lambda="+(double)((int)(load*10+0.5))/10+": "+avg_probes+"\t Theoretical: "+actual);
    }    
}
