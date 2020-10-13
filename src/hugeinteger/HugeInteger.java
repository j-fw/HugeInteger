

package hugeinteger;
import java.util.Random;
import java.util.Arrays;
//import java.util.Random;
/**
 *
 * @author jack
 */

public class HugeInteger {
    int n1[];
    //Constructor 1
    //copies string num to array, with the first digit denoting pos or neg, 1 and 0 respectivily
    HugeInteger(String val) throws IllegalArgumentException {
        if(val.charAt(0)=='-'){
            n1 = new int[val.length()];//if neg then array is proper size already
            n1[0]=0;  
        }
        else{
            n1 = new int[val.length()+1];//if pos then 1 bigger needed for sign digit at beginning
            n1[0]=1;
        }  
        for(int j = 1; j<n1.length; j++){
            if((val.charAt(j-n1[0])-48)<0 || (val.charAt(j-n1[0])-48)>9){
                throw new IllegalArgumentException("Number must only contain digits from 0 to 9");
            }
            n1[j] = val.charAt(j-n1[0])-48;//converts from ascii to decimal
        } 
    }
    
    //Constructor 2
    public HugeInteger(int n)throws IllegalArgumentException{
        if(n<1){
            throw new IllegalArgumentException("Must enter number > 0");
        }
        n1 = new int[n+1];
        Random rand = new Random();
        n1[0] = (int)(Math.round(Math.random())); //sets the first num randomly to 1 or 0 denoting positive or negative respectivly
        for(int j = 1; j<n+1; j++){
            n1[j] = (int)rand.nextInt(10);//generates random numbers from 0 to 9
            if(n1[1]==0){
                n1[j] = (int)rand.nextInt(9)+1;
            } 
        }
    }
    
    //Constructor 3
    public HugeInteger(int[] n)throws IllegalArgumentException{//used to construct a new huge integer from a passed array
        int j = 1;
        if(n[0]>1 || n[0]<0){
                throw new IllegalArgumentException("Invalid input at index: " + 0 + ": " + n[0] + ", first digit must be either 0 or 1 indicating neg or pos respectively");
            }
        while(j<n.length-1 && n[j]==0 ){//counts how many zeros after the first there are
            if(n[j]>10 || n[j]<0){
                throw new IllegalArgumentException("Invalid input at index: " + (j) + ": " + n[j] + ", each index in array must be between 0 and 9 inclusive");
            }
            j++;
        }
        j-=1;
        n1 = new int[n.length-j];
        for(int i = j; i<n.length-1;i++){//copies the array minus the leading zeros after the first
            if(n[i+1]>9 || n[i+1]<0){
                throw new IllegalArgumentException("Invalid input at index: " + (i+1) + ": " + n[i+1] + ", each index in array must be between 0 and 9 inclusive");
            }
            n1[i-j+1]=n[i+1];
        }
        n1[0] = n[0];
    }
    
    public HugeInteger(int[] n, int num){
        n1 = new int[n.length+1];
        n1[0] = 1;
        for(int j = 0; j<n.length; j++){
            n1[j+1] = n[j];
        }
    }
    
    public int compareTo(HugeInteger h){//returns the largest hugeinteger
        
        if(n1[0]==1&&h.n1[0]==0){//first checks if one is pos and one is neg
            return 1;
        }
        else if(n1[0]==0 && h.n1[0]==1){
            return -1;
        }
        else if(n1.length!=h.n1.length){//if the arrays are different size and the pos/neg conditions are met
            return ((n1.length>h.n1.length && n1[0]==1)||(n1.length<h.n1.length && n1[0]==0))?1:-1;
        }//if n1 is larger and pos or smaller and neg
        else if(n1.length==h.n1.length){//if lengths of arrays are equal loops through both arrays to find which is bigger
            for(int j = 0; j<n1.length;j++){
                if(n1[j]>h.n1[j] && n1[0]==1 || n1[j]<h.n1[j] && n1[0]==0){//if this is bigger and pos or smaller and neg
                    return 1;
                }
                else if(h.n1[j]>n1[j] && h.n1[0]==1 || h.n1[j]<n1[j] && n1[0]==0){//same as this
                    return -1;
                }
            }
            return 0;
        }
        return 1;
    }
    
    public HugeInteger add(HugeInteger h){
        
        if(this.compareTo(new HugeInteger("0"))==0 || h.compareTo(new HugeInteger("0"))==0){
            return (this.compareTo(new HugeInteger("0"))==0)?h:this;
        }
        int[] longer = (n1.length>=h.n1.length)?n1:h.n1;
        int[] shorter = (n1.length<h.n1.length)?n1:h.n1;//finds which number is longer and assigns accordingly
        int diff = longer.length-shorter.length;
        
        //creates new array 1 longer than longest in case new decimal place needed, leading zeros removed later in constructor
        int[] new1 = new int[longer.length+1];
        int carry = 0, j;
        
        if(longer[0]==shorter[0]){//if both numbers have the same sign
            for(j  = longer.length-1; j>diff;j--){//loops for the length of the shorter array but starting at the end of nums
            new1[j+1] = (longer[j]+shorter[j-diff]+carry)%10;//[j+1] bceause new1 is created 1 longer,[j-diff] to avoid index issues   
            
                if(longer[j]+shorter[j-diff]+carry>=10){//changes the carry value depending on whether a carry occurs
                    carry = 1;
                }
                else{
                    carry = 0;
                }
            } 
            for(int i = j; i>0; i--){//loops for the rest of the longer array, simply copying numbers unless a carry is needed
                new1[i+1] = (longer[i]+carry)%10;
                if(longer[i]+carry>=10){
                    carry = 1;
                }
                else{
                    carry = 0;
                } 
            }
            new1[1] = carry;//this is the extra space created at the beginning in case number got larger, carry value is simply copied
            if(longer[0]==0){//if both numbers are negative, new num is nagative and vice-versa
                new1[0]=0;
            }
            else{
                new1[0]=1;
            }
        }
        else{//if numbers have different signs subtraction occurs
            longer = (this.absLargest(h)==this)?this.n1:h.n1;//checks which number has larger absolute value and assigns accordingly
            shorter = (this.absLargest(h)==h)?this.n1:h.n1;
            new1[0] = ((this.absLargest(h)==this && n1[0]==0)||(this.absLargest(h)==h)&&h.n1[0]==0)?0:1;
            
            int borrow = 0;
            for(j = longer.length-1; j> diff; j--){//loops for absolute smallest number starting at 1s position
                new1[j+1] = (longer[j] - shorter[j-diff] + borrow)%10;//same indexing as add
                if(longer[j]-shorter[j-diff]+borrow < 0){//checks to see is new would be below zero, subtracts it from 10 to get value
                    new1[j+1] = 10 + (longer[j] - shorter[j-diff] + borrow);
                    borrow = -1; 
                }
                else{
                    borrow = 0;
                }
            }
            
            for(int i = j; i>0; i--){//loops for rest of absolute largest number 
                new1[i+1] = (longer[i]+borrow)%10;
                if(longer[j]+borrow < 0){
                    new1[i+1] = (10 + (longer[i] + borrow)>=10)?(10 + (longer[i] + borrow))%10:10 + (longer[i] + borrow);
                    borrow = -1;
                }
                else{
                    borrow = 0;
                }
            }
        }
        return new HugeInteger(new1);//uses array constructor to remove leading 0's and to make new HugeInteger
    }
    
    public HugeInteger subtract(HugeInteger h){//uses add function but changes number's sign to get desired outcome
        HugeInteger num2 = new HugeInteger(h.n1);//makes a deep copy of h so as not to alter h itself during operations
        num2.n1[0] = (h.n1[0]==1)?0:1;
        return this.add(num2);
    }

    //multiply function using same "carry" logic used in add function
    public HugeInteger multiply(HugeInteger h){
        if(n1[1]==0 || h.n1[1]==0){//if any numbers are 0 simply returns array of 0's size 2
           return new HugeInteger(new int[2]); 
        }
        //creates new array for numbers to be stored, longest_array.length*2 because that is largest new num could be
        int[] new1 = new int[(h.n1.length>n1.length)?h.n1.length*2:n1.length*2];
        new1[0] = (n1[0]==h.n1[0])?1:0;//sets new sign to be pos or neg depending on if this and h have same sign
        
        int[] longer = (this.absLargest(h)==this)?this.n1:h.n1;
        int[] shorter = (this.absLargest(h)==h)?this.n1:h.n1;//determines absolute largest number and sets that to longest
        
        int carry,term,k,p=0,n,i;
        
        //one array is used to store the new number, the numbers already in positions are read and added to new number being added to...
        //... that deciaml slot, increasing efficiency by requiring one array instead of smaller.length number of arrays
        
        for(i = longer.length-1; i>0; i--){//loops this.length*that.length times
            k = new1.length-1-p++;//used to index which decimal place to start at, starts from 1's to 10's to 100's etc...
            carry=0;
        //resets carry value, very important otherwise carry value from last shorter loop will be used during first new shorter loop
            for(n = shorter.length-1; n>0; n--){
                term = shorter[n]*longer[i] + carry + new1[k];//gets indicies multiplied plus the carry value plus what was already in new
                carry = (term/10);//sets carry to 10s place of term, ie what will be added to the 1's column of next index
                new1[k--] = (term%10);//sets index to 1's place of term, then decreases k
            }
            new1[k]=(carry);//any leftover carry is added to new decimal place 
        }
        return new HugeInteger(new1);
    }
    
    public HugeInteger countUp(HugeInteger h){
        int j = 0;
        HugeInteger num1 = new HugeInteger("0");
        while(num1.compareTo(this)<=0){
            num1 = num1.add(h);
            j++;
        }
        num1 = new HugeInteger(Integer.toString(--j));
        return num1;
    }
    
    //divide function uses logic similar to long division done by hand
    public HugeInteger divide3(HugeInteger h)throws IllegalArgumentException{
        
        if(h.n1[1]==0){
            throw new IllegalArgumentException("Illegal Input: Cannot divide by 0");
        }
        int temp1 = n1[0], temp2 = h.n1[0];
        n1[0]=1; h.n1[0]=1;//stores signs in temp variables then makes both numbers positive for ease of calculations
        if(this.absLargest(h).compareTo(h)==0){
            return (this.compareTo(h)==0)?(temp1==temp2)?new HugeInteger("1"):new HugeInteger("-1"):new HugeInteger("0");
        }//if h >= this, returns -1,1, or 0, depending on signs and size of h
        HugeInteger new1 = new HugeInteger(new int[n1.length - (h.n1.length-1)],1);//creates new array for answer to be stored in
        int i = -1, m = 0, index,k;
        HugeInteger next = new HugeInteger("0");
        HugeInteger shift = new HugeInteger("10");
        
        while(h.compareTo(next.add(new HugeInteger(Arrays.copyOfRange(n1,0,h.n1.length+i))))==1){
                //loops to find the first set of numbers in this that are > h
                i++;
            }
        next = new HugeInteger(Arrays.copyOfRange(n1,0,h.n1.length+i));
        //creates a new hugeinteger > h
        for( k = 0; k<n1.length; k++){
            
            new1.n1[k+1+m] = (next.countUp(h)).n1[1];//calls a function that counts how many times h goes into next, then puts it in new
            //System.out.println(new1.n1[k+1+m] + " " + next.divide(h));
            next = next.subtract(h.multiply(next.countUp(h)));//subtracts h*the answer from previous line 
            index = 0;
            while(next.compareTo(h)<0){//while next is less than h
                if(index>0){
                   //if loop triggers more than once, then more than 1 shift was needed, so put a 0 in the answer for that digit place
                   m++;
                   new1.n1[k+1+m]=0;
                }
                next = next.multiply(shift);
                
                //adds a decimal place to next to make room for next digit to drop down from this, in order to make next>h
                if((h.n1.length+i)>n1.length-1){//if loop has gone through all digits in dividend then we have the answer
                    n1[0] = temp1; h.n1[0] = temp2;//reverts numbers to their original signs in case they need to be reused
                    new1.n1[0] = (n1[0]==h.n1[0])?1:0;
                    if(k+1+m<new1.n1.length-1){//if the number of digits added to the answer is < size of the answer remove 0s at end
                        return new HugeInteger(Arrays.copyOfRange(new1.n1,0,k+2+m));
                    }
                    return new1;
                }
                next = next.add(new HugeInteger(Integer.toString(n1[(h.n1.length+i)])));
                //next.n1[next.n1.length-1] = n1[(h.n1.length+i)];
                //next.n1[0] = 1;
                i++;
                index++;
            }
        }
        n1[0] = temp1; h.n1[0] = temp2;
        return new1;
    }   
    
    public void printArr(int[] arr){//function that prints an array's contents used in testing/debugging
        System.out.println("[ ");
        for(int j = 0; j<arr.length; j++){
           System.out.print(arr[j]); 
        }
        System.out.println(" ]");
    }
    
    public HugeInteger absLargest(HugeInteger h){
        if(n1.length!=h.n1.length){
            return (n1.length>h.n1.length)?this:h;
        }
        else{
            for(int j = 1; j<n1.length; j++){
               if(n1[j]!=h.n1[j]){
                   return (n1[j]>h.n1[j])?this:h;
               } 
            }
            return this;
        }
    }
    
    @Override
    public String toString(){
        String s = new String();
        if(n1[1]==0){//if 0 exists after sign digit then the number is 0
           return "0"; 
        }
        if(n1[0]==0){//prints a - if neg, nothing if pos
            s+="-";
        }
        
        for(int j = 1; j<n1.length;j++){
            s += (char)(n1[j]+48);//converts number to char value and concatenates to string
        }
        return s;
    }
}
