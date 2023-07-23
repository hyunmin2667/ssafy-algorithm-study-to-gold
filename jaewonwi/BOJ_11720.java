import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		String numStr = sc.next();
		
		char[] chArr = numStr.toCharArray();
		
		int sum = 0;
		for (int i = 0; i < chArr.length; i++) {
			sum += chArr[i] - '0';
		}
		System.out.println(sum);
	}

}