
package hugeinteger;
import java.math.BigInteger;
import java.util.Random;
public class Lab_12 {

    public static void main(String[] args) {
        
        //System.out.println(avgRunTime2(10));
        
        HugeInteger num1 = new HugeInteger("1"),num2 = new HugeInteger("9999"),num3 = new HugeInteger("-1"),num4 = num1.add(num2),num5 = new HugeInteger("-500");
        HugeInteger num6 = num1.add(num5), num7 = new HugeInteger("500"), num8 = num3.add(num6), num9 = new HugeInteger("2");
        HugeInteger num10 = new HugeInteger(5), num11 = new HugeInteger(5), num12 = new HugeInteger(5), num13 = new HugeInteger(5);
        HugeInteger num14 = new HugeInteger(new int[]{1,2,3,4}),num15 = new HugeInteger(new int[]{0,5,7,4}),num16 = new HugeInteger(new int[]{0,0,2,3});
        HugeInteger num17 = new HugeInteger("9998"), num20 = new HugeInteger("-21"), num21 = new HugeInteger("-345");
        HugeInteger num18 = new HugeInteger("70891"), num19 = new HugeInteger("70"), num0 = new HugeInteger("0");
        //System.out.println(avgRunTime(4000,10000));
        HugeInteger num30 = new HugeInteger(10000), num31 = new HugeInteger(9999);
        System.out.println(num30.divide3(num31));
        /*
        System.out.println("Testing Constructors (Proper Input) \n/");
        System.out.println("#1-Strings: " + new HugeInteger("500") + ", " + new HugeInteger("-1234"));
        System.out.println("#2-Random: " + num10 + ", " + num11 + ", " + new HugeInteger(10));
        System.out.println("#3-Arrays: " + num14 + ", " + num15 + ", " + num16 + "\n/\n ");
        
        //System.out.println("Testing Constructors (Illegal Input) \n/");
        //System.out.println("#1-Strings: " + new HugeInteger("50a"));
        //System.out.println("#2-Random: " + new HugeInteger(0));
        //System.out.println("#3-Arrays: " + new HugeInteger(new int[]{-1,1}) +   "\n/\n ");
        
        System.out.println("Testing 'compareTo' function\n/");
        System.out.println(num7 + " compared to " + num9 + ": " + num2.compareTo(num9));
        System.out.println(num7 + " compared to " + num2 + ": " + num7.compareTo(num2));
        System.out.println(num7 + " compared to " + num7 + ": " + num7.compareTo(num7));
        System.out.println(num3 + " compared to " + num20 + ": " + num3.compareTo(num20));
        System.out.println(num3 + " compared to " + num1 + ": " + num3.compareTo(num1));
        System.out.println(num2 + " compared to " + num17 + ": " + num2.compareTo(num17) + "\n/\n");
        
        
        System.out.println("Testing 'add' function\n/");
        System.out.println(num1 + " + " + num2 + " = " + num4);
        System.out.println(num3 + " + " + num4 + " = " + num3.add(num4));
        System.out.println(num1 + " + " + num5 + " = " + num6);
        System.out.println(num3 + " + " + num6 + " = " + num8);
        System.out.println(num7 + " + " + num8 + " = " + num7.add(num8));
        System.out.println(num10 + " + " + num11 + " = "+num10.add(num11) +  "\n/\n");
        
        
        System.out.println("\nTesting 'subtract' function\n/");
        System.out.println(num4 + " - " + num1 + " = " + num4.subtract(num1));
        System.out.println(num1 + " - " + num4 + " = " + num1.subtract(num4));
        System.out.println(num7 + " - " + num8 + " = " + num7.subtract(num8));
        System.out.println(num7 + " - " + num7 + " = " + num7.subtract(num7));
        System.out.println(num8 + " - " + num1.subtract(num4) + " = " + num8.subtract(num1.subtract(num4)));
        System.out.println(num12 + " - "  + num13 + " = " + num12.subtract(num13) + "\n/\n");
        
        
        System.out.println("\nTesting 'multiply' function\n/");
        System.out.println(num1 + " * " + num2 + " = " + num1.multiply(num2));
        System.out.println(num3 + " * " + num2 + " = " + num3.multiply(num2));
        System.out.println(num3 + " * " + num3 + " = " + num3.multiply(num3));
        System.out.println(num2 + " * " + num9 + " = " + num2.multiply(num9));
        System.out.println(num2 + " * " + num0 + " = " + num0.multiply(num2) + "\n/\n");
        
        System.out.println("\nTesting 'divide' function\n/");
        System.out.println(num18 + " / " + num19 + " = " + num18.divide3(num19));
        System.out.println(num18 + " / " + num18 + " = " + num18.divide3(num18));
        System.out.println(num19 + " / " + num18 + " = " + num19.divide3(num18));
        System.out.println(num0 + " / " + num18 + " = " + num0.divide3(num18));
        System.out.println(num21 + " / " + num20 + " = " + num21.divide3(num20));
        //System.out.println(num19 + " / " + num0 + " = " + num19.divide3(num0)); //illegal input, dividing by 0
        /*
        */
        
    }
    
    public static double avgRunTime(int n, int k){
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime=0.0;
        int MAXNUMINTS = 100, comp;
        int MAXRUN = k;
        String s = new String();
        String s1 = new String();
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            //System.out.println(numInts);
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                {
                    huge3 = huge1.add(huge2);
                    
                }
            //System.out.println("done");
            endTime = System.currentTimeMillis();
            
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
            System.out.println(endTime-startTime + " " + numInts + " " + runTime/((double) numInts+1));
            
        }
        runTime = runTime/((double)MAXNUMINTS);
        return runTime;
    }
    
    public static BigInteger makeMeBig(int n){
        String val = new String();
        for(int j = 1; j<n+1; j++){
            Random rand = new Random();
            val += (int)rand.nextInt(10);
        }
        BigInteger phatnum = new BigInteger(val);
        phatnum = ((int)(Math.round(Math.random()))==1)?phatnum.negate():phatnum;
        return phatnum;
    }
    
    
    public static double avgRunTime2(int n){
        BigInteger huge1, huge2, huge3;
        int num1;
        long startTime, endTime;
        double runTime=0.0;
        int MAXNUMINTS = 100, comp;
        int MAXRUN = 50000;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = makeMeBig(n);
            huge2 = makeMeBig(n); //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < MAXRUN; numRun++)
                { 
                    huge3 = huge1.subtract(huge2); 
                    //System.out.println(huge3 + " " + numRun);
                }
            endTime = System.currentTimeMillis();
            
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
            System.out.println(endTime-startTime + " " + numInts + " " + (double) (endTime - startTime)/((double) MAXRUN));
            
        }
        runTime = runTime/((double)MAXNUMINTS);
        return runTime;
    }  
}
    

