import java.util.Iterator;
import java.util.Scanner;

class Max_And_Number {

	
	// Hen Shiryon
	// id-208469418
	// Ex 2
	public static void main(String[] args) {
		System.out.println("enter two numbers at the same length");
	    Scanner scanner = new Scanner(System.in);
		int num1=scanner.nextInt();
		int num2=scanner.nextInt();
		scanner.close();
		int res=0;
		int loction_in_number=1;
		// running of the two number and building the new number according
		// to the instructions
		while (num1>0)
		{
			
		    for (int i = 0; i < num1%10; i++)
		    {
		    	res+=num2%10*loction_in_number;
		    	loction_in_number*=10;
		    }
		   
		    num1/=10;
		    num2/=10;
		    
			
		
		}
		// counting how much digits there is in the new number
		    int test_new_num=res;
		    int countdigtis=0;
		    while (test_new_num>0) 
		    {
				countdigtis++;
				test_new_num/=10;
			
				
			}
		    if (countdigtis>9 || res<0) 
		    {
				System.out.println("the new number is out of 9 digits range");
				
			}
		    else 
		     System.out.println("the new nubmer is:"+res);

	}

}
