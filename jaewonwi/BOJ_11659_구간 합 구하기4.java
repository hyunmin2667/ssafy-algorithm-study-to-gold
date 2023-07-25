import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 구간합 배열
		int[] sarr = new int[N];
		sarr[0] = arr[0];
		for (int i = 1; i < N; i++) {
			sarr[i] = sarr[i - 1] + arr[i];	
		}
		
		// 케이스 구간 별 합
		for (int t = 0; t < M; t++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			
			int sum;
			if ((i - 1) == 0) {
				sum = sarr[j - 1];
			} else {
				sum = sarr[j - 1] - sarr[i - 2];	
			}

			System.out.println(sum);
		}
		
	}
	
}
