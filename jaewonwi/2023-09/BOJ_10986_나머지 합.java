
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		// 값 입력
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 바로 구간 합 배열 생성
		long[] S = new long[N];
		S[0] = sc.nextInt();
		for (int i = 1; i < N; i++) {
			S[i] = S[i-1] + sc.nextInt();
		}
		
		// 나머지가 같은 인덱스의 개수 카운팅을 위한 배열
		long[] C = new long[M];
		
		// 구간 카운트
		long count = 0;
		for (int i = 0; i < N; i++) {
			int remain = (int)(S[i] % M);
			if (remain == 0) count++;
			// 나머지가 같은 인덱스의 개수 counting
			C[remain]++;
		}
		
		for (int i = 0; i < M; i++) {
			if (C[i] > 1) {
				count = count + (C[i] * (C[i] - 1) / 2);
			}
		}
		
		System.out.println(count);
	}
	
}
