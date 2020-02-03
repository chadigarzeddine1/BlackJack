
public class CodingChallenge1 {
	
	public static boolean isLowercaseChar(char aChar) {
		return 'a' <= aChar && aChar <= 'z';
		}
	
	
	
	
	public static int firstLowercaseChar(String str) {
		return 0;
		
	}
	
	public static long ceilingOfMultiplication(int num1, double num2) {
		return (long)(num1*num2);
		
	}
	
	public static int addOctalDigits(int num) {
		int sum = 0;
		while(num > 0) {
			sum = sum + num%10;
			num = num/10;
		}
		sum = (sum < 10)?sum : addOctalDigits(sum);
		return sum;
		
	}

}
