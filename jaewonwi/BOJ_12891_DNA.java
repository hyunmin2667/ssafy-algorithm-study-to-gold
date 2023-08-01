import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// S와 P 입력
		int S = sc.nextInt();
		int P = sc.nextInt();
		
		// 받아온 문자열 => 캐릭터 배열
		String str = sc.next();
		char[] arr = str.toCharArray();
		
		// acgt 각 최소 포함 갯수
		int[] fixed = new int[4];
		int[] checking = new int[4];
		for (int i = 0; i < 4; i++) {
			fixed[i] = sc.nextInt();	// 최소 포함 갯수 배열
			checking[i] = fixed[i];		// 순회하면서 체크할 용도의 배열
		}

		boolean possible = true;	// 비밀번호 가능한지
		int count = 0;		// 가능한 비밀번호 갯수
	
		int start = 0;
		int end = P - 1;
		while (end < S) {
			for (int i = start; i <= end; i++) {
				if (arr[i] == 'A') {
					checking[0]--;
//					System.out.println(checking[0] + " " + arr[i]);
				} else if (arr[i] == 'C') {
					checking[1]--;
//					System.out.println(checking[1] + " " + arr[i]);
				} else if (arr[i] == 'G') {
					checking[2]--;
//					System.out.println(checking[2] + " " + arr[i]);
				} else if (arr[i] == 'T') {
					checking[3]--;
//					System.out.println(checking[3] + " " + arr[i]);
				}
			}
			
			// 최소갯수 만족 못하면 비밀번호가 될 수 없다.
			for (int check : checking) {
				if (check > 0)
					possible = false;
			}
			// 비밀번호가 가능하면 카운트
			if (possible == true) count++;
			
			// 한칸 뒤부터 다시 순회하기 위한 재설정
			start++;
			end++;
			checking = Arrays.copyOf(fixed, 4);
			possible = true;
		}
		System.out.println(count);
		
		
		
		
	}
}