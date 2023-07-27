import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int S = sc.nextInt();
		int P = sc.nextInt();
		
		String str = sc.next();
		char[] arr = str.toCharArray();
		
		int[] acgt = new int[4];
		for (int i = 0; i < 4; i++) {
			acgt[i] = sc.nextInt();
		}
		int[] fixed	= acgt;

		boolean possible = true;
		int pwd = 0;
	
		int start = 0;
		int end = P - 1;
		while (end < S) {
			for (int i = start; i <= end; i++) {
				if (arr[i] == 'A') acgt[0]--;
				else if (arr[i] == 'C') acgt[1]--;
				else if (arr[i] == 'G') acgt[2]--;
				else if (arr[i] == 'T') acgt[3]--;
			}
			
			for (int i = 0; i < 4; i++) {
				if (acgt[i] > 0) {
					possible = false;
					System.out.println("no!"+Arrays.toString(acgt));
					break;
				}
			}
			
			if (possible == true) {
				pwd++;
				System.out.println(Arrays.toString(acgt));
			}
			start++;
			end++;
			for (int i = 0; i < 4; i++) {
				acgt[ie]
			}
			possible = true;
		}
		System.out.println(pwd);
		
		
		
		
	}
}